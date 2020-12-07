package hr.java.vjezbe.entitet;

import java.time.LocalDate;

/**
 * Entitet koji opisuje prodaju te sadrzi podatke o artiklu koji je na prodaju,
 * korisniku koji je stvorio oglas te datum stvaranja oglasa
 * 
 * @author Nikola
 * @version 1.0
 */
public class Prodaja extends Entitet {
	private Artikl artikl;
	private Korisnik korisnik;
	private LocalDate datumObjave;

	/**
	 * Konstruktor koji inicijalizira podatke o artiklu, korisniku te datumu objave.
	 * 
	 * @param tempArtikl  Artikl koji je na prodaju
	 * @param object      Korisnik koji je stvorio oglas
	 * @param datumObjave Datum objave oglasa
	 */
	public Prodaja(Long id, Artikl artikl, Korisnik korisnik, LocalDate datumObjave) {
		super(id);
		this.artikl = artikl;
		this.korisnik = korisnik;
		this.datumObjave = datumObjave;
	}

	public Object getArtikl() {
		return artikl;
	}

	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}

	public Object getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public LocalDate getDatumObjave() {
		return datumObjave;
	}

	public void setDatumObjave(LocalDate datumObjave) {
		this.datumObjave = datumObjave;
	}

}
