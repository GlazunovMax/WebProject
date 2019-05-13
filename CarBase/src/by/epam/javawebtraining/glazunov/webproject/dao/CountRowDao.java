package by.epam.javawebtraining.glazunov.webproject.dao;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;

public interface CountRowDao {
	
	int getAllCarByIdDriverCount(long id) throws DaoException;
	
	int getAllRouteByIdDriverCount(long id) throws DaoException;
	
	int getAllOrderByIdClientCount(long id) throws DaoException;
	
	int getAllOrderWithoutRouteCount() throws DaoException;
	
	int getAllRouteCount() throws DaoException;
	
	int getAllCarCount() throws DaoException;//status = good
	

}
