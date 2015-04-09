package at.itb13.oculus.application;

import at.itb13.oculus.model.Patient;

public interface PatientViewController extends AutoCloseable {
	
	String createPatient(Patient patient) throws RegExpException, UniqueConstraintException;
	void updatePatient(Patient patient) throws RegExpException, UniqueConstraintException;
	Patient getPatient(String id);
	void close();
}
