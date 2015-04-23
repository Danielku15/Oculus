/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 22.04.2015
 */
package at.itb13.oculus.application;

import org.hibernate.HibernateException;

import at.itb13.oculus.model.Appointment;
import at.itb13.oculus.model.Patient;

/**
 * @author Manu
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
	
	@Override
	public void activate() {
		// TODO
		//MainController.getInstance().setPatientController(this);
	}
	
	@Override
	public void createAppointment() {
		_appointment = new Appointment();
	}
	
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

	@Override
	public void saveAppointment() throws IncompleteDataException, ObjectNotSavedException {
		// TODO
	}

	@Override
	public boolean validateData() throws IncompleteDataException {
		// TODO
		return true;
	}
}
