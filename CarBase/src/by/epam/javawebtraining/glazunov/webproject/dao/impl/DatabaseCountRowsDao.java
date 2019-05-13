package by.epam.javawebtraining.glazunov.webproject.dao.impl;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.CountRowDao;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPoolException;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.FactoryConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;

public class DatabaseCountRowsDao implements CountRowDao {
	private static Logger LOGGER = Logger.getLogger(DatabaseCountRowsDao.class);

	private static final String SQL_GET_ALL_CAR_BY_ID_DRIVER_COUNT = "SELECT COUNT(*) AS COUNT FROM users INNER JOIN users_has_cars ON users.id = users_has_cars.users_id INNER JOIN cars ON cars.id = users_has_cars.cars_id INNER JOIN car_condition ON cars.car_condition_id = car_condition.id WHERE users_has_cars.users_id = ?";
	private static final String COUNT = "COUNT";
	private static final String MESSAGE_FIND_COUNT_CAR_BY_ID_DRIVER_EXCEPTION = "Cannot find count cars by id driver";

	private static final String SQL_GET_ALL_ROUTE_BY_ID_DRIVER_COUNT = "SELECT COUNT(*) AS COUNT FROM routes INNER JOIN orders ON routes.orders_id = orders.id INNER JOIN users ON  orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users AS driv ON routes.users_id = driv.id INNER JOIN roles as rol ON driv.role_id = rol.id WHERE routes.users_id = ?";

	private static final String MESSAGE_FIND_COUNT_ROUTE_BY_ID_DRIVER_EXCEPTION = "Cannot find count routes by id driver";

	private static final String SQL_GET_ALL_ORDER_BY_ID_CLIENT = "SELECT COUNT(*) AS COUNT FROM orders INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users ON orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id WHERE users.id = ? ORDER BY orders.time_departure";

	private static final String MESSAGE_FIND_COUNT_ORDER_BY_ID_CLIENT_EXCEPTION = "Cannot find count orders by id client";

	private static final String SQL_GET_ALL_ORDER_WITHOUT_ROUTE = "SELECT COUNT(*) AS COUNT FROM orders INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users ON orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id WHERE orders.id NOT IN(SELECT routes.orders_id FROM routes)";

	private static final String MESSAGE_FIND_COUNT_ORDER_WITHOUT_ROUTE_EXCEPTION = "Cannot find count orders without route";

	private static final String SQL_GET_COUNT_ALL_ROUTE = "SELECT COUNT(*) AS COUNT FROM routes INNER JOIN orders ON routes.orders_id = orders.id INNER JOIN users ON  orders.users_id = users.id INNER JOIN roles ON users.role_id = roles.id INNER JOIN city ON orders.city_id_destination = city.id INNER JOIN city as dep ON orders.city_id_departure = dep.id INNER JOIN users AS driv ON routes.users_id = driv.id INNER JOIN roles as rol ON driv.role_id = rol.id";

	private static final String MESSAGE_FIND_COUNT_ROUTE_EXCEPTION = "Cannot find count all routes";

	private static final String SQL_GET_COUNT_ALL_CAR = "SELECT COUNT(*) AS COUNT FROM cars INNER JOIN car_condition ON cars.car_condition_id = car_condition.id";// WHERE car_condition.status_car = 'GOOD'

	private static final String MESSAGE_FIND_COUNT_CAR_EXCEPTION = "Cannot find count all cars";

	/**
	 * Method get a cars count
	 * 
	 * @param id
	 *            - driver id
	 * @return a cars count
	 * @throws DaoException
	 *             - if you cannot get cars count
	 */
	public int getAllCarByIdDriverCount(long id) throws DaoException {
		return getCountRows(id, SQL_GET_ALL_CAR_BY_ID_DRIVER_COUNT, MESSAGE_FIND_COUNT_CAR_BY_ID_DRIVER_EXCEPTION);	
	}

	/**
	 * Method get a routes count
	 * 
	 * @param id
	 *            - driver id
	 * @return a routes count
	 * @throws DaoException
	 *             - if you cannot get routes count
	 */
	@Override
	public int getAllRouteByIdDriverCount(long id) throws DaoException {
		String sqlAllRoutes = SQL_GET_ALL_ROUTE_BY_ID_DRIVER_COUNT;
		String messageException = MESSAGE_FIND_COUNT_ROUTE_BY_ID_DRIVER_EXCEPTION;
		
		return getCountRows(id, sqlAllRoutes, messageException);
	}
	
	/**
	 * Method get a orders count
	 * 
	 * @param id
	 *            - client id
	 * @return a orders count
	 * @throws DaoException
	 *             - if you cannot get orders count
	 */
	@Override
	public int getAllOrderByIdClientCount(long id) throws DaoException {
		String sql = SQL_GET_ALL_ORDER_BY_ID_CLIENT;
		String messageException = MESSAGE_FIND_COUNT_ORDER_BY_ID_CLIENT_EXCEPTION;
		
		return getCountRows(id, sql, messageException);
	}
	
	/**
	 * Method get a orders count without route
	 * 
	 * @return a orders count without route
	 * @throws DaoException
	 *             - if you cannot get orders without route count
	 */
	@Override
	public int getAllOrderWithoutRouteCount() throws DaoException {
		String sql = SQL_GET_ALL_ORDER_WITHOUT_ROUTE;
		String messageException = MESSAGE_FIND_COUNT_ORDER_WITHOUT_ROUTE_EXCEPTION;
		
		return getCountRows(sql, messageException);
	}

	/**
	 * Method get all route count
	 * 
	 * @return a routes count
	 * @throws DaoException
	 *             - if you cannot get routes count
	 */
	@Override
	public int getAllRouteCount() throws DaoException {
		String sql = SQL_GET_COUNT_ALL_ROUTE;
		String messageException = MESSAGE_FIND_COUNT_ROUTE_EXCEPTION;
		
		return getCountRows(sql, messageException);
	}
	
	/**
	 * Method get all cars count
	 * 
	 * @return a cars count
	 * @throws DaoException
	 *             - if you cannot get cars count
	 */
	@Override
	public int getAllCarCount() throws DaoException {
		String sql = SQL_GET_COUNT_ALL_CAR;
		String messageException = MESSAGE_FIND_COUNT_CAR_EXCEPTION;
		
		return getCountRows(sql, messageException);
	}

	
	private int getCountRows(String sql, String messageException) throws DaoException {
		int count = 0;

		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();

		Statement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
		}catch (SQLException e) {
			throw new DaoException(messageException);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closeStatement(statement);
		}	
			
		
		return count;

		
	}

	
	//common method
	private int getCountRows(long id, String sql, String messageException) throws DaoException {
		int count = 0;

		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try (Connection connection = connectionPool.takeConnection()) {
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				count = resultSet.getInt(COUNT);
			}
		} catch (SQLException e) {
			throw new DaoException(messageException, e);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closePreparedStatement(statement);
		}
		
		return count;
		
	}

	

	

	

	

	
}
