package at.itb13.oculus.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import at.itb13.oculus.database.PersistentObjectImpl;

@Entity
@Table(name = "changelog", catalog = "oculusdb")
public class ChangeLog extends PersistentObjectImpl implements java.io.Serializable {
	private static final long serialVersionUID = 5043161499141730614L;
	
	private int _number;
	private String _changedTable;
	private String _changedId;
	private Date _modified;
	private Mutation _mutation;

	public ChangeLog() {}

	@Column(name = "number")
	public int getNumber() {
		return _number;
	}

	public void setNumber(int number) {
		_number = number;
	}

	@Column(name = "changedtable")
	public String getChangedTable() {
		return _changedTable;
	}

	public void setChangedTable(String changedTable) {
		_changedTable = changedTable;
	}
	
	@Column(name = "changedid")
	public String getChangedId() {
		return _changedId;
	}

	public void setChangedId(String changedId) {
		_changedId = changedId;
	}

	@Column(name = "modified")
	public Date getModified() {
		return _modified;
	}

	public void setModified(Date modified) {
		_modified = modified;
	}
	
	@Column(name = "mutation")
	@Enumerated(EnumType.STRING)
	public Mutation getMutation() {
		return _mutation;
	}

	public void setMutation(Mutation mutation) {
		_mutation = mutation;
	}
}
