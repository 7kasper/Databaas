package databaas.dataplace;

public class NoPlaceException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoPlaceException() { 
		super();
	}

	public NoPlaceException(String message) {
		super(message);
	}

}
