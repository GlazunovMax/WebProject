package by.epam.javawebtraining.glazunov.webproject.dao.impl;

import static by.epam.javawebtraining.glazunov.webproject.dao.impl.SomeConstant.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.epam.javawebtraining.glazunov.webproject.dao.FeedbackDao;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.ConnectionPoolException;
import by.epam.javawebtraining.glazunov.webproject.dao.dbConnection.FactoryConnectionPool;
import by.epam.javawebtraining.glazunov.webproject.dao.exception.DaoException;
import by.epam.javawebtraining.glazunov.webproject.entity.Feedback;
import by.epam.javawebtraining.glazunov.webproject.entity.User;

public class DatabaseFeedbackDao implements FeedbackDao {
	private static Logger LOGGER = Logger.getLogger(DatabaseFeedbackDao.class);
	
	/**
	 * Add the feedback to the database.
	 * 
	 * @param feedback - the feedback
	 * @throws DaoException if can't add feedback to database
	 */
	@Override
	public void add(Feedback feedback) throws DaoException {
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		Connection connection = null;
		
		PreparedStatement statementVar = null;
		PreparedStatement statementClientId = null;
		PreparedStatement statementInsertFeedback = null;
		
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			
			//first transaction
			statementVar = connection.prepareStatement(SQL_TRANSACTION_VARIABLE_ADD_CLIENT_ID);
			statementVar.executeUpdate();

			//second transaction
			statementClientId = connection.prepareStatement(SQL_SELECT_TRANSACTION_CLIENT_ID);
			statementClientId.setString(1, feedback.getUser().getSurname());
			statementClientId.executeQuery();

			//third transaction
			statementInsertFeedback = connection.prepareStatement(SQL_INSERT_FEEDBACK_TRANSACTION);
			statementInsertFeedback.setString(1, feedback.getText());
			statementInsertFeedback.executeUpdate();

			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
				LOGGER.error(ROLLBACK_EXCEPTION, e2);
				throw new DaoException(ADD_FEEDBACK_EXCEPTION, e2);
			}
			throw new DaoException(ADD_FEEDBACK_EXCEPTION, e);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		}finally {
			ResourceClose.closePreparedStatement(statementInsertFeedback);
			ResourceClose.closePreparedStatement(statementClientId);
			ResourceClose.closePreparedStatement(statementVar);
			ResourceClose.closeConnection(connection);
		}
		
	}

	/**
	 * Delete the feedback from the database by id.
	 * 
	 *  @param id - the feedback's id
	 *  @throws DaoException if can't delete feedback to database
	 */
	@Override
	public void remove(long id) throws DaoException { 
		DaoSource.remove(id, SQL_REMOVE_FEEDBACK, REMOVE_FEEDBACK_EXCEPTION);	
	}

	
	/**
	 * Get all Feedback from the DB.
	 * 
	 * @return List of all Feedback
	 * @throws DaoException if can't get all Feedback
	 */
	@Override
	public List<Feedback> getAllFeedback(int offset, int countRows) throws DaoException {
		List<Feedback> feedbacks = new ArrayList<>();
		
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Feedback feedback = null;
		//User client = null;
		
		try (Connection connection = connectionPool.takeConnection()) {
			statement = connection.prepareStatement(SQL_SELECT_ALL_FEEDBACKS);
			
			statement.setInt(1, offset);
			statement.setInt(2, countRows);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				feedback = createFeedback(resultSet);

				feedbacks.add(feedback);
			}
		} catch (SQLException e) {
			throw new DaoException(GET_ALL_FEEDBACK_EXCEPTION);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closePreparedStatement(statement);
		}
		return feedbacks;
	}

	

	/**
	 * Get all the Feedback owned by the specific client.
	 *  
	 *  @param id - client's id
	 *  @throws DaoException - if can't get all the Feedback owned by the specific client
	 */
	@Override
	public List<Feedback> getAllFeedbackByClientId(long id, int offset, int countRows) throws DaoException {
		List<Feedback> feedbacks = new ArrayList<>();
		
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		Feedback feedback = null;
		
		try (Connection connection = connectionPool.takeConnection()) {
			statement = connection.prepareStatement(SQL_SELECT_ALL_FEEDBACK_BY_ID);
			statement.setLong(1, id);
			statement.setInt(2, offset);
			statement.setInt(3, countRows);
			
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				feedback = createFeedback(resultSet);
				
				feedbacks.add(feedback);		
			}
		} catch (SQLException e) {
			throw new DaoException(GET_ALL_FEEDBACK_EXCEPTION);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closeStatement(statement);
		}

		return feedbacks;
	}

	/**
	 * Edit the Feedback client.
	 *  
	 *  @param feedback - the feedback
	 *  @throws DaoException - if can't Edit the Feedback
	 */	
	@Override
	public void editFeedback(Feedback feedback) throws DaoException {
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		
	
		try(Connection connection = connectionPool.takeConnection()) {
			statement = connection.prepareStatement(SQL_UPDATE_FEEDBACK_BY_ID);
			
			statement.setString(1, feedback.getText());
			statement.setLong(2, feedback.getId());
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			throw new DaoException(UPDATE_FEEDBACK_EXCEPTION);
		} catch (ConnectionPoolException e) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e);
		}finally {
			ResourceClose.closePreparedStatement(statement);
		}
		
	}


	@Override
	public Feedback getFeedbackById(long id) throws DaoException {
		FactoryConnectionPool factoryConnectionPool = FactoryConnectionPool.getInstance();
		ConnectionPool connectionPool = factoryConnectionPool.getConnectionPool();
		
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		Feedback feedback = null;
		
		
		try(Connection connection = connectionPool.takeConnection()) {
			
			statement = connection.prepareStatement(SQL_SELECT_FEEDBACK_BY_ID);
			
			statement.setLong(1, id);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				feedback = createFeedback(resultSet);
			}	
		} catch (SQLException e) {
			throw new DaoException(GET_FEEDBACK_EXCEPTION_BY_ID);
		} catch (ConnectionPoolException e1) {
			LOGGER.error(MESSAGE_CONNECTION_POOL_EXCEPTION, e1);
		} finally {
			ResourceClose.closeResultSet(resultSet);
			ResourceClose.closeStatement(statement);
		}
		
		return feedback;
	}

	
	
	private Feedback createFeedback(ResultSet resultSet) throws SQLException {
		Feedback feedback = new Feedback();
		User client = new User();
		
		feedback.setId(resultSet.getLong(ID));
		feedback.setText(resultSet.getString("review"));
			client.setId(resultSet.getLong(CLIENT_ID));
			client.setName(resultSet.getString(NAME));
			client.setSurname(resultSet.getString(SURNAME));
			client.setLogin(resultSet.getString(LOGIN));
			client.setPassword(resultSet.getString(PASSWORD));
			client.setPhoneNumber(resultSet.getString(PHONE));
			client.setRole(User.Role.valueOf(resultSet.getString(TITLE).toUpperCase()));
		feedback.setUser(client);
		return feedback;
	}
}
