package fr.conversion.service;

import fr.conversion.exception.ConversionException;

public abstract class Service {

	public abstract void executeUC(String[] args) throws ConversionException;
	
}
