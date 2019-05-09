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
		
		while(scan.hasNext()) {
			currentLine = scan.nextLine();
			System.out.println(currentLine);
		}
		
		scan.close();
		return null;
	}
	
}
