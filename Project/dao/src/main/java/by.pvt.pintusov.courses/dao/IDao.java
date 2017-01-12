package by.pvt.pintusov.courses.dao;

import by.pvt.pintusov.courses.entities.Entity;
import by.pvt.pintusov.courses.exceptions.DaoException;

import java.util.List;

/**
 * Dao interface
 * @author pintusov
 * @version 1.0
 */

public interface IDao <T extends Entity> {
	/**
	 * adds entity to database
	 * @param entity - new entity
	 * @exception DaoException throws Dao exception
	 */
	void add (T entity) throws DaoException;

	/**
	 * updates entity in database
	 * @param entity - entity
	 * @exception DaoException throws Dao exception
	 */
	void update(T entity) throws DaoException;

	/**
	 * Delete Entity
	 * @exception DaoException throws Dao exception
	 */
	void delete () throws DaoException;

	/**
	 * Create a list of all elements in database
	 * @return List of all elements
	 * @exception DaoException throws Dao exception
	 */
	List<T> getAll () throws DaoException;

	/**
	 * Getting Entity by id
	 * @return entity max id
	 * @exception DaoException throws Dao exception
	 */
	int getMaxId () throws DaoException;
}
