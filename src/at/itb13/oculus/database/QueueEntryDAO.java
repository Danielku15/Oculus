/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.QueueEntry;

/**
 * @author Manu
 *
 */
class QueueEntryDAO extends GenericDAOImpl<QueueEntry, String> {


	public QueueEntryDAO(Session session) {
		super(QueueEntry.class, session);

	}

}
