package at.itb13.oculus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "referral", catalog = "oculusdb")
public class Referral implements java.io.Serializable {
	private static final long serialVersionUID = -3834665321047037012L;
	
	private Integer _id;
	private Appointment _appointment;

	public Referral() {}

	public Referral(Appointment appointment) {
		_appointment = appointment;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment")
	public Appointment getAppointment() {
		return _appointment;
	}

	public void setAppointment(Appointment appointment) {
		_appointment = appointment;
	}
}
