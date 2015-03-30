package at.itb13.oculus.model;

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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "appointment", catalog = "oculusdb")
public class Appointment implements java.io.Serializable {
	private static final long serialVersionUID = -2707317958842910903L;
	
	private int _calendarEntryId;
	private CalendarEntry _calendarEntry;
	private Patient _patient;
	private Integer _status;
	private Set<Referral> _referrals = new HashSet<Referral>(0);
	private Set<Measurements> _measurements = new HashSet<Measurements>(0);
	private Set<Diagnosis> _diagnoses = new HashSet<Diagnosis>(0);
	private Set<QueueEntry> _queueEntries = new HashSet<QueueEntry>(0);

	public Appointment() {}

	public Appointment(CalendarEntry calendarEntry) {
		_calendarEntry = calendarEntry;
	}

	public Appointment(CalendarEntry calendarEntry, Patient patient,
			Integer status, Set<Referral> referrals, Set<Measurements> measurements, Set<Diagnosis> diagnoses,
			Set<QueueEntry> queueEntries) {
		_calendarEntry = calendarEntry;
		_patient = patient;
		_status = status;
		_referrals = referrals;
		_measurements = measurements;
		_diagnoses = diagnoses;
		_queueEntries = queueEntries;
	}

	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "calendarentry"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "calendarentry", unique = true, nullable = false)
	public int getCalendarEntryId() {
		return _calendarEntryId;
	}

	public void setCalendarentryId(int calendarEntryId) {
		_calendarEntryId = calendarEntryId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public CalendarEntry getCalendarEntry() {
		return _calendarEntry;
	}

	public void setCalendarEntry(CalendarEntry calendarEntry) {
		_calendarEntry = calendarEntry;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient")
	public Patient getPatient() {
		return _patient;
	}

	public void setPatient(Patient patient) {
		_patient = patient;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return _status;
	}

	public void setStatus(Integer status) {
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
	public Set<Measurements> getMeasurements() {
		return _measurements;
	}

	public void setMeasurements(Set<Measurements> measurements) {
		_measurements = measurements;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment")
	public Set<Diagnosis> getDiagnosises() {
		return _diagnoses;
	}

	public void setDiagnoses(Set<Diagnosis> diagnoses) {
		_diagnoses = diagnoses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "appointment")
	public Set<QueueEntry> getQueueEntries() {
		return _queueEntries;
	}

	public void setQueueentries(Set<QueueEntry> queueEntries) {
		_queueEntries = queueEntries;
	}
}
