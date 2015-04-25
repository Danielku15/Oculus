package at.itb13.oculus.lang;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import at.itb13.oculus.config.ConfigFactory;
import at.itb13.oculus.config.ConfigFactory.Config;
import at.itb13.oculus.config.ConfigKey;

/**
 * 
 * Facade for the correct structure of the interface of all texts
 * It builds a class for easy access and defines the right language and language file
 *
 */
public class LangFacade {
	
	private static final String BASENAME = "lang";
	
	private static LangFacade _langFacade;
	
	private Locale _locale;
	private ResourceBundle _resourceBundle;
	
	static {
		_langFacade = new LangFacade();
	}
	
	private LangFacade() {}
	
	public static LangFacade getInstance() {
		return _langFacade;
	}
	
	/**
	 * static method which runs the private load method with the {@link LangFacade#BASENAME} as parameter
	 * @throws MissingResourceException if the {@link LangFacade#BASENAME} language not exists
	 */
	public static void load() throws MissingResourceException {
		_langFacade.load(BASENAME);
	}
	
	/**
	 * loads all important configurations into the {@link ConfigFactory} instance
	 * @param fileName language
	 * @throws MissingResourceException if the {@link LangFacade#BASENAME} language not exists
	 */
	private void load(String fileName) throws MissingResourceException {
		ConfigFactory configFactory = ConfigFactory.getInstance();
		Config config = configFactory.getConfig(ConfigFactory.CONFIGFILE);
		String language = config.getProperty(ConfigKey.LANGUAGE.getKey());
		String country = config.getProperty(ConfigKey.COUNTRY.getKey());
		_locale = new Locale(language, country);
		_resourceBundle = ResourceBundle.getBundle(fileName, _locale);
	}
    
    public String getString(LangKey key) {
    	return _resourceBundle.getString(key.getKey());
    }
    
    public ResourceBundle getResourceBundle(){
		return _resourceBundle;
    }
}
