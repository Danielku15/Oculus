package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.CalendarEntry;
/**
 * 
 * DAO (Data Access Object) of all {@link CalendarEntry}
 * @category DAO
 *
 */
class CalendarEntryDAO extends GenericDAOImpl<CalendarEntry, String> {

	public CalendarEntryDAO(Session session) {
		super(CalendarEntry.class, session);
	}
}
