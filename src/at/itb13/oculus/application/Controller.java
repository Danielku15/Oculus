package at.itb13.oculus.application;

import at.itb13.oculus.database.DBFacade;
/**
 * 
 * Superclass of all controllers and implements methods for instancing and closing database session
 *
 */
abstract class Controller implements AutoCloseable {
	protected final DBFacade _database;
	
	/**
	 * instance new database session
	 */
	public Controller() {
		_database = new DBFacade();
	}
	
	/**
	 * close database session
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close() {
		_database.close();
	}
}
