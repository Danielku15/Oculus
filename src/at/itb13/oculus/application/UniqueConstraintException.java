package at.itb13.oculus.application;

import at.itb13.oculus.database.PersistentObject;

/**
 * @author Patrick
 *
 */
public class UniqueConstraintException extends Exception {
	private static final long serialVersionUID = -6157620279567597607L;
	
	private String _field;
	private PersistentObject _failedEntity;
	private PersistentObject _existingEntity;

	public UniqueConstraintException(String field, PersistentObject failedEntity, PersistentObject existingEntity) {
		this._field = field;
		this._failedEntity = failedEntity;
		this._existingEntity = existingEntity;
	}
	
	public String getField() {
		return _field;
	}
	
	public String getFailedEntityID() {
		return _failedEntity.getID();
	}
	
	public String getExistingEntityID() {
		return _existingEntity.getID();
	}

	@Override
	public String toString() {
		return _field + " is a unique field and already exists in the database!";
	}
}
