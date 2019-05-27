package by.epam.javawebtraining.glazunov.webproject.service.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.dao.CityDao;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.dao.factory.DaoFactory;
import by.epam.javawebtraining.glazunov.webproject.entity.City;
import by.epam.javawebtraining.glazunov.webproject.service.CityService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

/**
 * The CityService class implements business logic.
 * @author Glazunov
 * @version 1.0
 */
public class CityServiceImpl implements CityService {
	private DaoFactory factory = DaoFactory.getInstance();
	private CityDao cityDao = factory.getCityDao();

	/**
	 * Get all cities.
	 * 
	 * @return List of all cities
	 * @throws ServiceException if can't get all cities
	 */
	@Override
	public List<City> getAllCity() throws ServiceException {
		try {
			return cityDao.getAll();
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_ALL_CITY, e);
		}
	}

	/**
	 * Add the city.
	 * 
	 * @param city - the city
	 * @throws ServiceException if can't add city
	 */
	@Override
	public void addCity(City city) throws ServiceException {
		Validation.validateModel(city);
		//Validation.validateCity(city);
		
		try {
			cityDao.add(city);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_ADD_CITY);
		}
		
	}
	
	/**
	 * Delete the city by id.
	 * 
	 * @param id - the city id
	 * @throws ServiceException if can't delete city
	 */
	@Override
	public void removeCity(long id) throws ServiceException {
		Validation.validId(id);

		try {
			cityDao.remove(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_REMOVE_CITY, e);
		}
	}

	
	/**
	 * Get city by id.
	 *  
	 *  @param id - city id
	 *  @throws ServiceException - if can't get city by id
	 */
	@Override
	public City getCityById(long id) throws ServiceException {
		City city = new City();
		
		Validation.validId(id);
		
		try {
			city = cityDao.getCityById(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_CITY_BY_ID, e);
		}
		return city;
	}

}
