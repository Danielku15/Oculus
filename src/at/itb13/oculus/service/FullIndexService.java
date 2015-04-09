package at.itb13.oculus.service;

import at.itb13.oculus.database.DBFacade;

public class FullIndexService extends Thread {

	private DBFacade _dbFacade;
	private boolean _running;
	
	public FullIndexService() {
		_dbFacade = new DBFacade();
	}
	
	@Override
	public void run() {
		try {
			_dbFacade.indexAll();
		} catch (InterruptedException e) {
			// thread has been interrupted
		}
		_running = false;
	}
	
	@Override
	public void start() {
		super.start();
		_running = true;
	}
	
	public boolean isRunning() {
		return _running;
	}
}
