package hr.java.vjezbe.entitet;

/**
 * Entitet koji nasljeduje klasu Korisnik i opisuje poslovnog korisnika te
 * sadrzi podatke o nazivu, web adresi, e-Mail adresi i telefonskom broju tvrtke
 * 
 * @author Nikola Filip
 * @version 1.0
 */
public class PoslovniKorisnik extends Korisnik {
	public String naziv, web;

	/**
	 * Konstruktor koji inicijalizira vrijednosti e-Mail adrese, telefonskog broja,
	 * naziva i web adrese tvrtke
	 * 
	 * @param email   e-Mail adresa tvrtke
	 * @param telefon Telefonski broj tvrtke
	 * @param naziv   Naziv tvrtke
	 * @param web     Web adresa tvrtke
	 */
	public PoslovniKorisnik(Long id, String email, String telefon, String naziv, String web) {
		super(id, email, telefon);
		this.naziv = naziv;
		this.web = web;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	@Override
	public String dohvatiKontakt() {
		return "PoslovniKorisnik [naziv=" + naziv + ", web=" + web + ", getEmail()=" + getEmail() + ", getTelefon()="
				+ getTelefon() + "]";
	}

}
