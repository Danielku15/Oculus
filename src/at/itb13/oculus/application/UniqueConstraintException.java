package at.itb13.oculus.application;

import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;

@SuppressWarnings("serial")
public class UniqueConstraintException extends Exception {
	
	private String _field;
	private Object _failedEntity;
	private Object _existingEntity;

	public UniqueConstraintException(String field, Object failedEntity, Object existingEntity) {
		this._field = field;
		this._failedEntity = failedEntity;
		this._existingEntity = existingEntity;
	}
	
	public String getField(){
		return _field;
	}
	
	public Object getFailedEntity(){
		return _failedEntity;
	}
	
	public Object getExistingEntity(){
		return _existingEntity;
	}

	@Override
	public String toString(){
		LangFacade langFacade = LangFacade.getInstance();
		return _field + " " + langFacade.getString(LangKey.ENTITYEXISTS);
	}
}
