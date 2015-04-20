package at.itb13.oculus.application;

import java.util.Date;
import java.util.List;

public interface QueueController extends AutoCloseable {
	
	List<String[]> getQueues();
	List<String[]> getQueueEntries(String queueId);
}
