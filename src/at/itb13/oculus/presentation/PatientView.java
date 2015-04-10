package at.itb13.oculus.presentation;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import at.itb13.oculus.application.PatientViewControllerImpl_old;
import at.itb13.oculus.application.RegExpException;
import at.itb13.oculus.application.UniqueConstraintException;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.model.Address;
import at.itb13.oculus.model.Gender;
import at.itb13.oculus.model.Patient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PatientView implements Initializable {
	
	private PatientViewControllerImpl_old _patientController;
	
	@FXML
	private Menu _fileLbl;
	@FXML
	private MenuItem _closeLbl;
	@FXML
	private Menu _editLbl;
	@FXML
	private Menu _helpLbl;
	@FXML
	private Tab _patienttabLbl;
	@FXML
	private Label _firstnameLblTop;
	@FXML
	private Label _firstnameLblTop1;
	@FXML
	private Label _firstnameLblTop2;
	@FXML
	private Label _firstnameLbl;
	@FXML
	private TextField _firstnameInput;
	@FXML
	private Label _secondnameLbl;
	@FXML
	private TextField _lastnameInput;
	@FXML
	private Label _birthdayLbl;
	@FXML
	private Label _genderLbl;
	@FXML
	private RadioButton _femaleInput;
	@FXML
	private RadioButton _maleInput;
	@FXML
	private ToggleGroup _genderToggleGroup;
	@FXML
	private Label _phonenumberLbl;
	@FXML
	private TextField _phonenumberInput;
	@FXML
	private Label _emailLbl;
	@FXML
	private TextField _emailInput;
	@FXML
	private Label _zipLbl;
	@FXML
	private TextField _zipInput;
	@FXML
	private Label _countryLbl;
	@FXML
	private TextField _countryInput;
	@FXML
	private Label _streetLbl;
	@FXML
	private TextField _streetInput;
	@FXML
	private Label _streetnumberLbl;
	@FXML
	private TextField _streetnumberInput;
	@FXML
	private Label _socialsecuritynumberLbl;
	@FXML
	private TextField _socialsecuritynumberInput;
	@FXML
	private Label _employerLbl;
	@FXML
	private TextField _employerInput;
	@FXML
	private Button _clearButton;
	@FXML
	private Button _saveButton;
	@FXML
	private DatePicker _birthdayInput;
    @FXML
    private AnchorPane _patientFormular;
	@FXML
	private Button _createNewPatientButton;
    @FXML
    private ScrollPane _patientFormulatScrollPane;
    @FXML
    private TabPane _tabPane;
    @FXML
    private TitledPane _patientMasterData;
   
    
	public PatientView() {
		_patientController = new PatientViewControllerImpl_old();
	}

	// Event Listener on MenuItem[#_close].onAction
	@FXML
	public void close(ActionEvent event) {
		_patientController.close();
	}
	
	@FXML
    void getFirstname(ActionEvent event) {
		System.out.println("Firstname eingegeben");
    }

    @FXML
    void getLastname(ActionEvent event) {
    	System.out.println("Lastname eingegeben");
    }

    @FXML
    void getBirthday(ActionEvent event) {

    }
    
    @FXML
    void getPhonenumber(ActionEvent event) {

    }

    @FXML
    void getEmail(ActionEvent event) {

    }

    @FXML
    void getZip(ActionEvent event) {

    }

    @FXML
    void getCountry(ActionEvent event) {

    }

    @FXML
    void getStreet(ActionEvent event) {

    }

    @FXML
    void getStreetnumber(ActionEvent event) {

    }

    @FXML
    void getSocialSecurityNumber(ActionEvent event) {

    }

    @FXML
    void getEmployer(ActionEvent event) {

    }
	
	public Patient getPatientFromView() {
		Patient patient = new Patient();
		patient.setFirstname(_firstnameInput.getText());
		patient.setLastname(_lastnameInput.getText());
		//patient.setBirthday(getDateFromBirthday());
		patient.setGender(getGenderFromPerson());
		patient.setPhoneNumber(_phonenumberInput.getText().toString());
		patient.setEmail(_emailInput.getText());
		patient.setAddress(getAddressFromPerson());
		patient.setSocialSecurityNumber(_socialsecuritynumberInput.getText());
		patient.setEmployer(_employerInput.getText());
		return patient;
	}
	
	
	private Address getAddressFromPerson(){
		Address address = new Address();
		address.setZip(_zipInput.getText());
		address.setCountry(_countryInput.getText());
		address.setStreet(_streetInput.getText());
		address.setStreetNumber(_streetnumberInput.getText());
		return address;
	}
	
	
	private Gender getGenderFromPerson(){
		if (_maleInput.isSelected()){
			return Gender.MALE;
		}
		else {
			return Gender.FEMALE;
		}
	}
	
	private Date getDateFromBirthday(){
		LocalDate lDate = _birthdayInput.getValue();
		Date date = java.sql.Date.valueOf(lDate);
		return date;
	}
	
	// Event Listener on Button[#_clearButton].onAction
	@FXML
	public void clear(ActionEvent event) {
		_firstnameInput.setText("");
		_lastnameInput.setText("");
		_birthdayInput.setValue(null);
		_maleInput.setSelected(false);
		_femaleInput.setSelected(true);
		_phonenumberInput.setText("");
		_emailInput.setText("");
		_zipInput.setText("");
		_countryInput.setText("");
		_streetInput.setText("");
		_socialsecuritynumberInput.setText("");
		_employerInput.setText("");
		
		Stage dialog = new Stage();
    	dialog.initStyle(StageStyle.DECORATED);
    	Scene scene = new Scene(new Group(new Text(200, 200, "Saved")));
    	dialog.centerOnScreen();
    	dialog.setScene(scene);
    	dialog.show();
		
	}
	
	// Event Listener on Button[#_saveButton].onAction
	@FXML
	public void save(ActionEvent event) {
		System.out.println("tttt");
		new Thread(new CreatePatientTask(getPatientFromView(), _patientController));
	}
	
	// Event Listener on Button[#_createNewPatientButton].onAction
	@FXML
	public void createTab(ActionEvent event) {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
}


class CreatePatientTask extends Task<String> {
	
	PatientViewControllerImpl_old _patientController;
	private Patient _patient;
	
	public CreatePatientTask(Patient patient, PatientViewControllerImpl_old patientController) {
		_patient = patient;
		_patientController = patientController;
	}
	
    @Override public String call() {
    	try {
			return _patientController.createPatient(_patient);
		} catch (RegExpException e) {
			e.printStackTrace();
		} catch (UniqueConstraintException e) {
			e.printStackTrace();
		}
		return null;
    }
    
    @Override protected void succeeded() {
    	
    	
    }
    
    @Override protected void cancelled() {
    	
    }
    
    @Override protected void failed() {
    	
    }

}