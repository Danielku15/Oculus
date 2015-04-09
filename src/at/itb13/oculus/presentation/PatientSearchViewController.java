package at.itb13.oculus.presentation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

import javafx.scene.control.ChoiceBox;

import javafx.scene.control.TableColumn;

public class PatientSearchViewController implements Initializable {
	@FXML
	private Label _patientsearchLbl;
	@FXML
	private TextField _patientsearchInput;
	@FXML
	private Button _patientsearchButton;
	@FXML
	private ChoiceBox<?> _patientsearchChoicebox;
	@FXML
	private TableColumn<?, ?> _idLbl;
	@FXML
	private TableColumn<?, ?> _firstnameLbl;
	@FXML
	private TableColumn<?, ?> _lastnameLbl;
	@FXML
	private TableColumn<?, ?> _birthdayLbl;
	@FXML
	private TableColumn<?, ?> _genderLbl;
	@FXML
	private TableColumn<?, ?> _phonenumberLbl;
	@FXML
	private TableColumn<?, ?> _emailLbl;
	@FXML
	private TableColumn<?, ?> _zipLbl;
	@FXML
	private TableColumn<?, ?> _countryLbl;
	@FXML
	private TableColumn<?, ?> _streetLbl;
	@FXML
	private TableColumn<?, ?> _streetnumberLbl;
	@FXML
	private TableColumn<?, ?> _socialsecuritynumberLbl;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO change choice box options to fit selected language
		_patientsearchChoicebox = new ChoiceBox<String>(FXCollections.observableArrayList("Vorname", "Nachname", "SVN"));
	}

}
