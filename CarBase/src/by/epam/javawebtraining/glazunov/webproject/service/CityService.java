package by.epam.javawebtraining.glazunov.webproject.service;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.entity.City;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

/**
 * 
 * @author Glazunov
 *
 */
public interface CityService {

	/**
	 * Get all cities.
	 * 
	 * @return List of all cities
	 * @throws ServiceException if can't get all cities
	 */
	List<City> getAllCity() throws ServiceException;

	/**
	 * Add the city.
	 * 
	 * @param city - the city
	 * @throws ServiceException if can't add city
	 */
	void addCity(City city) throws ServiceException;

	
	/**
	 * Delete the city by id.
	 * 
	 * @param id - the city id
	 * @throws ServiceException if can't delete city
	 */
	void removeCity(long id) throws ServiceException;

	/**
	 * Get city by id.
	 *  
	 *  @param id - city id
	 *  @throws ServiceException - if can't get city by id
	 */
	City getCityById(long id) throws ServiceException;
}
