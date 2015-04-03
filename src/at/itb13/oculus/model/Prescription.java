package at.itb13.oculus.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "prescription", catalog = "oculusdb")
public class Prescription extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = -7409504092922866644L;
	
	private Diagnosis _diagnosis;
	private Date _created;
	private Date _expired;
	private Integer _insurance;
	private Set<PrescriptionEntry> _prescriptionEntries = new HashSet<PrescriptionEntry>(0);

	public Prescription() {}

	public Prescription(Diagnosis diagnosis, Date created, Date expired,
			Integer insurance, Set<PrescriptionEntry> prescriptionEntries) {
		_diagnosis = diagnosis;
		_created = created;
		_expired = expired;
		_insurance = insurance;
		_prescriptionEntries = prescriptionEntries;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diagnosis")
	public Diagnosis getDiagnosis() {
		return _diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		_diagnosis = diagnosis;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", length = 19)
	public Date getCreated() {
		return _created;
	}

	public void setCreated(Date created) {
		_created = created;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "expired", length = 19)
	public Date getExpired() {
		return _expired;
	}

	public void setExpired(Date expired) {
		_expired = expired;
	}

	@Column(name = "insurance")
	public Integer getInsurance() {
		return _insurance;
	}

	public void setInsurance(Integer insurance) {
		_insurance = insurance;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prescription")
	public Set<PrescriptionEntry> getPrescriptionEntries() {
		return _prescriptionEntries;
	}

	public void setPrescriptionEntries(Set<PrescriptionEntry> prescriptionEntries) {
		_prescriptionEntries = prescriptionEntries;
	}
}
