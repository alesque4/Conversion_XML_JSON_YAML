package fr.conversion.service;

import fr.conversion.exception.ServiceException;

public abstract class Service {

	public abstract void executeUC(String[] args) throws ServiceException;
	
}
