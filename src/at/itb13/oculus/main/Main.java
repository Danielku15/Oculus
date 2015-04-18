package at.itb13.oculus.main;

import java.io.IOException;
import java.util.MissingResourceException;
import java.util.logging.Logger;

import at.itb13.oculus.config.ConfigFactory;
import at.itb13.oculus.config.ConfigFactory.Config;
import at.itb13.oculus.lang.LangFacade;
import at.itb13.oculus.presentation.GUIApplication;
import at.itb13.oculus.service.IndexService;
import at.itb13.oculus.util.LoggerUtil;

public class Main {

	public static void main(String[] args) {
		new Main().init(args);
	}
	
	public static void exit(int status) {
		LoggerUtil.close();
		System.exit(status);
	}
	
	public void init(String[] args) {
		
		// setup logger
		Logger logger = null;
		try {
			LoggerUtil.setup();
			logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		} catch (IOException e) {
			// could not setup logger
			e.printStackTrace();
			Main.exit(1);
		}
		
		// load configuration
		try {
			ConfigFactory configFactory = ConfigFactory.getInstance();
			Config config = configFactory.getConfig(ConfigFactory.CONFIGFILE);
			config.load();
		} catch (IOException e) {
			// could not load configuration
			logger.severe(e.getMessage());
			Main.exit(2);
		}
		
		// load language
		try {
			LangFacade.load();
		} catch (MissingResourceException e) {
			// could not load 
			logger.severe(e.getMessage());
			Main.exit(3);
		}
		
		//start index service
		new IndexService().start();
		
		// start GUI
		GUIApplication.main(args);
	}
}
