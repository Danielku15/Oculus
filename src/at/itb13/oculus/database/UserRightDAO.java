/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.UserRight;

/**
 * @author Manu
 *
 */
public class UserRightDAO extends GenericDAOImpl<UserRight, String> {

	public UserRightDAO(Session session) {
		super(UserRight.class, session);
	}

}
