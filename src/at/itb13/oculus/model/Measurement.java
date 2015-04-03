package at.itb13.oculus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "measurement", catalog = "oculusdb")
public class Measurement extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = 8620688504980052514L;
	
	private Appointment _appointment;
	private String _value;
	private String _description;

	public Measurement() {}

	public Measurement(Appointment appointment, String value, String description) {
		_appointment = appointment;
		_value = value;
		_description = description;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment")
	public Appointment getAppointment() {
		return _appointment;
	}

	public void setAppointment(Appointment appointment) {
		_appointment = appointment;
	}

	@Column(name = "value")
	public String getValue() {
		return _value;
	}

	public void setValue(String value) {
		_value = value;
	}

	@Column(name = "description")
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}
}
