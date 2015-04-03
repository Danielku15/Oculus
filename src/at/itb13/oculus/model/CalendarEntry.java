package at.itb13.oculus.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "calendarentry", catalog = "oculusdb")
public class CalendarEntry extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = 5792674371320855705L;
	
	private Employee _employee;
	private String _title;
	private String _description;
	private Date _start;
	private Date _end;

	public CalendarEntry() {}

	public CalendarEntry(Employee employee, String title, String description, Date start, Date end) {
		_employee = employee;
		_title = title;
		_description = description;
		_start = start;
		_end = end;
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
}
