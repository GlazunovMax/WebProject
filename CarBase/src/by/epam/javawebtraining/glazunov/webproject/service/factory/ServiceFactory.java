package by.epam.javawebtraining.glazunov.webproject.service.factory;

import by.epam.javawebtraining.glazunov.webproject.service.CarService;
import by.epam.javawebtraining.glazunov.webproject.service.CityService;
import by.epam.javawebtraining.glazunov.webproject.service.CountRowService;
import by.epam.javawebtraining.glazunov.webproject.service.FeedbackService;
import by.epam.javawebtraining.glazunov.webproject.service.OrderService;
import by.epam.javawebtraining.glazunov.webproject.service.RouteService;
import by.epam.javawebtraining.glazunov.webproject.service.UserService;
import by.epam.javawebtraining.glazunov.webproject.service.impl.CarServiceImpl;
import by.epam.javawebtraining.glazunov.webproject.service.impl.CityServiceImpl;
import by.epam.javawebtraining.glazunov.webproject.service.impl.CountRowServiceImpl;
import by.epam.javawebtraining.glazunov.webproject.service.impl.FeedbackServiceImpl;
import by.epam.javawebtraining.glazunov.webproject.service.impl.OrderServiceImpl;
import by.epam.javawebtraining.glazunov.webproject.service.impl.RouteServiceImpl;
import by.epam.javawebtraining.glazunov.webproject.service.impl.UserServiceImpl;

public class ServiceFactory {
	
	/** Creates a reference to the ServiceFactory object class */
	private static final ServiceFactory instance = new ServiceFactory();
	
	/** Creates a reference to the DatabaseCarService object class */
	private final CarService carService = new CarServiceImpl();
	
	/** Creates a reference to the DatabaseCityService object class */
	private final CityService cityService = new CityServiceImpl();
	
	/** Creates a reference to the DatabaseUserService object class */
	private final UserService userService = new UserServiceImpl();
	
	/** Creates a reference to the DatabaseOrderService object class */
	private final OrderService orderService = new OrderServiceImpl();
	
	/** Creates a reference to the DatabaseRouteService object class */
	private final RouteService routeService = new RouteServiceImpl();
	
	/** Creates a reference to the DatabaseCountRowService object class */
	private final CountRowService countRowService = new CountRowServiceImpl();
	
	/** Creates a reference to the DatabaseFeedbackService object class */
	private final FeedbackService feedbackService = new FeedbackServiceImpl();
	

	private ServiceFactory(){}

	/**
	 * Method to get a reference to the ServiceFactory object class {@link ServiceFactory#instance} 
	 * @return a reference to the ServiceFactory object class
	 */
	public static ServiceFactory getInstance() {
		return instance;
	}

	/**
	 * Method to get a reference to the DatabaseCarService object class {@link ServiceFactory#carService} 
	 * @return a reference to the DatabaseCarService object class
	 */
	public CarService getCarService() {
		return carService;
	}
	
	/**
	 * Method to get a reference to the DatabaseCityService object class {@link ServiceFactory#cityService} 
	 * @return a reference to the DatabaseCityService object class
	 */
	public CityService getCityService() {
		return cityService;
	}

	/**
	 * Method to get a reference to the DatabaseUserService object class {@link ServiceFactory#userService} 
	 * @return a reference to the DatabaseUserService object class
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * Method to get a reference to the DatabaseOrderService object class {@link ServiceFactory#orderService} 
	 * @return a reference to the DatabaseOrderService object class
	 */
	public OrderService getOrderService() {
		return orderService;
	}

	/**
	 * Method to get a reference to the DatabaseRouteService object class {@link ServiceFactory#routeService} 
	 * @return a reference to the DatabaseRouteService object class
	 */
	public RouteService getRouteService() {
		return routeService;
	}

	/**
	 * Method to get a reference to the DatabaseCountRowService object class {@link ServiceFactory#countRowService} 
	 * @return a reference to the DatabaseRouteService object class
	 */
	public CountRowService getCountRowService() {
		return countRowService;
	}
	
	/**
	 * Method to get a reference to the DatabaseFeedbackService object class {@link ServiceFactory#feedbackService} 
	 * @return a reference to the DatabaseFeedbackService object class
	 */
	public FeedbackService getFeedbackService() {
		return feedbackService;
	}
	
}
