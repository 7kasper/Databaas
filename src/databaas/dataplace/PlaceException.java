package databaas.dataplace;

/***
 * Abstraction for implementations of place exceptions, such as the jdbc SqlException.
 */
public class PlaceException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlaceException(String message, Throwable cause) {
		super(message, cause);
	}

}
