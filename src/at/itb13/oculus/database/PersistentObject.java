package at.itb13.oculus.database;

public interface PersistentObject {

	String getID();
	
	void setID(String id);
	
	int getVersion();
	
	void setVersion(int version);
}
