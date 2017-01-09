package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.constants.TransactionStatus;
import by.pvt.pintusov.courses.dao.Impl.CourseDaoImpl;
import by.pvt.pintusov.courses.dao.Impl.OperationDaoImpl;
import by.pvt.pintusov.courses.entities.Course;
import by.pvt.pintusov.courses.entities.Operation;
import by.pvt.pintusov.courses.entities.User;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.managers.PoolManager;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.utils.CoursesSystemLogger;

import java.sql.SQLException;
import java.util.List;

/**
 * Course service implementation
 * @author pintusov
 * @version 1.0
 */

public class CourseServiceImpl extends AbstractService <Course> {
	private static CourseServiceImpl instance;

	private CourseServiceImpl () {}

	/**
	 * Singleton for creating CourseServiceImpl
	 * @return instance of CourseServiceImpl
	 */
	public static synchronized CourseServiceImpl getInstance () {
		if (instance == null) {
			instance = new CourseServiceImpl();
		}
		return instance;
	}

	/**
	 * Calls Dao add() method
	 * @param course - object of Course class
	 * @throws SQLException
	 * @throws ServiceException
	 */
	@Override
	public void add(Course course) throws SQLException, ServiceException {
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			CourseDaoImpl.getInstance().add(course);
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException | DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Calls Dao getAll() method
	 * @return users of User class objects
	 * @throws SQLException
	 * @throws UnsupportedOperationException
	 */
	@Override
	public List<Course> getAll() throws SQLException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Calls Dao getById() method
	 * @param id - id of course
	 * @throws SQLException
	 * @throws ServiceException
	 */
	@Override
	public Course getById(int id) throws SQLException, ServiceException {
		Course course = null;
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			course = CourseDaoImpl.getInstance().getById(id);
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException | DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
			throw new ServiceException(e.getMessage());
		}
		return course;
	}

	/**
	 * Calls Dao update() method
	 * @param entity course entity
	 * @throws SQLException
	 * @throws ServiceException
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void update(Course entity) throws SQLException, ServiceException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Calls Dao delete() method
	 * @param id course id
	 * @throws SQLException
	 * @throws ServiceException
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void delete(int id) throws SQLException, ServiceException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Calls Dao getActiveCourses() method
	 * @return courses - list of active courses
	 * @throws SQLException
	 * @throws ServiceException
	 */
	public List <Course> getActiveCourses () throws SQLException, ServiceException {
		List <Course> courses = null;
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			courses = CourseDaoImpl.getInstance().getActiveCourses();
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException | DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
			throw new ServiceException(e.getMessage());
		}
		return courses;
	}

	/**
	 * Calls Dao updateCourseStatus() method
	 * @param id - course id
	 * @param status - course status
	 * @throws SQLException
	 * @throws ServiceException
	 */
	public void updateCourseStatus (int id, int status) throws SQLException, ServiceException {
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			CourseDaoImpl.getInstance().updateCourseStatus(id, status);
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException |DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Calls Dao checkCourseStatus() method
	 * @param id - course id
	 * @return isEnded - course status
	 * @throws SQLException
	 * @throws ServiceException
	 */
	public boolean checkCourseStatus (int id) throws SQLException, ServiceException {
		boolean isEnded = false;
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			isEnded = CourseDaoImpl.getInstance().isCourseStatusEnded(id);
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException | DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
			throw new ServiceException(e.getMessage());
		}
		return isEnded;
	}

	/**
	 * Calls Dao checkCourseStatus() method
	 * @param user - User
	 * @param description - description
	 * @param date - date
	 * @throws SQLException
	 * @throws ServiceException
	 */
	public void endCourse (User user, String description, String date) throws SQLException, ServiceException {
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			Operation operation = buildOperation (user, description, date);
			OperationDaoImpl.getInstance().add(operation);
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException | DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
			throw new ServiceException(e.getMessage());
		}
	}

	/**
	 * Build operation
	 * @param user - User
	 * @param description - description
	 * @param date - date
	 * @return operation
	 */
	private Operation buildOperation (User user, String description, String date) {
		Operation operation = new Operation();
		operation.setUserId(user.getId());
		operation.setCourseId(user.getCourseId());
		operation.setDescription(description);
		operation.setDate(date);
		return operation;
	}
}
