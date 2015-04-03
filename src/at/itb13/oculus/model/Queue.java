package at.itb13.oculus.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "queue", catalog = "oculusdb")
public class Queue extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = -7295993730546243765L;
	
	private String _name;
	private Set<Employee> _employees = new HashSet<Employee>(0);
	private Set<QueueEntry> _queueEntries = new HashSet<QueueEntry>(0);

	public Queue() {}

	public Queue(String name, Set<Employee> employees, Set<QueueEntry> queueEntries) {
		_name = name;
		_employees = employees;
		_queueEntries = queueEntries;
	}

	@Column(name = "name")
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "queue_employee", catalog = "oculusdb", joinColumns = { @JoinColumn(name = "queue", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "employee", nullable = false, updatable = false) })
	public Set<Employee> getEmployees() {
		return _employees;
	}

	public void setEmployees(Set<Employee> employees) {
		_employees = employees;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "queue")
	public Set<QueueEntry> getQueueEntries() {
		return _queueEntries;
	}

	public void setQueueEntries(Set<QueueEntry> queueEntries) {
		_queueEntries = queueEntries;
	}
}
