/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.PrescriptionEntry;

/**
 * @author Manu
 *
 */
class PrescriptionEntryDAO extends GenericDAOImpl<PrescriptionEntry, String>{

	public PrescriptionEntryDAO(Session session) {
		super(PrescriptionEntry.class, session);
	}
}
