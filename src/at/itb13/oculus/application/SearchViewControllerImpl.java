package at.itb13.oculus.application;

import java.util.List;

import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.model.SearchResult;
import at.itb13.oculus.model.Searchable;

public class SearchViewControllerImpl<T extends PersistentObject & Searchable> extends Controller implements SearchViewController {
	
	private Class<T> _type;
	private String _criteria;
	private SearchResult<T> _searchResult;

	public SearchViewControllerImpl(Class<T> type) {
		super();
		_type = type;
	}

	@Override
	public String getCriteria() {
		return _criteria;
	}

	@Override
	public boolean setCriteria(String criteria) {
		if((criteria != null) && (!criteria.trim().isEmpty())) {
			_criteria = criteria;
			return true;
		}
		return false;
	}

	@Override
	public void search() {
		if(_criteria != null) {
			_searchResult = _database.search(_type, _criteria);
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
	public List<String> getFieldNames() {
		if(_searchResult != null) {
			return _searchResult.getFieldNames();
		}
		return null;
	}

}
