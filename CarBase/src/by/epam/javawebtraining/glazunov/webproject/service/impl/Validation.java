package by.epam.javawebtraining.glazunov.webproject.service.impl;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epam.javawebtraining.glazunov.webproject.entity.Car;
import by.epam.javawebtraining.glazunov.webproject.entity.City;
import by.epam.javawebtraining.glazunov.webproject.entity.Model;
import by.epam.javawebtraining.glazunov.webproject.entity.Order;
import by.epam.javawebtraining.glazunov.webproject.entity.Route;
import by.epam.javawebtraining.glazunov.webproject.entity.User;
import by.epam.javawebtraining.glazunov.webproject.service.exception.ServiceException;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

public class Validation {
	
	private static final String MESSAGE_ERROR_COUNT_PASSENGER = "Passed parameter count passenger should be from 1 to 4!!";
	private static final String MESSAGE_ERROR_CAR_NUMBER = "Incorrect car number. Must be in XXXX format.";


	public static void validateModel(Model model) throws ServiceException{
		switch (model.getClass().getSimpleName()) {
		case "Car":
			validateCar((Car) model);
			break;
		case "City":
			validateCity((City) model);
			break;
		case "Order":
			validateOrder((Order) model);
			break;
		case "Route":
			validateRoute((Route) model);
			break;
		case "User":
			validateRegistration((User) model);
			break;
		default:
			break;
		}
	}
	//CAR
	public static void validateCar(Car car) throws ServiceException{
		if (car == null) {
			throw new ServiceException(MESSAGE_ERROR_CAR_IS_EMPTY);
		}
		
		if (car.getMark() == null || car.getMark().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_CAR_IS_EMPTY);
		}
		
		if(car.getNumber() == null || car.getNumber().isEmpty()){
			throw new ServiceException(MESSAGE_ERROR_CAR_IS_EMPTY);
		}
		
		String regexPas = "\\d{4}";
        Pattern patternPas = Pattern.compile(regexPas);
        Matcher matcherPas = patternPas.matcher(car.getNumber());

        if (!matcherPas.matches()) {
        	throw new ServiceException(MESSAGE_ERROR_CAR_NUMBER);
        }
	}

	public static void validateCars(List<Car> cars) throws ServiceException{
		if (cars == null || cars.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_EMPTY_CAR);
		}
	}
	
	//CITY
	public static void validateCity(City city) throws ServiceException{
		if (city == null) {
			throw new ServiceException(MESSAGE_ERROR_CITY_IS_EMPTY);
		}
		
		if (city.getCityName() == null || city.getCityName().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_CITY_IS_EMPTY);
		}
	}

	public static void validateCities(List<City> cities) throws ServiceException{
		if (cities == null || cities.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_EMPTY_CITY);
		}
	}
	
	//ORDER
	public static void validateOrder(Order order) throws ServiceException{
		if (order == null) {
			throw new ServiceException(MESSAGE_ERROR_ORDER_IS_EMPTY);
		}
		
		if (order.getDeparture() == null) {
			throw new ServiceException(MESSAGE_ERROR_ORDER_IS_EMPTY + " [order.getDeparture()]!!!");
		}
		
		if (order.getDestination() == null) {
			throw new ServiceException(MESSAGE_ERROR_ORDER_IS_EMPTY + " [order.getDestination()]!!!");
		}
		
		
		//регулярка        ((19|20)\d\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])
		/*String regex = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])\\s(0?[7-9]|[12][0-4]):([0-5][0-9]):([0-5][0-9])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(order.getTimeDeparture().toString());

        if (!matcher.matches()) {
        	throw new ServiceException(MEESAGE_ERROR_DATE_FORMAT + order.getTimeDeparture().toString() );
        }*/
		

		if (order.getTimeDeparture() == null) {
			throw new ServiceException(MESSAGE_ERROR_ORDER_IS_EMPTY + " [order.getTimeDeparture()]!!!");
		}
		
	
		if (order.getCountPassenger() > 4 || order.getCountPassenger() <= 0) {
			throw new ServiceException(MESSAGE_ERROR_COUNT_PASSENGER);
		}
		
		validateRegistration(order.getUser());
	}

	public static void validateOrders(List<Order> orders) throws ServiceException{
		if (orders == null || orders.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_EMPTY_ORDER);
		}
	}
	
	//ROUTE
	public static void validateRoute(Route route) throws ServiceException{
		if (route == null) {
			throw new ServiceException(MESSAGE_ERROR_ROUTE_IS_EMPTY);
		}
		
		validateOrder(route.getOrder());
		validateRegistration(route.getDriver());
		
		if (route.getMark() == null) {
			throw new ServiceException(MESSAGE_ERROR_ROUTE_IS_EMPTY + " [route.getMark()]");
		}
		
	}

	public static void validateRoutes(List<Route> routes) throws ServiceException{
		if (routes == null || routes.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_EMPTY_ROUTE);
		}
	}
	
	//USER
	public static void validateSingIn(String login, String password) throws ServiceException{
		if (login == null || login.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_LOGIN);
		}

		if (password == null || password.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_PASSWORD);
		}

	}
	
	public static void validateRegistration(User user) throws ServiceException{
		if (user == null) {
			throw new ServiceException(MESSAGE_ERROR_USER);
		}
		
		if (user.getName() == null || user.getName().isEmpty()){ //|| user.getName().matches("[A-Za-z0-9_]+")) {
			throw new ServiceException(MESSAGE_ERROR_NAME);
		}

		if (user.getSurname() == null || user.getSurname().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_SURNAME);
		}

		if (user.getLogin() == null || user.getLogin().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_LOGIN);
		}

		String regexPas = "\\w{4,15}";
        Pattern patternPas = Pattern.compile(regexPas);
        Matcher matcherPas = patternPas.matcher(user.getPassword());

        if (!matcherPas.matches()) {
        	throw new ServiceException(MESSAGE_ERROR_PASSWORD);
        }
        
		if (user.getPassword() == null || user.getPassword().isEmpty()){// || user.getPassword().matches("\\w{4,15}")) {
			throw new ServiceException(MESSAGE_ERROR_PASSWORD);
		}
		
		
		
		
		if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_PHONE);
		}
	
		String regex = "(29|33|25)\\d{7}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(user.getPhoneNumber());

        if (!matcher.matches()) {
        	throw new ServiceException(MESSAGE_ERROR_FORMAT_PHONE);
        }
		
		if (user.getRole() == null) {
			throw new ServiceException(MESSAGE_ERROR_ROLE);
		}
	}
	
	public static void validatDrivers(List<User> drivers) throws ServiceException{
		if (drivers == null || drivers.isEmpty()) {
			throw new ServiceException(MESSAGE_ERROR_EMPTY_DRIVER);
		}

	}
	
	
	//ID 
	public static void validId(Long id) throws ServiceException{
		 if (id == 0 || id < 0) {
				throw new ServiceException(MESSAGE_ERROR_ID);
			}
	 }
	
}
