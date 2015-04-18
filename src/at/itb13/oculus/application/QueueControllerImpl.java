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

}
