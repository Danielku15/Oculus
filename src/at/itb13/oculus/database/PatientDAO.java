/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 31.03.2015
 */
package at.itb13.oculus.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import at.itb13.oculus.model.Patient;

/**
 * @author Manu
 *
 */
class PatientDAO extends GenericDAOImpl<Patient, String> {

	public PatientDAO(Session session) {
		super(Patient.class, session);
	}	
	
	/**
	 * Returns a list of patients where firstname OR lastname LIKE name.
	 * @param name of patients to search for
	 * @return list of patients
	 */
	public List<Patient> getByName(String name) {
		return getByCriterion(Restrictions.or(Restrictions.like("firstname", name), Restrictions.like("lastname", name)));
	}
}
