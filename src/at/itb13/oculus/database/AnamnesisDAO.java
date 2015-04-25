package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Anamnesis;
/**
 * 
 * DAO (Data Access Object) of all {@link Anamnesis}
 * @category DAO
 *
 */
class AnamnesisDAO extends GenericDAOImpl<Anamnesis, String> {

	public AnamnesisDAO(Session session) {
		super(Anamnesis.class, session);
	}
}
