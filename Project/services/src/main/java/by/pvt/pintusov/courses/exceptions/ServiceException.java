package by.pvt.pintusov.courses.exceptions;

/**
 * Service Exception
 * @author pintusov
 * @version 1.0
 */
public class ServiceException extends Exception {
	public ServiceException (String message) { super(message); }

	public ServiceException(String message, Throwable cause){
		super(message, cause);
	}
}
