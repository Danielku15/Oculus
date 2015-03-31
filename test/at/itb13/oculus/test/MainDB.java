/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 31.03.2015
 */
package at.itb13.oculus.test;

import org.hibernate.Query;
import org.hibernate.Session;

import at.itb13.oculus.data.HibernateUtil;
import at.itb13.oculus.model.Drug;

/**
 * @author Manu
 *
 */
public class MainDB {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session s = HibernateUtil.getSessionFactory().openSession();
		Query q = s.createQuery("FROM at.itb13.oculus.model.Drug");
		q.list();
		s.close();
	}

}
