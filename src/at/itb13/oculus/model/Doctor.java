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
@Table(name = "doctor", catalog = "oculusdb")
public class Doctor implements java.io.Serializable {
	private static final long serialVersionUID = -8169094489926558070L;
	
	private int _employeeId;
	private Employee _employee;
	private String _doctorNumber;

	public Doctor() {}

	public Doctor(Employee employee) {
		_employee = employee;
	}

	public Doctor(Employee employee, String doctornumber) {
		_employee = employee;
		_doctorNumber = doctornumber;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "employee"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "employee", unique = true, nullable = false)
	public int getEmployeeId() {
		return _employeeId;
	}

	public void setEmployeeId(int employeeId) {
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

	@Column(name = "doctornumber")
	public String getDoctorNumber() {
		return _doctorNumber;
	}

	public void setDoctorNumber(String doctorNumber) {
		_doctorNumber = doctorNumber;
	}
}
