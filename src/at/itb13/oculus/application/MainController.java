package at.itb13.oculus.application;

class MainController {

	private static MainController _mainController;
	
	private PatientController _patientController;
	private QueueController _queueController;
	
	private MainController() {}
	
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
