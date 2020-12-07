package hr.java.vjezbe.entitet;

import java.util.ArrayList;

/**
 * Entitet koji predstavlja kategoriju artikala te sadrzi naziv kategorije i
 * listu artikala koji pripadaju toj kategoriji
 * 
 * @author Nikola Filip
 * @version 5.0
 */
public class Kategorija<T extends Artikl> extends Entitet {
	private String naziv;
	private ArrayList<T> artikli;

	/**
	 * Konstruktor koji inicijalizira podatak o nazivu kategorije te stvara listu
	 * artikala
	 * 
	 * @param naziv Naziv kategorije
	 */
	public Kategorija(Long id, String naziv) {
		super(id);
		this.naziv = naziv;
		this.artikli = new ArrayList<T>();
	}

	/**
	 * Metoda za dodavanje objekta klase T u listu artikala
	 * 
	 * @param e Objekt koji se dodaje u listu artikala
	 */
	public void dodajArtikl(T e) {
		artikli.add((T) e);
	}

	public T dohvatiArtikl(int index) {
		return artikli.get(index);
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public ArrayList<T> getArtikli() {
		return artikli;
	}

	public void setArtikli(ArrayList<T> artikli) {
		this.artikli = artikli;
	}

}
