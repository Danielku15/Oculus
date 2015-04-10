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
	public String[][] search(String criteria) {
		
		List<Patient> list =  _database.getSearchedPatient(criteria);
		
		String[][] array = new String[list.size()][];
		
		for(int i = 0; i < list.size(); i++) {
			Patient p = list.get(i);
			String[] row = new String[] {p.getFirstname(), p.getLastname(), null, p.getSocialSecurityNumber()};
			array[i] = row;
		}
		
		return array;
	}

}
