/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import at.itb13.oculus.model.Patient;
import at.itb13.oculus.model.QueueEntry;

/**
 * @author Manu
 *
 */
class QueueEntryDAO extends GenericDAOImpl<QueueEntry, String> {

	public QueueEntryDAO(Session session) {
		super(QueueEntry.class, session);
	}
	
	public List<QueueEntry> getByQueueId(String queueId, Date lowerBound) {
		List<QueueEntry> list = null;
		if(lowerBound != null) {
			list = getByCriterion("created", true, Restrictions.eq("queue.id", queueId));
		} else {
			list = getByCriterion("created", true, Restrictions.and(Restrictions.eq("queue.id", queueId), Restrictions.ge("created", lowerBound)));
		}
		return list;
	}
	
	public QueueEntry getById(String id){
		List<QueueEntry> list = getByCriterion(Restrictions.like("ID", id));
		return (list.isEmpty()) ? null : list.get(0);
	}
}
