package hr.java.vjezbe;

import java.math.BigDecimal;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class UnosStanovaController extends PocetnaStranicaController {

	@FXML
	private TextField opisTF;

	@FXML
	private TextField naslovTF;

	@FXML
	private TextField kvadraturaTF;

	@FXML
	private TextField cijenaTF;

	@FXML
	ChoiceBox<Stanje> cbxStanje = new ChoiceBox<>();

	public void initialize() {
		cbxStanje.setItems(FXCollections.observableArrayList(Stanje.values()));
	}

	public void zapisStana() throws Exception {

		String naslov = naslovTF.getText();
		String opis = opisTF.getText();
		String kvadratura = kvadraturaTF.getText();
		String cijena = cijenaTF.getText();
		Stanje stanje = cbxStanje.getValue();

		String alertText = "";
		boolean prikaziAlert = false;

		if (naslov.isEmpty() || naslov == null) {
			alertText += "Naslov je obavezan podatak!\n";
			prikaziAlert = true;
		}
		if (opis.isEmpty() || opis == null) {
			alertText += "Opis je obavezan podatak!\n";
			prikaziAlert = true;
		}
		if (kvadratura.isEmpty() || kvadratura == null) {
			alertText += "Kvadratura je obavezan podatak!\n";
			prikaziAlert = true;
		}
		if (cijena.isEmpty() || cijena == null) {
			alertText += "Cijena je obavezan podatak!\n";
			prikaziAlert = true;
		}
		if (stanje == null) {
			alertText += "Stanje je obavezan podatak!\n";
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
			Stan unos = new Stan(null, opis, naslov, new BigDecimal(cijena), stanje, Integer.parseInt(kvadratura));
			BazaPodataka.pohraniNoviStan(unos);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText(null);
			alert.setContentText("Podaci uspjesno uneseni!");
			alert.showAndWait();
		}
	}
}
