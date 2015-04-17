package at.itb13.oculus.application;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import at.itb13.oculus.model.Queue;
import at.itb13.oculus.model.QueueEntry;

public class QueueControllerImpl extends Controller implements QueueController {

	public QueueControllerImpl(String queueId) {
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
	public List<String[]> getQueueEntries(String queueId) {
		List<String[]> queueEntriesStr = new ArrayList<String[]>();
		List<QueueEntry> queueEntriesObj = new ArrayList<QueueEntry>();

		try {
			_database.beginTransaction();
			queueEntriesObj = _database.getAll(QueueEntry.class);
			_database.commitTransaction();

			for (QueueEntry queueEntryObj : queueEntriesObj) {
				String[] queueEntryStr = new String[5];
				// queueEntryId,EmployeeId,Patient firstname, Patient lastname,
				// Appointment start
				queueEntryStr[0] = queueEntryObj.getID();
				queueEntryStr[1] = queueEntryObj.getAppointment().getEmployee().getID();
				queueEntryStr[2] = queueEntryObj.getAppointment().getPatient().getFirstname();
				queueEntryStr[3] = queueEntryObj.getAppointment().getPatient().getLastname();
				queueEntryStr[4] = queueEntryObj.getAppointment().getStart().toString();
				queueEntriesStr.add(queueEntryStr);
			}
		} catch (HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}

		return queueEntriesStr;
	}

}
