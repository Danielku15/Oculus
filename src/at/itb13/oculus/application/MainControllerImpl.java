package at.itb13.oculus.application;

import at.itb13.oculus.presentation.MainView;
/**
 * 
 * The MainController references all active controllers and therefore simplifies communication between the controllers.
 *
 */
class MainControllerImpl implements MainController {

	private static MainControllerImpl _mainController;
	
	private PatientController _patientController;
	private QueueController _queueController;
	private TreatmentController _treatmentController;
	private MainView _mainView;
	
	private MainControllerImpl() {}
	
	/**
	 * The MainController is a singleton class.
	 * @return singleton instance of MainController
	 */
	public static MainControllerImpl getInstance() {
		if(_mainController == null) {
			_mainController = new MainControllerImpl();
		}
		return _mainController;
	}
	
	/**
	 * sets the MainView reference
	 * @param mainView enumeration for the different main views
	 */
	public void setMainView(MainView mainView) {
		_mainView = mainView;
	}
	
	public MainView getMainView() {
		return _mainView;
	}
	
	/**
	 * sets the PatientController reference
	 * @param patientController currently active patient controller
	 */
	public void setPatientController(PatientController patientController) {
		_patientController = patientController;
	}
	
	public PatientController getPatientController() {
		return _patientController;
	}
	
	/**
	 * sets the QueueController reference
	 * @param queueController currently active queue controller
	 */
	public void setQueueController(QueueController queueController) {
		_queueController = queueController;
	}
	
	public QueueController getQueueController() {
		return _queueController;
	}
	
	/**
	 * sets the TreatmentController reference
	 * @param treatmentController currently active treatment controller
	 */
	public void setTreatmentController(TreatmentController treatmentController) {
		_treatmentController = treatmentController;
	}
	
	public TreatmentController getTreatmentController() {
		return _treatmentController;
	}
}
