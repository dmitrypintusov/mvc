package by.pvt.pintusov.courses.managers;

import by.pvt.pintusov.courses.constants.DBConfig;
import by.pvt.pintusov.courses.utils.CoursesSystemLogger;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
/**
 * Pool manager Hikari
 * @author pintusov
 * @version 1.0
 */

public class HikariCP {
	private static HikariCP instance;
	private HikariDataSource dataSource;

	private HikariCP () {
		ResourceBundle bundle = ResourceBundle.getBundle(DBConfig.HIKARI);
		dataSource = new HikariDataSource();
		dataSource.setJdbcUrl(bundle.getString(DBConfig.HIKARI_URL));
		dataSource.setUsername(bundle.getString(DBConfig.HIKARI_USER));
		dataSource.setPassword(bundle.getString(DBConfig.HIKARI_PASSWORD));
	}

	/**
	 * Singleton for creating HikariCP
	 * @return instance of Hikari
	 */
	public static synchronized HikariCP getInstance () {
		if (instance == null) {
			instance = new HikariCP();
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
