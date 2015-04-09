package at.itb13.oculus.application;

import at.itb13.oculus.model.Patient;

public interface PatientViewController extends AutoCloseable {
	
	Patient createPatient(Patient patient);
	Patient updatePatient(Patient patient);
	Patient getPatient(String id);
	void close();
}
