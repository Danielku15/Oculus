package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Prescription;
/**
 * 
 * DAO (Data Access Object) of all {@link Prescription}
 * @category DAO
 *
 */
class PrescriptionDAO extends GenericDAOImpl<Prescription, String>{

	public PrescriptionDAO(Session session) {
		super(Prescription.class, session);
	}
}
