package at.itb13.oculus.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import at.itb13.oculus.model.Appointment;

/**
 * 
 * DAO (Data Access Object) of all {@link Appointment}
 * @category DAO
 *
 */
class AppointmentDAO extends GenericDAOImpl<Appointment, String> {

	public AppointmentDAO(Session session) {
		super(Appointment.class, session);
	}
	
	/**
	 * @param patientId criteria appointments of patient with this id
	 * @param lowerBound criteria not older than today
	 * @return {@link List} of {@link Appointment}
	 */
	public List<Appointment> getByPatientId(String patientId, Date lowerBound) {
		List<Appointment> list = new ArrayList<Appointment>();
		if(lowerBound == null) {
			list = getByCriterion("start", true, Restrictions.eq("patient.id", patientId));
		} else {
			list = getByCriterion("start", true, Restrictions.and(Restrictions.eq("patient.id", patientId), Restrictions.ge("start", lowerBound)));
		}
		return list;
	}
	
	/**
	 * @see at.itb13.oculus.database.GenericDAOImpl#search(java.lang.String)
	 * search of {@link Appointment} depending on parameter
	 * @param criteria for the search
	 * @return {@link List} of search results
	 */
	@Override
	public List<Appointment> search(String criteria) {		
		return search(criteria,
				"title",
				"description",
				//"start",
				"patient.firstname",
				"patient.lastname",
				"patient.address.street",
				"patient.address.streetNumber",
				"patient.address.zip",
				"patient.address.city",
				"patient.address.country");
	}
}
