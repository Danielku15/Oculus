package at.itb13.oculus.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.application.SearchController;
import at.itb13.oculus.config.ConfigFactory;
import at.itb13.oculus.config.ConfigFactory.Config;
import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.search.Searchable;
import at.itb13.oculus.util.GUIUtil;

/**
 * @author Patrick
 *
 * @param <T>
 */
public class SearchViewController<T extends PersistentObject & Searchable> {

	private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static final String SEARCHCONFIGSUFFIX = "search.properties";
	
	// class of objects that is searched for
	private Class<T> _type;
	// controller that is responsible for searching
	private SearchController<T> _searchController;
	// map that maps a unique column name (e.g. "firstname") to its associated index in the search result
	private Map<String, Integer> _indexMap;
	// list of consumers that accept an id of a chosen record
	private List<Consumer<String>> _consumers;
	// user defined search configuration
	private Config _searchConfig;
	// map that maps a unique column name (e.g. "firstname") to its associated column configuration
	private Map<String, ColumnConfig> _columnConfigMap;
	
	@FXML
	private Label _searchInputLabel;
	@FXML
	private TextField _searchInput;
	@FXML
	private TableView<String[]> _tableView;
	
	public SearchViewController(Class<T> type) {
		_type = type;
		_searchController = ControllerFactory.getInstance().getSearchController(type);
		_indexMap = _searchController.getIndexMap();
		_consumers = new LinkedList<Consumer<String>>();
		_searchConfig = loadSearchConfig();
		_columnConfigMap = initColumnConfigMap();
	}
	
	/** Concatenates the simple name of the search class with the search configuration suffix
	 * @return the filepath of the search configuration's associated properties file
	 */
	private String getSearchConfigFilePath() {
		return _type.getSimpleName().toLowerCase() + SEARCHCONFIGSUFFIX;	
	}
	
	/** Initializes the search configuration or restores the search configuration if it already exists 
	 * @return the initialized (restored) search configuration
	 */
	private Config loadSearchConfig() {
		ConfigFactory configFactory = ConfigFactory.getInstance();
		Config config = configFactory.getConfig(getSearchConfigFilePath());
		if(!config.isLoaded()) {
			try {
				config.load();
			} catch(IOException e) {
				LOGGER.warning("Could not load search configuration " + getSearchConfigFilePath() + " - using default configuration!");
			}
		}
		return config;
	}
	
	/** Saves the current search configuration
	 */
	private void saveSearchConfig() {
		try {
			_searchConfig.save();
		} catch (IOException e) {
			LOGGER.warning("Could not save search configuration " + getSearchConfigFilePath() + "!");
		}
	}
	
	/** Initializes the column configuration map with the current search configuration
	 * @return an initialized column configuration map
	 */
	private Map<String, ColumnConfig> initColumnConfigMap() {
		Map<String, ColumnConfig> columnConfigMap = new HashMap<String, ColumnConfig>();
		for(String key : _indexMap.keySet()) {
			if(!key.equals("id")) {
				columnConfigMap.put(key, new ColumnConfig(key));
			}
		}
		return columnConfigMap;
	}

	@FXML
	private void initialize() {
		// initialize list of table columns
		List<TableColumn<String[], String>> tableColumns = new ArrayList<TableColumn<String[], String>>(_indexMap.size());
		
		// fill list with null values
		for(int i = 0; i < (_indexMap.size() - 1); ++i) {
			tableColumns.add(null);
		}
		
		// initialize table view columns
		for(String key : _indexMap.keySet()) {			
			if(!key.equals("id")) {
				
				// initialize new table column with the appropriate column name
				TableColumn<String[], String> column = new TableColumn<String[], String>(getHeadingForKey(key));
				
				// set column id
				column.setId(key);
				
				// set cell value factory to display the right values in the right columns
				column.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<String[], String> cellData) {
						return mapColumn(cellData, key);
					}
				});
				
