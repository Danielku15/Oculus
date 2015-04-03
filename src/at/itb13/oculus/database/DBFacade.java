package at.itb13.oculus.database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import at.itb13.oculus.model.Anamnesis;
import at.itb13.oculus.model.Drug;
import at.itb13.oculus.model.Patient;

public class DBFacade implements AutoCloseable {
	
	private Session _session;
	@SuppressWarnings("rawtypes")
	private GenericDAO _anamnesisDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _patientDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _drugDAO;
	
	private Map<Class<?>, GenericDAO<PersistentObject, Serializable>> _daoMap;
	
	@SuppressWarnings("unchecked")
	public DBFacade() {
		_session = HibernateUtil.openSession();
		_anamnesisDAO = new AnamnesisDAO(_session);
		_patientDAO = new PatientDAO(_session);
		_drugDAO = new DrugDAO(_session);
		
		_daoMap = new HashMap<Class<?>, GenericDAO<PersistentObject, Serializable>>();
		_daoMap.put(Anamnesis.class, _anamnesisDAO);
		_daoMap.put(Patient.class, _patientDAO);
		_daoMap.put(Drug.class, _drugDAO);
	}
	
	public void beginTransaction() {
		_session.beginTransaction();
	}
	
	public void commitTransaction() {
		_session.getTransaction().commit();
	}
	
	public <T extends PersistentObject> T get(Class<T> type, String id) {		
		return type.cast(_daoMap.get(type).get(id));
	}
	
	@SuppressWarnings("unchecked")
	public <T extends PersistentObject> List<T> getAll(Class<T> type) {
		return (List<T>) _daoMap.get(type).getAll();
	}

	public <T extends PersistentObject> String create(T object) {
		return (String) _daoMap.get(object.getClass()).create(object);
	}

	public <T extends PersistentObject> void createOrUpdate(T object) {
		_daoMap.get(object.getClass()).createOrUpdate(object);
		
	}
	
	public <T extends PersistentObject> void update(T object) {
		_daoMap.get(object.getClass()).update(object);
	}

	/**
	 * @see PatientDAO#getByName(String)
	 * @param name of patients to search for
	 * @return list of patients
	 */
	public List<Patient> getPatientsByName(String name) {
		return ((PatientDAO) _patientDAO).getByName(name);
	}
	
	@Override
	public void close() {
		_session.close();
	}
}
