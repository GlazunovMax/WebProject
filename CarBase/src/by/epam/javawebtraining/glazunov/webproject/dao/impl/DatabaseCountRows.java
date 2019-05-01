package by.epam.javawebtraining.glazunov.webproject.dao.impl;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPoolException;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.FactoryConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Car;

public class DatabaseCountRows {
	private static Logger LOGGER = Logger.getLogger(DatabaseCountRows.class);

	private static final String SQL_GET_ALL_CAR_BY_ID_DRIVER_COUNT = "SELECT COUNT(*) AS COUNT FROM users INNER JOIN users_has_cars ON users.id = users_has_cars.users_id INNER JOIN cars ON cars.id = users_has_cars.cars_id INNER JOIN car_condition ON cars.car_condition_id = car_condition.id WHERE users_has_cars.users_id = ?";
	private static final String COUNT = "COUNT";
	private static final String MESSAGE_FIND_COUNT_CAR_BY_ID_DRIVER_EXCEPTION = "Cannot find count cars by id driver";

	/**
	 * Method get a cars count
	 * 
	 * @param genre
	 *            - book genre
	 * @return a cars count
	 * @throws DaoException
	 *             - if you cannot get cars count
	 */
	public int getAllCarByIdDriverCount(long id) throws DaoException {
		int count = 0;

		FactoryConnectionPool factory = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factory.getConnectionPool();

		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try (Connection connection = connectionPool.takeConnection()) {
			statement = connection.prepareStatement(SQL_GET_ALL_CAR_BY_ID_DRIVER_COUNT);
			statement.setLong(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				count = resultSet.getInt(COUNT);
			}
		} catch (SQLException e) {
			throw new DaoException(MESSAGE_FIND_COUNT_CAR_BY_ID_DRIVER_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closePreparedStatement(statement);
		}

		return count;

	}
}
