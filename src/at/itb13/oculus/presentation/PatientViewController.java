package at.itb13.oculus.presentation;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.application.IncompleteDataException;
import at.itb13.oculus.application.MainController;
import at.itb13.oculus.application.ObjectNotFoundException;
import at.itb13.oculus.application.ObjectNotSavedException;
import at.itb13.oculus.application.PatientController;
import at.itb13.oculus.application.UniqueConstraintException;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.util.GUIUtil;

/**
 * 
 * Controller for the view of a patient
 * @category ViewController
 *
 */
public class PatientViewController implements Initializable{
	
	private static final String MALE = "MALE";
	private static final String FEMALE = "FEMALE";
	
	/**
	 * application - {@link PatientViewController}
	 */
	private PatientController _patientController;

	/**
	 * parent - {@link PatientTabViewController}
	 */
	@FXML
	private PatientTabViewController _patientTabViewController;
	@FXML
	private TitledPane _patientMasterData;
	@FXML
	private Label _firstnameLabel;
	@FXML
	private TextField _firstnameInput;
	@FXML
	private Label _lastnameLabel;
	@FXML
	private TextField _lastnameInput;
	@FXML
	private Label _birthdayLabel;
	@FXML
	private DatePicker _birthdayInput;
	@FXML
	private Label _genderLabel;
	@FXML
	private Group _genderGroup;
	@FXML
	private RadioButton _maleInput;
	@FXML
	private ToggleGroup _genderToggleGroup;
	@FXML
	private RadioButton _femaleInput;
	@FXML
	private Label _phoneNumberLabel;
	@FXML
	private TextField _phoneNumberInput;
	@FXML
	private Label _emailLabel;
	@FXML
	private TextField _emailInput;
	@FXML
	private Label _zipLabel;
	@FXML
	private TextField _zipInput;
	@FXML
	private Label _countryLabel;
	@FXML
	private TextField _countryInput;
	@FXML
	private Label _streetLabel;
	@FXML
	private TextField _streetInput;
	@FXML
	private Label _streetNumberLabel;
	@FXML
	private TextField _streetNumberInput;
	@FXML
	private Label _socialSecurityNumberLabel;
	@FXML
	private TextField _socialSecurityNumberInput;
	@FXML
	private Label _employerLabel;
	@FXML
	private TextField _employerInput;
	@FXML
	private Button _saveButton;
	@FXML
	private Accordion _accordion;
	@FXML
    private Tab _newPatient;
    @FXML
    private Label _cityLabel;
    @FXML
    private TextField _cityInput;
    
	/**
	 * gets {@link PatientViewController} instance of {@link ControllerFactory}
	 */
	public PatientViewController() {
		_patientController = ControllerFactory.getInstance().getPatientController();
		_patientTabViewController = PatientTabViewController.getInstance();
	}

	/**
	 * activates {@link PatientController} on action into the {@link MainController}
	 */
	public void activate(){
		_patientController.activate();
	}
	
