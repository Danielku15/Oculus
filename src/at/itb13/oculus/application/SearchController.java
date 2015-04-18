package at.itb13.oculus.application;

import java.util.List;
import java.util.Map;

/**
 * @author Patrick
 *
 */
public interface SearchController {
	
	// getter
	String getCriteria();
	
	// setter
	boolean setCriteria(String criteria);
	
	// operations
	void search();
	List<String[]> getResults();	
	Map<String, Integer> getFieldMap();
}
