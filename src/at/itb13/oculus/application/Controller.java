package at.itb13.oculus.application;

import at.itb13.oculus.database.DBFacade;

public abstract class Controller implements AutoCloseable {
	protected final DBFacade _database;
	
	public Controller() {
		_database = new DBFacade();
	}

	@Override
	public void close() {
		_database.close();
	}
}
