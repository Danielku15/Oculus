package at.itb13.oculus.presentation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TreatmentMainViewController implements Initializable  {
	
	@FXML
	private QueueViewController _queueViewController;
	@FXML
	private TreatmentTabViewController _treatmentTabViewController;

	@Override
	public void initialize(URL url, ResourceBundle resBundle) {
		_queueViewController.addQueueEntryChosenListener(new QueueEntryChosenListener() {
			@Override
			public void queueEntryChosen(QueueEntryChosenEvent e) {
				_treatmentTabViewController.createForm(e.getAppointmentId());
			}
		});
	}
}
