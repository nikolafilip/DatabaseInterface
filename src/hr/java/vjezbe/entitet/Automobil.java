package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiGrupuOsiguranjaException;

/**
 * Entitet koji opisuje automobil, nasljeduje klasu Artikl i implementira
 * sucelje Vozilo, te sadrzi naziv, opis, cijenu i snagu
 * 
 * @author Nikola Filip
 * @version 1.0
 */

public class Automobil extends Artikl implements Vozilo {

	private static final Logger logger = LoggerFactory.getLogger(Automobil.class);

	public BigDecimal snagaKs;
//	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	/**
	 * Konstruktor koji inicijalizira podatke o opisu, naslovu, cijeni i snagi
	 * automobila.
	 * 
	 * @param opis    Opis automobila
	 * @param naslov  Naziv automobila
	 * @param cijena  Cijena automobila
	 * @param snagaKs Snaga automobila (Konjske snage)
	 */
	public Automobil(Long id, String opis, String naslov, BigDecimal cijena, Stanje stanje, BigDecimal snagaKs) {
		super(id, opis, naslov, cijena, stanje);
		this.snagaKs = snagaKs;
	}

	public BigDecimal getSnagaKs() {
		return snagaKs;
	}

	public void setSnagaKs(BigDecimal snagaKs) {
		this.snagaKs = snagaKs;
	}

	@Override
	public BigDecimal izracunajGrupuOsiguranja() {
		if (this.snagaKs.compareTo(new BigDecimal("50")) < 0)
			return new BigDecimal("1");
		else if (this.snagaKs.compareTo(new BigDecimal("50")) >= 0 && this.snagaKs.compareTo(new BigDecimal("70")) < 0)
			return new BigDecimal("2");
		else if (this.snagaKs.compareTo(new BigDecimal("70")) >= 0 && this.snagaKs.compareTo(new BigDecimal("90")) < 0)
			return new BigDecimal("3");
		else if (this.snagaKs.compareTo(new BigDecimal("90")) >= 0 && this.snagaKs.compareTo(new BigDecimal("110")) < 0)
			return new BigDecimal("4");
		else if (this.snagaKs.compareTo(new BigDecimal("110")) >= 0
				&& this.snagaKs.compareTo(new BigDecimal("400")) < 0)
			return new BigDecimal("4");
		else
			return new BigDecimal("6");

	}

	public String tekstOglasa() {
		BigDecimal cijenaOsiguranja = null;
		try {
			cijenaOsiguranja = this.izracunajCijenuOsiguranja();
			return "Naslov automobila: " + super.getNaslov() + "\nOpis automobila: " + super.getOpis()
					+ "\nSnaga automobila: " + this.snagaKs + "\nStanje automobila: " + this.getStanje()
					+ "\nIzracun osiguranja automobila: " + cijenaOsiguranja + "\nCijena automobila: "
					+ super.getCijena();
		} catch (NemoguceOdreditiGrupuOsiguranjaException e) {
			logger.info("Pogreska prilikom odredivanja cijene osiguranja!", e);
			return "Naslov automobila: " + super.getNaslov() + "\nOpis automobila: " + super.getOpis()
					+ "\nSnaga automobila: " + this.snagaKs + "\nStanje automobila: " + this.getStanje()
					+ "\nIzracun osiguranja automobila: Previse kw, ne mogu odrediti grupu osiguranja.\nCijena automobila: "
					+ super.getCijena();
		}
	}

}
