/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import at.itb13.oculus.model.ChangeLog;

/**
 * @author Patrick
 *
 */
class ChangeLogDAO extends GenericDAOImpl<ChangeLog, String> {

	public ChangeLogDAO(Session session) {
		super(ChangeLog.class, session);
	}
	
	public List<ChangeLog> getGreaterThan(int number, int maxResults) {
		return getByCriterion("number", true, maxResults, Restrictions.gt("number", number));
	}
}
