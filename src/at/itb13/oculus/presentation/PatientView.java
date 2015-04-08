package at.itb13.oculus.presentation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import at.itb13.oculus.application.PatientViewController;
import at.itb13.oculus.application.PatientViewControllerImpl;
import at.itb13.oculus.model.Patient;

public class PatientView implements Initializable {
	
	private PatientViewController _patientController;
	
	@FXML
	private Menu _file;
	@FXML
	private MenuItem _close;
	@FXML
	private Menu _language;
	@FXML
	private MenuItem _english;
	@FXML
	private MenuItem _german;
	@FXML
	private Menu _help;
	@FXML
	private MenuItem _about;
	@FXML
	private Label _firstname;
	@FXML
	private Label _gender;
	@FXML
	private Label _country;
	@FXML
	private Label _socialsecuritynumber;
	@FXML
	private Label _street;
	@FXML
	private Label _birthday;
	@FXML
	private Label _zip;
	@FXML
	private Label _streetnumber;
	@FXML
	private RadioButton _female;
	@FXML
	private RadioButton _male;
	@FXML
	private Label _email;
	@FXML
	private Label _employer;
	@FXML
	private Label _phonenumber;
	@FXML
	private Label _lastname;
	@FXML
	private Button _saveButton;
	@FXML
	private Button _clearButton;
	
	public PatientView() {
		_patientController = new PatientViewControllerImpl();
	}

	// Event Listener on MenuItem[#_close].onAction
	@FXML
	public void close(ActionEvent event) {
		_patientController.close();
	}
	
	public Patient getPatientFromView() {
		Patient patient = new Patient();
		patient.setFirstname("Dominik");
		patient.setLastname("Blaser");
		return patient;
	}
	
	// Event Listener on Button[#_saveButton].onAction
	@FXML
	public void save(ActionEvent event) {
		new Thread(new CreatePatientTask(getPatientFromView())).start();
	}
	
	// Event Listener on Button[#_clearButton].onAction
	@FXML
	public void clear(ActionEvent event) {
		// TODO Autogenerated
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	class CreatePatientTask extends Task<Patient> {
		
		private Patient _patient;
		
		public CreatePatientTask(Patient patient) {
			_patient = patient;
		}
		
	    @Override public Patient call() {
	    	return _patientController.createPatient(_patient);
	    }
	    
	    @Override protected void succeeded() {
	    	
	    }
	    
	    @Override protected void cancelled() {
	    	
	    }
	    
	    @Override protected void failed() {
	    	
	    }
	}
}
