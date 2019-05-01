package by.epam.javawebtraining.glazunov.webproject.dao.impl;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.RouteDao;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPoolException;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.FactoryConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.City;
import by.epam.javawebtraining.glazunov.webproject.entity.Order;
import by.epam.javawebtraining.glazunov.webproject.entity.Route;
import by.epam.javawebtraining.glazunov.webproject.entity.User;

/**
 * The DatabaseRouteDao class provides interaction the Route with the database.
 * 
 * @author Glazunov
 * @version 1.0
 */
public class DatabaseRouteDao implements RouteDao {
	private static Logger LOGGER = Logger.getLogger(DatabaseRouteDao.class);
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN);
	
	/**
	 * Get all routes from the DB.
	 * 
	 * @return List of all routes
	 * @throws DaoException if can't get all routes
	 */
	@Override
	public List<Route> getAll() throws DaoException {
		List<Route> routes = new ArrayList<>();

		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;

		Route route = null;
		Order order = null;
		User client = null;
		User driver = null;
		City departure = null;
		City destination = null;

		try (Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_SELECT_ALL_ROUTES);

			while (resultSet.next()) {
				route = new Route();
				order = new Order();
				driver = new User();
				client = new User();
				departure = new City();
				destination = new City();
				
				route.setId(resultSet.getLong(ID));
					order.setId(resultSet.getLong(ORDERS_ID));
						departure.setId(resultSet.getLong(ID_CITY_DEPARTURE));
						departure.setCityName(resultSet.getString(CITY_NAME_DEPARTURE));
					order.setDeparture(departure);
						destination.setId(resultSet.getLong(ID_CITY_DESTINATION));
						destination.setCityName(resultSet.getString(CITY_NAME_DESTINATION));
					order.setDestination(destination);
					order.setTimeDeparture(LocalDateTime.parse(resultSet.getString(TIME_DEPARTURE), formatter));
					order.setCountPassenger(resultSet.getInt(COUNT_PASSENGER));
						client.setId(resultSet.getLong(CLIENT_ID));
						client.setName(resultSet.getString(CLIENT_NAME));
						client.setSurname(resultSet.getString(CLIENT_SURNAME));
						client.setLogin(resultSet.getString(CLIENT_LOGIN));
						client.setPassword(resultSet.getString(CLIENT_PASSWORD));
						client.setPhoneNumber(resultSet.getString(CLIENT_PHONE));
						client.setRole(User.Role.valueOf(resultSet.getString(CLIENT_TITLE).toUpperCase()));
					order.setUser(client);
				route.setOrder(order);
					driver.setId(resultSet.getLong(DRIVER_ID));
					driver.setName(resultSet.getString(DRIVER_NAME));
					driver.setSurname(resultSet.getString(DRIVER_SURNAME));
					driver.setLogin(resultSet.getString(DRIVER_LOGIN));
					driver.setPassword(resultSet.getString(DRIVER_PASSWORD));
					driver.setPhoneNumber(resultSet.getString(DRIVER_PHONE));
					driver.setRole(User.Role.valueOf(resultSet.getString(DRIVER_TITLE).toUpperCase()));
				route.setDriver(driver);
				route.setMark(Route.Mark.valueOf(resultSet.getString(DONE).toUpperCase()));
				
				routes.add(route);			
			}
		} catch (SQLException e) {
			throw new DaoException(GET_ALL_ROUTES_EXCEPTION);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closeStatement(statement);
		}

		return routes;
	}

	/**
	 * Get all the routes owned by the specific driver.
	 *  
	 *  @param id - driver's id
	 *  @throws DaoException - if can't get all the ROUTES owned by the specific driver
	 */
	@Override
	public List<Route> getRouteById(long id) throws DaoException { // return  routes for Drivers(user_id)
		List<Route> routes = new ArrayList<>();

		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Route route = null;
		Order order = null;
		User client = null;
		User driver = null;
		City departure = null;
		City destination = null;

		try (Connection connection = connectionPool.takeConnection()) {
			statement = connection.prepareStatement(SQL_SELECT_ALL_ROUTES_BY_ID);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				route = new Route();
				order = new Order();
				driver = new User();
				client = new User();
				departure = new City();
				destination = new City();
				
				route.setId(resultSet.getLong(ID));
					order.setId(resultSet.getLong(ORDERS_ID));
						departure.setId(resultSet.getLong(ID_CITY_DEPARTURE));
						departure.setCityName(resultSet.getString(CITY_NAME_DEPARTURE));
					order.setDeparture(departure);
						destination.setId(resultSet.getLong(ID_CITY_DESTINATION));
						destination.setCityName(resultSet.getString(CITY_NAME_DESTINATION));
					order.setDestination(destination);
					order.setTimeDeparture(LocalDateTime.parse(resultSet.getString(TIME_DEPARTURE), formatter));
					order.setCountPassenger(resultSet.getInt(COUNT_PASSENGER));
						client.setId(resultSet.getLong(CLIENT_ID));
						client.setName(resultSet.getString(CLIENT_NAME));
						client.setSurname(resultSet.getString(CLIENT_SURNAME));
						client.setLogin(resultSet.getString(CLIENT_LOGIN));
						client.setPassword(resultSet.getString(CLIENT_PASSWORD));
						client.setPhoneNumber(resultSet.getString(CLIENT_PHONE));
						client.setRole(User.Role.valueOf(resultSet.getString(CLIENT_TITLE).toUpperCase()));
					order.setUser(client);
				route.setOrder(order);
					driver.setId(resultSet.getLong(DRIVER_ID));
					driver.setName(resultSet.getString(DRIVER_NAME));
					driver.setSurname(resultSet.getString(DRIVER_SURNAME));
					driver.setLogin(resultSet.getString(DRIVER_LOGIN));
					driver.setPassword(resultSet.getString(DRIVER_PASSWORD));
					driver.setPhoneNumber(resultSet.getString(DRIVER_PHONE));
					driver.setRole(User.Role.valueOf(resultSet.getString(DRIVER_TITLE).toUpperCase()));
				route.setDriver(driver);
				route.setMark(Route.Mark.valueOf(resultSet.getString(DONE).toUpperCase()));
				
				routes.add(route);			
			}
		} catch (SQLException e) {
			throw new DaoException(GET_ALL_ROUTES_EXCEPTION);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closeStatement(statement);
		}

		return routes;
	}

	/**
	 * Add the route to the database.
	 * 
	 * @param route - the route
	 * @throws DaoException if can't add route to database
	 */
	@Override
	public void add(Route route) throws DaoException {
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();

		Connection connection = null;

		PreparedStatement statementVar = null;
		PreparedStatement statementOrders = null;
		PreparedStatement statementUsers = null;
		PreparedStatement statementInsert = null;
		
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			
			// 1 - FIRST transaction
			statementVar = connection.prepareStatement(SQL_TRANSACTION_VARIABLE_ADD_ROUTE);
			statementVar.executeUpdate();
			
			// 2 - SECOND transaction
			statementOrders = connection.prepareStatement(SQL_SELECT_TRANSACTION_ORDERS);
			statementOrders.setLong(1, route.getOrder().getId());
			statementOrders.executeQuery();

			// 3 - THIRD transaction
			statementUsers = connection.prepareStatement(SQL_SELECT_TRANSACTION_USERS);
			statementUsers.setLong(1, route.getDriver().getId());
			statementUsers.executeQuery();
			
			System.out.println(1);
			// 4 - FORTH transaction
			statementInsert = connection.prepareStatement(SQL_INSERT_ROUTE_TRANSACTION);
			System.out.println(2);
			statementInsert.setString(1, String.valueOf(route.getMark()));//.toStriing
			System.out.println(3);
			statementInsert.executeUpdate();//errr

			System.out.println(4);
			connection.commit();
			
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
				LOGGER.error(ROLLBACK_EXCEPTION, e2);
				throw new DaoException(ADD_ROUTE_EXCEPTION, e2);
			}
			throw new DaoException(ADD_ROUTE_EXCEPTION, e);
		}catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		} finally {
			ResourceClose.closePreparedStatement(statementInsert);
			ResourceClose.closePreparedStatement(statementUsers);
			ResourceClose.closePreparedStatement(statementOrders);
			ResourceClose.closePreparedStatement(statementVar);
			ResourceClose.closeConnectionWithCommit(connection);
		}

	}

	/**
	 * Delete the route from the database by id.
	 * 
	 *  @param id - the route's id
	 *  @throws DaoException if can't delete route to database
	 */
	@Override
	public void remove(long id) throws DaoException {
		DaoSource.remove(id, SQL_DELETE_UPDATE_ROUTE, REMOVE_ROUTE_EXCEPTION);
	}

	/**
	 * The driver changes the route performance mark.
	 *
	 * @param id - route's id
	 * @param condition - route mark
	 * 
	 * @throws DaoException - if can't change the route performance mark.
	 */
	@Override
	public void editMarkRoute(long id, String name) throws DaoException {
		DaoSource.edit(id, name, SQL_EDIT_MARK_ROUTE, EDIT_MARK_ROUTE_EXCEPTION);
	}

}
