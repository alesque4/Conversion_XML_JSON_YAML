package fr.conversion.service.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import fr.conversion.exception.ParsingException;
import fr.conversion.model.Balise;

public abstract class Parser {
	@SuppressWarnings("unused")
	private File file;
	
	public abstract ArrayList<Balise> parse() throws ParsingException;
	
	public Parser(File f) {
		file = f;
	}
	
	/**
	 * Fournit un scanner sur le fichier à parser
	 * @return le scanner
	 * @throws ParsingException
	 */
	protected Scanner getScanner() throws ParsingException {
		try {
			return new Scanner(file);
		} catch (FileNotFoundException e) {
			throw new ParsingException("File not found. Shouldn't happen, file already found earlier.");
		}
	}

}
