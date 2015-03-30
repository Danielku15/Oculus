package at.itb13.oculus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "receptionist", catalog = "oculusdb")
public class Receptionist implements java.io.Serializable {
	private static final long serialVersionUID = -6364273493033293093L;
	
	private Integer _employeeId;
	private Employee _employee;

	public Receptionist() {}

	public Receptionist(Employee employee) {
		_employee = employee;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "employee"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "employee", unique = true, nullable = false)
	public Integer getEmployeeId() {
		return _employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		_employeeId = employeeId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public Employee getEmployee() {
		return _employee;
	}

	public void setEmployee(Employee employee) {
		_employee = employee;
	}
}
