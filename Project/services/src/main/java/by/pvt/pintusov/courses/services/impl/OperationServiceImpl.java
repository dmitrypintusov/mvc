package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.constants.TransactionStatus;
import by.pvt.pintusov.courses.dao.Impl.OperationDaoImpl;
import by.pvt.pintusov.courses.entities.Operation;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.managers.PoolManager;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.utils.CoursesSystemLogger;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by USER on 06.12.16.
 */
public class OperationServiceImpl extends AbstractService <Operation> {
	private static OperationServiceImpl instance;

	private OperationServiceImpl () {}

	public static synchronized OperationServiceImpl getInstance () {
		if (instance == null) {
			instance = new OperationServiceImpl();
		}
		return instance;
	}

	@Override
	public void add(Operation operation) throws SQLException, ServiceException {
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			OperationServiceImpl.getInstance().add(operation);
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException | DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public List<Operation> getAll() throws SQLException, ServiceException {
		List <Operation> operations = null;
		try {
			connection = PoolManager.getInstance().getConnection();
			connection.setAutoCommit(false);
			operations = OperationDaoImpl.getInstance().getAll();
			connection.commit();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_SUCCEED);
		} catch (SQLException |DaoException e) {
			connection.rollback();
			CoursesSystemLogger.getInstance().logError(getClass(), TransactionStatus.TRANSACTION_FAILED);
			throw new ServiceException(e.getMessage());
		}
		return operations;
	}

	@Override
	public Operation getById(int id) throws SQLException, ServiceException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void update(Operation entity) throws SQLException, ServiceException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void delete(int id) throws SQLException, ServiceException {
		throw new UnsupportedOperationException();
	}
}
