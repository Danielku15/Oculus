package at.itb13.oculus.service;

import java.util.EventListener;

/**
 * 
 * Interface for a listener of changes depending on the event
 *
 */
public interface TableChangeListener extends EventListener {
	void onTableChange(TableChangeEvent e);
}
