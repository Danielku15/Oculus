package at.itb13.oculus.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "calendarentry", catalog = "oculusdb")
public class CalendarEntry implements java.io.Serializable {
	private static final long serialVersionUID = 5792674371320855705L;
	
	private Integer _id;
	private Employee _employee;
	private String _title;
	private String _description;
	private Date _start;
	private Date _end;
	private Appointment _appointment;

	public CalendarEntry() {}

	public CalendarEntry(Employee employee, String title, String description,
			Date start, Date end, Appointment appointment) {
		_employee = employee;
		_title = title;
		_description = description;
		_start = start;
		_end = end;
		_appointment = appointment;
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
	@JoinColumn(name = "employee")
	public Employee getEmployee() {
		return _employee;
	}

	public void setEmployee(Employee employee) {
		_employee = employee;
	}

	@Column(name = "title")
	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	@Column(name = "description")
	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
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

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "calendarentry")
	public Appointment getAppointment() {
		return _appointment;
	}

	public void setAppointment(Appointment appointment) {
		_appointment = appointment;
	}
}