				// get the configuration for the current column
				ColumnConfig columnConfig = _columnConfigMap.get(key);
				// set the width property of the column according to the width in the column configuration
				column.setPrefWidth(columnConfig.getWidth());
				// set the visible property of the column according to the visibility in the column configuration
				column.setVisible(columnConfig.isVisible());
				
				// add listener to column width property to save changes to search configuration
				column.widthProperty().addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue<? extends Number> observableValue, Number oldWidth, Number newWidth) {
						_columnConfigMap.get(key).setWidth(newWidth.intValue());
						saveSearchConfig();
					}
				});
				
				// add listener to column visible property to save changes to search configuration
				column.visibleProperty().addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldVisibility, Boolean newVisibility) {
						_columnConfigMap.get(key).setVisible(newVisibility);
						saveSearchConfig();
					}
				});
				
				// set the position of the column according to the index in the column configuration
				tableColumns.set(columnConfig.getIndex(), column);
			}
		}
		// add all columns to the table view
		_tableView.getColumns().addAll(tableColumns);
		
		// add listener to columns to save column positions to search configuration
		_tableView.getColumns().addListener(new ListChangeListener<TableColumn<String[], ?>>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends TableColumn<String[], ?>> change) {
			    while (change.next()) {
			        if (change.wasRemoved()) {
			        	ObservableList<TableColumn<String[], ?>> columns = _tableView.getColumns();			          
			        	for (int i = 0; i < columns.size(); ++i) {
			        		_columnConfigMap.get(columns.get(i).getId()).setIndex(i);
			        	}
			      	}
				}
			    saveSearchConfig();
			}
		});
		
		// set row factory to notify consumers when a record has been chosen
		_tableView.setRowFactory(new Callback<TableView<String[]>, TableRow<String[]>>() {
			@Override
			public TableRow<String[]> call(TableView<String[]> tableView) {
				final TableRow<String[]> row = new TableRow<String[]>();
				row.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent e) {
						if(e.getClickCount() >= 2) {
							String[] result = row.getItem();
							notifyConsumers(result[_indexMap.get("id")]);			
						}
					}
				});
				return row;
			}
		});
		
		_tableView.setPlaceholder(new Label(LangFacade.getInstance().getString(LangKey.NOSEARCHRESULT)));
		
		_searchInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					GUIUtil.validate(_searchInputLabel, _searchController.setCriteria(_searchInput.getText()));
				}
			}
		});
		
		_searchInput.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				if(e.getCode().equals(KeyCode.ENTER)) {
					search(null);
				}
			}
		});
	}

	/** Gets the translated heading for the column identified by the passed key
	 * @param key that identifies the column
	 * @return
	 */
	private String getHeadingForKey(String key) {
		LangFacade langFacade = LangFacade.getInstance();
		String heading = "%" + key;
		LangKey langKey = null;
		try {
			langKey = LangKey.valueOf(key.toUpperCase());
		} catch(IllegalArgumentException e) {
			LOGGER.warning("Could not parse LangKey for string: " + key.toUpperCase());
		}
		if(langKey != null) {
			String tmpHeading = langFacade.getString(langKey);
			if(tmpHeading != null) {
				heading = tmpHeading;
			}
		}
		return heading;
	}
	
	/** runs search task
	 * @param event
	 */
	@FXML
	public void search(ActionEvent event) {
		GUIUtil.validate(_searchInputLabel, _searchController.setCriteria(_searchInput.getText()));
		new Thread(new SearchTask()).start();
	}
	
	/** Maps a unique column key to the associated value in a row 
	 * @param cellData represents the current row
	 * @param key identifies a unique column in the field map
	 * @return
	 */
	private ReadOnlyStringWrapper mapColumn(CellDataFeatures<String[], String> cellData, String key) {
		int i = _indexMap.get(key);
		if (cellData.getValue()[i] != null) {
			return new ReadOnlyStringWrapper(cellData.getValue()[i]);
		} else {
			return new ReadOnlyStringWrapper("");
		}
	}
	
	/** Notifies all consumers about a chosen record
	 * @param id of the chosen record
	 */
	private void notifyConsumers(String id) {
		for(Consumer<String> consumer : _consumers) {
			consumer.accept(id);
		}
	}
	
	/** Adds a consumer that needs to be notified when a record is chosen
	 * @param consumer to add for notification
	 */
	public void addConsumer(Consumer<String> consumer) {
		_consumers.add(consumer);
	}
	
	
	/** Removes a consumer that should no longer be notified when a record is chosen
	 * @param consumer to remove for notification
	 */
	public void removeConsumer(Consumer<String> consumer) {
		_consumers.remove(consumer);
	}
	
	/** Sets the search criteria of the responsible search controller
	 * @param criteria to be searched for
	 * @return true if the search criteria was valid, false if the search criteria was not valid
	 */
	public boolean setCriteria(String criteria) {
		_searchInput.setText(criteria);
		boolean valid = _searchController.setCriteria(criteria);
		GUIUtil.validate(_searchInputLabel, valid);
		return valid;
	}
	
	private class ColumnConfig {
		
		private static final boolean DEFAULTVISIBLE = true;
		private static final int DEFAULTWIDTH = 100;
		
		private static final String INDEXSUFFIX = "_index";
		private static final String WIDTHSUFFIX = "_width";
		private static final String VISIBLESUFFIX = "_visible";
		
		private String _key;
		private int _index;
		private int _width;
		private boolean _visible;
		
		public ColumnConfig(String key) {
			_key = key;
			loadConfig();
		}
		
		private void loadConfig() {
			String index = _searchConfig.getProperty(_key + INDEXSUFFIX);
			String width = _searchConfig.getProperty(_key + WIDTHSUFFIX);
			String visible = _searchConfig.getProperty(_key + VISIBLESUFFIX);
			if((index == null) || (width == null) || (visible == null)) {
				setDefaultConfig();
			} else {
				_index = Integer.valueOf(index);
				_width = Integer.valueOf(width);
				_visible = Boolean.valueOf(visible);
			}
		}
		
		private void setDefaultConfig() {
			setIndex(getMaxColumnIndex() + 1);
			setWidth(DEFAULTWIDTH);
			setVisible(DEFAULTVISIBLE);
		}
		
		private int getMaxColumnIndex() {
			int maxColumnIndex = -1;
			for(String key : _indexMap.keySet()) {
				String index = _searchConfig.getProperty(key + INDEXSUFFIX);
				if(index != null) {
					maxColumnIndex = Math.max(maxColumnIndex, Integer.valueOf(index));
				}
			}
			return maxColumnIndex;
		}
		
		public void setIndex(int index) {
			_index = index;
			_searchConfig.setProperty(_key + "_index", String.valueOf(_index));
		}
		
		public int getIndex() {
			return _index;
		}
		
		public void setWidth(int width) {
			_width = width;
			_searchConfig.setProperty(_key + "_width", String.valueOf(_width));
		}
		
		public int getWidth() {
			return _width;
		}
		
		public void setVisible(boolean visible) {
			_visible = visible;
			_searchConfig.setProperty(_key + "_visible", String.valueOf(_visible));
		}
		
		public boolean isVisible() {
			return _visible;
		}
	}
	
	private class SearchTask extends Task<Void> {
		
	    @Override
	    public Void call() {
	    	_searchController.search();
	    	return null;
	    }
	    
	    @Override
	    protected void succeeded() {
	    	List<String[]> results = _searchController.getResults();
	    	if((results != null) && (results.isEmpty())) {
	    		_tableView.setItems(null);
	    	} else {
	    		_tableView.setItems(FXCollections.observableList(results));
	    	}
	    }
	}
}
