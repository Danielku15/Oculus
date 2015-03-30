package at.itb13.oculus.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "measurements", catalog = "oculusdb")
public class Measurements implements java.io.Serializable {
	private static final long serialVersionUID = 1237634607489911809L;
	
	private Integer _id;
	private Appointment _appointment;
	private String _value;
	private String _description;

	public Measurements() {}

	public Measurements(Appointment appointment, String value,
			String description) {
		_appointment = appointment;
		_value = value;
		_description = description;
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
