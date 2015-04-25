package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.UserRight;
/**
 * 
 * DAO (Data Access Object) of all {@link UserRight}
 * @category DAO
 *
 */
public class UserRightDAO extends GenericDAOImpl<UserRight, String> {

	public UserRightDAO(Session session) {
		super(UserRight.class, session);
	}
}
