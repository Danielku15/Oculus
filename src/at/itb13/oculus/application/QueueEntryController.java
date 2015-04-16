package at.itb13.oculus.application;

import java.util.Date;
import java.util.List;

public interface QueueEntryController extends AutoCloseable {
	
	// getter
	String getPatientFirstname();
	String getPatientLastname();
	Date getAppointmentStart();
	Date getAppointmentEnd();
	String getAppointmentTitle();
	String getEmployeeFirstname();
	String getEmployeeLastname();
	
	// operations
	List<String[]> getQueues();
	void createQueueEntry();	
	void fetchPatient(String patientId) throws ObjectNotFoundException;
	void fetchAppointment(String appointmentId) throws ObjectNotFoundException;
	void fetchQueue(String queueId) throws ObjectNotFoundException;
	boolean saveQueueEntry() throws DataMismatchException, ObjectNotSavedException;
	boolean validateData() throws DataMismatchException;
}
