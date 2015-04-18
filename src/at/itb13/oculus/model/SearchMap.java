package at.itb13.oculus.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.IndexedEmbedded;

import at.itb13.oculus.database.PersistentObject;

/**
 * @author Patrick
 *
 * @param <T>
 */
public class SearchMap<T extends PersistentObject & Searchable> {
	
	private Map<String, Integer> _fieldMap;
	private Map<String, List<Method>> _methodMap;
	
	public SearchMap(Class<T> type) {
		initMethodMap(type);
		initFieldMap();
	}
	
	private void initMethodMap(Class<T> type) {
		_methodMap = new HashMap<String, List<Method>>();
		initMethodMap(type, new HashSet<Class<?>>(), null);
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
	
	private void initFieldMap() {
		_fieldMap = new HashMap<String, Integer>();
		
		int index = 0;
		_fieldMap.put("id", index++);
		for(String key : _methodMap.keySet()) {
			_fieldMap.put(key, index++);
		}
	}
	
	public Map<String, Integer> getFieldMap() {
		return _fieldMap;
	}
	
	public Map<String, List<Method>> getMethodMap() {
		return _methodMap;
	}	
}
