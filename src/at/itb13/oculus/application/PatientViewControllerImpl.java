package at.itb13.oculus.application;

import java.util.Date;

import org.hibernate.HibernateException;

import at.itb13.oculus.model.Address;
import at.itb13.oculus.model.Gender;
import at.itb13.oculus.model.Patient;

public class PatientViewControllerImpl extends Controller implements PatientViewController {
	
	private Patient _patient;
	
	public PatientViewControllerImpl() {
		createPatient();
	}

	@Override
	public String getId() {
		return _patient.getID();
	}

	@Override
	public String getFirstname() {
		return _patient.getFirstname();
	}

	@Override
	public String getLastname() {
		return _patient.getLastname();
	}

	@Override
	public Date getBirthday() {
		return _patient.getBirthday();
	}

	@Override
	public String getGender() {
		return _patient.getGender().toString();
	}

	@Override
	public String getPhoneNumber() {
		return _patient.getPhoneNumber();
	}

	@Override
	public String getEmail() {
		return _patient.getEmail();
	}

	@Override
	public String getSocialSecurityNumber() {
		return _patient.getSocialSecurityNumber();
	}

	@Override
	public String getEmployer() {
		return _patient.getEmployer();
	}

	@Override
	public String getStreet() {
		return _patient.getAddress().getStreet();
	}

	@Override
	public String getStreetNumber() {
		return _patient.getAddress().getStreetNumber();
	}

	@Override
	public String getZip() {
		return _patient.getAddress().getZip();
	}

	@Override
	public String getCity() {
		return _patient.getAddress().getCity();
	}

	@Override
	public String getCountry() {
		return _patient.getAddress().getCountry();
	}

	@Override
	public boolean setFirstname(String firstname) {
		if((firstname != null) && (!firstname.trim().isEmpty())) {
			_patient.setFirstname(firstname);
			return true;
		}
		return false;
	}

	@Override
	public boolean setLastname(String lastname) {
		if((lastname != null) && (!lastname.trim().isEmpty())) {
			_patient.setLastname(lastname);
			return true;
		}
		return false;
	}

	@Override
	public boolean setBirthday(Date birthday) {
		if(birthday != null) {
			_patient.setBirthday(birthday);
			return true;
		}
		return false;
	}

	@Override
	public boolean setGender(String gender) {
		if((gender != null) && (!gender.trim().isEmpty())) {
			Gender g = Gender.valueOf(gender);
			if(g != null) {
				_patient.setGender(g);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setPhoneNumber(String phoneNumber) {
		if(phoneNumber != null) {
			_patient.setPhoneNumber(phoneNumber);
			return true;
		}
		return false;
	}

	@Override
	public boolean setEmail(String email) {
		if(email != null) {
			_patient.setEmail(email);
			return true;
		}
		return false;
	}

	@Override
	public boolean setSocialSecurityNumber(String socialSecurityNumber) {
		if((socialSecurityNumber != null) && (isSocialSecurityNumberValid(socialSecurityNumber))) {
			_patient.setSocialSecurityNumber(socialSecurityNumber);
			return true;
		}
		return false;
	}

	@Override
	public boolean setEmployer(String employer) {
		if(employer != null) {
			_patient.setEmployer(employer);
			return true;
		}
		return false;
	}

	@Override
	public boolean setStreet(String street) {
		if(street != null) {
			Address address = _patient.getAddress();
			if(address != null) {
				address.setStreet(street);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setStreetNumber(String streetNumber) {
		if(streetNumber != null) {
			Address address = _patient.getAddress();
			if(address != null) {
				address.setStreetNumber(streetNumber);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setZip(String zip) {
		if(zip != null) {
			Address address = _patient.getAddress();
			if(address != null) {
				address.setZip(zip);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setCity(String city) {
		if(city != null) {
			Address address = _patient.getAddress();
			if(address != null) {
				address.setCity(city);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean setCountry(String country) {
		if(country != null) {
			Address address = _patient.getAddress();
			if(address != null) {
				address.setCountry(country);
				return true;
			}
		}
		return false;
	}

	@Override
	public void createPatient() {
		_patient = new Patient();
		_patient.setAddress(new Address());
	}

	@Override
	public void savePatient() {
		try {
			_database.beginTransaction();
			_database.createOrUpdate(_patient);
			_database.commitTransaction();
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public void loadPatient(String id) throws ObjectNotFoundException {
		Patient patient = null;
		try {
			_database.beginTransaction();
			patient = _database.get(Patient.class, id);
			_database.commitTransaction();
			if(patient != null) {
				_patient = patient;
			} else {
				throw new ObjectNotFoundException(Patient.class, id);
			}
		} catch(HibernateException e) {
			_database.rollbackTransaction();
			throw e;
		}
	}
	
	private boolean isSocialSecurityNumberValid(String socialSecurityNumber) {
		String svn = "^[0-9]{10}$";
		return socialSecurityNumber.matches(svn);
	}
}
