/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 10.04.2015
 */
package at.itb13.oculus.presentation;

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

	private List<Patient> _patientsList;
	private SearchViewControllerImpl<Patient> _searchViewController;

	public PatientSearchView() {
		_searchViewController = new SearchViewControllerImpl<Patient>();
	}

	@FXML
	private TextField _patientsearchInput;
	@FXML
	private TableView<Patient> _tableView;
	@FXML
	private TableColumn<Patient, String> _firstname;
	@FXML
	private TableColumn<Patient, String> _lastname;
	@FXML
	private TableColumn<Patient, String> _birthday;
	@FXML
	private TableColumn<Patient, String> _address;
	@FXML
	private TableColumn<Patient, String> _socialsecuritynumber;
	@FXML
	private Button _patientsearchButton;

	
	// für string array: http://stackoverflow.com/questions/20769723/populate-tableview-with-two-dimensional-array <3
	@FXML
	private void initialize() {
		_firstname.setCellValueFactory(new Callback<CellDataFeatures<Patient, String>, ObservableValue<String>>() {

					public javafx.beans.value.ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
						if (param.getValue().getFirstname() != null) {
							return new SimpleStringProperty(param.getValue().getFirstname());
						} else {
							return new SimpleStringProperty("");
						}
					}
				});
		
		_lastname.setCellValueFactory(new Callback<CellDataFeatures<Patient, String>, ObservableValue<String>>() {

			public javafx.beans.value.ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
				if (param.getValue().getFirstname() != null) {
					return new SimpleStringProperty(param.getValue().getLastname());
				} else {
					return new SimpleStringProperty("");
				}
			}
		});
		
		_socialsecuritynumber.setCellValueFactory(new Callback<CellDataFeatures<Patient, String>, ObservableValue<String>>() {

			public javafx.beans.value.ObservableValue<String> call(TableColumn.CellDataFeatures<Patient, String> param) {
				if (param.getValue().getFirstname() != null) {
					return new SimpleStringProperty(param.getValue().getSocialSecurityNumber());
				} else {
					return new SimpleStringProperty("");
				}
			}
		});
		
	}

	public void showAllPatientsInTable(ActionEvent e) {
		_patientsList = _searchViewController.search(_patientsearchInput
				.getText());
		ObservableList<Patient> patients = FXCollections
				.observableList(_patientsList);
		_tableView.setItems(patients);
	}

}
