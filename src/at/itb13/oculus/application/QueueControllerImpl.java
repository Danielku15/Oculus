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
 * The QueueController is responsible for operating with queues
 *
 */
class QueueControllerImpl extends Controller implements QueueController {
	
	private Queue _activeQueue;
	
	public QueueControllerImpl() {
		super();
	}

	/** 
	 * This method loads all queues from the database and fills them into a {@link List}.
	 * The information of the queue is saved in a {@link String} {@link Array}.
	 * Position 0 contains queue id,
	 * Position 1 contains queue name.
	 * @see at.itb13.oculus.application.QueueController#getQueues()
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

			for (Queue queueObj : queuesObj) {
				String[] queueStr = new String[2];
				queueStr[0] = queueObj.getID();
				queueStr[1] = queueObj.getName();
				queuesStr.add(queueStr);
			}
		} catch (HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
		return queuesStr;
	}

	/**
	 * The information of the queue entries is saved in a {@link String} {@link Array}.
	 * Position 0 contains queue entry id,
	 * Position 1 contains employee id,
	 * Position 2 contains employee first name,
	 * Position 3 contains employee last name,
	 * Position 4 contains patient id,
	 * Position 5 contains patient first name,
	 * Position 6 contains patient last name,
	 * Position 7 contains patient social security number.
	 * @see at.itb13.oculus.application.QueueController#getQueueEntries(java.lang.String)
	 * This method loads all queues entries for database and fills them into a {@link List}
	 * @param queueId is id from queue where the entries are saved
	 * @return {@link List} with {@link String} {@link Array} from {@link QueueEntry}
	 */
	@Override
	public synchronized List<String[]> getQueueEntries(String queueId) {
		List<String[]> queueEntriesStr = new ArrayList<String[]>();
		List<QueueEntry> queueEntriesObj = new ArrayList<QueueEntry>();

		try {
			_database.beginTransaction();
			queueEntriesObj = _database.getQueueEntriesByQueueId(queueId, DateUtil.truncateHours(new Date()));
			_database.commitTransaction();

			for (QueueEntry queueEntryObj : queueEntriesObj) {
				String[] queueEntryStr = new String[10];
				// queueEntryId
				queueEntryStr[0] = queueEntryObj.getID();
				Appointment appointment = queueEntryObj.getAppointment();
				if(appointment != null) {
					Employee employee = appointment.getEmployee();
					if(employee != null) {
						// employee id, firstname, lastname
						queueEntryStr[1] = employee.getID();
						queueEntryStr[2] = employee.getFirstname();
						queueEntryStr[3] = employee.getLastname();
					}
					Patient patient = appointment.getPatient();
					if(patient != null) {
						// patient id, firstname, lastname, svn
						queueEntryStr[4] = patient.getID();
						queueEntryStr[5] = patient.getFirstname();
						queueEntryStr[6] = patient.getLastname();
						queueEntryStr[7] = patient.getSocialSecurityNumber();
					}
					Date start = appointment.getStart();
					if(start != null) {
						// appointment start
						queueEntryStr[8] = start.toString();
					}
					queueEntryStr[9] = appointment.getID();
				}
				queueEntriesStr.add(queueEntryStr);
			}
		} catch (HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
		return queueEntriesStr;
	}
	
	/**
	 * loads the id of queue by name from database
	 * @see at.itb13.oculus.application.QueueController#getQueueIdByName(java.lang.String)
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
	 * @see at.itb13.oculus.application.QueueController#getQueueId()
	 * @return id of active queue saved in {@link QueueControllerImpl#_activeQueue}
	 */
	@Override
	public String getQueueId() {
		return _activeQueue.getID();
	}
	
	/**
	 * activates queue controller in {@link MainControllerImpl} by {@link MainControllerImpl#setQueueController(QueueController)}
	 * @see at.itb13.oculus.application.QueueController#activate()
	 */
	@Override
	public void activate() {
		MainControllerImpl.getInstance().setQueueController(this);
	}
	
	/**
	 * activates queue by saving into {@link QueueControllerImpl#_activeQueue}
	 * @see at.itb13.oculus.application.QueueController#fetchQueue()
	 */
	public void fetchQueue(String queueName){
		_activeQueue = _database.getQueueByName(queueName);
	}
	
	/**
	 * loads the id of a patient referenced in a queue entry
	 * @see at.itb13.oculus.application.QueueController#getPatientIdByQueueEntryId(java.lang.String)
	 * @param queueEntryId id of queue entry where the patient is referenced
	 * @return id of patient from queue entry
	 */
	public synchronized String getPatientIdByQueueEntryId(String queueEntryId){
		QueueEntry queue = null;
		try {
			_database.beginTransaction();
			queue = _database.getQueueEntryById(queueEntryId);
			_database.commitTransaction();
		} catch (HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
		if(queue != null) {
			Appointment appointment = queue.getAppointment();
			if(appointment != null) {
				Patient patient = appointment.getPatient();
				if(patient != null) {
					return patient.getID();
				}
			}
		}
		return null;
	}
}