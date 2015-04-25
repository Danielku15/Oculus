package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.PrescriptionEntry;
/**
 * 
 * DAO (Data Access Object) of all {@link PrescriptionEntry}
 * @category DAO
 *
 */
class PrescriptionEntryDAO extends GenericDAOImpl<PrescriptionEntry, String>{

	public PrescriptionEntryDAO(Session session) {
		super(PrescriptionEntry.class, session);
	}
}
