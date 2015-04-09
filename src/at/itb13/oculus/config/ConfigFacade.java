package at.itb13.oculus.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
			in = new FileInputStream(new File(CONFIGFILE));
			_configProperties.load(in);
			_loaded = true;
		} finally {
			if(in != null) {
				in.close();
			}
		}
	}
	
	public void save() throws IOException {
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File(CONFIGFILE));
			_configProperties.store(out, null);
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}
	
	public String getProperty(Config key) {
		return _configProperties.getProperty(key.getKey());
	}
	
	public String getProperty(Config key, String defaultValue) {
		return _configProperties.getProperty(key.getKey(), defaultValue);
	}
	
	public void setProperty(Config key, String value) {
		_configProperties.setProperty(key.getKey(), value);
	}
}
