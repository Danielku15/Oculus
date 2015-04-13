/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 10.04.2015
 */
package at.itb13.oculus.presentation;

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
import at.itb13.oculus.model.Patient;

/**
 * @author Manu
 *
 */
public class PatientSearchView {

	private SearchViewControllerImpl<Patient> _searchViewController;

	@FXML
	private TextField _patientsearchInput;
	@FXML
	private TableView<String[]> _tableView;
	@FXML
	private TableColumn<String[], String> _firstname;
	@FXML
	private TableColumn<String[], String> _lastname;
	@FXML
<<<<<<< HEAD
=======
	private TableColumn<String[], String> _street;
	@FXML
	private TableColumn<String[], String> _streetnumber;
	@FXML
	private TableColumn<String[], String> _zip;
	@FXML
	private TableColumn<String[], String> _city;
	@FXML
	private TableColumn<String[], String> _country;
	@FXML
>>>>>>> origin/master
	private TableColumn<String[], String> _socialsecuritynumber;
	@FXML
	private Button _patientsearchButton;
	
	public PatientSearchView() {
		_searchViewController = new SearchViewControllerImpl<Patient>(Patient.class);
	}

	// f�r string array: http://stackoverflow.com/questions/20769723/populate-tableview-with-two-dimensional-array <3
	@FXML
	private void initialize() {
		
		_patientsearchInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setCriteria(_patientsearchInput.getText());
				}
			}
		});
		
		_firstname.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {

					public javafx.beans.value.ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> param) {
						if (param.getValue()[0] != null) {
							return new SimpleStringProperty(param.getValue()[0]);
						} else {
							return new SimpleStringProperty("");
						}
					}
				});
		
		_lastname.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {

			public javafx.beans.value.ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> param) {
				if (param.getValue()[1] != null) {
					return new SimpleStringProperty(param.getValue()[1]);
				} else {
					return new SimpleStringProperty("");
				}
			}
		});
		
		_socialsecuritynumber.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {

			public javafx.beans.value.ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> param) {
				if (param.getValue()[3] != null) {
					return new SimpleStringProperty(param.getValue()[3]);
				} else {
					return new SimpleStringProperty("");
				}
			}
		});
		
	}

	public void setCriteria(String criteria) {
		adjustColor(_patientsearch, _searchViewController.setCriteria(criteria));
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
	    	return null;
	    }
	    
	    @Override protected void succeeded() {
	    	_tableView.setItems(FXCollections.observableList(_searchViewController.getResults()));
	    }
	}
}
