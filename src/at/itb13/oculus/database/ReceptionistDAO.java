/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.Receptionist;

/**
 * @author Manu
 *
 */
class ReceptionistDAO extends GenericDAOImpl<Receptionist, String> {

	public ReceptionistDAO(Session session) {
		super(Receptionist.class, session);
	}
}
