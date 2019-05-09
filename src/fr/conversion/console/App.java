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
		//Scanner input = new Scanner(System.in);
		
		/*
		File f1 = new File("bla/bla/fichier.xml");
		File f2 = new File("bla/bla/fichier.yml");
		File f3 = new File("bla/fic.json");
		
		Conversion c1 = new Conversion(f3, f2);
		Conversion c2 = new Conversion(f2, f3);
		
		System.out.println("c1 input : "+c1.getInputType());
		System.out.println("c2 input : "+c2.getInputType());
		*/
		
		//Choix du service en fonction des arguments
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
