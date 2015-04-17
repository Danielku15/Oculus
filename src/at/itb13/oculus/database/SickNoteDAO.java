/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.SickNote;

/**
 * @author Manu
 *
 */
class SickNoteDAO extends GenericDAOImpl<SickNote, String> {

	public SickNoteDAO(Session session) {
		super(SickNote.class, session);
	}
}
