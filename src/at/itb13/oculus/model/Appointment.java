package at.itb13.oculus.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Entity
@Indexed
@Table(name = "appointment", catalog = "oculus_c")
@PrimaryKeyJoinColumn(name = "calendarentry")
public class Appointment extends CalendarEntry implements java.io.Serializable, Searchable {
	private static final long serialVersionUID = -2707317958842910903L;
	
	private Patient _patient;
	private String _status;
	private Set<Referral> _referrals = new HashSet<Referral>(0);
	private Set<Measurement> _measurements = new HashSet<Measurement>(0);
	private Set<Diagnosis> _diagnoses = new HashSet<Diagnosis>(0);
	private Set<QueueEntry> _queueEntries = new HashSet<QueueEntry>(0);

	public Appointment() {}

	public Appointment(Patient patient, String status, Set<Referral> referrals, Set<Measurement> measurements, Set<Diagnosis> diagnoses, Set<QueueEntry> queueEntries) {
		_patient = patient;
		_status = status;
		_referrals = referrals;
		_measurements = measurements;
		_diagnoses = diagnoses;
		_queueEntries = queueEntries;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient")
	@IndexedEmbedded
	public Patient getPatient() {
		return _patient;
	}

	public void setPatient(Patient patient) {
		if(_patient != null) {
			_patient.removeAppointment(this);
		}
		_patient = patient;
		_patient.addAppointment(this);
	}

	@Column(name = "status")
	public String getStatus() {
		return _status;
	}

	public void setStatus(String status) {
		_status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment")
	public Set<Referral> getReferrals() {
		return _referrals;
	}

	public void setReferrals(Set<Referral> referrals) {
		_referrals = referrals;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment")
	public Set<Measurement> getMeasurements() {
		return _measurements;
	}

	public void setMeasurements(Set<Measurement> measurements) {
		_measurements = measurements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment")
	public Set<Diagnosis> getDiagnosises() {
		return _diagnoses;
	}

	public void setDiagnosises(Set<Diagnosis> diagnoses) {
		_diagnoses = diagnoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment")
	public Set<QueueEntry> getQueueEntries() {
		return _queueEntries;
	}

	public void setQueueEntries(Set<QueueEntry> queueEntries) {
		_queueEntries = queueEntries;
	}
	
	public void addQueueEntry(QueueEntry queueEntry) {
		_queueEntries.add(queueEntry);
	}
	
	public void removeQueueEntry(QueueEntry queueEntry) {
		_queueEntries.remove(queueEntry);
	}
}
