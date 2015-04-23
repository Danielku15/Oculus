package at.itb13.oculus.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import at.itb13.oculus.model.Patient;

/**
 * 
 * DAO (Data Access Object) of all {@link Patient}
 * @category DAO
 *
 */
class PatientDAO extends GenericDAOImpl<Patient, String> {

	public PatientDAO(Session session) {
		super(Patient.class, session);
	}	
	
	/**
	 * Returns a list of patients where first name OR last name LIKE name.
	 * @param name of patients to search for
	 * @return list of patients
	 */
	public List<Patient> getByName(String name) {
		return getByCriterion(Restrictions.or(Restrictions.like("firstname", name), Restrictions.like("lastname", name)));
	}
	
	/**
	 * @see at.itb13.oculus.database.GenericDAOImpl#search(java.lang.String)
	 * search of {@link Patient} depending on parameter
	 * @param criteria for the search
	 * @return {@link List} of search results
	 */
	@Override
	public List<Patient> search(String criteria) {		
		return search(criteria,
				"firstname",
				"lastname",
				"address.street",
				"address.streetNumber",
				"address.zip",
				"address.city",
				"address.country");
	}

	public List<Patient> getSearchedPatient(String name) {
		List<Patient> list = getByCriterion(Restrictions.or(Restrictions.like("firstname", name), Restrictions.like("lastname", name), Restrictions.like("socialSecurityNumber", name)));
		return list;
//				return getByCriterion(Restrictions.or(Restrictions.like("firstname", name), Restrictions.like("lastname", name), Restrictions.like("socialSecurityNumber", name)));
	}
	
	public Patient getBySocialSecurityNumber(String socialSecurityNumber) {
		List<Patient> list = getByCriterion(Restrictions.like("socialSecurityNumber", socialSecurityNumber));
		return (list.isEmpty()) ? null : list.get(0);
	}
}
