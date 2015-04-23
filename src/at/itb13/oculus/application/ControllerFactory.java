package at.itb13.oculus.application;

import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.search.Searchable;
/**
 * 
 * The ControllerFactory provides the access to controllers of the application layer.
 * His task is to instance controllers and returns the controller with limited methods defined in its interface.
 *
 */
public class ControllerFactory {
	
	private static ControllerFactory _controllerFactory;
	
	private ControllerFactory() {}
	
	/**
	 * The ControllerFactory is a singleton class.
	 * @return singleton instance of class
	 */
	public static ControllerFactory getInstance() {
		if(_controllerFactory == null) {
			_controllerFactory = new ControllerFactory();
		}
		return _controllerFactory;
	}

	public PatientController getPatientController(){
		return new PatientControllerImpl();
	}
	
	public QueueController getQueueController(){
		return new QueueControllerImpl();
	}
	
	public QueueEntryController getQueueEntryController(){
		return new QueueEntryControllerImpl();
	}
	
	public AppointmentController getAppointmentController(){
		return new AppointmentControllerImpl();
	}

	public <T extends PersistentObject & Searchable> SearchController<T> getSearchController(Class<T> type){
		return new SearchControllerImpl<T>(type);
	}
	

}
