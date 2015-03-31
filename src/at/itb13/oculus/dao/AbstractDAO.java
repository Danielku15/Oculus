/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 31.03.2015
 */
package at.itb13.oculus.dao;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Query;
import org.hibernate.Session;

import at.itb13.oculus.data.HibernateUtil;

/**
 * @author Manu
 *
 */
public abstract class AbstractDAO<T> {
	
	private String _tableName;
	
	public AbstractDAO(String tableName) {
		_tableName = tableName;
	}
	
	public List<T> getAll() {
		Session s = HibernateUtil.getSession();
		Query q = s.createQuery("FROM " + _tableName);
		return q.list();
	}
	
	public void add(T model) {
		Session s = HibernateUtil.getSession();
		s.beginTransaction();
		s.save(model);
		s.getTransaction().commit();
		
	}
	
	public void remove(T model) {
		Session s = HibernateUtil.getSession();
		s.beginTransaction();
		s.delete(model);
		s.getTransaction().commit();
	}
	
	public void update(T model) {
		Session s = HibernateUtil.getSession();
		s.beginTransaction();
		s.update(model);
		s.getTransaction().commit();
	}
	
	public void saveOrUpdate(T model) {
		Session s = HibernateUtil.getSession();
		s.beginTransaction();
		s.saveOrUpdate(model);
		s.getTransaction().commit();
	}
	
	protected String getTableName() {
		return _tableName;
	}
	
}
