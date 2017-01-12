package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.constants.ColumnName;
import by.pvt.pintusov.courses.constants.SqlRequest;
import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.entities.User;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.managers.HikariCP;
import by.pvt.pintusov.courses.managers.PoolManager;
import by.pvt.pintusov.courses.utils.ClosingUtil;
import by.pvt.pintusov.courses.utils.CoursesSystemLogger;
import by.pvt.pintusov.courses.utils.EntityBuilder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User Dao implementation
 * @author pintusov
 * @version 1.0
 */

public class UserDaoImpl extends AbstractDao <User> {
	private static UserDaoImpl instance;
	static String message;

	private UserDaoImpl () {}

	/**
	 * Singleton for creating UserDaoImpl instance
	 * @return instance of UserDaoImpl
	 */
	public static synchronized UserDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}

	/**
	 * Add User to database
	 * @param user instance of User to add
	 * @throws DaoException
	 */
	@Override
	public void add (User user) throws DaoException {
		/**
		 * Try with resources
		 * needed for safe closing
		 */
		try (Connection connection = HikariCP.getInstance().getConnection()){
			statement = connection.prepareStatement(SqlRequest.ADD_USER);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getLogin());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getAccessType());
			statement.executeUpdate();
		} catch (SQLException e) {
			message = "Unable to add the user ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		}
	}

	/**
	 * Get all students
	 * @return list of students
	 * @throws DaoException
	 */
	@Override
	public List<User> getAll() throws DaoException {
		List <User> list = new ArrayList<>();
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.GET_ALL_STUDENTS);
			result = statement.executeQuery();
			while (result.next()) {
				User user = UserDaoImpl.getInstance().buildUser(result);
				list.add(user);
			}
		} catch (SQLException e) {
			message = "Unable to return students list ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException (message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return list;
	}

	/**
	 * Get user by id
	 * @param id - used user id
	 * @return user
	 * @throws DaoException
	 */
	public User getById(int id) throws DaoException {
		User user = null;
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.GET_USER_BY_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				user = buildUser(result);
			}
		}
		catch (SQLException e){
			message = "Unable to return the user ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		}
		finally{
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return user;
	}

	/**
	 * Get user by login
	 * @param login - used user login
	 * @return user
	 * @throws DaoException
	 */
	public User getByLogin (String login) throws DaoException {
		User user = null;
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.GET_USER_BY_LOGIN);
			statement.setString(1, login);
			result = statement.executeQuery();
			while (result.next()) {
				user = buildUser(result);
			}
		} catch (SQLException e) {
			message = "Unable to find user by login ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return user;
	}

	/**
	 * Check if user is new
	 * @param login - used user login
	 * @return isNew
	 * @throws DaoException
	 */
	public boolean isNewUser (String login) throws DaoException {
		boolean isNew = true;
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.CHECK_LOGIN);
			statement.setString(1, login);
			result = statement.executeQuery();
			if (result.next()) {
				isNew = false;
			}
		} catch (SQLException e) {
			message = "Unable to check the user ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return isNew;
	}

	/**
	 * Check if user authorized
	 * @param login - used user login
	 * @param password - used user password
	 * @return isLogIn
	 * @throws DaoException
	 */
	public boolean isAuthorized (String login, String password) throws DaoException {
		boolean isLogIn = false;
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.CHECK_AUTHORIZATION);
			statement.setString(1, login);
			statement.setString(2, password);
			result = statement.executeQuery();
			if (result.next()) {
				isLogIn = true;
			}
		} catch (SQLException e) {
			message = "Unable to check authorization ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return isLogIn;
	}

	/**
	 * Delete User from database by login
	 * @param login - used user id
	 * @throws DaoException
	 */
	public void deleteByLogin (String login) throws DaoException {
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.DELETE_USER_BY_LOGIN);
			statement.setString(1, login);
			statement.executeUpdate();
		} catch (SQLException e) {
			message = "Unable to deleteByCourseName user ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(statement);
		}
	}

	/**
	 * Get max user id
	 * @return lastId of User
	 * @throws DaoException
	 */
	@Override
	public int getMaxId() throws DaoException {
		int lastId = -1;
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.GET_LAST_USER_ID);
			result = statement.executeQuery();
			while (result.next()){
				lastId = result.getInt(1);
			}
		} catch (SQLException e) {
			message = "Unable to get max user id ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return lastId;
	}

	/**
	 * Build user instance
	 * @param result - using ResultSet to build user instance
	 * @return user
	 * @throws SQLException
	 */
	private User buildUser (ResultSet result) throws SQLException {
		String firstName = result.getString(ColumnName.USER_FIRST_NAME);
		String lastName = result.getString(ColumnName.USER_LAST_NAME);
		String login = result.getString(ColumnName.USER_LOGIN);
		String password = result.getString(ColumnName.USER_PASSWORD);
		int accessType = result.getInt(ColumnName.USER_ACCESS_TYPE);
		User user = EntityBuilder.buildUser(firstName, lastName, login, password, accessType);
		return user;
	}

	/**
	 * No needs to implement
	 * @param user
	 * @throws UnsupportedOperationException
	 * @throws DaoException
	 */
	@Override
	public void update(User user) throws DaoException {
		throw new UnsupportedOperationException();
	}

	/**
	 * No needs to implement
	 * @throws UnsupportedOperationException
	 * @throws DaoException
	 */
	@Override
	public void delete() throws DaoException {
		throw new UnsupportedOperationException ();
	}
}
