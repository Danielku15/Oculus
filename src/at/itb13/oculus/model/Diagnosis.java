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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "diagnosis", catalog = "oculusdb")
public class Diagnosis implements java.io.Serializable {
	private static final long serialVersionUID = 3427544654363398598L;
	
	private Integer _id;
	private Appointment _appointment;
	private String _name;
	private Set<EyePrescription> _eyePrescriptions = new HashSet<EyePrescription>(0);
	private Set<Prescription> _prescriptions = new HashSet<Prescription>(0);
	private Set<SickNote> _sickNotes = new HashSet<SickNote>(0);

	public Diagnosis() {}

	public Diagnosis(Appointment appointment, String name,
			Set<EyePrescription> eyePrescriptions, Set<Prescription> prescriptions, Set<SickNote> sickNotes) {
		_appointment = appointment;
		_name = name;
		_eyePrescriptions = eyePrescriptions;
		_prescriptions = prescriptions;
		_sickNotes = sickNotes;
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

	@Column(name = "name")
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diagnosis")
	public Set<EyePrescription> getEyePrescriptions() {
		return _eyePrescriptions;
	}

	public void setEyePrescriptions(Set<EyePrescription> eyePrescriptions) {
		_eyePrescriptions = eyePrescriptions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diagnosis")
	public Set<Prescription> getPrescriptions() {
		return _prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		_prescriptions = prescriptions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "diagnosis")
	public Set<SickNote> getSickNotes() {
		return _sickNotes;
	}

	public void setSickNotes(Set<SickNote> sickNotes) {
		_sickNotes = sickNotes;
	}
}
