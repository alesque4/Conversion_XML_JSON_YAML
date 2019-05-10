package fr.conversion.service.parse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.conversion.exception.ParsingException;
import fr.conversion.model.Balise;

public abstract class Parser {
	
	private FileReader reader;
	private char lastChar;
	
	public abstract ArrayList<Balise> parse() throws ParsingException;
	public abstract Balise getNextBalise(String newName) throws ParsingException;
	public abstract String getBaliseName() throws ParsingException;
	public abstract HashMap<String, String> getBaliseChamps() throws ParsingException;
	
	public Parser(File f) {
		try {
			setReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			System.out.println("Can't find file to parse.");
			setReader(null);
		}
		setLastChar('a');
	}
	
	public Parser(String path) {
		try {
			setReader(new FileReader(new File(path)));
		} catch (FileNotFoundException e) {
			System.out.println("Can't find file to parse.");
			setReader(null);
		}
		setLastChar('a');
	}
	
	/**
	 * Return the next character in the reader
	 * @param reader
	 * @return the next character
	 * @throws ParsingException
	 */
	protected char getNextChar() throws ParsingException {
		int i;
		try {
			i = reader.read();
		} catch (IOException e) {
			throw new ParsingException("I/O Problem while reading character : "+e.getMessage());
		}
		
		if(i == -1) {
			lastChar = '\0';
			return lastChar;
		}else {
			lastChar = (char) i;
			return lastChar;
		}
	}
	
	/**
	 * bouge le reader jusqu'à ce qu'on atteigne un de char souhaités
	 * @param reader
	 * @param tabChar a tab of char to reach
	 * @throws ParsingException 
	 * @return Indique si on a atteind un char demandé
	 */
	public boolean moveReaderUntil(char[] tabChar) throws ParsingException {
		char currentChar = getLastChar();
		
		for(char c : tabChar) {
			if(currentChar == c) return true;
			else if(currentChar == '\0') return false;
		}
		while(true){
			currentChar = getNextChar();
			for(char c : tabChar) {
				if(currentChar == c) return true;
				else if(currentChar == '\0') return false;
			}
		}
	}
	
	/**
	 * Return the text in the reader until one of the characters in tabChar is reached
	 * (last char not included in result)
	 * @param reader
	 * @param tabChar a tab of char to reach
	 * @throws ParsingException 
	 * @return Indique si le traitement s'est bien passé
	 */
	public String getReaderTextUntil(char[] tabChar) throws ParsingException {
		char currentChar = getLastChar();
		String res = "";
			
		do {
			currentChar = getNextChar();
			for(int i=0; i<tabChar.length; i++) {
				if(currentChar == tabChar[i] || currentChar == '\0') return res;
			}
			res = res + currentChar;
		}while(true);
	}
	public FileReader getReader() {
		return reader;
	}
	private void setReader(FileReader reader) {
		this.reader = reader;
	}
	public char getLastChar() {
		return lastChar;
	}
	private void setLastChar(char lastChar) {
		this.lastChar = lastChar;
	}
}
