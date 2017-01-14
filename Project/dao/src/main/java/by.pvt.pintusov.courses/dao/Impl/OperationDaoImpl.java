package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.constants.ColumnName;
import by.pvt.pintusov.courses.constants.SqlRequest;
import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.entities.Operation;
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
 * Created by Дмитрий on 04.12.2016.
 */
public class OperationDaoImpl extends AbstractDao<Operation> {

	private static OperationDaoImpl instance;
	static String message;
	PoolManager manager = PoolManager.getInstance();

	private OperationDaoImpl() {
	}

	public static synchronized OperationDaoImpl getInstance() {
		if (instance == null) {
			instance = new OperationDaoImpl();
		}
		return instance;
	}

	@Override
	public void add(Operation operation) throws DaoException {
		try {
			connection = manager.getConnection();
			statement = connection.prepareStatement(SqlRequest.ADD_OPERATION);
			statement.setInt(1, operation.getUserId());
			statement.setInt(2, operation.getCourseId());
			statement.setString(3, operation.getDescription());
			statement.setString(4, operation.getDate());
			statement.executeUpdate();
		} catch (SQLException e) {
			message = "Unable to add the operation ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(statement);
		}
	}

	@Override
	public List<Operation> getAll() throws DaoException {
		List<Operation> list = new ArrayList<>();
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.GET_ALL_OPERATIONS);
			result = statement.executeQuery();
			while (result.next()) {
				Operation operation = buildOperation(result);
				list.add(operation);
			}
		} catch (SQLException e) {
			message = "Unable to get all operations ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return list;
	}

	public Operation getById(int id) throws DaoException {
		Operation operation = null;
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.GET_OPERATION_BY_ID);
			statement.setInt(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				operation = buildOperation(result);
			}
		} catch (SQLException e) {
			message = "Unable to get operation by id ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return operation;
	}

	public void delete(int id) throws DaoException {
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.DELETE_OPERATION_BY_ID);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			message = "Unable to deleteByCourseName operation by id ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(statement);
		}
	}

	@Override
	public int getMaxId() throws DaoException {
		int lastId = -1;
		try {
			connection = PoolManager.getInstance().getConnection();
			statement = connection.prepareStatement(SqlRequest.GET_ALL_OPERATIONS);
			result = statement.executeQuery();
			while (result.next()) {
				lastId = result.getInt(1);
			}
		} catch (SQLException e) {
			message = "Unable to get max operation id ";
			CoursesSystemLogger.getInstance().logError(getClass(), message);
			throw new DaoException(message, e);
		} finally {
			ClosingUtil.close(result);
			ClosingUtil.close(statement);
		}
		return lastId;
	}

	public static Operation buildOperation(ResultSet result) throws SQLException {
		int id = result.getInt(ColumnName.OPERATION_ID);
		int userId = result.getInt(ColumnName.USER_ID);
		int courseId = result.getInt(ColumnName.COURSES_ID);
		String description = result.getString(ColumnName.OPERATION_DESCRIPTION);
		String date = result.getString(ColumnName.OPERATION_DATE);
		Operation operation = EntityBuilder.buildOperation(id, userId, courseId, description, date);
		return operation;
	}

	@Override
	public void delete() throws DaoException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Operation entity) throws DaoException {
		throw new UnsupportedOperationException();
	}
}
