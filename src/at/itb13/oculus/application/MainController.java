package at.itb13.oculus.application;
/**
 * 
 * The MainController saves all the active controllers and provides quick access to open views.
 *
 */
class MainController {

	private static MainController _mainController;
	
	private PatientController _patientController;
	private QueueController _queueController;
	
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
}
