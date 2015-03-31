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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "queueentry", catalog = "oculusdb")
public class QueueEntry implements java.io.Serializable {
	private static final long serialVersionUID = 4434582231687956648L;
	
	private Integer _id;
	private Queue _queue;
	private Appointment _appointment;
	private Date _created;

	public QueueEntry() {}

	public QueueEntry(Queue queue, Appointment appointment, Date created) {
		_queue = queue;
		_appointment = appointment;
		_created = created;
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
	@JoinColumn(name = "queue")
	public Queue getQueue() {
		return _queue;
	}

	public void setQueue(Queue queue) {
		_queue = queue;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment")
	public Appointment getAppointment() {
		return _appointment;
	}

	public void setAppointment(Appointment appointment) {
		_appointment = appointment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", length = 19)
	public Date getCreated() {
		return _created;
	}

	public void setCreated(Date created) {
		_created = created;
	}
}