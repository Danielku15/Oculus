package at.itb13.oculus.application;

import at.itb13.oculus.model.Patient;

public interface PatientViewController {
	
	String createPatient(Patient patient) throws RegExpException;
	void updatePatient(Patient patient) throws RegExpException;
	Patient getPatient(String id);
	void close();
}
