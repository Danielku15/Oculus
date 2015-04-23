package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Drug;

/**
 * 
 * DAO (Data Access Object) of all {@link Drug}
 * @category DAO
 *
 */
class DrugDAO extends GenericDAOImpl<Drug, String> {

	public DrugDAO(Session session) {
		super(Drug.class, session);
	}
}
