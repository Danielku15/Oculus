package at.itb13.oculus.service;

import java.util.Date;
import java.util.EventObject;

public class TableChangeEvent extends EventObject {
	private static final long serialVersionUID = 342226452742973653L;
	
	private String _id;
	private Date _modified;
	
	public TableChangeEvent(String table, String id, Date modified) {
		super(table);
		_id = id;
		_modified = modified;
	}
	
	public String getID() {
		return _id;
	}
	
	public Date getModified() {
		return _modified;
	}
}
