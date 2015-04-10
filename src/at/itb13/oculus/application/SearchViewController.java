package at.itb13.oculus.application;

import java.util.List;

import at.itb13.oculus.model.Patient;

public interface SearchViewController extends AutoCloseable {
	
	void close();
	
	List<Patient> search(String criteria);
}
