package at.itb13.oculus.presentation;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import at.itb13.oculus.application.PatientControllerImpl;


public class PatientViewController implements Initializable{
	
	private static final Color COLOR_FAIL = Color.RED;
	private static final Color COLOR_SUCCESS = Color.BLACK;
	
	//application - PatientViewControllerImpl
	private PatientControllerImpl _patientController;

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
		_patientController = new PatientControllerImpl();		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if (_patientTabViewController == null) {
			_patientTabViewController = PatientTabViewController.getInstance();
		}
			
		_accordion.setExpandedPane(_patientMasterData);
		
		_femaleInput.setSelected(false);
		_maleInput.setSelected(false);
		
		_firstnameInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setFirstname(_firstnameInput.getText());
				}
			}
		});
		
		_lastnameInput.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
		    	System.out.println(_lastnameInput.getText());
		    	setLastname(_lastnameInput.getText());
				if (_patientTabViewController != null){
					_patientTabViewController.setTabLabelName(_lastnameInput.getText());
				}
		    }
		});
		
		_birthdayInput.valueProperty().addListener(new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> arg0,
					LocalDate oldValue, LocalDate newValue) {
				if (newValue != null){
					setBirthday(_birthdayInput.getValue());		
				}
				
			}
		});
		
		_maleInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					//TODO upper case in application layer
					setGender(_maleInput.getText().toUpperCase());
				}
			}
		});
		
		_femaleInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					//TODO upper case in application layer
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
		
		_emailInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setEmail(_emailInput.getText());
				}
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
		
		_countryInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setCountry(_countryInput.getText());
				}
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
		
		_streetNumberInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setStreetNumber(_streetNumberInput.getText());
				}
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
		
		_socialSecurityNumberInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setSocialSecurityNumber(_socialSecurityNumberInput.getText());
				}
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
		//TODO change label name when patient is saved
		new Thread(new CreatePatientTask()).start();
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
		if (!_patientController.setSocialSecurityNumber(socialsecuritynumber)) {
			_socialSecurityNumberLabel.setTextFill(COLOR_FAIL);
		} else {
			_socialSecurityNumberLabel.setTextFill(COLOR_SUCCESS);
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

	    @Override public String call() {
	    	_patientController.savePatient();	
	    	return _patientController.getId();
	    }
	    
	    @Override protected void succeeded() {
	    	
	    }
	    
	    @Override protected void cancelled() {
	    }
	    
	    @Override protected void failed() {
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
