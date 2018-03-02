package databaas;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaasLogger {
	
	private static Logger defaultLogger = Logger.getLogger("");

	public static Logger getDefaultLogger() {
		return DatabaasLogger.defaultLogger;
	}

	public static void setDefaultLogger(Logger defaultLogger) {
		DatabaasLogger.defaultLogger = defaultLogger;
	}
	
	public static void log(Exception e, String message) {
		defaultLogger.log(Level.SEVERE, message, e);
	}
	
}
