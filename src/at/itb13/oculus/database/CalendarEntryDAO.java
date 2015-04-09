/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.CalendarEntry;

/**
 * @author Manu
 *
 */
class CalendarEntryDAO extends GenericDAOImpl<CalendarEntry, String> {

	public CalendarEntryDAO(Session session) {
		super(CalendarEntry.class, session);
	}
}