package at.itb13.oculus.presentation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class PatientMainViewController implements Initializable  {

	@FXML
	private QueueViewController _queueViewController;
	@FXML
	private PatientTabViewController _patientTabViewController;

	@Override
	public void initialize(URL url, ResourceBundle resBundle) {		
		_queueViewController.init(this);
		_patientTabViewController.init(this);
	}

	public void setNewTab(String id){
		_patientTabViewController.createFormular(id);
	}
}
