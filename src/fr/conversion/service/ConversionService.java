package fr.conversion.service;

import java.io.File;
import java.io.IOException;

import fr.conversion.exception.ConversionException;

public class ConversionService extends Service {

	@Override
	public void executeUC(String[] args) throws ConversionException {
		String extension = "", pathIn = "", pathOut = "";
		File fileIn, fileOut;
		
		//analyse des paramètres
		extension = args[1];
		pathIn = args[2];
		pathOut = args[3];
		
		System.out.println("Ext : "+extension);
		System.out.println("pathIn : "+pathIn);
		System.out.println("pathOut : "+pathOut);
		
		//Initialisation des fichiers
		fileIn = new File(pathIn);
		if(!fileIn.canRead()) {
			throw new ConversionException("Can't read input file");
		}
		fileOut = new File(pathOut+'.'+extension);
		try {
			fileOut.createNewFile();
		} catch (IOException e) {
			throw new ConversionException("I/O error with output file.");
		}
		if(!fileOut.canWrite()) {
			throw new ConversionException("Can't write output file");
		}
		
		System.out.println("File in : "+fileIn);
		System.out.println("File out : "+fileOut);
		
	}
	
}
