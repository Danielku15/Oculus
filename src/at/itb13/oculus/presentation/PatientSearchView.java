/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 10.04.2015
 */
package at.itb13.oculus.presentation;

import java.util.Arrays;
import java.util.List;

import at.itb13.oculus.application.SearchViewControllerImpl;
import at.itb13.oculus.model.Patient;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * @author Manu
 *
 */
public class PatientSearchView {

	private String[][] _patientsList;
	private SearchViewControllerImpl<Patient> _searchViewController;

	public PatientSearchView() {
		_searchViewController = new SearchViewControllerImpl<Patient>();
	}

	@FXML
	private TextField _patientsearchInput;
	@FXML
	private TableView<String[]> _tableView;
	@FXML
	private TableColumn<String[], String> _firstname;
	@FXML
	private TableColumn<String[], String> _lastname;
	@FXML
	private TableColumn<String[], String> _address;
	@FXML
	private TableColumn<String[], String> _socialsecuritynumber;
	@FXML
	private Button _patientsearchButton;

	
	// für string array: http://stackoverflow.com/questions/20769723/populate-tableview-with-two-dimensional-array <3
	@FXML
	private void initialize() {
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
		
		_address.setCellValueFactory(new Callback<CellDataFeatures<String[], String>, ObservableValue<String>>() {

			public javafx.beans.value.ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> param) {
				if (param.getValue()[2] != null) {
					return new SimpleStringProperty(param.getValue()[2]);
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

	public void showAllPatientsInTable(ActionEvent e) {
		_patientsList = _searchViewController.search(_patientsearchInput
				.getText());
		ObservableList<String[]> patients = FXCollections
				.observableList(Arrays.asList(_patientsList));
		_tableView.setItems(patients);
	}

}
