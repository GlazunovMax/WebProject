package by.epam.javawebtraining.glazunov.webproject.service.impl;

import java.util.List;
import java.util.Set;

import by.epam.javawebtraining.glazunov.webproject.dao.CarDao;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.dao.factory.DaoFactory;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;

import by.epam.javawebtraining.glazunov.webproject.service.CarService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

/**
 * The CarService class implements business logic.
 * @author Glazunov
 * @version 1.0
 */
public class CarServiceImpl implements CarService {
	private DaoFactory factory = DaoFactory.getInstance();
	private CarDao carDao = factory.getCarDao();
	
	/**
	 * Get all cars.
	 * 
	 * @return List of all cars
	 * @throws ServiceException if can't get all cars
	 */
	@Override
	public List<Car> getAllCar() throws ServiceException {
		try {
			return carDao.getAll();
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_ALL_CAR, e);
		}
	}

	/**
	 * Add the car.
	 * 
	 * @param car - the car
	 * @throws ServiceException if can't add car.
	 */
	@Override
	public void addCar(Car car) throws ServiceException {
		// Validation.validateCar(car);
		Validation.validateModel(car);

		try {
			carDao.add(car);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_ADD_CAR);
		}

	}

	/**
	 * Delete the car by id.
	 * 
	 * @param id - the car's id
	 * @throws ServiceException if can't delete car by id.
	 */
	@Override
	public void removeCar(long id) throws ServiceException {
		Validation.validId(id);

		try {
			carDao.remove(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_REMOVE_CAR, e);
		}

	}

	/**
	 * Get all the cars owned by the specific driver.
	 *  
	 * @param id - driver's id
	 * @throws DaoException - if can't get all the cars owned by the specific driver
	 */
	@Override
	public Set<Car> getAllCarByIdDriver(long id, int offset, int countRows) throws ServiceException {
		Validation.validId(id);

		try {
			return carDao.getAllCarByIdDriver(id, offset, countRows);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_CARS_BY_ID_DRIVER, e);
		}

	}

	/**
	 * Change car condition.
	 * @param id - car's id
	 * @param condition - car condition
	 * 
	 * @throws ServiceException - if can't change car condition
	 */
	@Override
	public void editCarCondition(long id, String condition) throws ServiceException {
		Validation.validId(id);
		if (condition == null || condition.isEmpty()) {
			throw new ServiceException(MESSAGE_EMPTY_CONDITION_EDIT_CAR_CONDITION);
		}

		try {
			carDao.editCarCondition(id, condition);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_EDIT_CAR_CONDITION, e);
		}

	}

}
