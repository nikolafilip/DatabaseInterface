package hr.java.vjezbe.entitet;

/**
 * Entitet koji nasljeduje klasu Korisnik i opisuje privatnog korisnika te
 * sadrzi podatke o imenu, prezimenu, e-Mail adresi i telefonskom broju
 * korisnika
 * 
 * @author Nikola Filip
 * @version 1.0
 */
public class PrivatniKorisnik extends Korisnik {

	private String ime, prezime;

	/**
	 * Konstruktor koji inicijalizira vrijednosti imena, prezimena, telefonskog
	 * broja i e-Mail adrese korisnika
	 * 
	 * @param email   e-Mail adresa korisnika
	 * @param telefon Telefonski broj korisnika
	 * @param ime     Ime korisnika
	 * @param prezime Prezime korisnika
	 */
	public PrivatniKorisnik(Long id, String email, String telefon, String ime, String prezime) {
		super(id, email, telefon);
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	@Override
	public String dohvatiKontakt() {
		return "PrivatniKorisnik [ime=" + ime + ", prezime=" + prezime + ", Email=" + getEmail() + ", Telefon="
				+ getTelefon() + "]";
	}
}
