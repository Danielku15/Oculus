/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 03.04.2015
 */
package at.itb13.oculus.database;

import org.hibernate.Session;
import at.itb13.oculus.model.Employee;

/**
 * @author Manu
 *
 */
class EmployeeDAO extends GenericDAOImpl<Employee, String> {

	public EmployeeDAO(Session session) {
		super(Employee.class, session);
	}
}
