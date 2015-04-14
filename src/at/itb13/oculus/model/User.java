package at.itb13.oculus.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "user", catalog = "oculus_c")
public class User extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = 995230072855104269L;
	
	private Employee _employee;
	private String _name;
	private String _password;
	private String _status;
	private Set<UserRole> _userRoles = new HashSet<UserRole>(0);

	public User() {}

	public User(Employee employee, String name, String password,
			String status, Set<UserRole> userRoles) {
		_employee = employee;
		_name = name;
		_password = password;
		_status = status;
		_userRoles = userRoles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee")
	public Employee getEmployee() {
		return _employee;
	}

	public void setEmployee(Employee employee) {
		_employee = employee;
	}

	@Column(name = "name")
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	@Column(name = "password")
	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	@Column(name = "status")
	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_userrole", catalog = "oculusdb", joinColumns = { @JoinColumn(name = "user", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "userrole", nullable = false, updatable = false) })
	public Set<UserRole> getUserRoles() {
		return _userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		_userRoles = userRoles;
	}
}
