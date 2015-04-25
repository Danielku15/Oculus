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
	public void initialize(URL arg0, ResourceBundle arg1) {
		_patientTabViewController.init(this);

		_queueViewController.addQueueEntryChosenListener(new QueueEntryChosenListener() {
			@Override
			public void queueEntryChosen(QueueEntryChosenEvent e) {
				_patientTabViewController.createFormular(e.getPatientId());
			}
		});
	}

	public void setNewTab(String id){
		_patientTabViewController.createFormular(id);
	}
}
