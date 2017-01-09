package by.pvt.pintusov.courses.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Logger for Courses System
 * @author pintusov
 * @version 1.0
 */
public class ClosingUtil {
	private ClosingUtil () {}

	/**
	 * Closing PreparedStatement instances
	 * @param statement getting statement for closing
	 */
	public static void close (PreparedStatement statement) {
		if (statement !=null) {
			try {
				statement.close();
			} catch (SQLException e) {
				CoursesSystemLogger.getInstance().logError(ClosingUtil.class, e.getMessage());
			}
		}
	}

	/**
	 * Closing ResultSet instances
	 * @param resultSet getting result set for closing
	 */
	public static void close (ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				CoursesSystemLogger.getInstance().logError(ClosingUtil.class, e.getMessage());
			}
		}
	}
}
