package at.itb13.oculus.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import at.itb13.oculus.model.Employee;
import at.itb13.oculus.model.Queue;
import at.itb13.oculus.model.QueueEntry;
import at.itb13.oculus.util.DateUtil;

/**
 * @author Carola
 *
 */
public class QueueControllerImpl extends Controller implements QueueController {
	
	public QueueControllerImpl() {
		super();
	}

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

	@Override
	public synchronized List<String[]> getQueueEntries(String queueId) {
		List<String[]> queueEntriesStr = new ArrayList<String[]>();
		List<QueueEntry> queueEntriesObj = new ArrayList<QueueEntry>();

		try {
			_database.beginTransaction();
			queueEntriesObj = _database.getQueueEntriesByQueueId(queueId, DateUtil.truncateHours(new Date()));
			_database.commitTransaction();

			for (QueueEntry queueEntryObj : queueEntriesObj) {
				String[] queueEntryStr = new String[9];
				// queueEntryId
				queueEntryStr[0] = queueEntryObj.getID();
				// employee id, firstname, lastname
				queueEntryStr[1] = queueEntryObj.getAppointment().getEmployee().getID();
				queueEntryStr[2] = queueEntryObj.getAppointment().getEmployee().getFirstname();
				queueEntryStr[3] = queueEntryObj.getAppointment().getEmployee().getLastname();
				// patient id, firstname, lastname, svn
				queueEntryStr[4] = queueEntryObj.getAppointment().getPatient().getID();
				queueEntryStr[5] = queueEntryObj.getAppointment().getPatient().getFirstname();
				queueEntryStr[6] = queueEntryObj.getAppointment().getPatient().getLastname();
				queueEntryStr[7] = queueEntryObj.getAppointment().getPatient().getSocialSecurityNumber();
				// appointment start
				queueEntryStr[8] = queueEntryObj.getAppointment().getStart().toString();
				queueEntriesStr.add(queueEntryStr);
			}
		} catch (HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}

		return queueEntriesStr;
	}
	
	public synchronized List<String[]> getEmployees() {
		List<String[]> employeesStr = new ArrayList<String[]>();
		List<Employee> employeesObj = new ArrayList<Employee>();

		try {
			_database.beginTransaction();
			employeesObj = _database.getAll(Employee.class);
			_database.commitTransaction();

			for (Employee employeeObj : employeesObj) {
				String[] employeeStr = new String[3];
				employeeStr[0] = employeeObj.getID();
				employeeStr[1] = employeeObj.getFirstname();
				employeeStr[2] = employeeObj.getLastname();
				employeesStr.add(employeeStr);
			}
		} catch (HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
		return employeesStr;
	}
	
	public synchronized String getIdOfQueue(String queueName){
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
}
