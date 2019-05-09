package fr.conversion.model;

import java.io.File;

public class Conversion{
	
	private File inFile;
	private File outFile;

	public void toXML() {
	}
	public void toJSON() {
	}
	public void toYAML() {
	}
	
	/**
	 * Retourne le type du fichier d'entrée
	 * @return le type du fichier d'entrée
	 */
	public FileType getInputType() {
		String extension = "";
		extension = inFile.getName().substring(inFile.getName().lastIndexOf('.')+1, inFile.getName().length());
		extension = extension.toLowerCase();
		
		switch(extension) {
		case "xml":
			return FileType.XML;
		case "json":
			return FileType.JSON;
		case "yml":
			return FileType.YAML;
		default:
			return null;
		}
	}
	
	public Conversion(File in,File out) {
		inFile = in;
		outFile = out;
	}
	
	public File getInFile() {
		return inFile;
	}
	
	public void setInFile(File inFile) {
		this.inFile = inFile;
	}
	
	public File getOutFile() {
		return outFile;
	}
	
	public void setOutFile(File outFile) {
		this.outFile = outFile;
	}
	
}
