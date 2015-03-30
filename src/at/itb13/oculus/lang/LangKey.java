package at.itb13.oculus.lang;

/**
 * represents an entry in the configuration file that can be access with a specified key
 * @author Patrick
 */
public enum LangKey {
	DOCTOR("doctor"),
	RECEPTIONIST("receptionist"),
	ORTHOPTIST("orthoptist");
	
	private String _key;
	
	private LangKey(String key) {
		_key = key;
	}
	
	public String getKey() {
		return _key;
	}
}
