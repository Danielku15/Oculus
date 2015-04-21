package at.itb13.oculus.application;

import at.itb13.oculus.model.Patient;
import at.itb13.oculus.model.Queue;

public final class MainController {

	private static MainController _mainController;
	
	private Patient _patient;
	private Queue _queue;
	
	private MainController() {}
	
	public static MainController getInstance() {
		if(_mainController == null) {
			_mainController = new MainController();
		}
		return _mainController;
	}
	
	public void setPatient(Patient patient) {
		_patient = patient;
	}
	
	public Patient getPatient() {
		return _patient;
	}
	
	public void setQueue(Queue queue) {
		_queue = queue;
	}
	
	public Queue getQueue() {
		return _queue;
	}
}
