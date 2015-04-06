package at.itb13.oculus.database;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

class GenericDAOImpl<T extends PersistentObject, PK extends Serializable> implements GenericDAO<T, PK> {

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
	@SuppressWarnings("unchecked")
	public List<T> getByCriterion(Criterion... criterions) {
		Criteria crit = _session.createCriteria(_type);
		for(Criterion criterion : criterions) {
			crit.add(criterion);
		}
        return crit.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
        return _session.createCriteria(_type).list();
	}
	
	@Override
	public void createOrUpdate(T object) {
		// TODO: prüfen ob saveOrUpdate für alle Fälle geeignet ist!
		_session.saveOrUpdate(object);
	}

	@Override
	public void update(T object) {
		_session.update(object);
	}

	@Override
	public void delete(T object) {
		_session.delete(object);
	}
}
