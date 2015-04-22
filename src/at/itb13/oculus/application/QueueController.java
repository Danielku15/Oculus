package at.itb13.oculus.application;

import java.util.List;

public interface QueueController extends AutoCloseable {
	
	// getter
	List<String[]> getQueues();
	List<String[]> getQueueEntries(String queueId);
	List<String[]> getEmployees();
	String getIdOfQueue(String queueName);
	String getQueueId();
	
	// operations
	void activate();
	void fetchQueue(String queueName);
	String getIdOfPatient(String queueEntryId);
}
