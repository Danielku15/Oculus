package at.itb13.oculus.presentation;

import java.util.EventObject;

/**
 * 
 * this class defines the event for the {@link CriteriaChangeListener}
 *
 */
public class CriteriaChangeEvent extends EventObject {
	private static final long serialVersionUID = -7814738022601043251L;

	private String _criteria;
	
	public CriteriaChangeEvent(SearchPanelController source, String criteria) {
		super(source);
		_criteria = criteria;
	}
	
	public String getCriteria() {
		return _criteria;
	}
}
