package at.itb13.oculus.database;

import org.hibernate.Session;

import at.itb13.oculus.model.Employee;

/**
 * 
 * DAO (Data Access Object) of all {@link Employee}
 * @category DAO
 *
 */
class EmployeeDAO extends GenericDAOImpl<Employee, String> {

	public EmployeeDAO(Session session) {
		super(Employee.class, session);
	}
}
