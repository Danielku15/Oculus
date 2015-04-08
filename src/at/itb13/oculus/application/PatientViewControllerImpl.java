package at.itb13.oculus.application;

import org.hibernate.HibernateException;

import at.itb13.oculus.model.Patient;

public class PatientViewControllerImpl extends Controller implements PatientViewController {
	
	@Override
	public Patient createPatient(Patient patient) {
		try {
			_database.beginTransaction();
			_database.create(patient);
			_database.commitTransaction();
		} catch(HibernateException e){
			_database.rollbackTransaction();
			throw e;
		}
		return patient;
	}

	@Override
	public Patient updatePatient(Patient patient) {
		return null;
	}

	@Override
	public Patient getPatient(String id) {
		return null;
	}
}
