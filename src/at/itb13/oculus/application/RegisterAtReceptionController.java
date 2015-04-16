package at.itb13.oculus.application;

import at.itb13.oculus.model.Appointment;
import at.itb13.oculus.model.Patient;
import at.itb13.oculus.model.QueueEntry;

public interface RegisterAtReceptionController {
	
	public Patient getPatient(String id);
	
	public void updatePatient(/*Patientendaten*/);
	
	public Appointment loadAppointment();
	
	public QueueEntry createQueueEntry(Appointment appointment );
	
	public void addEntryToQueue(QueueEntry entry);
	
	public void close();
}
