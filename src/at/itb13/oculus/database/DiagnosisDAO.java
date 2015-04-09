/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.Diagnosis;

/**
 * @author Manu
 *
 */
class DiagnosisDAO extends GenericDAOImpl<Diagnosis, String> {

	public DiagnosisDAO(Session session) {
		super(Diagnosis.class, session);
	}
}