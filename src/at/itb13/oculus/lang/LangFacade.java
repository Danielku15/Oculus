package at.itb13.oculus.lang;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import at.itb13.oculus.config.Config;
import at.itb13.oculus.config.ConfigFacade;

/**
 * @author Patrick
 *
 */
public class LangFacade {
	
	private static final String BASENAME = "bundle.lang";
	
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
	
	public static void load(Lang lang) throws IOException {
		_langFacade.load(BASENAME, lang);
	}
	
	private void load(String fileName, Lang lang) {
		ConfigFacade configFacade = ConfigFacade.getInstance();
		String language;
		String country;
		if (lang == Lang.GERMAN){
			language = configFacade.getProperty(Config.LANGUAGE_DE);
			country = configFacade.getProperty(Config.COUNTRY_DE);	
		}
		else {
			language = configFacade.getProperty(Config.LANGUAGE_EN);
			country = configFacade.getProperty(Config.COUNTRY_US);			
		}
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
