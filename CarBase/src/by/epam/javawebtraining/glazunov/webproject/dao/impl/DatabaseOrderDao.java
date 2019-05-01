package by.epam.javawebtraining.glazunov.webproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.ItemDao;
import by.epam.javawebtraining.glazunov.webproject.dao.OrderDao;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPoolException;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.FactoryConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;
import by.epam.javawebtraining.glazunov.webproject.entity.City;
import by.epam.javawebtraining.glazunov.webproject.entity.Order;
import by.epam.javawebtraining.glazunov.webproject.entity.User;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

/**
  * The DatabaseOrderDao class provides interaction the Order with the database.
  * 
  * @author Glazunov
  * @version 1.0
  */
public class DatabaseOrderDao implements OrderDao {
	private static final String LOCAL_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static Logger LOGGER = Logger.getLogger(DatabaseOrderDao.class);
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN);
	
	/**
	 * Get all orders from the DB.
	 * 
	 * @return List of all orders
	 * @throws DaoException if can't get all orders
	 */
	@Override
	public List<Order> getAll() throws DaoException {
		return getAllOrderByCondition(SQL_SELECT_ALL_ORDERS, GET_ALL_ORDERS_EXCEPTION);
	}

	/**
	 * Get all orders from the DB which is not in the route ...(driver not assigned and not in Routes table)
	 * 
	 * @return List of all orders which is not in the route
	 * @throws DaoException if can't get all orders which is not in the route
	 */
	@Override
	public List<Order> getAllOrderWithoutRoute() throws DaoException {
		return getAllOrderByCondition(SQL_SELECT_ALL_ORDERS_WITHOUT_ROUTE, GET_ALL_ORDERS_EXCEPTION);
	}
	
	/**
	 * General method for 
	 * @see DatabaseOrderDao#getAllOrderWithoutRoute()
	 *  and 
	 * @see DatabaseOrderDao#getAll()
	 * 
	 * @param sql - query the database
	 * @param exceptionMessage
	 * @return List orders
	 * @throws DaoException
	 */
	private List<Order> getAllOrderByCondition(String sql, String exceptionMessage) throws DaoException {
		List<Order> orders = new ArrayList<>();
		
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();

		Statement statement = null;
		ResultSet resultSet = null;
	
		Order order = null;
		City departure = null;
		City destination = null;
		User user = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				order = new Order();
				departure = new City();
				destination = new City();
				user = new User();
				
				order.setId(resultSet.getLong(ID));
					departure.setId(resultSet.getLong(ID_CITY_DEPARTURE));
					departure.setCityName(resultSet.getString(CITY_NAME_DEPARTURE));
				order.setDeparture(departure);
					destination.setId(resultSet.getLong(ID_CITY_DESTINATION));
					destination.setCityName(resultSet.getString(CITY_NAME_DESTINATION));
				order.setDestination(destination);
				order.setTimeDeparture(LocalDateTime.parse(resultSet.getString(TIME_DEPARTURE), formatter));
				order.setCountPassenger(resultSet.getInt(COUNT_PASSENGER));
					user.setId(resultSet.getLong(USER_ID));
					user.setName(resultSet.getString(NAME));
					user.setSurname(resultSet.getString(SURNAME));
					user.setLogin(resultSet.getString(LOGIN));
					user.setPassword(resultSet.getString(PASSWORD));
					user.setPhoneNumber(resultSet.getString(PHONE));
					user.setRole(User.Role.valueOf(resultSet.getString(TITLE).toUpperCase()));
				order.setUser(user);
				//route
				orders.add(order);
				
			}	
		} catch (SQLException e) {
			throw new DaoException(exceptionMessage);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closeStatement(statement);
		}
		return orders;
		
	}
	
	/**
	 * Get all the orders owned by the specific client.
	 *  
	 *  @param id - client's id
	 *  @throws DaoException - if can't get all the orders owned by the specific client
	 */
	@Override
	public List<Order> getOrderById(Long id) throws DaoException {// такой в routr
		List<Order> orders = new ArrayList<>();
		
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
	
		Order order = null;
		City departure = null;
		City destination = null;
		User user = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			
			statement = connection.prepareStatement(SQL_SELECT_ALL_ORDERS_BY_ID);
			
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				order = new Order();
				departure = new City();
				destination = new City();
				user = new User();
				System.out.println("!!");
				
				order.setId(resultSet.getLong(ID));
					departure.setId(resultSet.getLong(ID_CITY_DEPARTURE));
					departure.setCityName(resultSet.getString(CITY_NAME_DEPARTURE)); // err
				order.setDeparture(departure);
					destination.setId(resultSet.getLong(ID_CITY_DESTINATION));
					destination.setCityName(resultSet.getString(CITY_NAME_DESTINATION));
				order.setDestination(destination);
				order.setTimeDeparture(LocalDateTime.parse(resultSet.getString(TIME_DEPARTURE),formatter));
				order.setCountPassenger(resultSet.getInt(COUNT_PASSENGER));
					user.setId(resultSet.getLong(USER_ID));
					user.setName(resultSet.getString(NAME));
					user.setSurname(resultSet.getString(SURNAME));
					user.setLogin(resultSet.getString(LOGIN));
					user.setPassword(resultSet.getString(PASSWORD));
					user.setPhoneNumber(resultSet.getString(PHONE));
					user.setRole(User.Role.valueOf(resultSet.getString(TITLE).toUpperCase()));
				order.setUser(user);
				
				
				orders.add(order);
			}	
		} catch (SQLException e) {
			throw new DaoException(GET_ALL_ORDERS_EXCEPTION_BY_ID);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(statement);
		}
		return orders;
		
	}

	/**
	 * Add the order to the database.
	 * 
	 * @param order - the order
	 * @throws DaoException if can't add order to database
	 */
	@Override
	public void add(Order order) throws DaoException {
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		Connection connection = null;

		PreparedStatement statementVar = null;
		PreparedStatement statementCityDeparture = null;
		PreparedStatement statementCityDestination = null;
		PreparedStatement statementUser = null;
		PreparedStatement statementInsert = null;
		
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			
			// 1 - FIRST transaction
			statementVar = connection.prepareStatement(SQL_TRANSACTION_VARIABLE_ADD_ORDER);
			statementVar.executeUpdate();

			// 2 - SECOND transaction
			statementCityDeparture = connection.prepareStatement(SQL_SELECT_TRANSACTION_CITY_DEPARTURE);
			statementCityDeparture.setString(1, order.getDeparture().getCityName());
			statementCityDeparture.executeQuery();

			// 3 - THIRD transaction
			statementCityDestination = connection.prepareStatement(SQL_SELECT_TRANSACTION_CITY_DESTINATION);
			statementCityDestination.setString(1, order.getDestination().getCityName());
			statementCityDestination.executeQuery();
			
			// 4 - FORTH transaction
			statementUser = connection.prepareStatement(SQL_SELECT_TRANSACTION_USER);
			statementUser.setString(1, order.getUser().getLogin());
			statementUser.executeQuery();
			
			// 5 - FIFTH transaction
			statementInsert = connection.prepareStatement(SQL_INSERT_ORDER_TRANSACTION);
			statementInsert.setTimestamp(1, Timestamp.valueOf(order.getTimeDeparture()));//Date.valueOf(localTime
			statementInsert.setInt(2, order.getCountPassenger());
			statementInsert.executeUpdate();

			connection.commit();
						
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
				LOGGER.error(ROLLBACK_EXCEPTION, e2);
				throw new DaoException(ADD_ORDER_EXCEPTION, e2);
			}
			throw new DaoException(ADD_ORDER_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		} finally {
			ResourceClose.closePreparedStatement(statementInsert);
			ResourceClose.closePreparedStatement(statementUser);
			ResourceClose.closePreparedStatement(statementCityDestination);
			ResourceClose.closePreparedStatement(statementCityDeparture);
			ResourceClose.closePreparedStatement(statementVar);
			ResourceClose.closeConnection(connection);
		}
		
	}

	/**
	 * Delete the order from the database by id.
	 * 
	 *  @param id - the order's id
	 *  @throws DaoException if can't delete order to database
	 */
	@Override
	public void remove(long id) throws DaoException {
		DaoSource.remove(id, SQL_DELETE_UPDATE_ORDER, REMOVE_ORDER_EXCEPTION);
	}


	/**
	 * Get order from the DB by id.
	 * 
	 * @return order
	 * @param id - the order's id
	 * @throws DaoException if can't get order from the DB by id.
	 */
	@Override
	public Order getSingleOrderById(long id) throws DaoException {
		
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
	
		Order order = null;
		City departure = null;
		City destination = null;
		User user = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			
			statement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);
			
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				order = new Order();
				departure = new City();
				destination = new City();
				user = new User();
				
				order.setId(resultSet.getLong(ID));
					departure.setId(resultSet.getLong(ID_CITY_DEPARTURE));
					departure.setCityName(resultSet.getString(CITY_NAME_DEPARTURE)); // err
				order.setDeparture(departure);
					destination.setId(resultSet.getLong(ID_CITY_DESTINATION));
					destination.setCityName(resultSet.getString(CITY_NAME_DESTINATION));
				order.setDestination(destination);
				order.setTimeDeparture(LocalDateTime.parse(resultSet.getString(TIME_DEPARTURE), formatter));
				order.setCountPassenger(resultSet.getInt(COUNT_PASSENGER));
					user.setId(resultSet.getLong(USER_ID));
					user.setName(resultSet.getString(NAME));
					user.setSurname(resultSet.getString(SURNAME));
					user.setLogin(resultSet.getString(LOGIN));
					user.setPassword(resultSet.getString(PASSWORD));
					user.setPhoneNumber(resultSet.getString(PHONE));
					user.setRole(User.Role.valueOf(resultSet.getString(TITLE).toUpperCase()));
				order.setUser(user);		
			}	
		} catch (SQLException e) {
			throw new DaoException(GET_ORDER_EXCEPTION_BY_ID);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			closeResultSet(resultSet);
			closePreparedStatement(statement);
		}
		return order;
		
	}

	

}
