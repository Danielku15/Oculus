package at.itb13.oculus.application;

import java.lang.reflect.Array;
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
 * 
 * QueueEntryController is responsible for queue entries
 *
 */
class QueueEntryControllerImpl extends Controller implements QueueEntryController {

	/**
	 * saves currently selected patient
	 * @see QueueEntryControllerImpl#_patient
	 */
	private Patient _patient;
	/**
	 * saves assigned queue entry
	 * @see QueueEntryControllerImpl#_patient
	 */
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

	/** 
	 * This method loads all queues from the database and fills them into a {@link List}.
	 * The information of the queue is saved in a {@link String} {@link Array}.
	 * Position 0 contains queue id,
	 * Position 1 contains queue name.
	 * @see at.itb13.oculus.application.QueueEntryController#getQueues()
	 * @return {@link List} with {@link String} {@link Array} from {@link Queue}
	 */
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
	
	/**
	 * loads the id of queue by name from database
	 * @see at.itb13.oculus.application.QueueEntryController#getQueueIdByName(java.lang.String)
	 * @param queueName name of queue
	 * @return id of queue
	 */
	public synchronized String getQueueIdByName(String queueName){
		Queue queue = null;
		try {
			_database.beginTransaction();
			queue = _database.getQueueByName(queueName);
			_database.commitTransaction();
		} catch (HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
		if(queue != null) {
			return queue.getID();
		}
		return null;
	}
	
	/**
	 * @see at.itb13.oculus.application.QueueEntryController#getActiveQueueId()
	 * @return the selected queue id as {@link String} at {@link at.itb13.oculus.presentation.QueueViewController} from {@link MainControllerImpl} by controller {@link MainControllerImpl#getQueueController()}
	 */
	@Override
	public String getActiveQueueId() {
		return MainControllerImpl.getInstance().getQueueController().getQueueId();
	}
	
	/**
	 * @see at.itb13.oculus.application.QueueEntryController#getActivePatientId()
	 * @return the selected patient id as {@link String} at {@link at.itb13.oculus.presentation.PatientTabViewController} from {@link MainControllerImpl} by controller {@link MainControllerImpl#getPatientController()}
	 */
	@Override
	public String getActivePatientId() {
		PatientController tempController = MainControllerImpl.getInstance().getPatientController();
		if(tempController != null){
			return tempController.getID();
		}
		return null;
	}

	/**
	 * This method loads all appointments from the database and fills them into a {@link List}.
	 * The information of the appointment is saved in a {@link String} {@link Array}.
	 * Position 0 contains appointment id,
	 * Position 1 contains appointment title,
	 * Position 2 contains appointment description,
	 * Position 3 contains appointment start,
	 * Position 4 contains employee	first name,
	 * Position 5 contains employee last name.
	 * @see at.itb13.oculus.application.QueueEntryController#getAppointmentsByPatientId(java.lang.String
	 * @return {@link List} with {@link String} {@link Array} from {@link Appointment} by {@link Patient}
	 */
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
	
	/**
	 * creates a new queue entry
	 * @see at.itb13.oculus.application.QueueEntryController#createQueueEntry()
	 */
	@Override
	public void createQueueEntry() {
		_queueEntry = new QueueEntry();
	}
	
	/**
	 * loads the right queue entry into the {@link QueueEntryControllerImpl#_queueEntry} {@link QueueEntry}
	 * @see at.itb13.oculus.application.QueueEntryController#loadQueueEntry(java.lang.String)
	 * @param queueEntryId the id of the required queue entry
	 */
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
	
	/**
	 * loads the selected patient into the {@link QueueEntryControllerImpl#_patient} {@link Patient}
	 * @see at.itb13.oculus.application.QueueEntryController#fetchPatient(java.lang.String)
	 * @param patientId the id of the selected patient {@link Patient}
	 */
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
	
	/**
	 * loads the selected appointment into {@link QueueEntryControllerImpl#_queueEntry} by {@link QueueEntry#setAppointment(Appointment)}
	 * @see at.itb13.oculus.application.QueueEntryController#fetchAppointment(java.lang.String)
	 * @param appointmentId the id of the selected appointment
	 */
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
	
	/**
	 * loads the selected queue into {@link QueueEntryControllerImpl#_queueEntry} by {@link QueueEntry#setQueue(Queue)}
	 * @see at.itb13.oculus.application.QueueEntryController#fetchQueue(java.lang.String)
	 * @param queueId the id of the selected queue
	 */
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
	
	/**
	 * saves the queue entry {@link QueueEntryControllerImpl#_queueEntry} into the database
	 * @see at.itb13.oculus.application.QueueEntryController#saveQueueEntry()
	 */
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

	/**
	 * checks if all data is set and valid
	 * @see at.itb13.oculus.application.QueueEntryController#validateData()
	 * @return {@link Boolean} success or failure
	 */
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
}
