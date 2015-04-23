package at.itb13.oculus.database;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Property;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

/**
 * generic DAO (Data Access Object) superclass for all DAO classes
 * implements general functions for all DAO classes
 * @param <T> type of persistent object
 * @param <PK> type of primary key
 * @category DAO
 */
abstract class GenericDAOImpl<T extends PersistentObject, PK extends Serializable> implements GenericDAO<T, PK> {

	private Class<T> _type;
	private Session _session;
	
	public GenericDAOImpl(Class<T> type, Session session) {
		_type = type;
		_session = session;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PK create(T object) {
		return (PK) _session.save(object);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T get(PK id) {
		return (T) _session.get(_type, id);
	}
	
	@Override
	public List<T> getByCriterion(Criterion... criterions) {
		return getByCriterion(null, null, null, criterions);
	}
	
	@Override
	public List<T> getByCriterion(String orderBy, Boolean asc, Criterion... criterions) {
        return getByCriterion(orderBy, asc, null, criterions);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByCriterion(String orderBy, Boolean asc, Integer maxResults, Criterion... criterions) {
		Criteria crit = _session.createCriteria(_type);
		for(Criterion criterion : criterions) {
			crit.add(criterion);
		}
		if((orderBy != null) && (orderBy.length()) > 0 && (asc != null)) {
			Property prop = Property.forName(orderBy);
			if(asc) {
				crit.addOrder(prop.asc());
			} else {
				crit.addOrder(prop.desc());
			}
		}
		if((maxResults != null) && (maxResults > 0)) {
			crit.setMaxResults(maxResults);
		}
        return crit.list();
	}

	/**
	 * search of object depending on parameter
	 * @param criteria for the search
	 * @param fields for search
	 * @return {@link List} of search results
	 */
	@SuppressWarnings("unchecked")
	public List<T> search(String criteria, String... fields) {
		FullTextSession fullTextSession = Search.getFullTextSession(_session);
		QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(_type).get();
		org.apache.lucene.search.Query luceneQuery = null;
		if(criteria.trim().isEmpty()) {
			luceneQuery = queryBuilder.all().createQuery();
		} else {
			luceneQuery = queryBuilder.keyword().onFields(fields).matching(criteria).createQuery();
		}
		org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, _type);
		return (List<T>) fullTextQuery.list();
	}

	/**
	 * search of object depending on parameter
	 * @param criteria for the search
	 * @return {@link List} of search results
	 */
	public List<T> search(String criteria) {
		throw new UnsupportedOperationException("Method has to be implemented in subclass!");
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
        return _session.createCriteria(_type).list();
	}
	
	/**
	 * @see at.itb13.oculus.database.GenericDAO#createOrUpdate(at.itb13.oculus.database.PersistentObject)
	 * creates or updates object by hibernate into database
	 */
	@Override
	public void createOrUpdate(T object) {
		// TODO: prüfen ob saveOrUpdate für alle Fälle geeignet ist!
		_session.saveOrUpdate(object);
	}

	/**
	 * @see at.itb13.oculus.database.GenericDAO#update(at.itb13.oculus.database.PersistentObject)
	 * updates object by hibernate into database
	 */
	@Override
	public void update(T object) {
		_session.update(object);
	}

	/**
	 * @see at.itb13.oculus.database.GenericDAO#delete(at.itb13.oculus.database.PersistentObject)
	 * deletes object by hibernate from database
	 */
	@Override
	public void delete(T object) {
		_session.delete(object);
	}
}
