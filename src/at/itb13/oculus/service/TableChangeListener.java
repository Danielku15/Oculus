package at.itb13.oculus.service;

import java.util.EventListener;

public interface TableChangeListener extends EventListener {
	void onTableChange(TableChangeEvent e);
}
