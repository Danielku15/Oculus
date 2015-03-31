/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 31.03.2015
 */
package at.itb13.oculus.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import at.itb13.oculus.data.HibernateUtil;
import at.itb13.oculus.model.Patient;

/**
 * @author Manu
 *
 */
public class PatientDAO extends AbstractDAO<Patient> {

	/**
	 * @param tableName 
	 */
	public PatientDAO() {
		super("at.itb13.oculus.model.Patient");
	}	
	
	public List<Patient> getByName(String name) {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("FROM " + getTableName() + " WHERE _firstName LIKE :firstName OR _lastName LIKE :lastName");
		q.setString("firstName", name);
		q.setString("lastName", name);
		
		return q.list();
	}
	
}
