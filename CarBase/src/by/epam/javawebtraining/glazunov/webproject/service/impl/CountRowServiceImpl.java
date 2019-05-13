package by.epam.javawebtraining.glazunov.webproject.service.impl;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

import by.epam.javawebtraining.glazunov.webproject.dao.CountRowDao;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.dao.factory.DaoFactory;
import by.epam.javawebtraining.glazunov.webproject.service.CountRowService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

public class CountRowServiceImpl implements CountRowService{
	private static final String MESSAGE_ERROR_GET_COUNT_CAR_BY_ID_DRIVER = "Error!!! Can`t get count rows CARS by id driver!";
	private static final String MESSAGE_ERROR_GET_COUNT_ROUTES = "Error!!! Can`t get count rows ROUTES by id driver!";
	private static final String MESSAGE_ERROR_GET_COUNT_ORDERS_BY_ID_CLIENT = "Error!!! Can`t get count rows ORDERS by id client!";
	private static final String MESSAGE_ERROR_GET_COUNT_ALL_ORDER_WITHOUT_ROUTE = "Error!!! Can`t get count rows ORDERS without routes!";
	private static final String MESSAGE_ERROR_GET_COUNT_ROUTE = "Error!!! Can`t get count rows ROUTES!";
	private static final String MESSAGE_ERROR_GET_COUNT_CAR = "Error!!! Can`t get count rows CARS!";
	private DaoFactory factory = DaoFactory.getInstance();
	private CountRowDao countRowDao = factory.getCountRowDao(); 

	@Override
	public int getAllCarByIdDriverCount(long id) throws ServiceException {
		Validation.validId(id);
		
		try {
			return countRowDao.getAllCarByIdDriverCount(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_COUNT_CAR_BY_ID_DRIVER);
		}
	}

	@Override
	public int getAllRouteByIdDriverCount(long id) throws ServiceException {
		Validation.validId(id);
			
		try {
			return countRowDao.getAllRouteByIdDriverCount(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_COUNT_ROUTES);
		}
	}

	@Override
	public int getAllOrderByIdClientCount(long id) throws ServiceException {
		Validation.validId(id);

		try {
			return countRowDao.getAllOrderByIdClientCount(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_COUNT_ORDERS_BY_ID_CLIENT);
		}
	}

	@Override
	public int getAllOrderWithoutRouteCount() throws ServiceException {
		
		try {
			return countRowDao.getAllOrderWithoutRouteCount();
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_COUNT_ALL_ORDER_WITHOUT_ROUTE);
		}
	}

	@Override
	public int getAllRouteCount() throws ServiceException {
		
		try {
			return countRowDao.getAllRouteCount();
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_COUNT_ROUTE);
		}
	}

	@Override
	public int getAllCarCount() throws ServiceException {
		
		try {
			return countRowDao.getAllCarCount();
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_COUNT_CAR);
		}
	}

}
