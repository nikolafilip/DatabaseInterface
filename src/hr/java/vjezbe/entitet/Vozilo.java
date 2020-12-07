package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * Sucelje koje predstavlja vozilo te sadrzi metode koje: 1. racuna snagu vozila
 * u KW (Kilowatt) 2. racuna grupu osiguranja 3. racuna cijenu osiguranja
 * 
 * @author Nikola Filip
 *
 */
public interface Vozilo {
	/**
	 * Metoda koja racuna snagu vozila u KW a kao parametar prima snagu u HP
	 * (konjske snage)
	 * 
	 * @param hp Vrijednost snage vozila u konjskim snagama
	 * @return Vrijednost snage vozila u kilowattima
	 */
	public default BigDecimal izracunajKw(BigDecimal hp) {
		BigDecimal divisor = new BigDecimal("0.746");
		return hp.divide(divisor);
	}

	/**
	 * Metoda koja racuna grupu osiguranja. Ukoliko je nemoguce odrediti grupu
	 * osiguranja (snaga vozila je prevelika), metoda baca
	 * NemoguceOdreditiGrupuOsiguranjaException iznimku.
	 * 
	 * @return Grupa osiguranja
	 * @throws NemoguceOdreditiGrupuOsiguranjaException Iznimka koja se javlja
	 *                                                  ukoliko je snaga vozila veca
	 *                                                  od određenog broja
	 */
	public BigDecimal izracunajGrupuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException;

	/**
	 * Metoda koja racuna cijenu osiguranja ovisno o grupi osiguranja
	 * 
	 * @return Cijena osiguranja
	 * @throws NemoguceOdreditiGrupuOsiguranjaException Iznimka koja se javlja
	 *                                                  ukoliko je snaga vozila veca
	 *                                                  od određenog broja
	 */
	public default BigDecimal izracunajCijenuOsiguranja() throws NemoguceOdreditiGrupuOsiguranjaException {
		switch (izracunajGrupuOsiguranja().intValue()) {
		case 1:
			return new BigDecimal("1000");

		case 2:
			return new BigDecimal("2000");

		case 3:
			return new BigDecimal("3000");

		case 4:
			return new BigDecimal("4000");

		case 5:
			return new BigDecimal("5000");

		case 6:
			throw new NemoguceOdreditiGrupuOsiguranjaException("Previse kw, ne mogu odrediti grupu osiguranja.");

		default:
			return new BigDecimal("0");
		}
	}
}
