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
import javax.persistence.Table;

@Entity
@Table(name = "userright", catalog = "oculusdb")
public class UserRight implements java.io.Serializable {
	private static final long serialVersionUID = 7257411442780582444L;
	
	private Integer _id;
	private String _name;
	private String _description;
	private Set<UserRole> _userRoles = new HashSet<UserRole>(0);

	public UserRight() {}

	public UserRight(String name, String description, Set<UserRole> userRoles) {
		_name = name;
		_description = description;
		_userRoles = userRoles;
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
