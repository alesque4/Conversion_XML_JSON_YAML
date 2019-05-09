package fr.conversion.service;

public class HelpService extends Service {

	@Override
	public void executeUC(String[] args) {
		System.out.println("*** Converter help ***");
		System.out.println("");
		System.out.println("Usage : converter <options> origin destination");
		System.out.println("");
		System.out.println("options :");
		System.out.println("	-o format : output format for the destination file. Formats are xml, json, yaml");
		System.out.println("	--help : display help");
		System.out.println("");
	}

}
