package at.itb13.oculus.application;

import at.itb13.oculus.presentation.MainView;

/**
 * 
 * The MainController saves all the active controllers and provides quick access to open views.
 *
 */
public class MainController {

	private static MainController _mainController;
	
	private PatientController _patientController;
	private QueueController _queueController;
	private TreatmentController _treatmentController;
	private MainView _mainView;
	
	private MainController() {}
	
	/**
	 * The MainController is a singleton class
	 * If there is no instance of this class yet, create a new one
	 * @return singleton instance of MainController
	 */
	public static MainController getInstance() {
		if(_mainController == null) {
			_mainController = new MainController();
		}
		return _mainController;
	}
	
	/**
	 * changes the mainView reference
	 * @param mainView enumeration for the three view modes
	 */
	public void setMainView(MainView mainView) {
		_mainView = mainView;
	}
	
	public MainView getMainView() {
		return _mainView;
	}
	
	public void setPatientController(PatientController patientController) {
		_patientController = patientController;
	}
	
	public PatientController getPatientController() {
		return _patientController;
	}
	
	public void setQueueController(QueueController queueController) {
		_queueController = queueController;
	}
	
	public QueueController getQueueController() {
		return _queueController;
	}
	
	public void setTreatmentController(TreatmentController treatmentController) {
		_treatmentController = treatmentController;
	}
	
	public TreatmentController getTreatmentController() {
		return _treatmentController;
	}
}
