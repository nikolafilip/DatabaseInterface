package hr.java.vjezbe;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class UnosPoslovnihKorisnikaController extends PocetnaStranicaController {

	@FXML
	private TextField nazivTF;

	@FXML
	private TextField webTF;

	@FXML
	private TextField emailTF;

	@FXML
	private TextField telefonTF;

	public void zapisPoslovnogKorisnika() throws Exception {

		String naziv = nazivTF.getText();
		String web = webTF.getText();
		String email = emailTF.getText();
		String telefon = telefonTF.getText();

		String alertText = "";
		boolean prikaziAlert = false;

		if (naziv.isEmpty() || naziv == null) {
			alertText += "Naziv je obavezan podatak!\n";
			prikaziAlert = true;
		}
		if (web.isEmpty() || web == null) {
			alertText += "Web adresa je obavezan podatak!\n";
			prikaziAlert = true;
		}
		if (email.isEmpty() || email == null) {
			alertText += "E-mail je obavezan podatak!\n";
			prikaziAlert = true;
		}
		if (telefon.isEmpty() || telefon == null) {
			alertText += "Broj telefona je obavezan podatak!\n";
			prikaziAlert = true;
		}

		if (prikaziAlert == true) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText(alertText);
			alert.showAndWait();
		}

		else {
			PoslovniKorisnik korisnik = new PoslovniKorisnik(null, email, telefon, naziv, web);
			BazaPodataka.pohraniNovogPoslovnogKorisnika(korisnik);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("Podaci uspjesno uneseni!");
			alert.showAndWait();
		}
	}
}
