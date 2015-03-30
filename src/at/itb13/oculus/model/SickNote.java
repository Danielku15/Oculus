package at.itb13.oculus.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sicknote", catalog = "oculusdb")
public class SickNote implements java.io.Serializable {
	private static final long serialVersionUID = -1307790026476198047L;
	
	private Integer _id;
	private Diagnosis _diagnosis;
	private Date _start;
	private Date _end;
	private Integer _insurance;
	private String _description;

	public SickNote() {}

	public SickNote(Diagnosis diagnosis, Date start, Date end,
			Integer insurance, String description) {
		_diagnosis = diagnosis;
		_start = start;
		_end = end;
		_insurance = insurance;
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
	@JoinColumn(name = "diagnosis")
	public Diagnosis getDiagnosis() {
		return _diagnosis;
	}

	public void setDiagnosis(Diagnosis diagnosis) {
		_diagnosis = diagnosis;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start", length = 19)
	public Date getStart() {
		return _start;
	}

	public void setStart(Date start) {
		_start = start;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end", length = 19)
	public Date getEnd() {
		return _end;
	}

	public void setEnd(Date end) {
		_end = end;
	}

	@Column(name = "insurance")
	public Integer getInsurance() {
		return _insurance;
	}

	public void setInsurance(Integer insurance) {
		_insurance = insurance;
	}

	@Column(name = "description")
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}
}
