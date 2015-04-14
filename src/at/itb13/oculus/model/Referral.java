package at.itb13.oculus.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "referral", catalog = "oculus_c")
public class Referral extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = -3834665321047037012L;
	
	private Appointment _appointment;

	public Referral() {}

	public Referral(Appointment appointment) {
		_appointment = appointment;
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
