package by.epam.javawebtraining.glazunov.webproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.CityDao;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPoolException;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.FactoryConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.City;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

/**
 * The DatabaseCityDao class provides interaction the city with the database.
 * @author Glazunov
 * @version 1.0
 */
public class DatabaseCityDao implements CityDao {
	private static Logger LOGGER = Logger.getLogger(DatabaseCityDao.class);

	/**
	 * Get all cities from the DB.
	 * 
	 * @return List of all cities
	 * @throws DaoException if can't get all cities
	 */
	@Override
	public List<City> getAll() throws DaoException {
		List<City> cities = new ArrayList<>();

		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		Statement statement = null;
		ResultSet resultSet = null;
		City city = null;

		try (Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_SELECT_ALL_CITIES);

			while (resultSet.next()) {
				city = new City();

				city.setId(resultSet.getInt(ID));
				city.setCityName(resultSet.getString(NAME));

				cities.add(city);
			}
		} catch (SQLException e) {
			throw new DaoException(GET_ALL_CITIES_EXCEPTION);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			closeResultSet(resultSet);
			closeStatement(statement);
		}

		return cities;
	}

	/**
	 * Add the city to the database.
	 * 
	 *  @param city - the city
	 *  @throws DaoException if can't add city to database
	 */
	@Override
	public void add(City city) throws DaoException {
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		Connection connection = null;

		PreparedStatement preparedStatement = null;

		try {
			connection = connectionPool.takeConnection();
			preparedStatement = connection.prepareStatement(SQL_INSERT_CITY);

			preparedStatement.setString(1, city.getCityName());

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException(ADD_CITY_EXCEPTION);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		} finally {
			ResourceClose.closePreparedStatement(preparedStatement);
			ResourceClose.closeConnection(connection);
		}
	}

	/**
	 * Delete the city from the database by id.
	 * 
	 *  @param id - the city id
	 *  @throws DaoException if can't delete city to database
	 */
	@Override
	public void remove(long id) throws DaoException {
		DaoSource.remove(id, SQL_DELETE_UPDATE_CITY, REMOVE_CITY_EXCEPTION);
	}

	/**
	 * Get city by id.
	 *  
	 *  @param id - city id
	 *  @throws DaoException - if can't get city by id
	 */
	@Override
	public City getCityById(long id) throws DaoException {
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		City city = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.prepareStatement(SQL_SELECT_CITY_BY_ID);
			
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				city = new City();
				
				city.setId(resultSet.getLong(ID));
				city.setCityName(resultSet.getString(NAME));
			}
		
		} catch (SQLException e) {
			throw new DaoException(GET_CITY_BY_ID_EXCEPTION);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		}finally {
			closeResultSet(resultSet);
			closePreparedStatement(statement);
		}
		return city;

	}
}
