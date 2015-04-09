package at.itb13.oculus.application;

import at.itb13.oculus.model.Patient;

public interface PatientViewController {
	
	String createPatient(Patient patient);
	void updatePatient(Patient patient);
	Patient getPatient(String id);
	void close();
}
