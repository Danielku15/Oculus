package at.itb13.oculus.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "drug", catalog = "oculusdb")
public class Drug extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = -1100893797153771438L;
	
	private String _description;
	private Set<PrescriptionEntry> _prescriptionEntries = new HashSet<PrescriptionEntry>(0);

	public Drug() {}

	public Drug(String description, Set<PrescriptionEntry> prescriptionEntries) {
		_description = description;
		_prescriptionEntries = prescriptionEntries;
	}

	@Column(name = "description")
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "drug")
	public Set<PrescriptionEntry> getPrescriptionEntries() {
		return _prescriptionEntries;
	}

	public void setPrescriptionEntries(Set<PrescriptionEntry> prescriptionEntries) {
		_prescriptionEntries = prescriptionEntries;
	}
}
