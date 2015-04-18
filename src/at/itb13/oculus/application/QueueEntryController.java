package at.itb13.oculus.application;

import java.util.Date;
import java.util.List;

/**
 * @author Patrick
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
	
	// operations
	List<String[]> getQueues();
	void createQueueEntry();
	void loadQueueEntry(String queueEntryId) throws ObjectNotFoundException;
	void fetchPatient(String patientId) throws ObjectNotFoundException;
	void fetchAppointment(String appointmentId) throws ObjectNotFoundException;
	void fetchQueue(String queueId) throws ObjectNotFoundException;
	boolean saveQueueEntry() throws IncompleteDataException, DataMismatchException, ObjectNotSavedException;
	boolean validateData() throws IncompleteDataException, DataMismatchException;
}
