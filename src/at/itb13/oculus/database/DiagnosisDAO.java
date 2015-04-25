package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Diagnosis;
/**
 * 
 * DAO (Data Access Object) of all {@link Diagnosis}
 * @category DAO
 *
 */
class DiagnosisDAO extends GenericDAOImpl<Diagnosis, String> {

	public DiagnosisDAO(Session session) {
		super(Diagnosis.class, session);
	}
}
