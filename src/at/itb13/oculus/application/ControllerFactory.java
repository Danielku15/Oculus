package at.itb13.oculus.application;

import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.search.Searchable;
/**
 * 
 * The ControllerFactory provides the access to controllers of the application layer.
 * Its responsibility is to instantiate and return a controller with limited methods defined in its associated interface.
 *
 */
public class ControllerFactory {
	
	private static ControllerFactory _controllerFactory;
	
	private ControllerFactory() {}
	
	/**
	 * The ControllerFactory is a singleton class.
	 * @return singleton instance of ControllerFactory
	 */
	public static ControllerFactory getInstance() {
		if(_controllerFactory == null) {
			_controllerFactory = new ControllerFactory();
		}
		return _controllerFactory;
	}
	
	/**
	 * Getter {@link ControllerFactory#getMainController()} 
	 * @return {@link MainControllerImpl}
	 * Getter {@link ControllerFactory#getPatientController()}
	 * @return {@link PatientController}
	 * Getter {@link ControllerFactory#getQueueController()}
	 * @return {@link QueueController}
	 * Getter {@link ControllerFactory#getQueueEntryController()}
	 * @return {@link QueueEntryController}
	 * Getter {@link ControllerFactory#getTreatmentController()} 
	 * @return {@link TreatmentController}
	 * Getter {@link ControllerFactory#getSearchController()} 
	 * @return {@link SearchController}
	 */
	
	public MainController getMainController() {
		return MainControllerImpl.getInstance();
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
	
	public TreatmentController getTreatmentController(){
		return new TreatmentControllerImpl();
	}

	public <T extends PersistentObject & Searchable> SearchController<T> getSearchController(Class<T> type){
		return new SearchControllerImpl<T>(type);
	}
}
