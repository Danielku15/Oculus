package at.itb13.oculus.application;

import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.lang.LangKey;

@SuppressWarnings("serial")
public class RegExpException extends Exception {
	private String _attrName;
	private String _value;
	
	public RegExpException(String attrName, String value){
		this._attrName = attrName;
		this._value = value;
	}
	
	public String getAttrName(){
		return _attrName;
	}
	
	public String getValue(){
		return _value;
	}
	
	@Override
	public String toString(){
		
		LangFacade langFacade = LangFacade.getInstance();
		return langFacade.getString(LangKey.REGEXPERROR) + ": " + _attrName + " = " + _value;
	}
	
}
