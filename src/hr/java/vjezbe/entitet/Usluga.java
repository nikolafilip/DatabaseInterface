package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Entitet koji opisuje uslugu, nasljeduje klasu Artikl te sadrzi podatke o
 * opisu, nazivu i cijeni usluge
 * 
 * @author Nikola Filip
 *
 */
public class Usluga extends Artikl {

	/**
	 * Konstruktor koji inicijalizira vrijednosti naziva, opisa i cijene stana
	 * 
	 * @param opis   Opis stana
	 * @param naslov Naziv stana
	 * @param cijena Cijena stana
	 */
	public Usluga(Long id, String opis, String naslov, BigDecimal cijena, Stanje stanje) {
		super(id, opis, naslov, cijena, stanje);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String tekstOglasa() {
		return "Naslov oglasa: " + super.getNaslov() + "\nOpis usluge: " + super.getOpis() + "\nCijena usluge: "
				+ super.getCijena();

	}

}
