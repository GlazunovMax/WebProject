package by.epam.javawebtraining.glazunov.webproject.dao;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Model;


/**
 * Shows common methods on dao layer interfaces
 * @author Glazunov
 *
 * @param <T> - T extends Model
 */
public interface ItemDao<T extends Model> {
	static Logger LOGGER = Logger.getLogger(ItemDao.class);

	/**
	 * Get all <T> from the DB.
	 * 
	 * @return List of all <T>
	 * @throws DaoException if can't get all <T>
	 */
	List<T> getAll() throws DaoException;
	
	/**
	 * Add the item to the database.
	 * 
	 *  @param item - the item
	 *  @throws DaoException if can't add item to database
	 */
	void add(T item) throws DaoException;

	/**
	 * Delete the T extends Model from the database by id.
	 * 
	 *  @param id - the T extends Model id
	 *  @throws DaoException if can't add T extends Model to database
	 */
	void remove(long id) throws DaoException;
	
	
	
	
	
	default void closeResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
				LOGGER.debug(MESSAGE_RESULT_SET_CLOSED);
			}
		} catch (SQLException e) {
			LOGGER.error(MESSAGE_RESULT_SET_CLOSED_EXCEPTION, e);
		}
	}
	
	default void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
				LOGGER.debug(MESSAGE_PREPARED_STATEMENT_CLOSED);
			}
		} catch (SQLException e) {
			LOGGER.error(PREPARED_STATEMENT_CLOSED_EXCEPTION, e);
		}
	}

	default void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
				LOGGER.debug(MESSAGE_STATEMENT_CLOSED);
			}
		} catch (SQLException e) {
			LOGGER.error(MESSAGE_STATEMENT_CLOSED_EXCEPTION, e);
		}
	}
	
	default void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
				LOGGER.debug(MESSAGE_CONNECTION_CLOSED);
			}
		} catch (SQLException e) {
			LOGGER.error(CONNECTION_CLOSED_EXCEPTION);
		}
	}
	
	default void closeConnectionWithCommit(Connection connection) {
		try {
			if (connection != null) {
				connection.setAutoCommit(true);
				connection.close();
				LOGGER.debug(MESSAGE_CONNECTION_CLOSED);
			}
		} catch (SQLException e) {
			LOGGER.error(CONNECTION_CLOSED_EXCEPTION);
		}
	}

}
