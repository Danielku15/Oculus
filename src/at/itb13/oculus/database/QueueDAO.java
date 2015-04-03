/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.Queue;

/**
 * @author Manu
 *
 */
class QueueDAO extends GenericDAOImpl<Queue, String>{


	public QueueDAO(Session session) {
		super(Queue.class, session);

	}

}
