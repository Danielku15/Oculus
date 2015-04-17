/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.Insurance;

/**
 * @author Manu
 *
 */
class InsuranceDAO extends GenericDAOImpl<Insurance, String> {

	public InsuranceDAO(Session session) {
		super(Insurance.class, session);
	}
}
