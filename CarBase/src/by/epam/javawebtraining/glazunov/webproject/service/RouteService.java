package by.epam.javawebtraining.glazunov.webproject.service;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.entity.Route;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public interface RouteService {
	
	/**
	 * Get all the routes owned by the specific driver.
	 *  
	 * @param id - driver's id
	 * @throws ServiceException - if can't get all the ROUTES owned by the specific driver
	 */
	List<Route> getRouteById(long id, int offset, int countRows) throws ServiceException;

	/**
	 * Get all routes.
	 * 
	 * @return List of all routes
	 * @throws ServiceException if can't get all routes
	 */
	List<Route> getAllRoute(int offset, int countRows) throws ServiceException;

	/**
	 * Add the route.
	 * 
	 * @param route - the route
	 * @throws ServiceException if can't add route 
	 */
	void addRoute(Route route) throws ServiceException;

	/**
	 * Delete the route by id.
	 * 
	 * @param id - the route's id
	 * @throws ServiceException if can't delete route
	 */
	void removeRoute(long id) throws ServiceException;
	
	/**
	 * The driver changes the route performance mark.
	 *
	 * @param id - route's id
	 * @param condition - route mark
	 * 
	 * @throws ServiceException - if can't change the route performance mark.
	 */
	void editMarkRoute(long id, String name) throws ServiceException;
}
