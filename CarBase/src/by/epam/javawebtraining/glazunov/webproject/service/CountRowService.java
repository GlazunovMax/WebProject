package by.epam.javawebtraining.glazunov.webproject.service;

import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

public interface CountRowService {
	int getAllCarByIdDriverCount(long id) throws ServiceException;
	
	int getAllRouteByIdDriverCount(long id) throws ServiceException;
	
	int getAllOrderByIdClientCount(long id) throws ServiceException;
	
	int getAllOrderWithoutRouteCount() throws ServiceException;

	int getAllRouteCount() throws ServiceException;
	
	int getAllCarCount() throws ServiceException; //status car = GOOD 
}