	/**
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 * initiates all the input fields with their events and properties
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		//set patient master data visible
		_accordion.setExpandedPane(_patientMasterData);
		
		//set female and male input not selected
		_femaleInput.setSelected(false);
		_maleInput.setSelected(false);
		
		//set font size to birthday input 
		_birthdayInput.getEditor().setFont(Font.font(14));
		
		_firstnameInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setFirstname(_firstnameInput.getText());
				}
			}
		});
		
		_firstnameInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    	setTabLabelNameModified(true);
		    }
		});
		
		_lastnameInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    	setTabLabelNameModified(true);
		    }
		});
		
		_lastnameInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setLastname(_lastnameInput.getText());
				}
			}
		});
		
		_birthdayInput.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> arg0,
					LocalDate oldValue, LocalDate newValue) {
				if (newValue != null){
					setTabLabelNameModified(true);
					setBirthday(_birthdayInput.getValue());		
				}
				
			}
		});
		
		_maleInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue){
					setTabLabelNameModified(true);
					setGender(MALE);
				}
			}
		});
		
		_femaleInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue){
					setTabLabelNameModified(true);
					setGender(FEMALE);			
				}
			}
		});
		
		_phoneNumberInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setPhoneNumber(_phoneNumberInput.getText());
				}
			}
		});
		
		_phoneNumberInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    		setTabLabelNameModified(true);
		    }
		});
		
		_emailInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setEmail(_emailInput.getText());
				}
			}
		});
		
		_emailInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    		setTabLabelNameModified(true);
		    }
		});
		
		_zipInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setZip(_zipInput.getText());
				}
			}
		});
		
		_zipInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    		setTabLabelNameModified(true);
		    }
		});
		
		_countryInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setCountry(_countryInput.getText());
				}
			}
		});
		
		_countryInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    		setTabLabelNameModified(true);
		    }
		});
		
		_streetInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setStreet(_streetInput.getText());
				}
			}
		});
		
		_streetInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    		setTabLabelNameModified(true);
		    }
		});
		
		_streetNumberInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setStreetNumber(_streetNumberInput.getText());
				}
			}
		});
		
		_streetNumberInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    		setTabLabelNameModified(true);
		    }
		});
		
		_cityInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setCity(_cityInput.getText());
				}
			}
		});
		
		_cityInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    		setTabLabelNameModified(true);
		    }
		});
		
		_socialSecurityNumberInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue ) {
					setSocialSecurityNumber(_socialSecurityNumberInput.getText());
				}
			}
		});
		
		_socialSecurityNumberInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {	    	
		    		setTabLabelNameModified(true);  	
		    }
		});
		
		_employerInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setEmployer(_employerInput.getText());
				}
			}
		});
		
		_employerInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    		setTabLabelNameModified(true);
		    }
		});
	}
	
	/**
	 * Event Listener on Button[#_saveButton].onAction
	 * @param event
	 */
	@FXML
	public void save(ActionEvent event) {
		CreatePatientTask createPatientTask = new CreatePatientTask(this);
		
		//handle exceptions IncompleteDataException and ObjectNotSavedException
		createPatientTask.setOnFailed(new EventHandler<WorkerStateEvent>() {
		    @Override public void handle(WorkerStateEvent t) {
		    	LangFacade facade = LangFacade.getInstance();
		    	Alert errorDialog = new Alert(AlertType.ERROR);
		    	errorDialog.setTitle(facade.getString(LangKey.ERRORDIALOGTITEL));
				String message = "";
				if (createPatientTask.getException() instanceof IncompleteDataException){
		    		IncompleteDataException ex = (IncompleteDataException) createPatientTask.getException();
		    		errorDialog.setHeaderText(facade.getString(LangKey.INCOMPLETEDATAHEADER));
		    		for (int i = 0; i < ex.getFieldNames().size(); i++){				
						message += facade.getString(LangKey.valueOf(ex.getFieldNames().get(i).toUpperCase()));
						if (i+1 < ex.getFieldNames().size()){
							message += ", ";
						}
					}
				}
		    	else if (createPatientTask.getException() instanceof ObjectNotSavedException){
		    		ObjectNotSavedException ex = (ObjectNotSavedException) createPatientTask.getException();
		    		errorDialog.setHeaderText(facade.getString(LangKey.OBJECTNOTSAVEDHEADER));
		    		message = LangKey.OBJECTNOTSAVEDCONTENT + ex.getPersistentObjectID();
		    	}
				errorDialog.setContentText(message);
				errorDialog.showAndWait();
			}
		});
		
		//start new thread for saving
		new Thread(createPatientTask).start();
	}
	
	private void setTabLabelNameModified(boolean isModified){
		String tabLabelName = "";
		if (isModified){
			tabLabelName = "*";
		}
		if (!_firstnameInput.getText().isEmpty() && !_lastnameInput.getText().isEmpty()){
			tabLabelName += _firstnameInput.getText().charAt(0) + ". " + _lastnameInput.getText();
			_patientTabViewController.setTabLabelName(tabLabelName);
		}
		else if (!_lastnameInput.getText().isEmpty()){
			tabLabelName += _lastnameInput.getText();
			_patientTabViewController.setTabLabelName(tabLabelName);
		}
		else {
			tabLabelName += LangFacade.getInstance().getString(LangKey.NEWPATIENT);
			_patientTabViewController.setTabLabelName(tabLabelName);
		}
	}
	
	public void loadPatientToForm(String id){
		try {
			_patientController.loadPatient(id);
		} catch (ObjectNotFoundException e) {
			LangFacade facade = LangFacade.getInstance();
			Alert errorDialog = new Alert(AlertType.ERROR);
			errorDialog.setTitle(facade.getString(LangKey.ERRORDIALOGTITEL));
			errorDialog.setHeaderText(facade.getString(LangKey.OBJECTNOTFOUNDHEADER));
			errorDialog.setContentText(facade.getString(LangKey.OBJECTNOTFOUNDCONTENT) + " " + id);
			errorDialog.showAndWait();
		}
		setDataToForm();
		setTabLabelNameModified(false);
	}
	

