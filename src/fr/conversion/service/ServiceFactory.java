package fr.conversion.service;

public class ServiceFactory {

	/**
	 * Analyse des param�tres en entr�e et cr�ation du service correspondant
	 * @param args
	 * @return
	 */
	public Service serviceFactory(String[] args) {
		if(args.length >= 1) {
			switch(args[0]) {
			case "--help":
				return new HelpService();
			case "-o":
				return new ConversionService();
			}
		}
		return null;
	}
	
}
