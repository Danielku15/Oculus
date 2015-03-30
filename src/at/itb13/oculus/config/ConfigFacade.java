package at.itb13.oculus.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Patrick
 *
 */
public class ConfigFacade {

	private static final String CONFIGFILE = "config.properties";
	
	private static ConfigFacade _configFacade;
	
	private Properties _configProperties;
	private boolean _loaded;
	
	static {
		_configFacade = new ConfigFacade();
	}
	
	private ConfigFacade() {
		_configProperties = new Properties();
	}
	
	public static ConfigFacade getInstance() {
		if(!_configFacade._loaded) {
			throw new ConfigNotLoadedException();
		}
		return _configFacade;
	}
	
	public static void load() throws IOException {
		_configFacade.load(CONFIGFILE);
	}
	
	private void load(String fileName) throws IOException {
		InputStream in = null;
		try {
			in = getClass().getClassLoader().getResourceAsStream(fileName);
			_configProperties.load(in);
			_loaded = true;
		} finally {
			if(in != null) {
				in.close();
			}
		}
	}
	
	public String getProperty(Config key) {
		return _configProperties.getProperty(key.getKey());
	}
	
	public String getProperty(Config key, String defaultValue) {
		return _configProperties.getProperty(key.getKey(), defaultValue);
	}
}
