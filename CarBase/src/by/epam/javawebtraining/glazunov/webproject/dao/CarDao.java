package by.epam.javawebtraining.glazunov.webproject.dao;

import java.util.List;
import java.util.Set;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;
import by.epam.javawebtraining.glazunov.webproject.entity.City;
 
/**
  * @author Glazunov
  * @version 1.0
  */
public interface CarDao extends ItemDao<Car> {

	/**
	 * Get all the cars owned by the specific driver.
	 *  
	 *  @param id - driver's id
	 *  @throws DaoException - if can't get all the cars owned by the specific driver
	 */
	Set<Car> getAllCarByIdDriver(long id, int offset, int countRows) throws DaoException;
	
	/**
	 * Change car condition.
	 * @param id - car's id
	 * @param condition - car condition
	 * 
	 * @throws DaoException - if can't change car condition
	 */
	void editCarCondition(long id, String condition) throws DaoException;
	
	/**
	 * Get all Car from the DB.
	 * 
	 * @return List of all Car
	 * @throws DaoException if can't get all Car
	 */
	List<Car> getAll(int offset, int countRows) throws DaoException;
	
	/**
	 * add car for drivers in DB.
	 * 
	 * @param car - the car
	 * @throws DaoException if can't add car for drivers
	 */
	void addCarForDriver(Car car) throws DaoException;
	
}
