package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Drug;

class DrugDAO extends GenericDAOImpl<Drug, String> {

	public DrugDAO(Session session) {
		super(Drug.class, session);
	}
}
