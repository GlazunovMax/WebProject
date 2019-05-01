package by.epam.javawebtraining.glazunov.webproject.dao.exception;

/**
 * 
 * @author Glazunov
 * @version 1.0
 */
public class DaoException extends Exception {

	/**
	 * @see DaoException#DaoException(Exception)
	 * @see DaoException#DaoException(String)
	 * @see DaoException#DaoException(String, Exception)
	 */
	public DaoException() {
		super();
	}

	/**
	 * @see DaoException#DaoException()
	 * @see DaoException#DaoException(String)
	 * @see DaoException#DaoException(Exception)
	 * @param message - the detail message
	 * @param exception -  the cause
	 */
	public DaoException(String message, Exception exception) {
		super(message, exception);
	}

	/**
	 * @see DaoException#DaoException()
	 * @see DaoException#DaoException(Exception)
	 * @see DaoException#DaoException(String, Exception)
	 * @param message -  the detail message
	 */
	public DaoException(String message) {
		super(message);
	}

	/**
	 * @see DaoException#DaoException()
	 * @see DaoException#DaoException(String)
	 * @see DaoException#DaoException(String, Exception)
	 * @param exception - the cause
	 */
	public DaoException(Exception exception) {
		super(exception);
	}

}
