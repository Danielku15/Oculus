package at.itb13.oculus.application;

import java.util.List;

import at.itb13.oculus.model.Patient;

public class SearchViewControllerImpl<T> extends Controller implements SearchViewController {


	public SearchViewControllerImpl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see at.itb13.oculus.application.SearchViewController#search(java.lang.String)
	 */	
	@Override
	public List<Patient> search(String criteria) {
		
		return _database.getSearchedPatient(criteria);
	}

}
