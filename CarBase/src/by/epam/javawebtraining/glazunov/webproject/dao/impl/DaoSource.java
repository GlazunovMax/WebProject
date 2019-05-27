package by.epam.javawebtraining.glazunov.webproject.dao.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.MESSAGE_CONNECTION_POOL_EXCEPTION;
import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.REMOVE_CITY_EXCEPTION;
import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.SQL_DELETE_UPDATE_CITY;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPoolException;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.FactoryConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;

public class DaoSource {
	private static Logger LOGGER = Logger.getLogger(DaoSource.class);

	public static void edit(long id, String param, String sql, String exceptionMessage) throws DaoException {
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		
		try (Connection connection = connectionPool.takeConnection()) {
			statement = connection.prepareStatement(sql);
			statement.setString(1, param);
			statement.setLong(2, id);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException(exceptionMessage);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		}finally {
			ResourceClose.closePreparedStatement(statement);
		}
	}
	
	public static void remove(long id, String sql, String exeptionMessage) throws DaoException{
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;

		try (Connection connection = connectionPool.takeConnection()) {
			
			
			statement = connection.prepareStatement(sql);
			statement.setLong(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException(exeptionMessage);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			ResourceClose.closePreparedStatement(statement);
		}
	}

}
