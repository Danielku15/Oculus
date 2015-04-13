package at.itb13.oculus.application;

import at.itb13.oculus.model.Patient;

public interface ReceivePatientController {

	public Patient nextPatient();
	public Patient showNextPatient();
	public void newUntersuchungsprotokoll();
}
