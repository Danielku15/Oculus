package at.itb13.oculus.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import at.itb13.oculus.model.Queue;
/**
 * 
 * DAO (Data Access Object) of all {@link Queue}
 * @category DAO
 *
 */
class QueueDAO extends GenericDAOImpl<Queue, String>{

	public QueueDAO(Session session) {
		super(Queue.class, session);
	}
	
	public Queue getByName(String name) {
		List<Queue> list = getByCriterion(Restrictions.eq("name", name));
		return (list.isEmpty()) ? null : list.get(0);
	}
}
