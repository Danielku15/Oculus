package at.itb13.oculus.application;

public interface SearchViewController extends AutoCloseable {
	
	void close();
	
	String[][] search(String criteria);
}
