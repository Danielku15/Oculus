package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Receptionist;

/**
 * 
 * DAO (Data Access Object) of all {@link Receptionist}
 * @category DAO
 *
 */
class ReceptionistDAO extends GenericDAOImpl<Receptionist, String> {

	public ReceptionistDAO(Session session) {
		super(Receptionist.class, session);
	}
}
