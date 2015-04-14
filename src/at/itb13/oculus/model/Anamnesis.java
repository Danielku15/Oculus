package at.itb13.oculus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "anamnesis", catalog = "oculus_c")
public class Anamnesis extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = -5523916636804471709L;
	
	private Patient _patient;
	private String _note;

	public Anamnesis() {}

	public Anamnesis(Patient patient, String note) {
		_patient = patient;
		_note = note;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient")
	public Patient getPatient() {
		return _patient;
	}

	public void setPatient(Patient patient) {
		_patient = patient;
	}
	
	@Column(name = "note")
	public String getNote() {
		return _note;
	}

	public void setNote(String note) {
		_note = note;
	}
}
