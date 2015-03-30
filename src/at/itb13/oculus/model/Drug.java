package at.itb13.oculus.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "drug", catalog = "oculusdb")
public class Drug implements java.io.Serializable {
	private static final long serialVersionUID = -1100893797153771438L;
	
	private Integer _id;
	private String _description;
	private Set<PrescriptionEntry> _prescriptionEntries = new HashSet<PrescriptionEntry>(0);

	public Drug() {}

	public Drug(String description, Set<PrescriptionEntry> prescriptionEntries) {
		_description = description;
		_prescriptionEntries = prescriptionEntries;
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
