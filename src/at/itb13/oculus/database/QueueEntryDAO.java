/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import at.itb13.oculus.model.QueueEntry;

/**
 * @author Manu
 *
 */
class QueueEntryDAO extends GenericDAOImpl<QueueEntry, String> {

	public QueueEntryDAO(Session session) {
		super(QueueEntry.class, session);
	}
	
	public List<QueueEntry> getByQueueId(String queueId) {
		return getByCriterion("created", true, Restrictions.eq("queue.id", queueId));
	}
}
