package at.itb13.oculus.application;

import java.util.List;
/**
 * 
 * Queue controller interface
 *
 */
public interface QueueController extends AutoCloseable {
	
	// getter
	List<String[]> getQueues();
	List<String[]> getQueueEntries(String queueId);
	String getQueueIdByName(String queueName);
	String getQueueId();
	
	// operations
	void activate();
	void fetchQueue(String queueName);
	String getPatientIdByQueueEntryId(String queueEntryId);
}
