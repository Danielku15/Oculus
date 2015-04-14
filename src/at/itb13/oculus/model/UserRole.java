package at.itb13.oculus.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "userrole", catalog = "oculus_c")
public class UserRole extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = 4344085869241228022L;
	
	private String _name;
	private String _description;
	private Set<User> _users = new HashSet<User>(0);
	private Set<UserRight> _userRights = new HashSet<UserRight>(0);

	public UserRole() {}

	public UserRole(String name, String description, Set<User> users, Set<UserRight> userRights) {
		_name = name;
		_description = description;
		_users = users;
		_userRights = userRights;
	}

	@Column(name = "name")
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_userrole", catalog = "oculusdb", joinColumns = { @JoinColumn(name = "userrole", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user", nullable = false, updatable = false) })
	public Set<User> getUsers() {
		return _users;
	}

	public void setUsers(Set<User> users) {
		_users = users;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "userright_userrole", catalog = "oculusdb", joinColumns = { @JoinColumn(name = "userrole", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "userright", nullable = false, updatable = false) })
	public Set<UserRight> getUserRights() {
		return _userRights;
	}

	public void setUserRights(Set<UserRight> userRights) {
		_userRights = userRights;
	}
}
