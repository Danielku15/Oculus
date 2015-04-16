package at.itb13.oculus.application;

import at.itb13.oculus.database.PersistentObject;

public class DataMismatchException extends Exception {
	private static final long serialVersionUID = -9077841565433970120L;

	private PersistentObject _expected;
	private PersistentObject _received;
	
	public DataMismatchException(PersistentObject expected, PersistentObject received) {
		_expected = expected;
		_received = received;
	}
	
	public String getExpectedClassName() {
		return _expected.getClass().getName();
	}
	
	public String getReceivedClassName() {
		return _received.getClass().getName();
	}
	
	public String getExpectedID() {
		return _expected.getID();
	}
	
	public String getReceivedID() {
		return _received.getID();
	}
	
	@Override
	public String toString() {
		return "Expected " + getExpectedClassName() + " object with id " + getExpectedID() + ", received " + getReceivedClassName() + " object with id " + getReceivedID() + "!";
	}
}
