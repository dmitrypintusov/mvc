package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.constants.TransactionStatus;
import by.pvt.pintusov.courses.constants.UserType;
import by.pvt.pintusov.courses.dao.Impl.CourseDaoImpl;
import by.pvt.pintusov.courses.dao.Impl.UserDaoImpl;
import by.pvt.pintusov.courses.entities.Course;
import by.pvt.pintusov.courses.entities.User;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.managers.PoolManager;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.utils.CoursesSystemLogger;

import java.sql.SQLException;
import java.util.List;

/**
 * User service implementation
 * @author pintusov
 * @version 1.0
 */

public class UserServiceImpl extends AbstractService <User> {
	private static UserServiceImpl instance;

	private UserServiceImpl () {}

	/**
	 * Singleton for creating UserServiceImpl
	 * @return instance of UserServiceImpl
	 */
	public static synchronized UserServiceImpl getInstance () {
		if (instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}

	/**
	 * Calls Dao add() method
	 * @param user - object of User class
	 * @throws SQLException
	 * @throws ServiceException
	 */
	@Override
	public void add(User user) throws SQLException, ServiceException {
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			UserDaoImpl.getInstance().add(user);
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
	 * @throws ServiceException
	 */
	@Override
	public List<User> getAll() throws SQLException, ServiceException {
		List <User> users = null;
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			users = UserDaoImpl.getInstance().getAll();
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException | DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
			throw new ServiceException(e.getMessage());
		}
		return users;
	}

	/**
	 * Calls Dao getById() method
	 * @param id - id of entity
	 * @return object of derived class Entity
	 * @throws SQLException
	 * @throws UnsupportedOperationException
	 */
	@Override
	public User getById(int id) throws SQLException {
		throw new UnsupportedOperationException ();
	}

	/**
	 * Calls Dao update() method
	 * @param entity - object of derived class Entity
	 * @throws SQLException
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void update(User entity) throws SQLException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Calls Dao delete() method
	 * @param id - id of entity
	 * @throws SQLException
	 * @throws UnsupportedOperationException
	 */
	@Override
	public void delete(int id) throws SQLException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Calls Dao isAuthorized () method
	 * @param login - User login
	 * @param password - User password
	 * @return isAuthorized user
	 * @throws SQLException
	 * @throws ServiceException
	 */
	public boolean checkUserAuthorization (String login, String password) throws SQLException, ServiceException {
		boolean isAuthorized = false;
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			isAuthorized = UserDaoImpl.getInstance().isAuthorized(login, password);
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException | DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
			throw new ServiceException(e.getMessage());
		}
		return  isAuthorized;
	}

	/**
	 * Calls Dao getByLogin () method
	 * @param login - User login
	 * @return user
	 * @throws SQLException
	 * @throws ServiceException
	 */
	public User getUserByLogin (String login) throws SQLException, ServiceException {
		User user = null;
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			user = UserDaoImpl.getInstance().getByLogin(login);
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException | DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
		}
		return user;
	}

	/**
	 * Check User access type
	 * @param user - User instance
	 * @return userType
	 * @throws SQLException
	 */
	public UserType checkAccessType (User user) throws SQLException {
		UserType userType;
		if (UserType.STUDENT.ordinal() == user.getAccessType()) {
			userType = UserType.STUDENT;
		} else {
			userType = UserType.TEACHER;
		}
		return userType;
	}

	/**
	 * Calls Dao isNewUser () method
	 * Check if User is new
	 * @param user - User instance
	 * @return isNew
	 * @throws SQLException
	 * @throws ServiceException
	 */
	public boolean checkIsNewUser (User user) throws SQLException, ServiceException {
		boolean isNew = false;
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			if ((CourseDaoImpl.getInstance().getById(user.getCourseId()) == null) & (UserDaoImpl.getInstance().isNewUser(user.getLogin()))) {
				isNew = true;
			}
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException | DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
			throw new ServiceException(e.getMessage());
		}
		return isNew;
	}
}
