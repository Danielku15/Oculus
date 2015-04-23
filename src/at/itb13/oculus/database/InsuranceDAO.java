package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Insurance;

/**
 * 
 * DAO (Data Access Object) of all {@link Insurance}
 * @category DAO
 *
 */
class InsuranceDAO extends GenericDAOImpl<Insurance, String> {

	public InsuranceDAO(Session session) {
		super(Insurance.class, session);
	}
}
