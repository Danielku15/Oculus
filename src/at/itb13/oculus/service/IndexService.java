package at.itb13.oculus.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import at.itb13.oculus.config.Config;
import at.itb13.oculus.config.ConfigFacade;
import at.itb13.oculus.database.DBFacade;
import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.model.ChangeLog;
import at.itb13.oculus.model.Patient;

public class IndexService extends Thread {
	
	private static final int BATCHSIZE = 100;

	private ConfigFacade _configFacade;
	private DBFacade _dbFacade;
	private boolean _running;
	private int _curNumber;
	
	public IndexService() {
		_configFacade = ConfigFacade.getInstance();
		_dbFacade = new DBFacade();
		_running = true;
		_curNumber = Integer.valueOf(_configFacade.getProperty(Config.INDEX_NUMBER));
	}
	
	@Override
	public void run() {
		while(isRunning()) {
			try {
				// process next batch
				process(getNextBatch());
				// wait for defined interval
				sleep(Integer.valueOf(_configFacade.getProperty(Config.INDEX_INTERVAL)));
			} catch (InterruptedException e) {
				// thread has been interrupted
				_running = false;
			}
		}
	}
	
	private List<ChangeLog> getNextBatch() {
		_dbFacade.beginTransaction();
		List<ChangeLog> nextBatch = _dbFacade.getChangeLogsGreaterThan(_curNumber, BATCHSIZE);
		_dbFacade.commitTransaction();
		return nextBatch;
	}
	
	private void process(List<ChangeLog> changeLogs) {
		if(!changeLogs.isEmpty()) {
			_dbFacade.beginTransactionFulltext();
			Iterator<ChangeLog> iter = changeLogs.iterator();
			while(iter.hasNext()) {
				ChangeLog changeLog = iter.next();
				switch(changeLog.getMutation()) {
				case D:
					// TODO add processing for deletions
					break;
				case I:
				case U:
					updateIndex(changeLog.getChangedTable(), changeLog.getChangedId());
					break;
				default:
					throw new InternalError("Undefined mutation: " + changeLog.getMutation());
				}
				_curNumber = changeLog.getNumber();
			}
			_dbFacade.commitTransactionFulltext();
			try {
				storeCurNumber();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateIndex(String table, String id) {
		PersistentObject object = null;
		switch(table) {
		case "patient":
			object = _dbFacade.get(Patient.class, id);
			break;
		default:
			throw new InternalError("Undefined table: " + table);
		}
		_dbFacade.index(object);
	}
	
	private void storeCurNumber() throws IOException {
		_configFacade.setProperty(Config.INDEX_NUMBER, String.valueOf(_curNumber));
		_configFacade.save();
	}
	
	public boolean isRunning() {
		return _running;
	}
	
	public void kill() {
		_running = false;
	}
}
