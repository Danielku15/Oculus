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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import at.itb13.oculus.application.ControllerFactory;
import at.itb13.oculus.application.IncompleteDataException;
import at.itb13.oculus.application.ObjectNotFoundException;
import at.itb13.oculus.application.ObjectNotSavedException;
import at.itb13.oculus.application.PatientController;
import at.itb13.oculus.application.UniqueConstraintException;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;

public class PatientViewController implements Initializable{
	
	private static final Color COLOR_FAIL = Color.RED;
	private static final Color COLOR_SUCCESS = Color.web("0x333333ff");
	
	//application - PatientViewController
	private PatientController _patientController;

	//parent - PatientTabViewController
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
	private ToggleGroup _genderToggleGroup1;
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
	private Button _clearButton;
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
    
	public PatientViewController() {
		_patientController = ControllerFactory.getPatientController();		
	}

	public void activate(){
		_patientController.activate();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		_patientTabViewController = PatientTabViewController.getInstance();
			
		_accordion.setExpandedPane(_patientMasterData);
		
		_femaleInput.setSelected(false);
		_maleInput.setSelected(false);
		
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
		    	setTabLabelNameModified();
		    }
		});
		
		_lastnameInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    	if (!newValue.isEmpty()){
		    		setLastname(_lastnameInput.getText());
			    	setTabLabelNameModified();
		    	}
		    }
		});
		
		_birthdayInput.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> arg0,
					LocalDate oldValue, LocalDate newValue) {
				if (newValue != null){
					setTabLabelNameModified();
					setBirthday(_birthdayInput.getValue());		
				}
				
			}
		});
		
		_maleInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue){
					setTabLabelNameModified();
					setGender(_maleInput.getText().toUpperCase());
				}
			}
		});
		
		_femaleInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (!newValue){
					setTabLabelNameModified();
					setGender(_femaleInput.getText().toUpperCase());			
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
		    		setTabLabelNameModified();
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
		    		setTabLabelNameModified();
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
		    		setTabLabelNameModified();
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
		    		setTabLabelNameModified();
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
		    		setTabLabelNameModified();
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
		    		setTabLabelNameModified();
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
		    		setTabLabelNameModified();
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
		    		setTabLabelNameModified();  	
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
		    		setTabLabelNameModified();
		    }
		});
	}
	
	// Event Listener on Button[#_clearButton].onAction
	@FXML
	public void clear(ActionEvent event) {
		_firstnameInput.setText("");
		_lastnameInput.setText("");
		_birthdayInput.setValue(null);
		_maleInput.setSelected(false);
		_femaleInput.setSelected(false);
		_phoneNumberInput.setText("");
		_emailInput.setText("");
		_zipInput.setText("");
		_countryInput.setText("");
		_streetInput.setText("");
		_cityInput.setText("");
		_streetNumberInput.setText("");
		_socialSecurityNumberInput.setText("");
		_employerInput.setText("");
	}
	
	// Event Listener on Button[#_saveButton].onAction
	@FXML
	public void save(ActionEvent event) {
		CreatePatientTask createPatientTask = new CreatePatientTask();
		
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
				errorDialog.show();
			}
		});
		
		new Thread(createPatientTask).start();
	}
	
	private void setTabLabelNameModified(){
		if (!_firstnameInput.getText().isEmpty() && !_lastnameInput.getText().isEmpty()){
			_patientTabViewController.setTabLabelName("*" + _firstnameInput.getText().charAt(0) + ". " + _lastnameInput.getText());
		}
		else if (!_lastnameInput.getText().isEmpty()){
			_patientTabViewController.setTabLabelName("*" + _lastnameInput.getText());
		}
		else {
			LangFacade facade = LangFacade.getInstance();
			_patientTabViewController.setTabLabelName(facade.getString(LangKey.NEWPATIENT));
		}
	}
	
    private void setTabLabelNameIsUnmodified() {
    	if (!_firstnameInput.getText().isEmpty() && !_lastnameInput.getText().isEmpty()){
			_patientTabViewController.setTabLabelName(_firstnameInput.getText().charAt(0) + ". " + _lastnameInput.getText());
		}
		else if (!_lastnameInput.getText().isEmpty()) {
			_patientTabViewController.setTabLabelName(_lastnameInput.getText());
		}
		else {
			LangFacade facade = LangFacade.getInstance();
			_patientTabViewController.setTabLabelName(facade.getString(LangKey.NEWPATIENT));
		}
	}

	
	public void loadPatientToFormular(String id){
		try {
			_patientController.loadPatient(id);
		} catch (ObjectNotFoundException e) {
			e.printStackTrace();
		}
		setDataToFormular();
		setTabLabelNameIsUnmodified();
	}
	
	
	public void setDataToFormular(){
		_firstnameInput.setText(_patientController.getFirstname());
		_lastnameInput.setText(_patientController.getLastname());
		
		_birthdayInput.setValue(_patientController.getBirthday());
		
		String gender = _patientController.getGender();
		if (gender != null) {
			if(gender.equals("MALE")){
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
			_firstnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_firstnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setLastname(String lastname) {
		if (!_patientController.setLastname(lastname)) {
			_lastnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_lastnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setBirthday(LocalDate birthday) {
		if (!_patientController.setBirthday(birthday)) {
			_birthdayLabel.setTextFill(COLOR_FAIL);
		} else {
			_birthdayLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setGender(String gender) {
		if (!_patientController.setGender(gender)) {
			_genderLabel.setTextFill(COLOR_FAIL);
		} else {
			_genderLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setPhoneNumber(String phonenumber) {
		if (!_patientController.setPhoneNumber(phonenumber)) {
			_phoneNumberLabel.setTextFill(COLOR_FAIL);
		} else {
			_phoneNumberLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setEmail(String email) {
		if (!_patientController.setEmail(email)) {
			_emailLabel.setTextFill(COLOR_FAIL);
		} else {
			_emailLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setZip(String zip) {
		if (!_patientController.setZip(zip)) {
			_zipLabel.setTextFill(COLOR_FAIL);
		} else {
			_zipLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setCountry(String country) {
		if (!_patientController.setCountry(country)) {
			_countryLabel.setTextFill(COLOR_FAIL);
		} else {
			_countryLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setStreet(String street) {
		if (!_patientController.setStreet(street)) {
			_streetLabel.setTextFill(COLOR_FAIL);
		} else {
			_streetLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setStreetNumber(String streetnumber) {
		if (!_patientController.setStreetNumber(streetnumber)) {
			_streetNumberLabel.setTextFill(COLOR_FAIL);
		} else {
			_streetNumberLabel.setTextFill(COLOR_SUCCESS);
		}
	}
	
	void setCity(String city) {
		if (!_patientController.setCity(city)) {
			_streetNumberLabel.setTextFill(COLOR_FAIL);
		} else {
			_streetNumberLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setSocialSecurityNumber(String socialsecuritynumber) {
		try {
			if (!_patientController.setSocialSecurityNumber(socialsecuritynumber)) {
				_socialSecurityNumberLabel.setTextFill(COLOR_FAIL);
			} else {
				_socialSecurityNumberLabel.setTextFill(COLOR_SUCCESS);
			}
		} catch (UniqueConstraintException e) {
			e.printStackTrace();
		}
	}

	void setEmployer(String employer) {
		if (!_patientController.setEmployer(employer)) {
			_employerLabel.setTextFill(COLOR_FAIL);
		} else {
			_employerLabel.setTextFill(COLOR_SUCCESS);
		}
	}
	
	private class CreatePatientTask extends Task<String> {

		@Override public String call() throws IncompleteDataException, ObjectNotSavedException {
			_patientController.savePatient();
			return null;
	    }
	    
	    @Override protected void succeeded() {
	    	setTabLabelNameIsUnmodified();
	    }
	    
	    @Override protected void failed() {
	    	super.failed();
	    }
	}

	public void init(PatientTabViewController patientTabViewController) {
		_patientTabViewController = patientTabViewController;
	}

	public PatientTabViewController getPatientTabViewController() {
		return _patientTabViewController;
	}

	public void setPatientTabViewController(PatientTabViewController _patientTabViewController) {
		this._patientTabViewController = _patientTabViewController;
	}
}
