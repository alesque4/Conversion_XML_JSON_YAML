package fr.conversion.exception;

@SuppressWarnings("serial")
public abstract class ConversionException extends Exception {

	public ConversionException(String msg) {
		super(msg);
	}

	public ConversionException() {
		super();
	}

}
