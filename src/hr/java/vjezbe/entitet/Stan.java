package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.CijenaJePreniskaException;

/**
 * Entitet koji opisuje stan - nasljeduje klasu Artikl i implementira sucelje
 * Nekretnina te sadrzi podatke o nazivu, opisu, cijeni i kvadraturi stana
 * 
 * @author Nikola Filip
 * @version 1.0
 */
public class Stan extends Artikl implements Nekretnina {
	private static final Logger logger = LoggerFactory.getLogger(Stan.class);
	private Integer kvadratura;

	/**
	 * Konstruktor koji inicijalizira vrijednosti naziva, opisa, cijene i kvadrature
	 * stana.
	 * 
	 * @param opis       Opis stana
	 * @param naslov     Naziv stana
	 * @param cijena     Cijena stana
	 * @param kvadratura Kvadratura stana
	 */

	public Stan(Long id, String opis, String naslov, BigDecimal cijena, Stanje stanje, int kvadratura) {
		super(id, opis, naslov, cijena, stanje);
		this.kvadratura = kvadratura;
	}

	public Integer getKvadratura() {
		return kvadratura;
	}

	public void setKvadratura(Integer kvadratura) {
		this.kvadratura = kvadratura;
	}

	@Override
	public String tekstOglasa() {
		try {
			this.izracunajPorez(getCijena());
			return "Naslov nekretnine: " + super.getNaslov() + "\nOpis nekretnine: " + super.getOpis()
					+ "\nKvadratura nekretnine: " + this.getKvadratura() + "\nStanje nekretnine: " + this.getStanje()
					+ "\nPorez na nekretnine: " + this.izracunajPorez(getCijena()) + "\nCijena nekretnine: "
					+ super.getCijena();

		} catch (CijenaJePreniskaException e) {
			logger.info("Pogreska prilikom određivanja iznosa poreza!", e);
			return "Naslov nekretnine: " + super.getNaslov() + "\nOpis nekretnine: " + super.getOpis()
					+ "\nKvadratura nekretnine: " + this.getKvadratura() + "\nStanje nekretnine: " + this.getStanje()
					+ "\nPorez na nekretnine: Cijena ne smije biti manja od 10000kn" + "\nCijena nekretnine: "
					+ super.getCijena();

		}

	}

}
