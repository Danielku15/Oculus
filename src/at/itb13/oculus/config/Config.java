package at.itb13.oculus.config;

/**
 * represents an entry in the configuration file that can be access with a specified key
 * @author Patrick
 */
public enum Config {
	LANGUAGE_EN("language_en"),
	COUNTRY_US("country_US"),
	
	LANGUAGE_DE("language_de"),
	COUNTRY_DE("country_DE");
	
	private String _key;
	
	private Config(String key) {
		_key = key;
	}
	
	public String getKey() {
		return _key;
	}
}
