package by.epam.javawebtraining.glazunov.webproject.dao.dbConnection;

public class ConnectionPoolException extends Exception{

	public ConnectionPoolException() {
		super();
	}

	public ConnectionPoolException(String message, Exception exception) {
		super(message, exception);
	}
	
}
