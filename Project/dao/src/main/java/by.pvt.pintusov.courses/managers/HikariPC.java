package by.pvt.pintusov.courses.managers;

import by.pvt.pintusov.courses.constants.DBConfig;
import by.pvt.pintusov.courses.utils.CoursesSystemLogger;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Pool manager Hikari
 * @author pintusov
 * @version 1.0
 */

public class HikariPC {
	private static HikariPC instance;
	HikariConfig config = new HikariConfig(DBConfig.HIKARI);
	HikariDataSource dataSource = new HikariDataSource(config);

	/**
	 * Singleton for creating HikariPC
	 * @return instance of Hikari
	 */
	public static synchronized HikariPC getInstance () {
		if (instance == null) {
			instance = new HikariPC();
		}
		return instance;
	}

	/**
	 * @return connection - Connection instance
	 * @throws SQLException
	 */
	public Connection getConnection () throws SQLException {
		Connection connection = dataSource.getConnection();
		return connection;
	}

	/**
	 * Closing connection
	 * @param connection choose connection
	 */
	public void releaseConnection (Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				String message ="Unable to close connection";
				CoursesSystemLogger.getInstance().logError(getClass(), message);
			}
		}
	}
}
