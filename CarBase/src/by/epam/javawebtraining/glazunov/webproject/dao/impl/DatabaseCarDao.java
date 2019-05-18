package by.epam.javawebtraining.glazunov.webproject.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.CarDao;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPoolException;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.FactoryConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;
import by.epam.javawebtraining.glazunov.webproject.entity.City;
import by.epam.javawebtraining.glazunov.webproject.entity.User;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;
/**
 * The DatabaseCarDao class provides interaction car with the database.
 * 
 * @author Glazunov
 * @version 1.0
 */
public class DatabaseCarDao implements CarDao {
	private static final String SQL_INSERT_CAR_WITH_DRIVERS = "INSERT INTO users_has_cars(users_id, cars_id) VALUES(?, (SELECT id FROM cars WHERE cars.mark = ?))";//"INSERT INTO users_has_cars(users_id, cars_id) VALUES(?, ?)";
	private static final String ADD_CARS_FOR_DRIVER_EXCEPTION = "Error adding car for driver!";
	private static final String SQL_SELECT_CAR_BY_ID = "SELECT cars.id, cars.mark, cars.car_number, car_condition.status_car FROM cars INNER JOIN car_condition ON cars.car_condition_id = car_condition.id WHERE cars.id = ?";
	private static final String GET_CAR_BY_ID_EXCEPTION = "Error! You cannot get car by id!";
	private static Logger LOGGER = Logger.getLogger(DatabaseCarDao.class);
	
	
	/**
	 * Get all cars from the DB.
	 * 
	 * @return List of all cars
	 * @throws DaoException if can't get all cars
	 */
	@Override
	public List<Car> getAll(int offset, int countRows) throws DaoException {
		List<Car> cars = new ArrayList<>();
		Set<User> drivers = new HashSet<>();

		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ResultSet resultSetUser = null;
		Car car = null;
		User driver = null;
		
		try (Connection connection = connectionPool.takeConnection()) {

			statement = connection.prepareStatement(SQL_SELECT_ALL_CAR);
			
			statement.setInt(1, offset);
			statement.setInt(2, countRows);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				car = new Car();

				car.setId(resultSet.getLong(ID));
				car.setMark(resultSet.getString(MARK));
				car.setNumber(resultSet.getString(CAR_NUMBER));
				car.setStatusCar(Car.StatusCar.valueOf(resultSet.getString(CAR_CONDITION).toUpperCase()));
				
				long id = resultSet.getLong(ID);
				
				preparedStatement = connection.prepareStatement(SQL_SELECT_DRIVERS);
				preparedStatement.setLong(1, id);
				resultSetUser = preparedStatement.executeQuery();
				System.out.println(id);
					
					while (resultSetUser.next()){
						driver = new User();
					
						driver.setId(resultSetUser.getLong(ID));
						driver.setName(resultSetUser.getString(NAME));
						driver.setSurname(resultSetUser.getString(SURNAME));
						driver.setLogin(resultSetUser.getString(LOGIN));
						driver.setPassword(resultSetUser.getString(PASSWORD));
						driver.setPhoneNumber(resultSetUser.getString(PHONE));
						driver.setRole(User.Role.valueOf(resultSetUser.getString(TITLE).toUpperCase()));
						//driver.setCars(car);
						drivers.add(driver);
					}
				car.setUsers(drivers); 
				drivers = new HashSet<>();
				cars.add(car);
			}
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_GET_CAR_EXCEPTION, e);
		} catch (ConnectionPoolException ex) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, ex);
		} finally {
			ResourceClose.closeResultSet(resultSetUser);
			ResourceClose.closePreparedStatement(preparedStatement);
			ResourceClose.closePreparedStatement(statement);
		}
		return cars;
	}

	/**
	 * Add the car to the database.
	 * 
	 *  @param car - the car
	 *  @throws DaoException if can't add car to database
	 */
	@Override
	public void add(Car car) throws DaoException {//+addCar
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();

		Connection connection = null;

		PreparedStatement preparedStatementVar = null;
		PreparedStatement preparedStatementConditionCar = null;
		PreparedStatement preparedStatementInsert = null;
		
		try {
			connection = connectionPool.takeConnection();
			
			
			connection.setAutoCommit(false);

			// 1 - FIRST transaction
			preparedStatementVar = connection.prepareStatement(SQL_TRANSACTION_VARIABLE);
			preparedStatementVar.executeUpdate();

			// 2 - SECOND transaction
			preparedStatementConditionCar = connection.prepareStatement(SQL_SELECT_TRANSACTION_CONDITION_CAR);
			preparedStatementConditionCar.setString(1, car.getStatusCar().toString());
			preparedStatementConditionCar.executeQuery();

			// 3 - THIRD transaction
			preparedStatementInsert = connection.prepareStatement(SQL_INSERT_CAR_TRANSACTION);
			preparedStatementInsert.setString(1, car.getMark());
			preparedStatementInsert.setString(2, car.getNumber());
			//preparedStatementInsert.set(3, car.getUsers());
			preparedStatementInsert.executeUpdate();
			connection.commit();
			//
			/*System.out.println("=1");
			
			
			preparedStatement = connection.prepareStatement(SQL_INSERT_CAR_WITH_DRIVERS);
			for (User user : car.getUsers()) {
				
				System.out.println("=3");
				preparedStatement.setLong(1, user.getId());
				System.out.println("=4 " +user.getId());
				preparedStatement.setString(2, car.getMark());
				System.out.println("=5 " + car.getMark());
				//preparedStatement.executeUpdate();
				System.out.println("=7");
				
			}
			System.out.println("=6");
			preparedStatement.executeUpdate();
			*/
			//connection.commit();

		/*	for (User user : car.getUsers()) {
				//connection.setAutoCommit(false);

				preparedStatement = connection.prepareStatement(SQL_INSERT_CAR_WITH_DRIVERS);
				
				preparedStatement.setLong(1, user.getId());
				preparedStatement.setLong(2, car.getId());
				preparedStatement.executeUpdate();
				//connection.commit();
				
				
			}*/
			
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
				LOGGER.error(ROLLBACK_EXCEPTION);
				throw new DaoException(ADD_CAR_EXCEPTION);
			}
			throw new DaoException(ADD_CAR_EXCEPTION);
		} catch (ConnectionPoolException ex) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, ex);
		} finally {
			ResourceClose.closePreparedStatement(preparedStatementInsert);
			ResourceClose.closePreparedStatement(preparedStatementConditionCar);
			ResourceClose.closePreparedStatement(preparedStatementVar);
			ResourceClose.closeConnectionWithCommit(connection);
		}

	}

	/**
	 * Delete the car from the database by id.
	 * 
	 *  @param id - the car's id
	 *  @throws DaoException if can't delete car to database
	 */
	@Override
	public void remove(long id) throws DaoException {
		DaoSource.remove(id, SQL_DELETE_UPDATE_CAR, REMOVE_CAR_EXCEPTION);
	}

	/**
	 * Get all the cars owned by the specific driver.
	 *  
	 *  @param id - driver's id
	 *  @throws DaoException - if can't get all the cars owned by the specific driver
	 */
	

	@Override
	public Set<Car> getAllCarByIdDriver(long id, int offset, int countRows) throws DaoException {
		
		Set<Car> cars = new HashSet<>();
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.prepareStatement(SQL_SELECT_CARS_BY_ID_DRIVER);
			statement.setLong(1, id);//idDriver
			statement.setInt(2, offset); 
			statement.setInt(3, countRows);
			
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				Car car = new Car();
				
				car.setId(resultSet.getLong(ID));
				car.setMark(resultSet.getString(MARK));
				car.setNumber(resultSet.getString(CAR_NUMBER));
				car.setStatusCar(Car.StatusCar.valueOf(resultSet.getString(CAR_CONDITION).toUpperCase()));
			
				cars.add(car);
			}

		} catch (SQLException e) {
			throw new DaoException(GET_CARS_BY_ID_DRIVER_EXCEPTION);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		}finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closePreparedStatement(statement);
		}
		
		return cars;
	}

	/**
	 * Change car condition.
	 * @param id - car's id
	 * @param condition - car condition
	 * 
	 * @throws DaoException - if can't change car condition
	 */
	@Override
	public void editCarCondition(long id, String condition) throws DaoException {
		DaoSource.edit(id, condition, SQL_EDIT_CAR_CONDITION, EDIT_CAR_CONDITION_EXCEPTION);	
	}

	@Override
	public void addCarForDriver(Car car) throws DaoException {
		if(!(car.getUsers().isEmpty() || car.getUsers() == null)){
			
			FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
			ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
	
			PreparedStatement statement = null;
		
			try(Connection connection = connectionPool.takeConnection()) {
				
				statement = connection.prepareStatement(SQL_INSERT_CAR_WITH_DRIVERS);
				for (User user : car.getUsers()) {
					statement.setLong(1, user.getId());
					statement.setString(2, car.getMark());
					statement.executeUpdate();	
				}
			
			} catch (SQLException e) {
				throw new DaoException(ADD_CARS_FOR_DRIVER_EXCEPTION);
			} catch (ConnectionPoolException e1) {
				LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
			}finally {
				ResourceClose.closePreparedStatement(statement);
			}
		
		}
	}

	@Override
	public void addCarsDriver(User driver) throws DaoException {

		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();

		PreparedStatement statement = null;
	
		try(Connection connection = connectionPool.takeConnection()) {
			
			statement = connection.prepareStatement(SQL_INSERT_CAR_WITH_DRIVERS);
			
			for (Car car : driver.getCars()) {
				statement.setLong(1, driver.getId());
				statement.setString(2, car.getMark());
				statement.executeUpdate();
			}
		
		} catch (SQLException e) {
			throw new DaoException(ADD_CARS_FOR_DRIVER_EXCEPTION);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		}finally {
			ResourceClose.closePreparedStatement(statement);
		}
		
	}

	@Override
	public Car getCarById(long id) throws DaoException {
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Car car = null;
		
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.prepareStatement(SQL_SELECT_CAR_BY_ID);
			
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				car = new Car();
				
				car.setId(resultSet.getLong(ID));
				car.setMark(resultSet.getString(MARK));					
				car.setNumber(resultSet.getString(CAR_NUMBER));
				car.setStatusCar(Car.StatusCar.valueOf(resultSet.getString(CAR_CONDITION).toUpperCase()));				
				
			}
		} catch (SQLException e) {
			throw new DaoException(GET_CAR_BY_ID_EXCEPTION);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closePreparedStatement(statement);
		}
		return car;
	}
}
