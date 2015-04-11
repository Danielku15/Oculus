package at.itb13.oculus.presentation;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import at.itb13.oculus.application.PatientViewControllerImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.DatePicker;
import javafx.scene.paint.Color;
import javafx.scene.Group;


public class FormularView implements Initializable{
	
	private static final Color COLOR_FAIL = Color.RED;
	private static final Color COLOR_SUCCESS = Color.BLACK;
	
	private PatientViewControllerImpl _patientController;
	
	@FXML
	private ScrollPane _patientFormulatScrollPane1;
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
	private Label _phonenumberLabel;
	@FXML
	private TextField _phonenumberInput;
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
	private Label _streetnumberLabel;
	@FXML
	private TextField _streetnumberInput;
	@FXML
	private Label _socialsecuritynumberLabel;
	@FXML
	private TextField _socialsecuritynumberInput;
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
	
	public FormularView() {
		_patientController = new PatientViewControllerImpl();		
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
		
	}
	
	// Event Listener on Button[#_saveButton].onAction
	@FXML
	public void save(ActionEvent event) {
		new Thread(new CreatePatientTask(_patientController)).start();
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

	void setBirthday(Date birthday) {
		if (!_patientController.setBirthday(birthday)) {
			_firstnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_firstnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setGender(String gender) {
		if (!_patientController.setGender(gender)) {
			_lastnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_lastnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setPhoneNumber(String phonenumber) {
		if (!_patientController.setPhoneNumber(phonenumber)) {
			_firstnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_firstnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setEmail(String email) {
		if (!_patientController.setEmail(email)) {
			_lastnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_lastnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setZip(String zip) {
		if (!_patientController.setZip(zip)) {
			_lastnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_lastnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setCountry(String country) {
		if (!_patientController.setCountry(country)) {
			_lastnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_lastnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setStreet(String street) {
		if (!_patientController.setStreet(street)) {
			_lastnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_lastnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setStreetNumber(String streetnumber) {
		if (!_patientController.setStreetNumber(streetnumber)) {
			_lastnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_lastnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setSocialSecurityNumber(String socialsecuritynumber) {
		if (!_patientController.setSocialSecurityNumber(socialsecuritynumber)) {
			_lastnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_lastnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}

	void setEmployer(String employer) {
		if (!_patientController.setEmployer(employer)) {
			_lastnameLabel.setTextFill(COLOR_FAIL);
		} else {
			_lastnameLabel.setTextFill(COLOR_SUCCESS);
		}
	}
        
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		_accordion.setExpandedPane(_patientMasterData);
		
		_firstnameInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setFirstname(_firstnameInput.getText());
				}
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
		
		_birthdayInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					LocalDate lDate = _birthdayInput.getValue();
					Date date = java.sql.Date.valueOf(lDate);				
					setBirthday(date);
				}
			}
		});
		
		_maleInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setGender(_maleInput.getText().toUpperCase());
				}
			}
		});
		
		_femaleInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setGender(_femaleInput.getText().toUpperCase());
				}
			}
		});
		
		_phonenumberInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setPhoneNumber(_phonenumberInput.getText());
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
		
		_streetnumberInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setStreetNumber(_streetnumberInput.getText());
				}
			}
		});
		
		_socialsecuritynumberInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setSocialSecurityNumber(_socialsecuritynumberInput.getText());
				}
			}
		});
		
		_employerInput.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					setEmployer(_emailInput.getText());
				}
			}
		});
	}
	
}

class CreatePatientTask extends Task<String> {
	
	PatientViewControllerImpl _patientController;
	
	public CreatePatientTask(PatientViewControllerImpl patientController) {
		_patientController = patientController;
		_patientController.createPatient();
	}
	
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
