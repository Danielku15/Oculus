package at.itb13.oculus.application;

import org.hibernate.HibernateException;

import at.itb13.oculus.model.Address;
import at.itb13.oculus.model.Gender;
import at.itb13.oculus.model.Patient;

public abstract class PatientController extends Controller implements IPatientController{
	
	@Override
	public boolean createNewPatient(String firstname, String lastname, String birthday, Gender gender,
			String phoneNumber, String email, Address address, String socialSecurityNumber, String employer) {
		boolean flag = false;

		Patient newPatient = new Patient();
		newPatient.setFirstname(firstname);
		newPatient.setLastname(lastname);
		newPatient.setBirthday(birthday);
		newPatient.setGender(gender);
		newPatient.setPhoneNumber(phoneNumber);
		newPatient.setEmail(email);
		newPatient.setAddress(address);
		newPatient.setSocialSecurityNumber(socialSecurityNumber);
		newPatient.setEmployer(employer);
		try{
			_database.create(newPatient);
			_database.commitTransaction();
			flag = true;
		} catch(HibernateException ex){
			_database.rollbackTransaction();
		}
		return flag;
	}

}
