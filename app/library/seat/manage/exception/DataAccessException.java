package library.seat.manage.exception;

/**
 * 
 * @author mahs
 * @version 1.0
 * @created 2-1-2013 13:20:00
 */
public class DataAccessException extends RuntimeException {
	public DataAccessException() {
		super();
	}

	public DataAccessException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DataAccessException(String arg0) {
		super(arg0);
	}

	public DataAccessException(Throwable arg0) {
		super(arg0);
	}
}
