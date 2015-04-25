package at.itb13.oculus.application;

import java.security.InvalidParameterException;
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
/**
 * 
 * The PatientController business logic of patient operations
 *
 */
class PatientControllerImpl extends Controller implements PatientController {
	
	/**
	 * Saves active patient in {@link PatientControllerImpl#_patient} variable {@link Patient}
	 */
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
		_patient.setFirstname(firstname);
		return isFirstnameValid(firstname);
	}

	@Override
	public boolean setLastname(String lastname) {
		_patient.setLastname(lastname);
		return isLastnameValid(lastname);
	}

	@Override
	public boolean setBirthday(LocalDate birthday) {
		if(birthday != null) {
			Instant instant = birthday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			Date date = Date.from(instant);
			_patient.setBirthday(date);
		} else {
			_patient.setBirthday(null);
		}
		return true;
	}

	@Override
	public boolean setGender(String gender) {
		if((gender != null) && (!gender.trim().isEmpty())) {
			Gender g = Gender.valueOf(gender);
			if(g != null) {
				_patient.setGender(g);
			} else {
				throw new InvalidParameterException(gender + "is not a valid gender!");
			}
		} else {
			_patient.setGender(null);
		}
		return true;
	}

	@Override
	public boolean setPhoneNumber(String phoneNumber) {
		_patient.setPhoneNumber(phoneNumber);
		return true;
	}

	@Override
	public boolean setEmail(String email) {
		_patient.setEmail(email);
		return true;
	}

	@Override
	public synchronized boolean setSocialSecurityNumber(String socialSecurityNumber) throws UniqueConstraintException {
		_patient.setSocialSecurityNumber(socialSecurityNumber);
		if(isSocialSecurityNumberValid(socialSecurityNumber)) {
			try {
				_database.beginTransaction();
				Patient patient = _database.getPatientBySocialSecurityNumber(socialSecurityNumber);
				_database.commitTransaction();
				if((patient != null) && (!patient.equals(_patient))) {
					throw new UniqueConstraintException("socialSecurityNumber", _patient, patient);
				}
			} catch(HibernateException e) {
				_database.rollbackTransaction();
				throw e;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean setEmployer(String employer) {
		_patient.setEmployer(employer);
		return true;
	}

	@Override
	public boolean setStreet(String street) {
		Address address = _patient.getAddress();
		if(address == null) {
			address = new Address();
			_patient.setAddress(address);
		}
		address.setStreet(street);
		return true;
	}

	@Override
	public boolean setStreetNumber(String streetNumber) {
		Address address = _patient.getAddress();
		if(address == null) {
			address = new Address();
			_patient.setAddress(address);
		}
		address.setStreetNumber(streetNumber);
		return true;
	}

	@Override
	public boolean setZip(String zip) {
		Address address = _patient.getAddress();
		if(address == null) {
			address = new Address();
			_patient.setAddress(address);
		}
		address.setZip(zip);
		return true;
	}

	@Override
	public boolean setCity(String city) {
		Address address = _patient.getAddress();
		if(address == null) {
			address = new Address();
			_patient.setAddress(address);
		}
		address.setCity(city);
		return true;
	}

	@Override
	public boolean setCountry(String country) {
		Address address = _patient.getAddress();
		if(address == null) {
			address = new Address();
			_patient.setAddress(address);
		}
		address.setCountry(country);
		return true;
	}
	
	/**
	 * update open {@link PatientController}
	 * @see at.itb13.oculus.application.PatientController#activate()
	 */
	@Override
	public void activate() {
		MainControllerImpl.getInstance().setPatientController(this);
	}
	
	/**
	 * creates new {@link Patient} object in {@link PatientControllerImpl#_patient}
	 * @see at.itb13.oculus.application.PatientController#createPatient()
	 */
	@Override
	public void createPatient() {
		_patient = new Patient();
	}
	
	/**
	 * @see at.itb13.oculus.application.PatientController#loadPatient(java.lang.String)
	 * @param patientId ID of patient which will be load from database in {@link PatientControllerImpl#_patient} variable
	 */
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
	
	/**
	 * saves active patient of {@link PatientControllerImpl#_patient} variable
	 * @see at.itb13.oculus.application.PatientController#savePatient()
	 */
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
	
	/** 
	 * @see at.itb13.oculus.application.PatientController#validateData()
	 * @return true if the data is valid
	 */
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
	
	/**
	 * checks if first name is valid
	 * @param firstname testing variable
	 * @return {@link Boolean} true or false
	 */
	private boolean isFirstnameValid(String firstname) {
		return (firstname != null) && (!firstname.trim().isEmpty());
	}

	/**
	 * checks if last name is valid
	 * @param lastname testing variable
	 * @return {@link Boolean} true or false
	 */
	private boolean isLastnameValid(String lastname) {
		return (lastname != null) && (!lastname.trim().isEmpty());
	}

	/**
	 * checks if social security number is valid
	 * @param social security number testing variable
	 * @return {@link Boolean} true or false
	 */
	private boolean isSocialSecurityNumberValid(String socialSecurityNumber) {
		if(socialSecurityNumber != null) {
			String regExp = "^[0-9]{10}$";
			return socialSecurityNumber.matches(regExp);
		}
		return false;
	}
}
