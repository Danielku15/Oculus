package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Orthoptist;

/**
 * 
 * DAO (Data Access Object) of all {@link Orthoptist}
 * @category DAO
 *
 */
class OrthoptistDAO extends GenericDAOImpl<Orthoptist, String> {

	public OrthoptistDAO(Session session) {
		super(Orthoptist.class, session);
	}
}
