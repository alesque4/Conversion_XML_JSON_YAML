package fr.conversion.service.parse;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.conversion.exception.ParsingException;
import fr.conversion.model.Balise;

public class XmlParser extends Parser{
	
	public static final char[] OPEN_BALISE = {'<'};
	public static final char[] BLANKS = {' ','\t','\n'};
	public static final char[] END_BALISE = {'/','>'};
	public static final char[] BLANKS_AND_END_BALISE = {'/','>',' ','\t','\n'};
	public static final char[] SEPARATOR_CHAMP_VALEUR = {'='};
	public static final int MARK = 50;

	public XmlParser(File f) {
		super(f);
	}

	@Override
	public ArrayList<Balise> parse() throws ParsingException {
		Balise currentBalise = new Balise();
		ArrayList<Balise> listBalise = new ArrayList<Balise>();
		boolean keepReading = true;
		
		//Tant qu'on a quelque chose à lire
		while(keepReading) {
			currentBalise = getNextBalise("");
			if(currentBalise == null) {
				keepReading = false;
			}else {
				listBalise.add(currentBalise);
			}
		}
		
		return listBalise;
	}

	@Override
	public Balise getNextBalise(String newName) throws ParsingException {
		Balise balise = new Balise();
		String nextName = "";
		
		//Récupération du nom de la balise
		if(newName.equals("")) {
			balise.setNom(getBaliseName());
		}else {
			balise.setNom(newName);
		}
		System.out.println("---Nom lu : "+balise.getNom());
		
		if(balise.getNom().equals("")) {
			return null;	//Cas où il n'y a plus de balises à lire
		}
			
		//Récupération des champs si il y en a
		if(getLastChar() != '>' && getLastChar() != '/') {
			try {
				System.out.println("Recherche champs balise "+balise.getNom());
				balise.setChamps(getBaliseChamps());
			} catch (ParsingException e) {
				balise.setChamps(new HashMap<String, String>());
				System.out.println("Error while parsing champs");
			}
		}
		
		//Cas où la balise se ferme elle même
		if(getLastChar() == '/') {
			if(getNextChar() == '>') {
				System.out.println("Return Balise />");
				return balise;
			}
		}
		
		//Tant qu'on trouve pas la balise fermante, on ajoute les balises
		//qu'on trouve dans les balises filles
		while(true) {
			
			nextName = getBaliseName();
			//Si c'est la balise fermant cette balise on la retourne
			if(nextName.equals('/'+balise.getNom())) {
				System.out.println("Return Balise >");
				return balise;
			//Sinon on l'ajoute aux balises filles
			}else if(nextName.equals("")) {
				//Pas de balise alors qu'on devrait en trouver
				throw new ParsingException("Pas de balise où il devrait en avoir une.");
			}else{
				balise.getListBalises().add(getNextBalise(nextName));
			}
		}
	}

	@Override
	public String getBaliseName() throws ParsingException {
		String name = "";
		
		//Déplacement jusqu'à l'ouverture de la balise
		if(moveReaderUntil(OPEN_BALISE)) {
			name = getReaderTextUntil(BLANKS_AND_END_BALISE);
			return name;
		}else {
			return "";
		}
	}

	@Override
	public HashMap<String, String> getBaliseChamps() throws ParsingException {
		String currentKey = "", currentValue = "";
		HashMap<String, String> result = new HashMap<String, String>();
		boolean keepLooking = true;
		
		//Do that until end of balise
		while(keepLooking) {
			currentKey = getReaderTextUntil(SEPARATOR_CHAMP_VALEUR);
			currentValue = getReaderTextUntil(BLANKS_AND_END_BALISE);
			
			System.out.println("K: "+currentKey+" V: "+currentValue);
			if(!currentKey.equals("")) {
				result.put(currentKey, currentValue);
			}else {
				System.out.println("Empty key !");
			}
		
			//Si on atteind la fin de la balise on a trouvé tous les champs
			while(getLastChar() == ' ') getNextChar();
			if(getLastChar() == '/' || getLastChar() == '>') {
				keepLooking = false;
				System.out.println("Fin recherche champs");
			}
		}
		return result;
	}	
}
