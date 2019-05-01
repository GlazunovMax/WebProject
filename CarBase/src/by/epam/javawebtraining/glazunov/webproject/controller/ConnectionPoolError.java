package by.epam.javawebtraining.glazunov.webproject.controller;

public class ConnectionPoolError extends RuntimeException {

	/**
	 * @see ConnectionPoolError#ConnectionPoolError(Exception)
	 * @see ConnectionPoolError#ConnectionPoolError(String)
	 * @see ConnectionPoolError#ConnectionPoolError(String, Exception)
	 */
	public ConnectionPoolError() {
		super();
	}

	/**
	 * @see ConnectionPoolError#ConnectionPoolError()
	 * @see ConnectionPoolError#ConnectionPoolError(String)
	 * @see ConnectionPoolError#ConnectionPoolError(Exception)
	 * @param message - the detail message
	 * @param e -  the cause
	 */
	public ConnectionPoolError(String message, Exception e) {
		super(message, e);
	}

	/**
	 * @see ConnectionPoolError#ConnectionPoolError()
	 * @see ConnectionPoolError#ConnectionPoolError(Exception)
	 * @see ConnectionPoolError#ConnectionPoolError(String, Exception)
	 * @param message -  the detail message
	 */
	public ConnectionPoolError(String message) {
		super(message);
	}

	/**
	 * @see ConnectionPoolError#ConnectionPoolError()
	 * @see ConnectionPoolError#ConnectionPoolError(String)
	 * @see ConnectionPoolError#ConnectionPoolError(String, Exception)
	 * @param e - the cause
	 */
	public ConnectionPoolError(Exception e) {
		super(e);
	}
}
