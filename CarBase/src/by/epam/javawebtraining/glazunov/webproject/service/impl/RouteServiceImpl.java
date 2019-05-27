package by.epam.javawebtraining.glazunov.webproject.service.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.util.ArrayList;
import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.dao.RouteDao;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.dao.factory.DaoFactory;
import by.epam.javawebtraining.glazunov.webproject.entity.Route;
import by.epam.javawebtraining.glazunov.webproject.service.RouteService;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

/**
 * The RouteService class implements business logic.
 * @author Glazunov
 * @version 1.0
 */
public class RouteServiceImpl implements RouteService {
	private DaoFactory factory = DaoFactory.getInstance();
	private RouteDao routeDao = factory.getRouteDao();

	/**
	 * Get all routes.
	 * 
	 * @return List of all routes
	 * @throws ServiceException if can't get all routes
	 */
	@Override
	public List<Route> getAllRoute(int offset, int countRows) throws ServiceException {
		try {
			return routeDao.getAll(offset, countRows);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_ALL_ROUTE, e);
		}
	}

	/**
	 * Add the route.
	 * 
	 * @param route - the route
	 * @throws ServiceException if can't add route 
	 */
	@Override
	public void addRoute(Route route) throws ServiceException {

		Validation.validateModel(route);
		// Validation.validateRoute(route);
		try {
			routeDao.add(route);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_ADD_ROUTE);
		}

	}

	/**
	 * Delete the route by id.
	 * 
	 * @param id - the route's id
	 * @throws ServiceException if can't delete route
	 */
	@Override
	public void removeRoute(long id) throws ServiceException {
		Validation.validId(id);
		try {
			routeDao.remove(id);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_REMOVE_ROUTE, e);
		}

	}

	/**
	 * Get all the routes owned by the specific driver.
	 *  
	 * @param id - driver's id
	 * @throws ServiceException - if can't get all the ROUTES owned by the specific driver
	 */
	@Override
	public List<Route> getRouteById(long id, int offset, int countRows) throws ServiceException {
		List<Route> routes = new ArrayList<>();

		Validation.validId(id);

		try {
			routes = routeDao.getRouteById(id, offset, countRows);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_GET_ROUTE_BY_ID, e);
		}

		return routes;
	}

	/**
	 * The driver changes the route performance mark.
	 *
	 * @param id - route's id
	 * @param condition - route mark
	 * 
	 * @throws ServiceException - if can't change the route performance mark.
	 */
	@Override
	public void editMarkRoute(long id, String name) throws ServiceException {
		Validation.validId(id);
		
		if (name == null || name.isEmpty()) {
			throw new ServiceException(MESSAGE_EMPTY_NAME_EDIT_MARK);
		}
		
		try {
			routeDao.editMarkRoute(id, name);
		} catch (DaoException e) {
			throw new ServiceException(MESSAGE_ERROR_EDIT_MARK_ROUTE, e);
		}
	}

}
