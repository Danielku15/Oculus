package at.itb13.oculus.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "eyeprescription", catalog = "oculusdb")
public class EyePrescription implements java.io.Serializable {
	private static final long serialVersionUID = 842598488776707292L;
	
	private Integer _id;
	private Diagnosis _diagnosis;
	private BigDecimal _ldiopter;
	private BigDecimal _rdiopter;

	public EyePrescription() {}

	public EyePrescription(Diagnosis diagnosis, BigDecimal ldiopter,
			BigDecimal rdiopter) {
		_diagnosis = diagnosis;
		_ldiopter = ldiopter;
		_rdiopter = rdiopter;
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

	@Column(name = "ldiopter", precision = 4)
	public BigDecimal getLdiopter() {
		return _ldiopter;
	}

	public void setLdiopter(BigDecimal ldiopter) {
		_ldiopter = ldiopter;
	}

	@Column(name = "rdiopter", precision = 4)
	public BigDecimal getRdiopter() {
		return _rdiopter;
	}

	public void setRdiopter(BigDecimal rdiopter) {
		_rdiopter = rdiopter;
	}
}
