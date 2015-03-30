package at.itb13.oculus.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee", catalog = "oculusdb")
public class Employee implements java.io.Serializable {
	private static final long serialVersionUID = -1684256194909712599L;
	
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
	private Receptionist _receptionist;
	private Doctor _doctor;
	private Set<Queue> _queues = new HashSet<Queue>(0);
	private Set<CalendarEntry> _calendarEntries = new HashSet<CalendarEntry>(0);
	private Orthoptist _orthoptist;
	private Set<User> _users = new HashSet<User>(0);

	public Employee() {}

	public Employee(String firstname, String lastname, String birthday,
			Integer gender, String phoneNumber, String email, String zip,
			String country, String street, String streetNumber,
			String socialSecurityNumber, Receptionist receptionist,
			Doctor doctor, Set<Queue> queues, Set<CalendarEntry> calendarEntries,
			Orthoptist orthoptist, Set<User> users) {
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
		_receptionist = receptionist;
		_doctor = doctor;
		_queues = queues;
		_calendarEntries = calendarEntries;
		_orthoptist = orthoptist;
		_users = users;
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

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "employee")
	public Receptionist getReceptionist() {
		return _receptionist;
	}

	public void setReceptionist(Receptionist receptionist) {
		_receptionist = receptionist;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "employee")
	public Doctor getDoctor() {
		return _doctor;
	}

	public void setDoctor(Doctor doctor) {
		_doctor = doctor;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "queue_employee", catalog = "oculusdb", joinColumns = { @JoinColumn(name = "employee", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "queue", nullable = false, updatable = false) })
	public Set<Queue> getQueues() {
		return _queues;
	}

	public void setQueues(Set<Queue> queues) {
		_queues = queues;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<CalendarEntry> getCalendarEntries() {
		return _calendarEntries;
	}

	public void setCalendarEntries(Set<CalendarEntry> calendarEntries) {
		_calendarEntries = calendarEntries;
	}

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "employee")
	public Orthoptist getOrthoptist() {
		return _orthoptist;
	}

	public void setOrthoptist(Orthoptist orthoptist) {
		_orthoptist = orthoptist;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<User> getUsers() {
		return _users;
	}

	public void setUsers(Set<User> users) {
		_users = users;
	}
}
