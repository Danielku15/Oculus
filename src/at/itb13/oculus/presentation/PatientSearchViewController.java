package at.itb13.oculus.presentation;

import java.util.function.Consumer;

import at.itb13.oculus.model.Patient;

public class PatientSearchViewController extends SearchViewController<Patient> {
	
	public PatientSearchViewController(Consumer<String> consumer) {
		super(Patient.class, consumer);
	}
}
