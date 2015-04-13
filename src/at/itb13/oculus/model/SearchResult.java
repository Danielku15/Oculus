package at.itb13.oculus.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

import at.itb13.oculus.database.PersistentObject;

/** represents the result of a search
 * @author Patrick
 * 
 * @param <T> type of search result
 */
public class SearchResult<T extends PersistentObject & Searchable> {
	
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private List<T> _searchables;
	private Map<String, List<Method>> _methodMap;
	private List<String> _fieldNames;
	private List<List<String>> _results;
	
	public SearchResult(Class<T> type, List<T> searchables) {
		_searchables = searchables;
		_methodMap = new HashMap<String, List<Method>>();
		initMethodMap(type, new HashSet<Class<?>>(), null);
		initResults();
	}
	
	private void initMethodMap(Class<?> type, Set<Class<?>> closeList, List<Method> methodList) {
		// add current class to close list
		closeList.add(type);
		
		for(Method method : type.getMethods()) {
			// check if there is a field annotation
			if(method.isAnnotationPresent(Field.class)) {
				String key = getKeyFromMethodName(method.getName());
				if(!key.equalsIgnoreCase("id")) {
					List<Method> methods = null;
					if(methodList != null) {
						methods = new ArrayList<Method>(methodList);
					} else {
						methods = new ArrayList<Method>();
					}
					methods.add(method);
					_methodMap.put(key, methods);
				}
			} else {
				// check if there is an index embedded annotation
				if(method.isAnnotationPresent(IndexedEmbedded.class)) {
					Class<?> subtype = method.getReturnType();
					// check if return type is searchable and close list does not contain subtype		
					if(Searchable.class.isAssignableFrom(subtype) && !closeList.contains(subtype)) {
						// recursion
						List<Method> methods = null;
						if(methodList != null) {
							methods = new ArrayList<Method>(methodList);
						} else {
							methods = new ArrayList<Method>();
						}
						methods.add(method);
						initMethodMap(subtype, closeList, methods);
					}
				}
			}
		}
	}
	
	private String getKeyFromMethodName(String methodName) {
		return methodName.replaceFirst("(?i)^get", "").replaceFirst("\\(\\)", "").toLowerCase();
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
	
	public List<String> getFieldNames() {
		if(_fieldNames == null) {
			initFieldNames();
		}
		return _fieldNames;
	}
	
	private void initFieldNames() {
		_fieldNames = new ArrayList<String>();
		_fieldNames.add("id");
		_fieldNames.addAll(_methodMap.keySet());
	}
	
	public List<List<String>> getResults() {
		if(_results == null) {
			initResults();
		}
		return _results;
	}
	
	private void initResults() {
		_results = new ArrayList<List<String>>(_searchables.size());
		for(Searchable searchable : _searchables) {
			List<String> result = new ArrayList<String>(_methodMap.size() + 1);
			result.add(((PersistentObject) searchable).getID());
			for(List<Method> methodList : _methodMap.values()) {
				Object object = invokeRecursive(methodList, searchable, 0);
				if(object != null) {
					result.add(object.toString());
				} else {
					result.add("");
				}
			}
			_results.add(result);
		}
	}
}
