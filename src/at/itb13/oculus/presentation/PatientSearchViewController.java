package at.itb13.oculus.presentation;

import java.net.URL;
import java.util.ResourceBundle;

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
		// TODO Auto-generated method stub
		/*
		 * 2. In your controller's initialize method, initialize the content of your choice box.

package myexample;
import ...

public class MyController implements Initializable {

@FXML // fx:id="myChoices"
private ChoiceBox<?> myChoices; // Value injected by FXMLLoader

@Override // This method is called by the FXMLLoader when initialization is complete
public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
assert myChoices != null : "fx:id=\"myChoices\" was not injected: check your FXML file 'foo.fxml'.";

// initialize your logic here: all @FXML variables will have been injected
// => you can add items to "myChoices" here:
// 
myChoices.setItems(FXCollections.observableArrayList());
myChoices.getItems().add(...);

		 * */
		
	}

}
