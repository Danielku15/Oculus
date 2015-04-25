package at.itb13.oculus.application;

import java.util.List;
import java.util.Map;

import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.search.FieldMap;
import at.itb13.oculus.search.SearchResult;
import at.itb13.oculus.search.Searchable;
/**
 * 
 * Generic full text SearchController
 * @param <T> defines the type of searched class/object
 * 
 */
class SearchControllerImpl<T extends PersistentObject & Searchable> extends Controller implements SearchController<T> {
	
	private static final int MINCRITERIALENGTH = 3;
	
	private Class<T> _type;
	/**
	 * Maps an indexed field to its associated value in the search result
	 * @see SearchControllerImpl#_fieldMap
	 */
	private FieldMap<T> _fieldMap;
	private String _criteria;
	private SearchResult<T> _searchResult;

	public SearchControllerImpl(Class<T> type) {
		super();
		_type = type;
		_fieldMap = new FieldMap<T>(type);
	}

	@Override
	public String getCriteria() {
		return _criteria;
	}

	@Override
	public Map<String, Integer> getIndexMap() {
		return _fieldMap.getIndexMap();
	}

	/**
	 * @see at.itb13.oculus.application.SearchController#getResults()
	 * @return the results loaded by {@link SearchControllerImpl#search()}
	 */
	@Override
	public List<String[]> getResults() {
		if(_searchResult != null) {
			return _searchResult.getResults();
		}
		return null;
	}

	@Override
	public boolean setCriteria(String criteria) {
		_criteria = criteria;
		return isCriteriaValid(criteria);
	}
	
	/**
	 * Checks the validation of the searching text
	 * @param criteria searched text depending on {@link SearchControllerImpl#MINCRITERIALENGTH}
	 * @return {@link Boolean} valid or invalid
	 */
	private boolean isCriteriaValid(String criteria) {
		return ((criteria != null) && ((criteria.trim().isEmpty()) || (criteria.trim().length() >= MINCRITERIALENGTH)));
	}
	
	/**
	 * loads the search results from database depending on {@link SearchControllerImpl#_searchResult}, {@link SearchControllerImpl#_fieldMap} and {@link SearchControllerImpl#_criteria}
	 * @see at.itb13.oculus.application.SearchController#search()
	 * @category MainMethod
	 */
	@Override
	public void search() {
		if(_criteria == null) {
			_criteria = "";
		}
		_searchResult = _database.search(_type, _fieldMap, _criteria);
	}
}
