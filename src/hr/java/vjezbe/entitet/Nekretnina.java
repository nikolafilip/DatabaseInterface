package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * Sucelje koje opisuje nekretninu i sadrzi metodu za racunanje poreza ovisno o
 * cijeni.
 * 
 * @author Nikola Filip
 * @version 1.0
 */
public interface Nekretnina {
	/**
	 * Metoda koja racuna porez ovisno o cijeni. Kao parametar prima cijenu, te
	 * ukoliko je cijena manja od 10000kn baca exception CijenaJePreniskaException,
	 * a ukoliko nije vraca kolicinu poreza.
	 * 
	 * @param cijena Cijena nekretnine
	 * @return Kolicina poreza
	 * @throws CijenaJePreniskaException Kada je cijena manja od 10000
	 */
	public default BigDecimal izracunajPorez(BigDecimal cijena) throws CijenaJePreniskaException {
		if (cijena.compareTo(new BigDecimal("10000")) == -1)
			throw new CijenaJePreniskaException("Cijena ne smije biti manja od 10000kn");
		else
			return cijena.multiply(new BigDecimal("0.03"));
	}
}
