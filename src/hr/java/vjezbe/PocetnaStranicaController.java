package hr.java.vjezbe;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

/**
 * Kontroler za PocetnaStranica.fxml - pocetna stranica koja se pojavljuje kada
 * se upali program.
 * 
 * @author Nikola Filip
 *
 */
public class PocetnaStranicaController {

	/**
	 * Metoda za prikaz ekrana za pretragu automobila.
	 */
	public void prikaziPretraguAutomobila() {

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Automobili.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda za prikaz ekrana za pretragu stanova.
	 */
	public void prikaziPretraguStanova() {

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Stanovi.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda za prikaz ekrana za pretragu usluga.
	 */
	public void prikaziPretraguUsluga() {

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Usluge.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda za prikaz ekrana za pretragu privatnih korisnika.
	 */
	public void prikaziPretraguPrivatnihKorisnika() {

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("PrivatniKorisnici.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda za prikaz ekrana za pretragu poslovnih korisnika.
	 */
	public void prikaziPretraguPoslovnihKorisnika() {

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("PoslovniKorisnici.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda za prikaz ekrana za unos automobila.
	 */
	public void prikaziUnosAutomobila() {

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosAutomobila.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda za prikaz ekrana za unos usluga.
	 */
	public void prikaziUnosUsluga() {

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosUsluge.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda za prikaz ekrana za unos stanova.
	 */
	public void prikaziUnosStanova() {

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosStanova.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda za prikaz ekrana za unos privatnih korisnika.
	 */
	public void prikaziUnosPrivatnihKorisnika() {

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosPrivatnihKorisnika.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metoda za prikaz ekrana za unos poslovnih korisnika.
	 */
	public void prikaziUnosPoslovnihKorisnika() {

		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("UnosPoslovnihKorisnika.fxml"));
			Main.setMainPage(root);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
