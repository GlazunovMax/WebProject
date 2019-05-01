package by.epam.javawebtraining.glazunov.webproject.dao;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.City;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public interface CityDao extends ItemDao<City> {
	
	/**
	 * Get city by id.
	 *  
	 *  @param id - city id
	 *  @throws DaoException - if can't get city by id
	 */
	City getCityById(long id) throws DaoException;
}
