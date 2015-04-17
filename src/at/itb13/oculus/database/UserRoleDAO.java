/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.UserRole;

/**
 * @author Manu
 *
 */
class UserRoleDAO extends GenericDAOImpl<UserRole, String> {

	public UserRoleDAO(Session session) {
		super(UserRole.class, session);
	}
}
