package at.itb13.oculus.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "employee", catalog = "oculusdb")
public abstract class Employee extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = -1684256194909712599L;
	
	private String _firstname;
	private String _lastname;
	private Date _birthday;
	private String _gender;
	private String _phoneNumber;
	private String _email;
	private Address _address;
	private String _socialSecurityNumber;
	private Set<Queue> _queues = new HashSet<Queue>(0);
	private Set<CalendarEntry> _calendarEntries = new HashSet<CalendarEntry>(0);
	private Set<User> _users = new HashSet<User>(0);

	public Employee() {}

	public Employee(String firstname, String lastname, Date birthday,
			String gender, String phoneNumber, String email, Address address,
			String socialSecurityNumber, Set<Queue> queues, Set<CalendarEntry> calendarEntries,	Set<User> users) {
		_firstname = firstname;
		_lastname = lastname;
		_birthday = birthday;
		_gender = gender;
		_phoneNumber = phoneNumber;
		_email = email;
		_address = address;
		_socialSecurityNumber = socialSecurityNumber;
		_queues = queues;
		_calendarEntries = calendarEntries;
		_users = users;
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
	public Date getBirthday() {
		return _birthday;
	}

	public void setBirthday(Date birthday) {
		_birthday = birthday;
	}

	@Column(name = "gender")
	public String getGender() {
		return _gender;
	}

	public void setGender(String gender) {
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "employee")
	public Set<User> getUsers() {
		return _users;
	}

	public void setUsers(Set<User> users) {
		_users = users;
	}
}
