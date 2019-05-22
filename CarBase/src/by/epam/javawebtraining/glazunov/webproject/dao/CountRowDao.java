package by.epam.javawebtraining.glazunov.webproject.dao;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;

/**
 * @author Glazunov
 * @version 1.0
 *	 Counts the number of rows returned from the database
 */
public interface CountRowDao {
	
	int getAllCarByIdDriverCount(long id) throws DaoException;
	
	int getAllRouteByIdDriverCount(long id) throws DaoException;
	
	int getAllOrderByIdClientCount(long id) throws DaoException;
	
	int getAllOrderWithoutRouteCount() throws DaoException;
	
	int getAllRouteCount() throws DaoException;
	
	int getAllCarCount() throws DaoException;//status = good
	
	int getAllFeedbackByIdClientCount(long id) throws DaoException;
	
	int getAllFeedbackCount() throws DaoException;
	
}
