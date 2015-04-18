package at.itb13.oculus.database;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;

import at.itb13.oculus.model.Anamnesis;
import at.itb13.oculus.model.Appointment;
import at.itb13.oculus.model.CalendarEntry;
import at.itb13.oculus.model.ChangeLog;
import at.itb13.oculus.model.Diagnosis;
import at.itb13.oculus.model.Doctor;
import at.itb13.oculus.model.Drug;
import at.itb13.oculus.model.Employee;
import at.itb13.oculus.model.EyePrescription;
import at.itb13.oculus.model.Insurance;
import at.itb13.oculus.model.Measurement;
import at.itb13.oculus.model.Orthoptist;
import at.itb13.oculus.model.Patient;
import at.itb13.oculus.model.Prescription;
import at.itb13.oculus.model.PrescriptionEntry;
import at.itb13.oculus.model.Queue;
import at.itb13.oculus.model.QueueEntry;
import at.itb13.oculus.model.Receptionist;
import at.itb13.oculus.model.Referral;
import at.itb13.oculus.model.SearchMap;
import at.itb13.oculus.model.SearchResult;
import at.itb13.oculus.model.Searchable;
import at.itb13.oculus.model.SickNote;
import at.itb13.oculus.model.User;
import at.itb13.oculus.model.UserRight;
import at.itb13.oculus.model.UserRole;

public class DBFacade implements AutoCloseable {
	
	private Session _session;
	private FullTextSession _fullTextSession;
	@SuppressWarnings("rawtypes")
	private Map<Class<? extends PersistentObject>, GenericDAO> _daoMap;

	private GenericDAO<Anamnesis, String> _anamnesisDAO;
	private GenericDAO<Appointment, String> _appointmentDAO;
	private GenericDAO<CalendarEntry, String> _calendarEntryDAO;
	private GenericDAO<ChangeLog, String> _changeLogDAO;
	private GenericDAO<Diagnosis, String> _diagnosisDAO;
	private GenericDAO<Doctor, String> _doctorDAO;
	private GenericDAO<Drug, String> _drugDAO;
	private GenericDAO<Employee, String> _employeeDAO;
	private GenericDAO<EyePrescription, String> _eyePrescriptionDAO;
	private GenericDAO<Insurance, String> _insuranceDAO;
	private GenericDAO<Measurement, String> _measurementDAO;
	private GenericDAO<Orthoptist, String> _orthoptistDAO;
	private GenericDAO<Patient, String> _patientDAO;
	private GenericDAO<Prescription, String> _prescriptionDAO;
	private GenericDAO<PrescriptionEntry, String> _prescriptionEntryDAO;
	private GenericDAO<Queue, String> _queueDAO;
	private GenericDAO<QueueEntry, String> _queueEntryDAO;
	private GenericDAO<Receptionist, String> _receptionistDAO;
	private GenericDAO<Referral, String> _referralDAO;
	private GenericDAO<SickNote, String> _sickNoteDAO;
	private GenericDAO<User, String> _userDAO;
	private GenericDAO<UserRight, String> _userRightDAO;
	private GenericDAO<UserRole, String> _userRoleDAO;
	
