package at.itb13.oculus.application;

import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.model.Searchable;

public class ControllerFactory {

	public static PatientController getPatientController(){
		return new PatientControllerImpl();
	}
	
	public static QueueController getQueueController(){
		return new QueueControllerImpl();
	}
	
	public static QueueEntryController getQueueEntryController(){
		return new QueueEntryControllerImpl();
	}

	public static <T extends PersistentObject & Searchable> SearchController<T> getSearchController(Class<T> type){
		return new SearchControllerImpl<T>(type);
	}
}
