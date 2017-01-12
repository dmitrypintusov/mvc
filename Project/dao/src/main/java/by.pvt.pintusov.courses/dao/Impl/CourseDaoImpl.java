package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.constants.ColumnName;
import by.pvt.pintusov.courses.constants.SqlRequest;
import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.entities.Course;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.managers.PoolManager;
import by.pvt.pintusov.courses.utils.ClosingUtil;
import by.pvt.pintusov.courses.utils.CoursesSystemLogger;
import by.pvt.pintusov.courses.utils.EntityBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Course Dao implementation
 * @author pintusov
 * @version 1.0
 */

public class CourseDaoImpl extends AbstractDao <Course> {
	private static CourseDaoImpl instance;
	static String message;

	private CourseDaoImpl () {}
	/**
	 * Singleton for creating CourseDaoImpl instance
	 * @return instance of UserDaoImpl
	 */
	public static synchronized CourseDaoImpl getInstance () {
		if (instance == null) {
			instance = new CourseDaoImpl();
		}
		return instance;
	}

	/**
	 * Add Course to database
	 * @param course instance of Course to add
	 * @throws DaoException
	 */
	@Override
	public void add(Course course) throws DaoException {
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.ADD_COURSE);
			statement.setString(1, course.getCourseName());
			statement.setInt(2, course.getHours());
			statement.setInt(3, course.getStatus());
			statement.executeUpdate();
		} catch (SQLException e) {
			message = "Unable to add course ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw  new DaoException(message, e);
		} finally {
			ClosingUtil.close(statement);
		}
	}

	/**
	 * Get all courses from database
	 * @return list of courses
	 * @throws DaoException
	 */
	@Override
	public List<Course> getAll() throws DaoException {
		List <Course> list = new ArrayList<>();
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.GET_ALL_COURSES);
			result = statement.executeQuery();
			while (result.next()){
				Course course = buildCourse (result);
				list.add(course);
			}
		} catch (SQLException e) {
			message = "Unable to get courses list ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return list;
	}

	/**
	 * Get Course from database
	 * @param id id of Course to get
	 * @return course instance
	 * @throws DaoException
	 */
	public Course getById (int id) throws DaoException {
		Course course = null;
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.GET_COURSE_BY_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				course =buildCourse(result);
			}
		} catch (SQLException e) {
			message = "Unable to get course by id ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return course;
	}

	/**
	 * Check if Course is ended
	 * @param id - Course to check
	 * @return isEnded course status
	 * @throws DaoException
	 */
	public boolean isCourseStatusEnded (int id) throws DaoException {
		boolean isEnded = false;
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.CHECK_COURSE_STATUS);
			statement.setInt(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				if (result.getInt("status") == 1) {
					isEnded = true;
				}
			}
		} catch (SQLException e) {
			message = "Unable to check course status ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return isEnded;
	}

	/**
	 * Get list of active courses
	 * @return list of active courses
	 * @throws DaoException
	 */
	public List <Course> getActiveCourses () throws DaoException {
		List <Course> list = new ArrayList<>();
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.GET_ACTIVE_COURSES);
			result = statement.executeQuery();
			while (result.next()){
				Course course = buildCourse(result);
				list.add(course);
			}
		} catch (SQLException e) {
			message = "Unable to get active courses ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return list;
	}

	/**
	 * Update Course status
	 * @param id of Course to update
	 * @param status of course
	 * @throws DaoException
	 */
	public void updateCourseStatus (int id, int status) throws DaoException {
		try {
			connection =PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.UPDATE_COURSE_STATUS);
			statement.setInt(1, id);
			statement.setInt(2, status);
			statement.executeUpdate();
		} catch (SQLException e) {
			message = "Unable to update course status ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(statement);
		}
	}

	/**
	 * Delete Course by course name
	 * @param courseName of Course to deleteByCourseName
	 * @throws DaoException
	 */
	public void deleteByCourseName(String courseName) throws DaoException {
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.DELETE_COURSE_BY_COURSENAME);
			statement.setString(1, courseName);
			statement.executeUpdate();
		} catch (SQLException e) {
			message = "Unable to deleteByCourseName course by id ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(statement);
		}
	}

	/**
	 * Get max Course id
	 * @return lastId
	 * @throws DaoException
	 */
	@Override
	public int getMaxId() throws DaoException {
		int lastId = -1;
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.GET_LAST_COURSE_ID);
			result = statement.executeQuery();
			while (result.next()) {
				lastId = result.getInt(1);
			}
		} catch (SQLException e) {
			message = "Unable to get max course id ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return lastId;
	}

	/**
	 * Build course instance
	 * @param result - using ResultSet to build course instance
	 * @return course
	 * @throws SQLException
	 */
	private Course buildCourse (ResultSet result) throws SQLException {
		String courseName = result.getString(ColumnName.COURSES_NAME);
		int hours = result.getInt(ColumnName.COURSES_HOURS);
		int status = result.getInt(ColumnName.COURSES_STATUS);
		Course course = EntityBuilder.buildCourse(courseName, hours, status);
		return course;
	}

	/**
	 * No needs to implement
	 * @param course
	 * @throws UnsupportedOperationException
	 * @throws DaoException
	 */
	@Override
	public void update(Course course) throws DaoException {
		throw new UnsupportedOperationException();
	}

	/**
	 * No needs to implement
	 * @throws UnsupportedOperationException
	 * @throws DaoException
	 */
	@Override
	public void delete() throws DaoException {
		throw new UnsupportedOperationException();
	}
}
