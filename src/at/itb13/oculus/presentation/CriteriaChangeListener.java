package at.itb13.oculus.presentation;

import java.util.EventListener;

public interface CriteriaChangeListener extends EventListener {
	void onCriteriaChange(CriteriaChangeEvent e);
}
