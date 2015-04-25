package at.itb13.oculus.application;

import at.itb13.oculus.database.DBFacade;
/**
 * 
 * Superclass of all controllers and implements methods for instantiating and closing database facade
 *
 */
abstract class Controller implements AutoCloseable {
	protected final DBFacade _database;
	
	/**
	 * instantiate new controller with new database facade
	 */
	public Controller() {
		_database = new DBFacade();
	}
	
	/**
	 * close controller and its associated database facade
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close() {
		_database.close();
	}
}
