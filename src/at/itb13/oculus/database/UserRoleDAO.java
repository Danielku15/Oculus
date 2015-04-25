package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.UserRole;
/**
 * 
 * DAO (Data Access Object) of all {@link UserRole}
 * @category DAO
 *
 */
class UserRoleDAO extends GenericDAOImpl<UserRole, String> {

	public UserRoleDAO(Session session) {
		super(UserRole.class, session);
	}
}
