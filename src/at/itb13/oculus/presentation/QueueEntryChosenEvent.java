package at.itb13.oculus.presentation;

import java.util.EventObject;

/**
 * 
 * this class defines the event for the {@link QueueEntryChosenListener}
 *
 */
public class QueueEntryChosenEvent extends EventObject {
	private static final long serialVersionUID = 2756292301739450558L;
	
	private String _queueEntryId;
	private String _patientId;
	private String _appointmentId;
	
	public QueueEntryChosenEvent(QueueViewController queueViewController, String queueEntryId, String patientId, String appointmentId) {
		super(queueViewController);
		_queueEntryId = queueEntryId;
		_patientId = patientId;
		_appointmentId = appointmentId;
	}
	
	public String getQueueEntryId() {
		return _queueEntryId;
	}
	
	public String getPatientId() {
		return _patientId;
	}
	
	public String getAppointmentId() {
		return _appointmentId;
	}
}
