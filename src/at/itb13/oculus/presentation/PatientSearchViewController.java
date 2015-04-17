package at.itb13.oculus.presentation;

import at.itb13.oculus.model.Appointment;

public class PatientSearchViewController extends SearchViewController<Appointment> {
	
	public PatientSearchViewController() {
		super(Appointment.class);
	}
}
