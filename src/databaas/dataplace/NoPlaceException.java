package databaas.dataplace;

public class NoPlaceException extends Exception {
	private static final long serialVersionUID = 3600231859488132002L;

	public NoPlaceException() { 
		super();
	}

	public NoPlaceException(String message) {
		super(message);
	}

}
