package at.itb13.oculus.application;

import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.search.Searchable;

public class ControllerFactory {
	
	private static ControllerFactory _controllerFactory;
	
	private ControllerFactory() {}
	
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

	public <T extends PersistentObject & Searchable> SearchController<T> getSearchController(Class<T> type){
		return new SearchControllerImpl<T>(type);
	}
}
