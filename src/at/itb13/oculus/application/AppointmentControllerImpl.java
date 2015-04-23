/**
 * Autor: Manu Ljubicic
 * Projekt: Oculus
 * Datum: 22.04.2015
 */
package at.itb13.oculus.application;

import org.hibernate.HibernateException;

import at.itb13.oculus.model.Patient;

/**
 * @author Manu
 *
 */
public class AppointmentControllerImpl extends Controller implements AppointmentController{

	private Patient _patient;
	
	public AppointmentControllerImpl(){
		createPatient();
	}

	private void createPatient() {
		_patient = new Patient();		
	}
	

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return _patient.getID();
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return _patient.getFirstname();
	}


	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return _patient.getLastname();
	}


	@Override
	public String getSocialSecurityNumber() {
		// TODO Auto-generated method stub
		return _patient.getSocialSecurityNumber();
	}
	
	@Override
	public synchronized void loadPatient(String patientId) throws ObjectNotFoundException {
		Patient patient = null;
		try {
			_database.beginTransaction();
			patient = _database.get(Patient.class, patientId);
			_database.commitTransaction();
			if(patient != null) {
				_patient = patient;
			} else {
				throw new ObjectNotFoundException(Patient.class, patientId);
			}
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
	}


	

}
