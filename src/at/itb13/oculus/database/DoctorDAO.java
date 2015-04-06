/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.Doctor;

/**
 * @author Manu
 *
 */
class DoctorDAO extends GenericDAOImpl<Doctor, String> {

	public DoctorDAO(Session session) {
		super(Doctor.class, session);
	}
}
