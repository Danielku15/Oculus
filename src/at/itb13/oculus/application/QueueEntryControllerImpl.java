package at.itb13.oculus.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import at.itb13.oculus.model.Appointment;
import at.itb13.oculus.model.Employee;
import at.itb13.oculus.model.Patient;
import at.itb13.oculus.model.Queue;
import at.itb13.oculus.model.QueueEntry;

public class QueueEntryControllerImpl extends Controller implements QueueEntryController {

	private Patient _patient;
	private Appointment _appointment;
	private Queue _queue;
	
	@Override
	public void fetchPatient(String patientId) throws ObjectNotFoundException {
		try {
			_database.beginTransaction();
			_patient = _database.get(Patient.class, patientId);
			_database.commitTransaction();
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw new ObjectNotFoundException(Patient.class, patientId);
		}
	}

	@Override
	public String getPatientFirstname() {
		return _patient.getFirstname();
	}

	@Override
	public String getPatientLastname() {
		return _patient.getLastname();
	}

	@Override
	public void fetchAppointment(String appointmentId) throws ObjectNotFoundException {
		try {
			_database.beginTransaction();
			_appointment = _database.get(Appointment.class, appointmentId);
			_database.commitTransaction();
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw new ObjectNotFoundException(Appointment.class, appointmentId);
		}
	}

	@Override
	public Date getAppointmentStart() {
		return _appointment.getStart();
	}

	@Override
	public Date getAppointmentEnd() {
		return _appointment.getEnd();
	}

	@Override
	public String getAppointmentTitle() {
		return _appointment.getTitle();
	}

	@Override
	public String getEmployeeFirstname() {
		Employee employee = _appointment.getEmployee();
		if(employee != null) {
			return employee.getFirstname();
		}
		return null;
	}

	@Override
	public String getEmployeeLastname() {
		Employee employee = _appointment.getEmployee();
		if(employee != null) {
			return employee.getLastname();
		}
		return null;
	}

	@Override
	public void fetchQueue(String queueId) throws ObjectNotFoundException {
		try {
			_database.beginTransaction();
			_queue = _database.get(Queue.class, queueId);
			_database.commitTransaction();
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw new ObjectNotFoundException(Queue.class, queueId);
		}
	}

	@Override
	public List<String[]> getQueues() throws HibernateException {
		List<Queue> queuesObj = new ArrayList<Queue>();
		List<String[]> queuesStr = new ArrayList<String[]>();
		try {
			_database.beginTransaction();
			queuesObj = _database.getAll(Queue.class);
			_database.commitTransaction();
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
		for(Queue queueObj : queuesObj) {
			String[] queueStr = new String[2];
			queueStr[0] = queueObj.getID();
			queueStr[1] = queueObj.getName();
			queuesStr.add(queueStr);
		}
		return queuesStr;
	}

	@Override
	public void createQueueEntry() throws HibernateException {
		QueueEntry queueEntry = new QueueEntry(_queue, _appointment, new Date());
		try {
			_database.beginTransaction();
			_database.create(queueEntry);
			_database.commitTransaction();
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
	}
}
