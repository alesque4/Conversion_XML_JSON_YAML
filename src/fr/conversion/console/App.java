/**
 * Outil de conversion de fichiers entre XML, JSON et YAML
 * @author Alexandre MEUR
 */
package fr.conversion.console;

import fr.conversion.exception.ConversionException;
import fr.conversion.service.Service;
import fr.conversion.service.ServiceFactory;

public class App {

	public static void main(String[] args) {

		ServiceFactory factory = new ServiceFactory();
		Service service = factory.serviceFactory(args);		
		
		//Lancement du service si possible
		if(service != null) {
			try {
				service.executeUC(args);
			} catch (ConversionException e) {
				System.out.println(e.getMessage());
			}
		}else {
			System.out.println("Couldn't launch conversion.");
			System.out.println("Use converter --help");
		}
		
	}
	
}
