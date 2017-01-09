package by.pvt.pintusov.courses.services;

import by.pvt.pintusov.courses.entities.Entity;
import by.pvt.pintusov.courses.exceptions.ServiceException;

import java.sql.SQLException;
import java.util.List;

/**
 * Service interface
 * @author pintusov
 * @version 1.0
 */

public interface IService <T extends Entity> {

	/**
	 * Calls Dao add() method
	 * @param entity - object of derived class Entity
	 * @throws SQLException
	 * @throws ServiceException
	 */
	void add (T entity) throws SQLException, ServiceException;

	/**
	 *  Calls Dao getAll() method
	 * @return list of objects of derived class Entity
	 * @throws SQLException
	 * @throws ServiceException
	 */
	List<T> getAll () throws SQLException, ServiceException;

	/**
	 * Calls Dao getById() method
	 * @param id - id of entity
	 * @return object of derived class Entity
	 * @throws SQLException
	 * @throws ServiceException
	 */
	T getById (int id) throws SQLException, ServiceException;

	/**
	 * Calls Dao update() method
	 * @param entity - object of derived class Entity
	 * @throws SQLException
	 * @throws ServiceException
	 */
	void update (T entity) throws SQLException, ServiceException;

	/**
	 * Calls Dao delete() method
	 * @param id - id of entity
	 * @throws SQLException
	 * @throws ServiceException
	 */
	void delete (int id) throws SQLException, ServiceException;

}
