package by.pvt.pintusov.courses.utils;

import org.apache.log4j.Logger;

/**
 * Logger for Courses System
 * @author pintusov
 * @version 1.0
 */
public class CoursesSystemLogger {
	private Logger logger;
	private static CoursesSystemLogger instance;

	private CoursesSystemLogger () {}

	/**
	 * Singleton for creating Courses System Logger
	 * @return instance of logger
	 */
	public static synchronized CoursesSystemLogger getInstance () {
		if (instance == null) {
			instance = new CoursesSystemLogger();
		}
		return instance;
	}

	/**
	 * Used to log error
	 * @param sender class that creates exception
	 * @param message message created by exception
	 */
	public void logError (Class sender, String message) {
		logger = Logger.getLogger(sender);
		logger.error(message);
	}
}
