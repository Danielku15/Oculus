package at.itb13.oculus.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "orthoptist", catalog = "oculus_c")
@PrimaryKeyJoinColumn(name="employee")
public class Orthoptist extends Employee implements java.io.Serializable {
	private static final long serialVersionUID = -3669100652643276339L;
	
	//private Employee _employee;

	public Orthoptist() {}

	/*public Orthoptist(Employee employee) {
		_employee = employee;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Employee getEmployee() {
		return _employee;
	}

	public void setEmployee(Employee employee) {
		_employee = employee;
	}*/
}
