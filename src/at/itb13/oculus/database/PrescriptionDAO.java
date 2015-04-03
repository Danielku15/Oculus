/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Prescription;

/**
 * @author Manu
 *
 */
class PrescriptionDAO extends GenericDAOImpl<Prescription, String>{

	public PrescriptionDAO(Session session) {
		super(Prescription.class, session);

	}

}
