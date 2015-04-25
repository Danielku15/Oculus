package at.itb13.oculus.application;

import at.itb13.oculus.presentation.MainViewContent;
/**
 * 
 * Main controller interface
 *
 */
public interface MainController {
	
	// getter
	MainViewContent getMainView();
	PatientController getPatientController();
	QueueController getQueueController();
	TreatmentController getTreatmentController();
	
	// setter
	void setMainView(MainViewContent mainView);
	void setPatientController(PatientController patientController);
	void setQueueController(QueueController queueController);
	void setTreatmentController(TreatmentController treatmentController);
}
