package at.itb13.oculus.application;

import java.util.List;
import java.util.Map;

import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.search.Searchable;

/**
 * @author Patrick
 *
 */
public interface SearchController<T extends PersistentObject & Searchable> {
	
	// getter
	String getCriteria();
	
	// setter
	boolean setCriteria(String criteria);
	
	// operations
	void search();
	List<String[]> getResults();	
	Map<String, Integer> getFieldMap();
}
