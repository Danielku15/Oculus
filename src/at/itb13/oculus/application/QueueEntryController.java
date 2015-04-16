package at.itb13.oculus.application;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

public interface QueueEntryController extends AutoCloseable {
	
	void fetchPatient(String patientId) throws ObjectNotFoundException;
	
	String getPatientFirstname();
	String getPatientLastname();
	
	void fetchAppointment(String appointmentId) throws ObjectNotFoundException;
	
	Date getAppointmentStart();
	Date getAppointmentEnd();
	String getAppointmentTitle();
	String getEmployeeFirstname();
	String getEmployeeLastname();
	
	void fetchQueue(String queueId) throws ObjectNotFoundException;
	List<String[]> getQueues() throws HibernateException;
	
	void createQueueEntry() throws HibernateException;	
}
