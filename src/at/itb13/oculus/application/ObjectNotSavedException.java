package at.itb13.oculus.application;

import at.itb13.oculus.database.PersistentObject;

public class ObjectNotSavedException extends Exception {
	private static final long serialVersionUID = -5514904979060339632L;
	
	private PersistentObject _object;
	
	public ObjectNotSavedException(PersistentObject object) {
		_object = object;
	}
	
	public String getPersistentObjectID() {
		return _object.getID();
	}
	
	@Override
	public String toString() {
		return "Object of class " + _object.getClass() + " with ID " + _object.getID() + " not saved!";
	}
}
