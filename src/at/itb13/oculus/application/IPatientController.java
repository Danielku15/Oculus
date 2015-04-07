package at.itb13.oculus.application;

import at.itb13.oculus.model.Address;
import at.itb13.oculus.model.Gender;

public interface IPatientController {
	
	public boolean createNewPatient(String firstname, String lastname, String birthday, Gender gender,
			String phoneNumber, String email, Address address, String socialSecurityNumber, String employer);
	
	

}
