package at.itb13.oculus.application;

import org.hibernate.HibernateException;

import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;
import at.itb13.oculus.model.Patient;

public class PatientViewControllerImpl extends Controller implements PatientViewController {
	
	@Override
	public String createPatient(Patient patient) throws RegExpException, UniqueConstraintException{
		//Regular Expression
		String name = "^[A-Za-z -]*$";
		String svn = "^[0-9]{10}$";
		LangFacade langFacade = LangFacade.getInstance();
		
		if(!patient.getFirstname().matches(name)){
			throw new RegExpException(langFacade.getString(LangKey.FIRSTNAME), patient.getFirstname());
		}
		if(!patient.getLastname().matches(name)){
			throw new RegExpException(langFacade.getString(LangKey.LASTNAME), patient.getLastname());
		}
		if(!patient.getSocialSecurityNumber().matches(svn)){
			throw new RegExpException(langFacade.getString(LangKey.SOCIALSECURITYNUMBER), patient.getSocialSecurityNumber());
		}else{
			Patient tmpPatient = _database.getbySocialSecurityNumber(patient.getSocialSecurityNumber());
			if( tmpPatient != null){
				throw new UniqueConstraintException(langFacade.getString(LangKey.SOCIALSECURITYNUMBER), patient, tmpPatient);
			}
		}
		//Transaction
		String id;
		try {
			_database.beginTransaction();
			id = _database.create(patient);
			_database.commitTransaction();
		} catch(HibernateException e){
			_database.rollbackTransaction();
			throw e;
		}
		return id;
	}

	@Override
	public void updatePatient(Patient patient) throws RegExpException, UniqueConstraintException {
		//Regular Expression 
		String name = "^[A-Za-z -]*$";
		String svn = "^[0-9]{10}$";
		LangFacade langFacade = LangFacade.getInstance();
		
		if(!patient.getFirstname().matches(name)){
			throw new RegExpException(langFacade.getString(LangKey.FIRSTNAME), patient.getFirstname());
		}
		if(!patient.getLastname().matches(name)){
			throw new RegExpException(langFacade.getString(LangKey.LASTNAME), patient.getLastname());
		}
		if(!patient.getSocialSecurityNumber().matches(svn)){
			throw new RegExpException(langFacade.getString(LangKey.SOCIALSECURITYNUMBER), patient.getSocialSecurityNumber());
		}else{
			Patient tmpPatient = _database.getbySocialSecurityNumber(patient.getSocialSecurityNumber());
			if( tmpPatient != patient || tmpPatient != null){
				throw new UniqueConstraintException(langFacade.getString(LangKey.SOCIALSECURITYNUMBER), patient, tmpPatient);
			}
		}
		//Transaction
		try{
			_database.beginTransaction();
			_database.update(patient);
			_database.commitTransaction();
		} catch(HibernateException e){
			_database.rollbackTransaction();
			throw e;
		}
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
