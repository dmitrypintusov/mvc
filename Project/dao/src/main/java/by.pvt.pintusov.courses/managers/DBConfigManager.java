package by.pvt.pintusov.courses.managers;

import by.pvt.pintusov.courses.constants.DBConfigConstant;

import java.util.ResourceBundle;

/**
 * Created by USER on 02.12.16.
 */
public class DBConfigManager {
	private final ResourceBundle bundle = ResourceBundle.getBundle(DBConfigConstant.DATABASE_SOURCE);
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
