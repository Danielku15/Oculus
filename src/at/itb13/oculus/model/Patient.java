package at.itb13.oculus.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "patient", catalog = "oculusdb")
public class Patient implements java.io.Serializable {
	private static final long serialVersionUID = -7225613928118556705L;
	
	private Integer _id;
	private String _firstname;
	private String _lastname;
	private String _birthday;
	private Integer _gender;
	private String _phoneNumber;
	private String _email;
	private String _zip;
	private String _country;
	private String _street;
	private String _streetNumber;
	private String _socialSecurityNumber;
	private String _employer;
	private Set<Anamnesis> _anamneses = new HashSet<Anamnesis>(0);
	private Set<Appointment> _appointments = new HashSet<Appointment>(0);

	public Patient() {}

	public Patient(String firstname, String lastname, String birthday,
			Integer gender, String phoneNumber, String email, String zip,
			String country, String street, String streetNumber,
			String socialSecurityNumber, String employer, Set<Anamnesis> anamneses,
			Set<Appointment> appointments) {
		_firstname = firstname;
		_lastname = lastname;
		_birthday = birthday;
		_gender = gender;
		_phoneNumber = phoneNumber;
		_email = email;
		_zip = zip;
		_country = country;
		_street = street;
		_streetNumber = streetNumber;
		_socialSecurityNumber = socialSecurityNumber;
		_employer = employer;
		_anamneses = anamneses;
		_appointments = appointments;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return _id;
	}

	public void setId(Integer id) {
		_id = id;
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
	public Integer getGender() {
		return _gender;
	}

	public void setGender(Integer gender) {
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

	@Column(name = "zip")
	public String getZip() {
		return _zip;
	}

	public void setZip(String zip) {
		_zip = zip;
	}

	@Column(name = "country")
	public String getCountry() {
		return _country;
	}

	public void setCountry(String country) {
		_country = country;
	}

	@Column(name = "street")
	public String getStreet() {
		return _street;
	}

	public void setStreet(String street) {
		_street = street;
	}

	@Column(name = "streetnumber")
	public String getStreetNumber() {
		return _streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		_streetNumber = streetNumber;
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
