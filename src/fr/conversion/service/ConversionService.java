package fr.conversion.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import fr.conversion.exception.ParsingException;
import fr.conversion.exception.ServiceException;
import fr.conversion.model.Balise;
import fr.conversion.model.FileType;
import fr.conversion.service.parse.*;


public class ConversionService extends Service {

	@Override
	public void executeUC(String[] args) throws ServiceException {
		String extension = "", pathIn = "", pathOut = "";
		File fileIn, fileOut;
		ArrayList<Balise> model = new ArrayList<Balise>();
		Parser parser;
		
		//analyse des paramètres
		extension = args[1];
		pathIn = args[2];
		pathOut = args[3];
		
		//Initialisation des fichiers
		fileIn = new File(pathIn);
		if(!fileIn.canRead()) {
			throw new ServiceException("Can't read input file");
		}
		fileOut = new File(pathOut+'.'+extension);
		try {
			fileOut.createNewFile();
		} catch (IOException e) {
			throw new ServiceException("I/O error with output file.");
		}
		if(!fileOut.canWrite()) {
			throw new ServiceException("Can't write output file");
		}
		
		switch(fileType(fileIn)) {
		case CSV:
			parser = new CsvParser(fileIn);
			break;
		case JSON:
			parser = new JsonParser(fileIn);
			break;
		case XML:
			parser = new XmlParser(fileIn);
			break;
		case YAML:
			throw new ServiceException("YAML Files are not accepted in input.");
		default:
			//Ne devrait pas arriver ici.
			parser = new CsvParser(fileIn);
			break;			
		}

		//Parse start
		try {
			model = parser.parse();
		} catch (ParsingException e) {
			System.out.println(e.getMessage());
		}
		
		
		System.out.println("MODELE : ");
		System.out.println(model);
		
			
	}
	
	/**
	 * Retourne le format du fichier f
	 * @param f
	 * @return le format du fichier
	 * @throws ServiceException 
	 */
	public static FileType fileType(File f) throws ServiceException {
		String extension ="", fileName = f.getName();
		
		extension = f.getName().substring(fileName.lastIndexOf('.')+1,fileName.length());
		switch(extension.toLowerCase()) {
		case "xml":
			return FileType.XML;
		case "json":
			return FileType.JSON;
		case "yml":
			return FileType.YAML;
		case "csv":
			return FileType.CSV;
		default:
			throw new ServiceException("Unknown file format");
		}
	}
	
}