	@SuppressWarnings("rawtypes")
	public DBFacade() {
		// open new session
		_session = HibernateUtil.openSession();
		_fullTextSession = Search.getFullTextSession(_session);
		
		// initialize DAO objects
		_anamnesisDAO = new AnamnesisDAO(_session);
		_appointmentDAO = new AppointmentDAO(_session);
		_calendarEntryDAO = new CalendarEntryDAO(_session);
		_changeLogDAO = new ChangeLogDAO(_session);
		_diagnosisDAO = new DiagnosisDAO(_session);
		_doctorDAO = new DoctorDAO(_session);
		_drugDAO = new DrugDAO(_session);
		_employeeDAO = new EmployeeDAO(_session);
		_eyePrescriptionDAO = new EyePrescriptionDAO(_session);
		_insuranceDAO = new InsuranceDAO(_session);
		_measurementDAO = new MeasurementDAO(_session);
		_orthoptistDAO = new OrthoptistDAO(_session);
		_patientDAO = new PatientDAO(_session);
		_prescriptionDAO = new PrescriptionDAO(_session);
		_prescriptionEntryDAO = new PrescriptionEntryDAO(_session);
		_queueDAO = new QueueDAO(_session);
		_queueEntryDAO = new QueueEntryDAO(_session);
		_receptionistDAO = new ReceptionistDAO(_session);
		_referralDAO = new ReferralDAO(_session);
		_sickNoteDAO = new SickNoteDAO(_session);
		_userDAO = new UserDAO(_session);
		_userRightDAO = new UserRightDAO(_session);
		_userRoleDAO = new UserRoleDAO(_session);
		
		// add DAO objects to hash map
		_daoMap = new HashMap<Class<? extends PersistentObject>, GenericDAO>();
		_daoMap.put(Anamnesis.class, _anamnesisDAO);
		_daoMap.put(Appointment.class, _appointmentDAO);
		_daoMap.put(CalendarEntry.class, _calendarEntryDAO);
		_daoMap.put(ChangeLog.class, _changeLogDAO);
		_daoMap.put(Diagnosis.class, _diagnosisDAO);
		_daoMap.put(Doctor.class, _doctorDAO);
		_daoMap.put(Drug.class, _drugDAO);
		_daoMap.put(Employee.class, _employeeDAO);
		_daoMap.put(EyePrescription.class, _eyePrescriptionDAO);
		_daoMap.put(Insurance.class, _insuranceDAO);
		_daoMap.put(Measurement.class, _measurementDAO);
		_daoMap.put(Orthoptist.class, _orthoptistDAO);
		_daoMap.put(Patient.class, _patientDAO);
		_daoMap.put(Prescription.class, _prescriptionDAO);
		_daoMap.put(PrescriptionEntry.class, _prescriptionEntryDAO);
		_daoMap.put(Queue.class, _queueDAO);
		_daoMap.put(QueueEntry.class, _queueEntryDAO);
		_daoMap.put(Receptionist.class, _receptionistDAO);
		_daoMap.put(Referral.class, _referralDAO);
		_daoMap.put(SickNote.class, _sickNoteDAO);
		_daoMap.put(User.class, _userDAO);
		_daoMap.put(UserRight.class, _userRightDAO);
		_daoMap.put(UserRole.class, _userRoleDAO);
	}
	
	public void beginTransaction() {
		_session.beginTransaction();
	}
	
	public void commitTransaction() {
		_session.getTransaction().commit();
	}
	
	public void rollbackTransaction() {
		_session.getTransaction().rollback();
	}
	
	public void beginTransactionFulltext() {
		_fullTextSession.beginTransaction();
	}
	
	public void commitTransactionFulltext() {
		_fullTextSession.getTransaction().commit();
	}
	
	public void rollbackTransactionFulltext() {
		_fullTextSession.getTransaction().rollback();
	}
	
	public <T extends PersistentObject> void index(T object) {
		_fullTextSession.index(object);
	}
	
	public void indexAll() throws InterruptedException {
		_fullTextSession.createIndexer().startAndWait();
	}
	
	@SuppressWarnings("unchecked")
	public <T extends PersistentObject> T get(Class<T> type, String id) {		
		return (T) _daoMap.get(type).get(id);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends PersistentObject> List<T> getAll(Class<T> type) {
		return (List<T>) _daoMap.get(type).getAll();
	}

	@SuppressWarnings("unchecked")
	public <T extends PersistentObject> String create(T object) {
		return (String) _daoMap.get(object.getClass()).create(object);
	}

	@SuppressWarnings("unchecked")
	public <T extends PersistentObject> void createOrUpdate(T object) {
		_daoMap.get(object.getClass()).createOrUpdate(object);		
	}
	
	@SuppressWarnings("unchecked")
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
	
	@SuppressWarnings("unchecked")
	public <T extends PersistentObject & Searchable> SearchResult<T> search(Class<T> type, SearchMap<T> searchMap, String criteria) {	
		List<T> result = (List<T>) _daoMap.get(type).search(criteria);
		return new SearchResult<T>(searchMap, result);
	}
	
	public List<ChangeLog> getChangeLogsGreaterThan(int number, int maxResults) {
		return ((ChangeLogDAO) _changeLogDAO).getGreaterThan(number, maxResults);
	}
	
	public List<Patient> getSearchedPatient(String name){
		return ((PatientDAO) _patientDAO).getSearchedPatient(name);
	}
	
	public Patient getPatientBySocialSecurityNumber(String socialSecurityNumber){
		return ((PatientDAO) _patientDAO).getBySocialSecurityNumber(socialSecurityNumber);
	}
	
	public List<QueueEntry> getQueueEntriesByQueueId(String queueId, Date lowerBound) {
		return ((QueueEntryDAO) _queueEntryDAO).getByQueueId(queueId, lowerBound);
	}
	
	public List<Appointment> getAppointmentsByPatientId(String patientId, Date lowerBound) {
		return ((AppointmentDAO) _appointmentDAO).getByPatientId(patientId, lowerBound);
	}
		
	@Override
	public void close() {
		_session.close();
	}
}
