package hr.java.vjezbe;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class UnosPrivatnihKorisnikaController extends PocetnaStranicaController {

	@FXML
	private TextField imeTF;

	@FXML
	private TextField prezimeTF;

	@FXML
	private TextField emailTF;

	@FXML
	private TextField telefonTF;

	public void zapisPrivatnogKorisnika() throws Exception {

		String ime = imeTF.getText();
		String prezime = prezimeTF.getText();
		String email = emailTF.getText();
		String telefon = telefonTF.getText();

		String alertText = "";
		boolean prikaziAlert = false;

		if (ime.isEmpty() || ime == null) {
			alertText += "Ime je obavezan podatak!\n";
			prikaziAlert = true;
		}
		if (prezime.isEmpty() || prezime == null) {
			alertText += "Prezime je obavezan podatak!\n";
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
			PrivatniKorisnik korisnik = new PrivatniKorisnik(null, email, telefon, ime, prezime);
			BazaPodataka.pohraniNovogPrivatnogKorisnika(korisnik);

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("Podaci uspjesno uneseni!");
			alert.showAndWait();
		}
	}
}
