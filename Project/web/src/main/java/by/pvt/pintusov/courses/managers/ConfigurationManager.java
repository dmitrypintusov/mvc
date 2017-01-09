package by.pvt.pintusov.courses.managers;

import by.pvt.pintusov.courses.constants.ConfigConstant;

import java.util.ResourceBundle;

/**
 * Configuration manager for web
 * @author pintusov
 * @version 1.0
 */

public class ConfigurationManager {
	private final ResourceBundle bundle = ResourceBundle.getBundle(ConfigConstant.CONFIGS_SOURCE);
	private static ConfigurationManager instance;

	private ConfigurationManager () {}

	/**
	 * Singleton for configuration manager
	 * @return instance of Configuration manager
	 */
	public static synchronized ConfigurationManager getInstance () {
		if (instance == null) {
			instance = new ConfigurationManager();
		}
		return instance;
	}

	/**
	 * Getting pages parameters
	 * @return page path
	 */
	public String getProperty (String key) { return bundle.getString(key); }
}
