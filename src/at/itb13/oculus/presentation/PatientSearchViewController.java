/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 10.04.2015
 */
package at.itb13.oculus.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import at.itb13.oculus.application.SearchViewControllerImpl;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.model.Patient;

/**
 * @author Manu
 *
 */
public class PatientSearchViewController {

	private SearchViewControllerImpl<Patient> _searchViewController;
	private Map<String, Integer> _fieldMap;

	@FXML
	private Label _searchTermLabel;
	@FXML
	private TextField _searchInput;
	@FXML
	private TableView<String[]> _tableView;
	@FXML
	private Button _searchButton;
	
	public PatientSearchViewController() {
		_searchViewController = new SearchViewControllerImpl<Patient>(Patient.class);
		_fieldMap = _searchViewController.getFieldMap();
	}

	// für string array: http://stackoverflow.com/questions/20769723/populate-tableview-with-two-dimensional-array <3
	@FXML
	private void initialize() {
		LangFacade langFacade = LangFacade.getInstance();
		
		List<TableColumn<String[], String>> tableColumns = new ArrayList<TableColumn<String[], String>>(_fieldMap.size());
		for(String key : _fieldMap.keySet()) {
			TableColumn<String[], String> column = new TableColumn<String[], String>(langFacade.getString(LangKey.valueOf(key.toUpperCase())));
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
		
		_searchInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setCriteria(_searchInput.getText());
				}
			}
		});
	}

	private ObservableValue<String> mapColumn(TableColumn.CellDataFeatures<String[], String> param, String key) {
		int i = _fieldMap.get(key);
		if (param.getValue()[i] != null) {
			return new SimpleStringProperty(param.getValue()[i]);
		} else {
			return new SimpleStringProperty("");
		}
	}
	
	public void setCriteria(String criteria) {
		adjustColor(_searchTermLabel, _searchViewController.setCriteria(criteria));
	}
	
	public void adjustColor(Label label, boolean valid) {
		if(valid) {
			label.setTextFill(Color.BLACK);
		} else {
			label.setTextFill(Color.RED);
		}
	}
	
	@FXML
	public void search(ActionEvent event) {
		new Thread(new SearchTask()).start();
	}
	
	private class SearchTask extends Task<Void> {
		
	    @Override public Void call() {
	    	_searchViewController.search();
	    	_fieldMap = _searchViewController.getFieldMap();
	    	return null;
	    }
	    
	    @Override protected void succeeded() {
	    	_tableView.setItems(FXCollections.observableList(_searchViewController.getResults()));
	    }
	}
}
