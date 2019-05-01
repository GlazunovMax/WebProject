package by.epam.javawebtraining.glazunov.webproject.service.exception;

public class ServiceException extends Exception {
	
	/**
	 * @see ServiceException#ServiceException(Exception)
	 * @see ServiceException#ServiceException(String)
	 * @see ServiceException#ServiceException(String, Exception)
	 */
	public ServiceException() {
		super();
	}

	/**
	 * @see ServiceException#ServiceException()
	 * @see ServiceException#ServiceException(Exception)
	 * @see ServiceException#ServiceException(String, Exception)
	 * @param message -  the detail message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @see ServiceException#ServiceException()
	 * @see ServiceException#ServiceException(String)
	 * @see ServiceException#ServiceException(String, Exception)
	 * @param e - the cause
	 */
	public ServiceException(Exception e) {
		super(e);
	}

	/**
	 * @see ServiceException#ServiceException()
	 * @see ServiceException#ServiceException(String)
	 * @see ServiceException#ServiceException(Exception)
	 * @param message - the detail message
	 * @param e -  the cause
	 */
	public ServiceException(String message, Exception e) {
		super(message, e);
	}
	

}
