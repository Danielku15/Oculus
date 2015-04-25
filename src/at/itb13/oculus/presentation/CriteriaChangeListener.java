package at.itb13.oculus.presentation;

import java.util.EventListener;

/**
 * 
 * Interface for a listener of changes depending on the criteria event
 *
 */
public interface CriteriaChangeListener extends EventListener {
	void onCriteriaChange(CriteriaChangeEvent e);
}
