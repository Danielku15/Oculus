package at.itb13.oculus.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "insurance", catalog = "oculusdb")
public class Insurance extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = 7223897319094479262L;
	
	private String _name;
	private Set<SickNote> _sicknotes = new HashSet<SickNote>(0);
	private Set<Prescription> _prescriptions = new HashSet<Prescription>(0);
	private Set<Patient> _patients = new HashSet<Patient>(0);

	public Insurance() {}

	public Insurance(String name, Set<SickNote> sicknotes, Set<Prescription> prescriptions,	Set<Patient> patients) {
		_name = name;
		_sicknotes = sicknotes;
		_prescriptions = prescriptions;
		_patients = patients;
	}
	
	@Column(name = "name")
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "insurance")
	public Set<SickNote> getSicknotes() {
		return _sicknotes;
	}

	public void setSicknotes(Set<SickNote> sicknotes) {
		_sicknotes = sicknotes;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "insurance")
	public Set<Prescription> getPrescriptions() {
		return _prescriptions;
	}

	public void setPrescriptions(Set<Prescription> prescriptions) {
		_prescriptions = prescriptions;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "patient_insurance", catalog = "oculusdb", joinColumns = { @JoinColumn(name = "insurance", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "patient", nullable = false, updatable = false) })
	public Set<Patient> getPatients() {
		return _patients;
	}

	public void setPatients(Set<Patient> patients) {
		_patients = patients;
	}
}
