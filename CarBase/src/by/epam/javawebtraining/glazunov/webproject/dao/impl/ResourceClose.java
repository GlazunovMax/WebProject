package by.epam.javawebtraining.glazunov.webproject.dao.impl;

import static by.epam.javawebtraining.glazunov.webproject.util.SomeConstant.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class ResourceClose {
	private static Logger LOGGER = Logger.getLogger(ResourceClose.class);
	
	public static void closePreparedStatement(PreparedStatement preparedStatement) {
		try {
			if (preparedStatement != null) {
				preparedStatement.close();
				LOGGER.debug(MESSAGE_PREPARED_STATEMENT_CLOSED);
			}
		} catch (SQLException e) {
			LOGGER.error(PREPARED_STATEMENT_CLOSED_EXCEPTION, e);
		}
	}

	public static void closeStatement(Statement statement) {
		try {
			if (statement != null) {
				statement.close();
				LOGGER.debug(MESSAGE_STATEMENT_CLOSED);
			}
		} catch (SQLException e) {
			LOGGER.error(MESSAGE_STATEMENT_CLOSED_EXCEPTION, e);
		}
	}

	public static void closeResultSet(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
				LOGGER.debug(MESSAGE_RESULT_SET_CLOSED);
			}
		} catch (SQLException e) {
			LOGGER.error(MESSAGE_RESULT_SET_CLOSED_EXCEPTION, e);
		}
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
				LOGGER.debug(MESSAGE_CONNECTION_CLOSED);
			}
		} catch (SQLException e) {
			LOGGER.error(CONNECTION_CLOSED_EXCEPTION);
		}
	}
	
	public static void closeConnectionWithCommit(Connection connection) {
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
