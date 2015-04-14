package at.itb13.oculus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "doctor", catalog = "oculus_c")
@PrimaryKeyJoinColumn(name="employee")
public class Doctor extends Employee implements java.io.Serializable {
	private static final long serialVersionUID = -8169094489926558070L;
	
	private String _doctorNumber;

	public Doctor() {}

	public Doctor(String doctornumber) {
		_doctorNumber = doctornumber;
	}

	@Column(name = "doctornumber")
	public String getDoctorNumber() {
		return _doctorNumber;
	}

	public void setDoctorNumber(String doctorNumber) {
		_doctorNumber = doctorNumber;
	}
}
