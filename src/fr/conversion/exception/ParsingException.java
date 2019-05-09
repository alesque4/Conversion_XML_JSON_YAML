package fr.conversion.exception;

@SuppressWarnings("serial")
public class ParsingException extends ConversionException{
	
	public ParsingException() {
		super();
	}
	
	public ParsingException(String msg) {
		super(msg);
	}
	
}
