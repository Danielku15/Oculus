package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.SickNote;
/**
 * 
 * DAO (Data Access Object) of all {@link SickNote}
 * @category DAO
 *
 */
class SickNoteDAO extends GenericDAOImpl<SickNote, String> {

	public SickNoteDAO(Session session) {
		super(SickNote.class, session);
	}
}
