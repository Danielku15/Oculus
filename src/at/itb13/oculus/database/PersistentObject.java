package at.itb13.oculus.database;

/**
 * @author Patrick
 *
 */
public interface PersistentObject {

	String getID();
	
	void setID(String id);
	
	int getVersion();
	
	void setVersion(int version);
}
