/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.EyePrescription;

/**
 * @author Manu
 *
 */
class EyePrescriptionDAO extends GenericDAOImpl<EyePrescription, String> {

	public EyePrescriptionDAO(Session session) {
		super(EyePrescription.class, session);
	}
}
