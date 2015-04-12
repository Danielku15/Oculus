package at.itb13.oculus.database;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;

/**
 * @author Patrick
 *
 * @param <T> type of persistent object
 * @param <PK> type of primary key
 */
interface GenericDAO<T extends PersistentObject, PK extends Serializable> {
	
	PK create(T object);
	
	T get(PK id);
	
	List<T> getByCriterion(Criterion... criterions);
	
	List<T> getByCriterion(String orderBy, Boolean asc, Criterion... criterions);
	
	List<T> getByCriterion(String orderBy, Boolean asc, Integer maxResults, Criterion... criterions);
	
	List<T> search(String criteria, String... fields);
	
	List<T> search(String criteria);
	
	List<T> getAll();
	
	void createOrUpdate(T object);
	
	void update(T object);
	
	void delete(T object);
}
