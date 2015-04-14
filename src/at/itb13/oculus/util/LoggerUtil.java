package at.itb13.oculus.util;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public final class LoggerUtil {
	
	private static final String LOGFILE = "log/log.txt";
	
	private static Handler _fileHandler;
	private static Formatter _simpleFormatter;
	
	public static void setup() throws IOException {
		// get the global logger to configure it
	    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	    // set logging level
	    logger.setLevel(Level.INFO);
	    // initialize file handler
	    _fileHandler = new FileHandler(LOGFILE, true);
	    // initialize formatter
	    _simpleFormatter = new SimpleFormatter();
	    // set formatter of handler
	    _fileHandler.setFormatter(_simpleFormatter);
	    // add handler to global logger
	    logger.addHandler(_fileHandler);
	}
	
	public static void close() {
		_fileHandler.close();
	}
}
