package at.itb13.oculus.database;

/**
 * 
 * Interface for PersistentObject
 *
 */
public interface PersistentObject {

	String getID();
	
	void setID(String id);
	
	int getVersion();
	
	void setVersion(int version);
}
