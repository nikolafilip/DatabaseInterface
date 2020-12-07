package hr.java.vjezbe.entitet;

/**
 * Entitet koji opisuje korisnika te sadrzi podatke u e-mailu i broju telefona.
 * 
 * @author Nikola Filip
 * @version 1.0
 */
public abstract class Korisnik extends Entitet {
	private String email, telefon;

	/**
	 * Konstruktor koji inicijalizira podatke o e-mail adresi i telefonskom broju
	 * korisnika
	 * 
	 * @param email   e-Mail adresa korisnika
	 * @param telefon Telefonski broj korisnika
	 */
	public Korisnik(Long id, String email, String telefon) {
		super(id);
		this.email = email;
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public abstract String dohvatiKontakt();

}
