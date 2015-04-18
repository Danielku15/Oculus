package at.itb13.oculus.application;

/**
 * @author Patrick
 *
 */
public class ObjectNotFoundException extends Exception {
	private static final long serialVersionUID = 6134098525702546932L;
	
	private Class<?> _type;
	private String _id;
	
	public ObjectNotFoundException(Class<?> type, String id) {
		_type = type;
		_id = id;
	}
	
	public String getClassName() {
		return _type.getName();
	}
	
	public String getID() {
		return _id;
	}
	
	@Override
	public String toString() {
		return "Object of class " + getClassName() + " with ID " + getID() + " not found!";
	}
}
