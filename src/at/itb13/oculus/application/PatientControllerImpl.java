package at.itb13.oculus.application;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;

import at.itb13.oculus.model.Address;
import at.itb13.oculus.model.Gender;
import at.itb13.oculus.model.Patient;

public class PatientControllerImpl extends Controller implements PatientController {
	
	private Patient _patient;
	
	public PatientControllerImpl() {
		super();
		createPatient();
	}

	@Override
	public String getID() {
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
	public LocalDate getBirthday() {
		Date birthday = _patient.getBirthday();
		if(birthday != null) {
			return birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
		return null;
	}

	@Override
	public String getGender() {
		Gender gender = _patient.getGender();
		if(gender != null) {
			return gender.toString();	
		}
		return null;
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
		Address address = _patient.getAddress();
		if(address != null) {
			return address.getStreet();	
		}
		return null;
	}

	@Override
	public String getStreetNumber() {
		Address address = _patient.getAddress();
		if(address != null) {
			return address.getStreetNumber();	
		}
		return null;
	}

	@Override
	public String getZip() {
		Address address = _patient.getAddress();
		if(address != null) {
			return address.getZip();	
		}
		return null;
	}

	@Override
	public String getCity() {
		Address address = _patient.getAddress();
		if(address != null) {
			return address.getCity();	
		}
		return null;
	}

	@Override
	public String getCountry() {
		Address address = _patient.getAddress();
		if(address != null) {
			return address.getCountry();	
		}
		return null;
	}

	@Override
	public boolean setFirstname(String firstname) {
		if(isFirstnameValid(firstname)) {
			_patient.setFirstname(firstname);
			return true;
		}
		return false;
	}

	@Override
	public boolean setLastname(String lastname) {
		if(isLastnameValid(lastname)) {
			_patient.setLastname(lastname);
			return true;
		}
		return false;
	}

	@Override
	public boolean setBirthday(LocalDate birthday) {
		if(birthday != null) {
			Instant instant = birthday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			Date date = Date.from(instant);
			_patient.setBirthday(date);
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
	public synchronized boolean setSocialSecurityNumber(String socialSecurityNumber) throws UniqueConstraintException {
		if(isSocialSecurityNumberValid(socialSecurityNumber)) {
			try {
				_database.beginTransaction();
				Patient patient = _database.getPatientBySocialSecurityNumber(socialSecurityNumber);
				if(patient != null) {
					throw new UniqueConstraintException("socialSecurityNumber", _patient, patient);
				}
				_database.commitTransaction();
			} catch(HibernateException e) {
				_database.rollbackTransaction();
				throw e;
			}
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
			if(address == null) {
				address = new Address();
				_patient.setAddress(address);
			}
			address.setStreet(street);
			return true;
		}
		return false;
	}

	@Override
	public boolean setStreetNumber(String streetNumber) {
		if(streetNumber != null) {
			Address address = _patient.getAddress();
			if(address == null) {
				address = new Address();
				_patient.setAddress(address);
			}
			address.setStreetNumber(streetNumber);
			return true;
		}
		return false;
	}

	@Override
	public boolean setZip(String zip) {
		if(zip != null) {
			Address address = _patient.getAddress();
			if(address == null) {
				address = new Address();
				_patient.setAddress(address);
			}
			address.setZip(zip);
			return true;
		}
		return false;
	}

	@Override
	public boolean setCity(String city) {
		if(city != null) {
			Address address = _patient.getAddress();
			if(address == null) {
				address = new Address();
				_patient.setAddress(address);
			}
			address.setCity(city);
			return true;
		}
		return false;
	}

	@Override
	public boolean setCountry(String country) {
		if(country != null) {
			Address address = _patient.getAddress();
			if(address == null) {
				address = new Address();
				_patient.setAddress(address);
			}
			address.setCountry(country);
			return true;
		}
		return false;
	}

	@Override
	public void createPatient() {
		_patient = new Patient();
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

	@Override
	public synchronized void savePatient() throws IncompleteDataException, ObjectNotSavedException {
		if(validateData()) {
			try {
				_database.beginTransaction();
				_database.createOrUpdate(_patient);
				_database.commitTransaction();
			} catch(HibernateException e) {
				_database.rollbackTransaction();
				throw new ObjectNotSavedException(_patient);
			}
		}
	}
	
	@Override
	public boolean validateData() throws IncompleteDataException {
		List<String> fieldNames = new ArrayList<String>();
		if(!isFirstnameValid(_patient.getFirstname())) {
			fieldNames.add("firstname");
		}
		if(!isLastnameValid(_patient.getLastname())) {
			fieldNames.add("lastname");
		}
		if(!isSocialSecurityNumberValid(_patient.getSocialSecurityNumber())) {
			fieldNames.add("socialSecurityNumber");
		}
		if(!fieldNames.isEmpty()) {
			throw new IncompleteDataException(fieldNames);
		}
		return true;
	}
	
	private boolean isFirstnameValid(String firstname) {
		return (firstname != null) && (!firstname.trim().isEmpty());
	}
	
	private boolean isLastnameValid(String lastname) {
		return (lastname != null) && (!lastname.trim().isEmpty());
	}
	
	private boolean isSocialSecurityNumberValid(String socialSecurityNumber) {
		if(socialSecurityNumber != null) {
			String regExp = "^[0-9]{10}$";
			return socialSecurityNumber.matches(regExp);
		}
		return false;
	}
}
