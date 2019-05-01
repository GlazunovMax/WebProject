package by.epam.javawebtraining.glazunov.webproject.service;

import java.util.List;
import java.util.Set;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public interface CarService {

	/**
	 * Get all cars.
	 * 
	 * @return List of all cars
	 * @throws ServiceException if can't get all cars
	 */
	List<Car> getAllCar() throws ServiceException;

	/**
	 * Add the car.
	 * 
	 * @param car - the car
	 * @throws ServiceException if can't add car.
	 */
	void addCar(Car car) throws ServiceException;

	/**
	 * Delete the car by id.
	 * 
	 * @param id - the car's id
	 * @throws ServiceException if can't delete car by id.
	 */
	void removeCar(long id) throws ServiceException;
	
	/**
	 * Get all the cars owned by the specific driver.
	 *  
	 * @param id - driver's id
	 * @throws DaoException - if can't get all the cars owned by the specific driver
	 */
	Set<Car> getAllCarByIdDriver(long id, int offset, int countRows) throws ServiceException;
	
	/**
	 * Change car condition.
	 * @param id - car's id
	 * @param condition - car condition
	 * 
	 * @throws ServiceException - if can't change car condition
	 */
	void editCarCondition(long id, String condition) throws ServiceException;
}