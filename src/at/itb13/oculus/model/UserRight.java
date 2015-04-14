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
@Table(name = "userright", catalog = "oculus_c")
public class UserRight extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = 7257411442780582444L;
	
	private String _name;
	private String _description;
	private Set<UserRole> _userRoles = new HashSet<UserRole>(0);

	public UserRight() {}

	public UserRight(String name, String description, Set<UserRole> userRoles) {
		_name = name;
		_description = description;
		_userRoles = userRoles;
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
	@JoinTable(name = "userright_userrole", catalog = "oculusdb", joinColumns = { @JoinColumn(name = "userright", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "userrole", nullable = false, updatable = false) })
	public Set<UserRole> getUserRoles() {
		return _userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		_userRoles = userRoles;
	}
}
