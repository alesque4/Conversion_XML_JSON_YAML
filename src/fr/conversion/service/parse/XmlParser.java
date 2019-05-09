package fr.conversion.service.parse;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import fr.conversion.exception.ParsingException;
import fr.conversion.model.Balise;

public class XmlParser extends Parser{

	public XmlParser(File f) {
		super(f);
	}

	@Override
	public ArrayList<Balise> parse() throws ParsingException {
		Scanner scan = getScanner();
		String currentLine = "";
		Balise currentBalise = new Balise();
		int curretnId = 0, currentIdStart = 0, currentIdEnd = 0;;
		
		while(scan.hasNext()) {
			currentLine = scan.nextLine();
			currentIdStart = currentLine.indexOf('<')+1;
			currentIdEnd = currentLine.indexOf('>');
			
			currentBalise.setNom(currentLine.substring(currentIdStart, currentIdEnd));
			System.out.println(currentBalise);
		}
		
		scan.close();
		return null;
	}
	
}
