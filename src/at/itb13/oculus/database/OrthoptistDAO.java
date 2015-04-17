/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.Orthoptist;

/**
 * @author Manu
 *
 */
class OrthoptistDAO extends GenericDAOImpl<Orthoptist, String> {

	public OrthoptistDAO(Session session) {
		super(Orthoptist.class, session);
	}
}
