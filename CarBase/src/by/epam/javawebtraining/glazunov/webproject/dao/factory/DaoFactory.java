package by.epam.javawebtraining.glazunov.webproject.dao.factory;

import by.epam.javawebtraining.glazunov.webproject.dao.CarDao;
import by.epam.javawebtraining.glazunov.webproject.dao.CityDao;
import by.epam.javawebtraining.glazunov.webproject.dao.OrderDao;
import by.epam.javawebtraining.glazunov.webproject.dao.RouteDao;
import by.epam.javawebtraining.glazunov.webproject.dao.UserDao;
import by.epam.javawebtraining.glazunov.webproject.dao.impl.DatabaseCarDao;
import by.epam.javawebtraining.glazunov.webproject.dao.impl.DatabaseCityDao;
import by.epam.javawebtraining.glazunov.webproject.dao.impl.DatabaseOrderDao;
import by.epam.javawebtraining.glazunov.webproject.dao.impl.DatabaseRouteDao;
import by.epam.javawebtraining.glazunov.webproject.dao.impl.DatabaseUserDao;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public class DaoFactory {
	/** Creates a reference to the DaoFactory object class */
	private static final DaoFactory instance = new DaoFactory();
	
	/** Creates a reference to the DatabaseCarDao object class */
	private final CarDao carDao = new DatabaseCarDao();
	
	/** Creates a reference to the DatabaseCityDao object class */
	private final CityDao cityDao = new DatabaseCityDao();
	
	/** Creates a reference to the DatabaseUserDao object class */
	private final UserDao userDao = new DatabaseUserDao();
	
	/** Creates a reference to the DatabaseOrderDao object class */
	private final OrderDao orderDao = new DatabaseOrderDao();
	
	/** Creates a reference to the DatabaseRouteDao object class */
	private final RouteDao routeDao = new DatabaseRouteDao();
	

	private DaoFactory(){}

	/**
	 * Method to get a reference to the DaoFactory object class {@link DaoFactory#instance} 
	 * @return a reference to the DaoFactory object class
	 */
	public static DaoFactory getInstance() {
		return instance;
	}

	/**
	 * Method to get a reference to the DatabaseCarDao object class {@link DaoFactory#carDao} 
	 * @return a reference to the DatabaseCarDao object class
	 */
	public CarDao getCarDao() {
		return carDao;
	}
	
	/**
	 * Method to get a reference to the DatabaseCityDao object class {@link DaoFactory#cityDao} 
	 * @return a reference to the DatabaseCityDao object class
	 */
	public CityDao getCityDao() {
		return cityDao;
	}

	/**
	 * Method to get a reference to the DatabaseUserDao object class {@link DaoFactory#userDao} 
	 * @return a reference to the DatabaseUserDao object class
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * Method to get a reference to the DatabaseOrderDao object class {@link DaoFactory#orderDao} 
	 * @return a reference to the DatabaseOrderDao object class
	 */
	public OrderDao getOrderDao() {
		return orderDao;
	}

	/**
	 * Method to get a reference to the DatabaseRouteDao object class {@link DaoFactory#routeDao} 
	 * @return a reference to the DatabaseRouteDao object class
	 */
	public RouteDao getRouteDao() {
		return routeDao;
	}
	
	
}
