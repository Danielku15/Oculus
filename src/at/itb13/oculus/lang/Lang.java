package at.itb13.oculus.lang;

/**
 * @author Patrick
 *
 */
public enum Lang {
	GERMAN("DE"), ENGLISH("EN");
	
	private String _languageCode;
	
	private Lang(String languageCode) {
		_languageCode = languageCode;
	}
	
	public String getLanguageCode() {
		return _languageCode;
	}
}
