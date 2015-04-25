package at.itb13.oculus.application;

import at.itb13.oculus.presentation.MainView;
/**
 * 
 * Main controller interface
 *
 */
public interface MainController {
	
	// getter
	MainView getMainView();
	PatientController getPatientController();
	QueueController getQueueController();
	TreatmentController getTreatmentController();
	
	// setter
	void setMainView(MainView mainView);
	void setPatientController(PatientController patientController);
	void setQueueController(QueueController queueController);
	void setTreatmentController(TreatmentController treatmentController);
}
