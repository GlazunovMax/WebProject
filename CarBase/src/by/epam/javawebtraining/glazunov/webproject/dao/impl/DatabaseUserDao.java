package by.epam.javawebtraining.glazunov.webproject.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.UserDao;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPoolException;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.FactoryConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;
import by.epam.javawebtraining.glazunov.webproject.entity.User;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

/**
 * The DatabaseUserDao class provides interaction the User with the database.
 * @author Glazunov
 *
 */
public class DatabaseUserDao implements UserDao {
	private static Logger LOGGER = Logger.getLogger(DatabaseUserDao.class);

	/** Check user by login and password to the database
	 * 
	 * @param login
	 * @param password
	 * @return returns user
	 * @throws DaoException if you cannot to check user by login and password 
	 */
	@Override
	public User singIn(String login, String password) throws DaoException {
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		Connection connection = null;

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		User user = null;

		try {
			user = new User();

			connection = connectionPool.takeConnection();

			statement = connection.prepareStatement(SQL_SELECT_SING_IN);

			statement.setString(1, login);
			statement.setString(2, password);

			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				user.setId(resultSet.getLong(ID));
				user.setName(resultSet.getString(NAME));
				user.setSurname(resultSet.getString(SURNAME));
				user.setLogin(resultSet.getString(LOGIN));
				user.setPassword(resultSet.getString(PASSWORD));
				user.setPhoneNumber(resultSet.getString(PHONE));
				// role.setTitleRole(resultSet.getString(TITLE));
				user.setRole(User.Role.valueOf(resultSet.getString(TITLE).toUpperCase()));

			} else {
				user = null;
				LOGGER.debug(MESSAGE_USER_NOT_REGISTER);
			}

		} catch (SQLException e) {
			throw new DaoException(MESSAGE_SIGH_IN_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		} finally {	
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closePreparedStatement(statement);
			ResourceClose.closeConnection(connection);
		}

		return user;
	}

	/** Add the user to the database
	 * 
	 * @param user
	 * @throws DaoException if you cannot add user
	 */
	@Override
	public void registration(User user) throws DaoException {
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		Connection connection = null;

		PreparedStatement statementVar = null;
		PreparedStatement statementRoles = null;
		PreparedStatement statementInsert = null;

		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);

			// 1 - FIRST transaction
			statementVar = connection.prepareStatement(SQL_TRANSACTION_VARIABLE_REGISTRATION);
			statementVar.executeUpdate();

			// 2 - SECOND transaction
			statementRoles = connection.prepareStatement(SQL_SELECT_TRANSACTION_ROLE);
			statementRoles.setString(1, user.getRole().toString().toLowerCase());
			statementRoles.executeQuery();

			// 3 - THIRD transaction
			statementInsert = connection.prepareStatement(SQL_INSERT_USER_TRANSACTION);
			statementInsert.setString(1, user.getName());
			statementInsert.setString(2, user.getSurname());
			statementInsert.setString(3, user.getLogin());
			statementInsert.setString(4, user.getPassword());
			statementInsert.setString(5, user.getPhoneNumber());
			
			statementInsert.executeUpdate();

			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
				LOGGER.error(ROLLBACK_EXCEPTION, e2);
				throw new DaoException(MESSAGE_REGISTRATION_EXCEPTION, e2);
			}
			throw new DaoException(MESSAGE_REGISTRATION_EXCEPTION, e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			ResourceClose.closePreparedStatement(statementInsert);
			ResourceClose.closePreparedStatement(statementRoles);
			ResourceClose.closePreparedStatement(statementVar);
			ResourceClose.closeConnection(connection);
		}

	}

	/**
	 * Get all drivers from the DB.(user table)
	 * 
	 * @return List of all drivers
	 * @throws DaoException if can't get all drivers
	 */
	@Override
	public List<User> getAllDriver() throws DaoException {
		List<User> users = new ArrayList<>();
		Set<Car> cars = new HashSet<>();
		
		
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSetCar = null;
		User user = null;
		Car car = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SQL_SELECT_ALL_DRIVERS);
			
			while (resultSet.next()) {
				user = new User();
				
				user.setId(resultSet.getLong(ID));
				user.setName(resultSet.getString(NAME));
				user.setSurname(resultSet.getString(SURNAME));
				user.setLogin(resultSet.getString(LOGIN));
				user.setPassword(resultSet.getString(PASSWORD));
				user.setPhoneNumber(resultSet.getString(PHONE));
				user.setRole(User.Role.valueOf(resultSet.getString(TITLE).toUpperCase()));
				
				
				Long id = resultSet.getLong(ID);
				preparedStatement = connection.prepareStatement(SQL_SELECT_DRIVERS_CAR);
				preparedStatement.setLong(1, id);
				resultSetCar = preparedStatement.executeQuery();
				
					while(resultSetCar.next()){
						car = new Car();						
						car.setId(resultSetCar.getLong(ID));
						car.setMark(resultSetCar.getString(MARK));					
						car.setNumber(resultSetCar.getString(CAR_NUMBER));
						car.setStatusCar(Car.StatusCar.valueOf(resultSetCar.getString(CAR_CONDITION).toUpperCase()));				
						
						cars.add(car);
					}
				user.setCars(cars);
				cars = new HashSet<>();
				users.add(user);
			}
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_GET_DRIVER_EXCEPTION, e);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closeStatement(statement);
		}
		
		return users;
	}

	/**
	 * Get user by id.
	 *  
	 *  @param id - user's id
	 *  @throws DaoException - if can't get user by id
	 */
	@Override
	public User getUserById(Long id) throws DaoException {
		
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		User user = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
			
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				user = new User();
				
				user.setId(resultSet.getLong(ID));
				user.setName(resultSet.getString(NAME));
				user.setSurname(resultSet.getString(SURNAME));
				user.setLogin(resultSet.getString(LOGIN));
				user.setPassword(resultSet.getString(PASSWORD));
				user.setPhoneNumber(resultSet.getString(PHONE));
				user.setRole(User.Role.valueOf(resultSet.getString(TITLE).toUpperCase()));
				
			}
		} catch (SQLException e) {
			throw new DaoException(GET_USER_BY_ID_EXCEPTION);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closePreparedStatement(statement);
		}
	
		return user;
	}

}
