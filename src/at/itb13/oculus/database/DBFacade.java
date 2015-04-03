package at.itb13.oculus.database;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import at.itb13.oculus.model.Anamnesis;
import at.itb13.oculus.model.Appointment;
import at.itb13.oculus.model.CalendarEntry;
import at.itb13.oculus.model.Diagnosis;
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
import at.itb13.oculus.model.SickNote;
import at.itb13.oculus.model.User;
import at.itb13.oculus.model.UserRight;
import at.itb13.oculus.model.UserRole;

public class DBFacade implements AutoCloseable {
	
	private Session _session;
	@SuppressWarnings("rawtypes")
	private GenericDAO _anamnesisDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _appointmentDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _calendarEntryDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _diagnosisDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _doctorDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _drugDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _employeeDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _eyePrescriptionDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _insuranceDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _measurementDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _orthoptistDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _patientDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _prescriptionDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _prescriptionEntryDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _queueDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _queueEntryDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _receptionistDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _referralDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _sickNoteDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _userDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _userRightDAO;
	@SuppressWarnings("rawtypes")
	private GenericDAO _userRoleDAO;
	
	private Map<Class<?>, GenericDAO<PersistentObject, Serializable>> _daoMap;
	
	@SuppressWarnings("unchecked")
	public DBFacade() {
		_session = HibernateUtil.openSession();
		_anamnesisDAO = new AnamnesisDAO(_session);
		_appointmentDAO = new AppointmentDAO(_session);
		_calendarEntryDAO = new CalendarEntryDAO(_session);
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
		
		
		_daoMap = new HashMap<Class<?>, GenericDAO<PersistentObject, Serializable>>();
		_daoMap.put(Anamnesis.class, _anamnesisDAO);
		_daoMap.put(Appointment.class, _appointmentDAO);
		_daoMap.put(CalendarEntry.class, _calendarEntryDAO);
		_daoMap.put(Diagnosis.class, _diagnosisDAO);
		_daoMap.put(Patient.class, _patientDAO);
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
