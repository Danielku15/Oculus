package at.itb13.oculus.search;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.util.DateUtil;

/**
 * Represents the result of a search 
 * @param <T> type of search result
 */
public class SearchResult<T extends PersistentObject & Searchable> {
	
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private FieldMap<T> _fieldMap;
	private List<T> _searchables;
	private List<String[]> _results;
	
	public SearchResult(FieldMap<T> searchMap, List<T> searchables) {
		_fieldMap = searchMap;
		_searchables = searchables;
	}

	private Object invokeRecursive(List<Method> methodList, Searchable searchable, int index) {
		if(searchable != null) {
			if(methodList.size() > 0) {
				Method method = methodList.get(index);
				try {
					if((methodList.size() - (index + 1)) == 0) {
						return method.invoke(searchable);
					} else {
						return invokeRecursive(methodList, (Searchable) method.invoke(searchable), ++index);
					}
				} catch(Exception e) {
					logger.severe(e.getMessage());
					throw new InternalError(e.getMessage());
				}
			}
		}
		return null;
	}
	
	public List<String[]> getResults() {
		if(_results == null) {
			initResults();
		}
		return _results;
	}
	
	private void initResults() {
		_results = new ArrayList<String[]>(_searchables.size());
		Map<String, List<Method>> methodMap = _fieldMap.getMethodMap();
		for(Searchable searchable : _searchables) {
			int i = 0;
			String[] result = new String[methodMap.size() + 1]	;
			result[i++] = ((PersistentObject) searchable).getID();
			for(List<Method> methodList : methodMap.values()) {
				Object object = invokeRecursive(methodList, searchable, 0);
				if(object != null) {
					if(object instanceof Date) {
						result[i++] = DateUtil.format((Date) object);
					} else {
						result[i++] = object.toString();
					}
				} else {
					result[i++] = "";
				}
			}
			_results.add(result);
		}
	}
}
