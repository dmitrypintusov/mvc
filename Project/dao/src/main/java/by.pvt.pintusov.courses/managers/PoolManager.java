package by.pvt.pintusov.courses.managers;

import by.pvt.pintusov.courses.constants.DBConfig;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.utils.CoursesSystemLogger;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Pool manager
 * @author pintusov
 * @version 1.0
 */

public class PoolManager {
	private static PoolManager instance;
	private BasicDataSource dataSource;
	private static ThreadLocal <Connection> connectionHolder = new ThreadLocal<>();

	/**
	 * Setting properties for pool manager from database.properties
	 */
	private PoolManager () {
		ResourceBundle bundle = ResourceBundle.getBundle(DBConfig.DB);
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(bundle.getString(DBConfig.DRIVER));
		dataSource.setUrl(bundle.getString(DBConfig.URL));
		dataSource.setUsername(bundle.getString(DBConfig.USER));
		dataSource.setPassword(bundle.getString(DBConfig.PASSWORD));
	}

	/**
	 * Singleton for creating Pool manager
	 * @return instance of pool manager
	 */
	public static synchronized PoolManager getInstance () {
		if (instance == null) {
			instance = new PoolManager();
		}
		return instance;
	}

	/**
	 * Starting connection
	 * @return connection
	 */
	private Connection connect () throws SQLException {
		Connection connection = dataSource.getConnection();
		return connection;
	}

	/**
	 * Getting connection
	 * @return connection
	 * @exception DaoException thows Dao Exception
	 */
	public Connection getConnection () throws DaoException {
		try {
			if (connectionHolder.get() == null) {
				Connection connection = connect ();
				connectionHolder.set(connection);
			}
		} catch (SQLException e) {
			String message ="Unable to get connection";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		}
		return connectionHolder.get();
	}

	/**
	 * Closing connection
	 * @param connection choose connection
	 */
	public void releaseConnection (Connection connection) {
		if (connection != null) {
			try {
				connection.close();
				connectionHolder.remove();
			} catch (SQLException e) {
				String message ="Unable to close connection";
				CoursesSystemLogger.getInstance().logError(getClass(), message);
			}
		}
	}
}