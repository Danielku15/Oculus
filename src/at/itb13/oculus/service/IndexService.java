package at.itb13.oculus.service;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.HibernateException;

import at.itb13.oculus.config.Config;
import at.itb13.oculus.config.ConfigFacade;
import at.itb13.oculus.database.DBFacade;
import at.itb13.oculus.database.PersistentObject;
import at.itb13.oculus.model.ChangeLog;
import at.itb13.oculus.model.Patient;

public class IndexService {
	
	private static final int BATCHSIZE = 100;
	
	private ConfigFacade _configFacade;
	private int _curNumber;
	private IndexTask _indexTask;
	private Timer _timer;
	private boolean _running;
	
	public IndexService() {
		_configFacade = ConfigFacade.getInstance();
		_curNumber = Integer.valueOf(_configFacade.getProperty(Config.INDEX_NUMBER));
	}
	
	public synchronized void start() {
		if(!_running) {
			_indexTask = new IndexTask();
			_timer = new Timer();
			_timer.scheduleAtFixedRate(_indexTask, 0, Integer.valueOf(_configFacade.getProperty(Config.INDEX_INTERVAL)));
			_running = true;
		}
	}
	
	public synchronized void cancel() {
		if(_running) {
			_timer.cancel();
			_timer.purge();
			_indexTask.cancel();
			_indexTask = null;
			_running = false;
		}
	}
	
	public boolean isRunning() {
		return _running;
	}
	
	private class IndexTask extends TimerTask {
		
		private DBFacade _dbFacade;

		public IndexTask() {
			_dbFacade = new DBFacade();
		}
		
		@Override
		public boolean cancel() {
			boolean result = super.cancel();
			_dbFacade.close();
			return result;
		}
		
		@Override
		public void run() {
			// process next batch
			process(getNextBatch());
		}
		
		private List<ChangeLog> getNextBatch() {
			List<ChangeLog> nextBatch = null;
			try {
				_dbFacade.beginTransaction();
				nextBatch = _dbFacade.getChangeLogsGreaterThan(_curNumber, BATCHSIZE);
				_dbFacade.commitTransaction();
			} catch(HibernateException e) {
				_dbFacade.rollbackTransaction();
				nextBatch = null;
			}
			return nextBatch;
		}
		
		private void process(List<ChangeLog> changeLogs) {
			if((changeLogs != null) && !changeLogs.isEmpty()) {
				int curNumber = _curNumber;
				try {
					_dbFacade.beginTransactionFulltext();
					Iterator<ChangeLog> iter = changeLogs.iterator();
					while(iter.hasNext()) {
						ChangeLog changeLog = iter.next();
						switch(changeLog.getMutation()) {
						case D:
							break;
						case I:
						case U:
							updateIndex(changeLog.getChangedTable(), changeLog.getChangedId());
							break;
						default:
							throw new InternalError("Undefined mutation: " + changeLog.getMutation());
						}
						curNumber = changeLog.getNumber();
					}
					_dbFacade.commitTransactionFulltext();
					_curNumber = curNumber;
					try {
						storeCurNumber();
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch(HibernateException e) {
					_dbFacade.rollbackTransactionFulltext();
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
	}
}
