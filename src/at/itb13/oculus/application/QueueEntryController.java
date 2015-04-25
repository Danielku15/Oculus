package at.itb13.oculus.application;

import java.util.Date;
import java.util.List;
/**
 * 
 * Queue entry controller interface
 *
 */
public interface QueueEntryController extends AutoCloseable {
	
	// getter
	String getPatientFirstname();
	String getPatientLastname();
	Date getAppointmentStart();
	Date getAppointmentEnd();
	String getAppointmentTitle();
	String getEmployeeFirstname();
	String getEmployeeLastname();
	String getQueueName();
	
	// operations
	List<String[]> getQueues();
	String getActiveQueueId();
	String getActivePatientId();
	List<String[]> getAppointmentsByPatientId(String patientId);
	void createQueueEntry();
	void loadQueueEntry(String queueEntryId) throws ObjectNotFoundException;
	void fetchPatient(String patientId) throws ObjectNotFoundException;
	void fetchAppointment(String appointmentId) throws ObjectNotFoundException;
	void fetchQueue(String queueId) throws ObjectNotFoundException;
	boolean saveQueueEntry() throws IncompleteDataException, DataMismatchException, ObjectNotSavedException;
	boolean validateData() throws IncompleteDataException, DataMismatchException;
}
