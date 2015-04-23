package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.EyePrescription;

/**
 * 
 * DAO (Data Access Object) of all {@link EyePrescription}
 * @category DAO
 *
 */
class EyePrescriptionDAO extends GenericDAOImpl<EyePrescription, String> {

	public EyePrescriptionDAO(Session session) {
		super(EyePrescription.class, session);
	}
}
