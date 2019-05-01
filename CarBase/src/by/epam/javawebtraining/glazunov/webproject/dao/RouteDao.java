package by.epam.javawebtraining.glazunov.webproject.dao;

import java.util.List;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Route;

/**
 * @author Glazunov
 * @version 1.0
 */
public interface RouteDao extends ItemDao<Route> {
	
	/**
	 * Get all the routes owned by the specific driver.
	 *  
	 *  @param id - driver's id
	 *  @throws DaoException - if can't get all the ROUTES owned by the specific driver
	 */
	List<Route> getRouteById(long id) throws DaoException;
	
	/**
	 * The driver changes the route performance mark.
	 *
	 * @param id - route's id
	 * @param condition - route mark
	 * 
	 * @throws DaoException - if can't change the route performance mark.
	 */
	void editMarkRoute(long id, String mark) throws DaoException;	
	
}
