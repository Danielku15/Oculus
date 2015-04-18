package at.itb13.oculus.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Patrick
 *
 */
public class ConfigFactory {

	public static final String CONFIGFILE = "config.properties";
	
	private static ConfigFactory _configFactory;
	private Map<String, Config> _configMap;
	
	private ConfigFactory() {
		_configMap = new HashMap<String, Config>();
	}
	
	public static ConfigFactory getInstance() {
		if(_configFactory == null) {
			_configFactory = new ConfigFactory();
		}
		return _configFactory;
	}
	
	public Config getConfig(String filePath) {
		if(!_configMap.containsKey(filePath)) {
			_configMap.put(filePath, new Config(filePath));
		} 
		return _configMap.get(filePath);
	}
	
	public class Config {
		
		private String _filePath;
		private Properties _properties;
		private boolean _loaded;
		
		private Config(String filePath) {
			_filePath = filePath;
			_properties = new Properties();
		}
		
		public void load() throws IOException {
			InputStream in = null;
			try {
				in = new FileInputStream(new File(_filePath));
				_properties.load(in);
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
				out = new FileOutputStream(new File(_filePath));
				_properties.store(out, null);
			} finally {
				if(out != null) {
					out.close();
				}
			}
		}
		
		public String getProperty(String key) {
			return _properties.getProperty(key);
		}
		
		public String getProperty(String key, String defaultValue) {
			return _properties.getProperty(key);
		}
		
		public void setProperty(String key, String value) {
			_properties.setProperty(key, value);
		}
		
		public boolean isLoaded() {
			return _loaded;
		}
	}
}
