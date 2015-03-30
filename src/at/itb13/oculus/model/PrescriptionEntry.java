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
@Table(name = "prescriptionentry", catalog = "oculusdb")
public class PrescriptionEntry implements java.io.Serializable {
	private static final long serialVersionUID = -4566669490893856606L;
	
	private Integer _id;
	private Drug _drug;
	private Prescription _prescription;
	private String _description;
	private Integer _amount;

	public PrescriptionEntry() {}

	public PrescriptionEntry(Drug drug, Prescription prescription,
			String description, Integer amount) {
		_drug = drug;
		_prescription = prescription;
		_description = description;
		_amount = amount;
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
	@JoinColumn(name = "drug")
	public Drug getDrug() {
		return _drug;
	}

	public void setDrug(Drug drug) {
		_drug = drug;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prescription")
	public Prescription getPrescription() {
		return _prescription;
	}

	public void setPrescription(Prescription prescription) {
		_prescription = prescription;
	}

	@Column(name = "description")
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	@Column(name = "amount")
	public Integer getAmount() {
		return _amount;
	}

	public void setAmount(Integer amount) {
		_amount = amount;
	}
}
