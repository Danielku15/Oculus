package at.itb13.oculus.application;

import java.util.List;

public interface SearchViewController {
	
	// getter
	String getCriteria();
	
	// setter
	boolean setCriteria(String criteria);
	
	// operations
	void search();
	List<List<String>> getResults();	
	List<String> getFieldNames();
}
