package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.User;
/**
 * 
 * DAO (Data Access Object) of all {@link User}
 * @category DAO
 *
 */
class UserDAO extends GenericDAOImpl<User, String>{

	public UserDAO(Session session) {
		super(User.class, session);
	}
}
