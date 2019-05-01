package by.epam.javawebtraining.glazunov.webproject.dao;

import java.util.Set;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;
 
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
	
}
