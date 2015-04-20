package at.itb13.oculus.service;

import java.util.EventObject;

public class TableChangeEvent extends EventObject {
	private static final long serialVersionUID = 342226452742973653L;
	
	private String _id;
	
	public TableChangeEvent(String table, String id) {
		super(table);
		_id = id;
	}
	
	public String getID() {
		return _id;
	}	
}