	public void setDataToForm(){
		_firstnameInput.setText(_patientController.getFirstname());
		_lastnameInput.setText(_patientController.getLastname());
		
		_birthdayInput.setValue(_patientController.getBirthday());
		
		String gender = _patientController.getGender();
		if (gender != null) {
			if(gender.equals(MALE)){
				_maleInput.setSelected(true);
			} else{
				_femaleInput.setSelected(true);
			}
		}
		
		_phoneNumberInput.setText(_patientController.getPhoneNumber());
		_emailInput.setText(_patientController.getEmail());
		_streetInput.setText(_patientController.getStreet());
		_streetNumberInput.setText(_patientController.getStreetNumber());
		_zipInput.setText(_patientController.getZip());
		_cityInput.setText(_patientController.getCity());
		_countryInput.setText(_patientController.getCountry());
		_socialSecurityNumberInput.setText(_patientController.getSocialSecurityNumber());
		_employerInput.setText(_patientController.getEmployer());			
	}
	
	void setFirstname(String firstname) {
		if (!_patientController.setFirstname(firstname)) {
			GUIUtil.validate(_firstnameLabel, false);
		} else {
			GUIUtil.validate(_firstnameLabel, true);
		}
	}

	void setLastname(String lastname) {
		if (!_patientController.setLastname(lastname)) {
			GUIUtil.validate(_lastnameLabel, false);
		} else {
			GUIUtil.validate(_lastnameLabel, true);
		}
	}

	void setBirthday(LocalDate birthday) {
		if (!_patientController.setBirthday(birthday)) {
			GUIUtil.validate(_birthdayLabel, false);
		} else {
			GUIUtil.validate(_birthdayLabel, true);
		}
	}

	void setGender(String gender) {
		if (!_patientController.setGender(gender)) {
			GUIUtil.validate(_genderLabel, false);
		} else {
			GUIUtil.validate(_genderLabel, true);
		}
	}

	void setPhoneNumber(String phonenumber) {
		if (!_patientController.setPhoneNumber(phonenumber)) {
			GUIUtil.validate(_phoneNumberLabel, false);
		} else {
			GUIUtil.validate(_phoneNumberLabel, true);
		}
	}

	void setEmail(String email) {
		if (!_patientController.setEmail(email)) {
			GUIUtil.validate(_emailLabel, false);
		} else {
			GUIUtil.validate(_emailLabel, true);
		}
	}

	void setZip(String zip) {
		if (!_patientController.setZip(zip)) {
			GUIUtil.validate(_zipLabel, false);
		} else {
			GUIUtil.validate(_zipLabel, true);
		}
	}

	void setCountry(String country) {
		if (!_patientController.setCountry(country)) {
			GUIUtil.validate(_countryLabel, false);
		} else {
			GUIUtil.validate(_countryLabel, true);
		}
	}

	void setStreet(String street) {
		if (!_patientController.setStreet(street)) {
			GUIUtil.validate(_streetLabel, false);
		} else {
			GUIUtil.validate(_streetLabel, true);
		}
	}

	void setStreetNumber(String streetnumber) {
		if (!_patientController.setStreetNumber(streetnumber)) {
			GUIUtil.validate(_streetNumberLabel, false);
		} else {
			GUIUtil.validate(_streetNumberLabel, true);
		}
	}
	
	void setCity(String city) {
		if (!_patientController.setCity(city)) {
			GUIUtil.validate(_cityLabel, false);
		} else {
			GUIUtil.validate(_cityLabel, true);
		}
	}

	void setSocialSecurityNumber(String socialsecuritynumber) {
		try {
			if (!_patientController.setSocialSecurityNumber(socialsecuritynumber)) {
				GUIUtil.validate(_socialSecurityNumberLabel, false);
			} else {
				GUIUtil.validate(_socialSecurityNumberLabel, true);
			}
		} catch (UniqueConstraintException e) {
			e.printStackTrace();
		}
	}

	void setEmployer(String employer) {
		if (!_patientController.setEmployer(employer)) {
			GUIUtil.validate(_employerLabel, false);
		} else {
			GUIUtil.validate(_employerLabel, true);
		}
	}
	
	public void init(PatientTabViewController patientTabViewController) {
		_patientTabViewController = patientTabViewController;
	}
	
	private class CreatePatientTask extends Task<String> {

		private PatientViewController _patientViewController;
		
		public CreatePatientTask(PatientViewController patientViewController){
			_patientViewController = patientViewController;
		}
		@Override public String call() throws IncompleteDataException, ObjectNotSavedException {
			_patientController.savePatient();
			return null;
	    }
	    
	    @Override protected void succeeded() {
	    	setTabLabelNameModified(false);
	    	_patientTabViewController.setCurrentUserObject(_patientViewController);
	    }
	}
}

