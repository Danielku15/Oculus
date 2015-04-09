package at.itb13.oculus.application;

import java.util.regex.PatternSyntaxException;

import org.hibernate.HibernateException;

import at.itb13.oculus.model.Patient;

public class PatientViewControllerImpl extends Controller implements PatientViewController {
	
	@Override
	public String createPatient(Patient patient) {
		//Regular Expression
		String name = "[-A-Za-z ]";
		String svn = "";
		try{
			name.matches(patient.getFirstname());
			name.matches(patient.getLastname());
			svn.matches(patient.getSocialSecurityNumber());
		}catch(PatternSyntaxException e){
			throw e;
		}
		//Transaction
		String id;
		/*try {
			_database.beginTransaction();
			id = _database.create(patient);
			_database.commitTransaction();
		} catch(HibernateException e){
			_database.rollbackTransaction();
			throw e;
		}
		return id;*/return "12";
	}

	@Override
	public void updatePatient(Patient patient) {
		//Regular Expression 
		String name = "[-A-Za-z ]";
		String svn = "";
		try{
			name.matches(patient.getFirstname());
			name.matches(patient.getLastname());
			svn.matches(patient.getSocialSecurityNumber());
		}catch(PatternSyntaxException e){
			throw e;
		}
		//Transaction
		/*try{
			_database.beginTransaction();
			_database.update(patient);
			_database.commitTransaction();
		} catch(HibernateException e){
			_database.rollbackTransaction();
			throw e;
		}*/
	}

	@Override
	public Patient getPatient(String id) {
		Patient patientResult;
		try{
			_database.beginTransaction();
			patientResult = _database.get(Patient.class, id);
			_database.commitTransaction();
		} catch(HibernateException e){
			_database.rollbackTransaction();
			throw e;
		}
		return patientResult;
	}
}
