package at.itb13.oculus.config;

/** Represents an entry in the configuration file that can be access with a specified key
 * @author Patrick
 */
public enum ConfigKey {
	LANGUAGE("language"),
	COUNTRY("country"),
	INDEX_INTERVAL("index_interval"),
	INDEX_NUMBER("index_number");
	
	private String _key;
	
	private ConfigKey(String key) {
		_key = key;
	}
	
	public String getKey() {
		return _key;
	}
}
