package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Doctor;
/**
 * 
 * DAO (Data Access Object) of all {@link Doctor}
 * @category DAO
 *
 */
class DoctorDAO extends GenericDAOImpl<Doctor, String> {

	public DoctorDAO(Session session) {
		super(Doctor.class, session);
	}
}
