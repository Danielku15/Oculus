package at.itb13.oculus.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import at.itb13.oculus.application.SearchControllerImpl;
import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.model.Searchable;

public abstract class SearchViewController<T extends PersistentObject & Searchable> {

	//application - SearchViewControllerImpl
	private SearchControllerImpl<T> _searchController;
	private Map<String, Integer> _fieldMap;
	
	@FXML
	private TableView<String[]> _tableView;
	
	public SearchViewController(Class<T> type) {
		_searchController = new SearchControllerImpl<T>(type);
		_fieldMap = _searchController.getFieldMap();
	}

	@FXML
	private void initialize() {		
		List<TableColumn<String[], String>> tableColumns = new ArrayList<TableColumn<String[], String>>(_fieldMap.size());
		for(String key : _fieldMap.keySet()) {
			String heading = getHeadingForKey(key);
			TableColumn<String[], String> column = new TableColumn<String[], String>(heading);
			if(key.equals("id")) {
				column.setVisible(false);
			}
			column.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {
				public javafx.beans.value.ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> param) {
					return mapColumn(param, key);
				}
			});
			tableColumns.add(column);
		}
		_tableView.getColumns().addAll(tableColumns);
		
		_tableView.setRowFactory(new Callback<TableView<String[]>, TableRow<String[]>>() {
			@Override
			public TableRow<String[]> call(TableView<String[]> arg0) {
				final TableRow<String[]> row = new TableRow<String[]>();
				row.setOnMousePressed(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent t) {
						if(t.getClickCount() >= 2) {
							String[] result = row.getItem();
							onSearchableChosen(result[_fieldMap.get("id")]);			
						}
					}
				});
				return row;
			}
		});
	}

	@FXML
	public void search(ActionEvent event) {
		new Thread(new SearchTask()).start();
	}
	
	private String getHeadingForKey(String key) {
		LangFacade langFacade = LangFacade.getInstance();
		String heading = "%" + key;
		LangKey langKey = LangKey.valueOf(key.toUpperCase());
		if(langKey != null) {
			String tmpHeading = langFacade.getString(langKey);
			if(tmpHeading != null) {
				heading = tmpHeading;
			}
		}
		return heading;
	}
	
	private ObservableValue<String> mapColumn(TableColumn.CellDataFeatures<String[], String> param, String key) {
		int i = _fieldMap.get(key);
		if (param.getValue()[i] != null) {
			return new SimpleStringProperty(param.getValue()[i]);
		} else {
			return new SimpleStringProperty("");
		}
	}
	
	public boolean setCriteria(String criteria) {
		return _searchController.setCriteria(criteria);
	}
	
	public abstract void onSearchableChosen(String id);
	
	private class SearchTask extends Task<Void> {	
	    @Override public Void call() {
	    	_searchController.search();
	    	return null;
	    }
	    
	    @Override protected void succeeded() {
	    	_tableView.setItems(FXCollections.observableList(_searchController.getResults()));
	    }
	}
}
