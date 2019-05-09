package fr.conversion.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Balise {
	
	private String nom;
	private HashMap<String, String> champs;
	private ArrayList<Balise> listBalises;
	
	public Balise() {
		nom = "";
		champs = new HashMap<String, String>();
		listBalises = new ArrayList<Balise>();
	}
	
	public Balise(String nom) {
		this.nom = nom;
		champs = new HashMap<String, String>();
		listBalises = new ArrayList<Balise>();
	}
	
	public String toString() {
		String res = new String(nom)+" ";
		
		for(Balise b : listBalises) {
			res = res + b.toString() + " ";
		}
		return res;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public HashMap<String, String> getChamps() {
		return champs;
	}
	public void setChamps(HashMap<String, String> champs) {
		this.champs = champs;
	}
	public ArrayList<Balise> getListBalises() {
		return listBalises;
	}
	public void setListBalises(ArrayList<Balise> listBalises) {
		this.listBalises = listBalises;
	}
	
	

}
