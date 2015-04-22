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
import at.itb13.oculus.util.DateUtil;

/**
 * @author Patrick
 *
 */
class QueueEntryControllerImpl extends Controller implements QueueEntryController {

	private Patient _patient;
	private QueueEntry _queueEntry;

	public QueueEntryControllerImpl() {
		super();
		createQueueEntry();
	}
	
	@Override
	public String getPatientFirstname() {
		if(_patient != null) {
			return _patient.getFirstname();
		}
		return null;
	}

	@Override
	public String getPatientLastname() {
		if(_patient != null) {
			return _patient.getLastname();
		}
		return null;
	}

	@Override
	public Date getAppointmentStart() {
		Appointment appointment = _queueEntry.getAppointment();
		if(appointment != null) {
			return appointment.getStart();
		}
		return null;
	}

	@Override
	public Date getAppointmentEnd() {
		Appointment appointment = _queueEntry.getAppointment();
		if(appointment != null) {
			return appointment.getEnd();
		}
		return null;
	}

	@Override
	public String getAppointmentTitle() {
		Appointment appointment = _queueEntry.getAppointment();
		if(appointment != null) {
			return appointment.getTitle();
		}
		return null;
	}

	@Override
	public String getEmployeeFirstname() {
		Appointment appointment = _queueEntry.getAppointment();
		if(appointment != null) {
			Employee employee = appointment.getEmployee();
			if(employee != null) {
				return employee.getFirstname();
			}
		}
		return null;
	}

	@Override
	public String getEmployeeLastname() {
		Appointment appointment = _queueEntry.getAppointment();
		if(appointment != null) {
			Employee employee = appointment.getEmployee();
			if(employee != null) {
				return employee.getLastname();
			}
		}
		return null;
	}
	
	@Override
	public String getQueueName(){
		Queue queue = _queueEntry.getQueue();
		if(queue != null) {
			return queue.getName();
		}
		return null;
	}

	@Override
	public synchronized List<String[]> getQueues() {
		List<String[]> queuesStr = new ArrayList<String[]>();
		List<Queue> queuesObj = new ArrayList<Queue>();
		
		try {
			_database.beginTransaction();
			queuesObj = _database.getAll(Queue.class);
			_database.commitTransaction();
			
			for(Queue queueObj : queuesObj) {
				String[] queueStr = new String[2];
				queueStr[0] = queueObj.getID();
				queueStr[1] = queueObj.getName();
				queuesStr.add(queueStr);
			}
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
		return queuesStr;
	}

	@Override
	public synchronized List<String[]> getAppointmentsByPatientId(String patientId) {
		List<String[]> appointmentsStr = new ArrayList<String[]>();
		List<Appointment> appointmentsObj = new ArrayList<Appointment>();
		
		try {
			_database.beginTransaction();
			appointmentsObj = _database.getAppointmentsByPatientId(patientId, DateUtil.truncateHours(new Date()));
			_database.commitTransaction();
			
			for(Appointment appointmentObj : appointmentsObj) {
				String[] appointmentStr = new String[6];
				appointmentStr[0] = appointmentObj.getID();
				appointmentStr[1] = appointmentObj.getTitle();
				appointmentStr[2] = appointmentObj.getDescription();
				appointmentStr[3] = DateUtil.format(appointmentObj.getStart());
				Employee employee = appointmentObj.getEmployee();
				if(employee != null) {
					appointmentStr[4] = employee.getFirstname();
					appointmentStr[5] = employee.getLastname();
				}
				appointmentsStr.add(appointmentStr);
			}
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
		return appointmentsStr;
	}
	
	@Override
	public void createQueueEntry() {
		_queueEntry = new QueueEntry();
	}
	
	@Override
	public synchronized void loadQueueEntry(String queueEntryId) throws ObjectNotFoundException {
		try {
			_database.beginTransaction();
			QueueEntry queueEntry = _database.get(QueueEntry.class, queueEntryId);
			_database.commitTransaction();
			if(queueEntry != null) {
				_queueEntry = queueEntry;
			} else {
				throw new ObjectNotFoundException(QueueEntry.class, queueEntryId);
			}
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
	}
	
	@Override
	public synchronized void fetchPatient(String patientId) throws ObjectNotFoundException {
		try {
			_database.beginTransaction();
			Patient patient = _database.get(Patient.class, patientId);
			_database.commitTransaction();
			if(patient != null) {
				_patient = patient;
			} else {
				throw new ObjectNotFoundException(Patient.class, patientId);
			}
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
	}
	
	@Override
	public synchronized void fetchAppointment(String appointmentId) throws ObjectNotFoundException {
		try {
			_database.beginTransaction();
			Appointment appointment = _database.get(Appointment.class, appointmentId);
			_database.commitTransaction();
			if(appointment != null) {
				_queueEntry.setAppointment(appointment);
			} else {
				throw new ObjectNotFoundException(Appointment.class, appointmentId);
			}
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
	}	
	
	@Override
	public synchronized void fetchQueue(String queueId) throws ObjectNotFoundException {
		try {
			_database.beginTransaction();
			Queue queue = _database.get(Queue.class, queueId);
			_database.commitTransaction();
			if(queue != null) {
				_queueEntry.setQueue(queue);
			} else {
				throw new ObjectNotFoundException(Queue.class, queueId);
			}
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
	}
	
	@Override
	public synchronized boolean saveQueueEntry() throws IncompleteDataException, DataMismatchException, ObjectNotSavedException {
		if(validateData()) {
			_queueEntry.setCreated(new Date());
			try {
				_database.beginTransaction();
				_database.createOrUpdate(_queueEntry);
				_database.commitTransaction();
			} catch(HibernateException e) {
				_database.rollbackTransaction();
				throw new ObjectNotSavedException(_queueEntry);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean validateData() throws IncompleteDataException, DataMismatchException {
		List<String> fieldNames = new ArrayList<String>();
		if(_patient == null) {
			fieldNames.add("patient");
		}
		if(_queueEntry.getAppointment() == null) {
			fieldNames.add("appointment");
		}
		if(_queueEntry.getQueue() == null) {
			fieldNames.add("queue");
		}
		if(!fieldNames.isEmpty()) {
			throw new IncompleteDataException(fieldNames);
		}
		
		Appointment appointment = _queueEntry.getAppointment();
		if((appointment.getPatient() == null) || (_patient.equals(appointment.getPatient()))) {
			// set patient of appointment
			appointment.setPatient(_patient);
		} else {
			throw new DataMismatchException(appointment.getPatient(), _patient);
		}
		return true;
	}

	@Override
	public String getQueueId() {
		return MainController.getInstance().getQueueController().getQueueId();
	}

	@Override
	public String getPatientId() {
		PatientController tempController = MainController.getInstance().getPatientController();
		if(tempController != null){
			return tempController.getID();
		}
		return null;
	}
	

}
