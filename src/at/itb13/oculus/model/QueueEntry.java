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
@Table(name = "queueentry", catalog = "oculus_c")
public class QueueEntry extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = 4434582231687956648L;
	
	private Queue _queue;
	private Appointment _appointment;
	private Date _created;

	public QueueEntry() {}

	public QueueEntry(Queue queue, Appointment appointment, Date created) {
		_queue = queue;
		_appointment = appointment;
		_created = created;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "queue")
	public Queue getQueue() {
		return _queue;
	}

	public void setQueue(Queue queue) {
		if(_queue != null) {
			_queue.removeQueueEntry(this);
		}
		_queue = queue;
		_queue.addQueueEntry(this);
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appointment")
	public Appointment getAppointment() {
		return _appointment;
	}

	public void setAppointment(Appointment appointment) {
		if(_appointment != null) {
			_appointment.removeQueueEntry(this);
		}
		_appointment = appointment;
		_appointment.addQueueEntry(this);
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
