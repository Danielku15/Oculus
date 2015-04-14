package at.itb13.oculus.application;

import at.itb13.oculus.model.Appointment;
import at.itb13.oculus.model.Patient;
import at.itb13.oculus.model.Queue;
import at.itb13.oculus.model.QueueEntry;

public interface RegisterAtReceptionController {
	
	public Patient getPatient();
	
	public boolean acceptPatient();
	
	public Queue loadQueue();
	
	public QueueEntry createQueueEntry(Appointment appointment );
	
	public void addEntryToQueue(QueueEntry entry);
	
	public void close();
	
}
