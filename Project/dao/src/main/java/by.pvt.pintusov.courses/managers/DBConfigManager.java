package by.pvt.pintusov.courses.managers;

import by.pvt.pintusov.courses.constants.DBConfig;

import java.util.ResourceBundle;

/**
 * JDBC
 * @author pintusov
 * @version 1.0
 */


public class DBConfigManager {
	private final ResourceBundle bundle = ResourceBundle.getBundle(DBConfig.DB);
	private static DBConfigManager instance;

	private DBConfigManager() {}

	public static synchronized DBConfigManager getInstance () {
		if (instance == null) {
			instance = new DBConfigManager();
		}
		return instance;
	}

	public String getProperty (String key) { return bundle.getString(key); }
}
