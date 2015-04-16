package at.itb13.oculus.application;

import java.time.LocalDate;
import java.util.Date;

public interface PatientController extends AutoCloseable {

	// getter
	String getId();
	String getFirstname();	
	String getLastname();
	Date getBirthday();
	String getGender();
	String getPhoneNumber();
	String getEmail();
	String getSocialSecurityNumber();
	String getEmployer();
	String getStreet();
	String getStreetNumber();
	String getZip();
	String getCity();
	String getCountry();
	
	// setter
	boolean setFirstname(String firstname);
	boolean setLastname(String lastname);
	boolean setBirthday(LocalDate birthday);
	boolean setGender(String gender);
	boolean setPhoneNumber(String phoneNumber);
	boolean setEmail(String email);
	boolean setSocialSecurityNumber(String socialSecurityNumber);
	boolean setEmployer(String employer);
	boolean setStreet(String street);
	boolean setStreetNumber(String streetNumber);
	boolean setZip(String zip);
	boolean setCity(String city);
	boolean setCountry(String country);
	
	// operations
	void createPatient();
	void savePatient();
	void loadPatient(String id) throws ObjectNotFoundException;
}
