package at.itb13.oculus.application;
import java.util.List;
import java.util.Map;

import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.model.SearchMap;
import at.itb13.oculus.model.SearchResult;
import at.itb13.oculus.model.Searchable;

/**
 * @author Patrick
 *
 * @param <T>
 */
class SearchControllerImpl<T extends PersistentObject & Searchable> extends Controller implements SearchController<T> {
	
	private Class<T> _type;
	private SearchMap<T> _searchMap;
	
	private String _criteria;
	private SearchResult<T> _searchResult;

	public SearchControllerImpl(Class<T> type) {
		super();
		_type = type;
		_searchMap = new SearchMap<T>(type);
	}

	@Override
	public String getCriteria() {
		return _criteria;
	}

	@Override
	public boolean setCriteria(String criteria) {
		if(criteria != null) {
			_criteria = criteria;
			return true;
		}
		return false;
	}

	@Override
	public void search() {
		if(_criteria != null) {
			_searchResult = _database.search(_type, _searchMap, _criteria);
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
	public Map<String, Integer> getFieldMap() {
		return _searchMap.getFieldMap();
	}
}
