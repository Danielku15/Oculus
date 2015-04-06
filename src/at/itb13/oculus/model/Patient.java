package at.itb13.oculus.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "patient", catalog = "oculusdb")
public class Patient extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = -7225613928118556705L;
	
	private String _firstname;
	private String _lastname;
	private String _birthday;
	private Gender _gender;
	private String _phoneNumber;
	private String _email;
	private Address _address;
	private String _socialSecurityNumber;
	private String _employer;
	private Set<Anamnesis> _anamneses = new HashSet<Anamnesis>(0);
	private Set<Appointment> _appointments = new HashSet<Appointment>(0);

	public Patient() {}

	public Patient(String firstname, String lastname, String birthday,
			Gender gender, String phoneNumber, String email, Address address, String socialSecurityNumber,
			String employer, Set<Anamnesis> anamneses,
			Set<Appointment> appointments) {
		_firstname = firstname;
		_lastname = lastname;
		_birthday = birthday;
		_gender = gender;
		_phoneNumber = phoneNumber;
		_email = email;
		_address = address;
		_socialSecurityNumber = socialSecurityNumber;
		_employer = employer;
		_anamneses = anamneses;
		_appointments = appointments;
	}

	@Column(name = "firstname")
	public String getFirstname() {
		return _firstname;
	}

	public void setFirstname(String firstname) {
		_firstname = firstname;
	}

	@Column(name = "lastname")
	public String getLastname() {
		return _lastname;
	}

	public void setLastname(String lastname) {
		_lastname = lastname;
	}

	@Column(name = "birthday")
	public String getBirthday() {
		return _birthday;
	}

	public void setBirthday(String birthday) {
		_birthday = birthday;
	}

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	public Gender getGender() {
		return _gender;
	}

	public void setGender(Gender gender) {
		_gender = gender;
	}

	@Column(name = "phonenumber")
	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		_phoneNumber = phoneNumber;
	}

	@Column(name = "email")
	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}
	
	@Embedded
	public Address getAddress() {
		return _address;
	}

	public void setAddress(Address address) {
		_address = address;
	}

	@Column(name = "socialsecuritynumber")
	public String getSocialSecurityNumber() {
		return _socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		_socialSecurityNumber = socialSecurityNumber;
	}

	@Column(name = "employer")
	public String getEmployer() {
		return _employer;
	}

	public void setEmployer(String employer) {
		_employer = employer;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Anamnesis> getAnamnesises() {
		return _anamneses;
	}

	public void setAnamnesises(Set<Anamnesis> anamneses) {
		_anamneses = anamneses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "patient")
	public Set<Appointment> getAppointments() {
		return _appointments;
	}

	public void setAppointments(Set<Appointment> appointments) {
		_appointments = appointments;
	}
}
