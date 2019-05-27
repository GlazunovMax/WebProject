package by.epam.javawebtraining.glazunov.webproject.dao;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Model;

/**
 * Shows common methods on dao layer interfaces
 * @author Glazunov
 *
 * @param <T> - T extends Model
 */
public interface ItemDao<T extends Model> {
	static Logger LOGGER = Logger.getLogger(ItemDao.class);

	/**
	 * Add the item to the database.
	 * 
	 *  @param item - the item
	 *  @throws DaoException if can't add item to database
	 */
	void add(T item) throws DaoException;

	/**
	 * Delete the T extends Model from the database by id.
	 * 
	 *  @param id - the T extends Model id
	 *  @throws DaoException if can't add T extends Model to database
	 */
	void remove(long id) throws DaoException;
}
