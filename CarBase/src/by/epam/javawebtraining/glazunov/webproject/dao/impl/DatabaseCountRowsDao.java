package by.epam.javawebtraining.glazunov.webproject.dao.impl;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.CountRowDao;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPoolException;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.FactoryConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;

public class DatabaseCountRowsDao implements CountRowDao {
	private static Logger LOGGER = Logger.getLogger(DatabaseCountRowsDao.class);

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


	/**
	 * Method get a feedback count
	 * 
	 * @param id
	 *            - client id
	 * @return a feedback count
	 * @throws DaoException
	 *             - if you cannot get feedbacks count
	 */
	@Override
	public int getAllFeedbackByIdClientCount(long id) throws DaoException {
		String sql = SQL_GET_ALL_FEEDBACK_BY_ID_CLIENT;
		String messageException = MESSAGE_FIND_COUNT_FEEDBACK_BY_ID_CLIENT_EXCEPTION;
		
		return getCountRows(id, sql, messageException);
	}
	
	
	/**
	 * Method get all feedback count
	 * 
	 * @return a feedback count
	 * @throws DaoException
	 *             - if you cannot get feedback count
	 */
	@Override
	public int getAllFeedbackCount() throws DaoException {
		String sql = SQL_GET_COUNT_ALL_FEEDBACK;
		String messageException = MESSAGE_FIND_COUNT_FEEDBACK_EXCEPTION;
		
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
