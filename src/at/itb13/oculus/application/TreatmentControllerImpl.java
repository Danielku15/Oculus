package at.itb13.oculus.application;

import org.hibernate.HibernateException;

import at.itb13.oculus.model.Appointment;
import at.itb13.oculus.model.Patient;
/**
 * 
 * The TreatmentController operates with {@link Appointment}
 *
 */
public class TreatmentControllerImpl extends Controller implements TreatmentController{

	private Appointment _appointment;
	
	public TreatmentControllerImpl(){
		createAppointment();
	}
	
	@Override
	public String getPatientFirstname() {
		Patient patient = _appointment.getPatient();
		if(patient != null) {
			return patient.getFirstname();
		}
		return null;
	}

	@Override
	public String getPatientLastname() {
		Patient patient = _appointment.getPatient();
		if(patient != null) {
			return patient.getLastname();
		}
		return null;
	}

	@Override
	public String getPatientSocialSecurityNumber() {
		Patient patient = _appointment.getPatient();
		if(patient != null) {
			return patient.getSocialSecurityNumber();
		}
		return null;
	}
	
	/**
	 * update open {@link TreatmentController}
	 * @see at.itb13.oculus.application.TreatmentController#activate()
	 */
	@Override
	public void activate() {
		MainControllerImpl.getInstance().setTreatmentController(this);
	}
	
	/**
	 * creates a new {@link Appointment} objekt and saves it in the {@link TreatmentControllerImpl#_appointment} variable
	 * @see at.itb13.oculus.application.TreatmentController#createAppointment()
	 */
	@Override
	public void createAppointment() {
		_appointment = new Appointment();
	}
	
	/**
	 * loads {@link Appointment} from database depending on the appointment id
	 * @see at.itb13.oculus.application.TreatmentController#loadAppointment(java.lang.String)
	 * @param appointmentId id of selected appointment
	 */
	@Override
	public synchronized void loadAppointment(String appointmentId) throws ObjectNotFoundException {
		Appointment appointment = null;
		try {
			_database.beginTransaction();
			appointment = _database.get(Appointment.class, appointmentId);
			_database.commitTransaction();
			if(appointment != null) {
				_appointment = appointment;
			} else {
				throw new ObjectNotFoundException(Appointment.class, appointmentId);
			}
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
	}

	/**
	 * saves the {@link TreatmentControllerImpl#_appointment} in the database
	 * @see at.itb13.oculus.application.TreatmentController#saveAppointment()
	 * {@code not implemented yet}
	 */
	@Override
	public void saveAppointment() throws IncompleteDataException, ObjectNotSavedException {
		// TODO method for saving the appointment into the database 
	}

	/**
	 * validates the data of {@link TreatmentControllerImpl#_appointment}
	 * @see at.itb13.oculus.application.TreatmentController#saveAppointment()
	 * @return {@link Boolean} valid or invalid
	 * {@code not implemented yet}
	 */
	@Override
	public boolean validateData() throws IncompleteDataException {
		// TODO validates the data of the appointment 
		return true;
	}
}
