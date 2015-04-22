package at.itb13.oculus.application;
import java.util.List;
import java.util.Map;

import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.search.FieldMap;
import at.itb13.oculus.search.SearchResult;
import at.itb13.oculus.search.Searchable;

/**
 * @author Patrick
 *
 * @param <T>
 */
class SearchControllerImpl<T extends PersistentObject & Searchable> extends Controller implements SearchController<T> {
	
	private static final int MINCRITERIALENGTH = 3;
	
	private Class<T> _type;
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
	public boolean setCriteria(String criteria) {
		criteria = criteria.trim();
		if(((criteria != null) && criteria.isEmpty()) || (criteria.length() >= MINCRITERIALENGTH)) {
			_criteria = criteria;
			return true;
		}
		return false;
	}

	@Override
	public void search() {
		if(_criteria != null) {
			_searchResult = _database.search(_type, _fieldMap, _criteria);
		}
	}

	@Override
	public List<String[]> getResults() {
		if(_searchResult != null) {
			return _searchResult.getResults();
		}
		return null;
	}

	@Override
	public Map<String, Integer> getIndexMap() {
		return _fieldMap.getIndexMap();
	}
}
