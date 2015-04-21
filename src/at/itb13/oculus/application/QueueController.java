package at.itb13.oculus.application;

import java.util.List;

public interface QueueController extends AutoCloseable {
	
	// getter
	List<String[]> getQueues();
	List<String[]> getQueueEntries(String queueId);
	List<String[]> getEmployees();
	String getIdOfQueue(String queueName);
	
	// operations
	void fetchQueue(String queueName);
	void activate();
}
