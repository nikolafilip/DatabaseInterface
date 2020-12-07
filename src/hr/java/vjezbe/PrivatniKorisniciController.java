package hr.java.vjezbe;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Kontroler za PrivatniKorisnici.fxml - ekran za pretragu privatnih korisnika.
 * 
 * @author Nikola Filip
 *
 */
public class PrivatniKorisniciController extends PocetnaStranicaController {
	@FXML
	private TextField imeTF;

	@FXML
	private TextField prezimeTF;

	@FXML
	private TextField emailTF;

	@FXML
	private TextField telefonTF;

	@FXML
	private TableView<PrivatniKorisnik> tablicaPrivatnihKorisnika;

	@FXML
	private TableColumn<PrivatniKorisnik, String> ime;

	@FXML
	private TableColumn<PrivatniKorisnik, String> prezime;

	@FXML
	private TableColumn<PrivatniKorisnik, String> email;

	@FXML
	private TableColumn<PrivatniKorisnik, String> telefon;

	/**
	 * Metoda za inicijalizaciju u kojoj se inicijalizira tablica.
	 * 
	 * @throws Exception
	 */
	@FXML
	public void initialize() throws Exception {
		ime.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("ime"));
		prezime.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("prezime"));
		email.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("email"));
		telefon.setCellValueFactory(new PropertyValueFactory<PrivatniKorisnik, String>("telefon"));

		List<PrivatniKorisnik> korisnici = new ArrayList<>();
		try (Connection connection = BazaPodataka.spajanjeNaBazu()) {
			Statement query = connection.createStatement();
			ResultSet resultSetKorisnici = query.executeQuery(
					"select distinct korisnik.id, ime, prezime, email, telefon from korisnik inner join tipKorisnika "
							+ "on tipKorisnika.id = korisnik.idTipKorisnika where tipKorisnika.naziv = 'PrivatniKorisnik'");

			while (resultSetKorisnici.next()) {
				Long id = resultSetKorisnici.getLong("id");
				String Ime = resultSetKorisnici.getString("ime");
				String Prezime = resultSetKorisnici.getString("prezime");
				String Email = resultSetKorisnici.getString("email");
				String Telefon = resultSetKorisnici.getString("telefon");

				PrivatniKorisnik noviKorisnik = new PrivatniKorisnik(id, Email, Telefon, Ime, Prezime);
				korisnici.add(noviKorisnik);
			}

		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			throw new BazaPodatakaException(poruka, e);
		}

		tablicaPrivatnihKorisnika.setItems(FXCollections.observableArrayList(korisnici));
	}

	public static ObservableList<PrivatniKorisnik> listaPrivatnihKorisnika = FXCollections.observableArrayList();

	/**
	 * Metoda za pretragu privatnih korisnika.
	 * 
	 * @throws BazaPodatakaException
	 */
	public void pretraziPrivatneKorisnike() throws BazaPodatakaException {
		String ime = imeTF.getText().toLowerCase();
		String prezime = prezimeTF.getText().toLowerCase();
		String email = emailTF.getText().toLowerCase();
		String telefon = telefonTF.getText();

		List<PrivatniKorisnik> listaKorisnika = BazaPodataka.dohvatiPrivatnogKorisnikaPremaKriterijima(email, telefon,
				ime, prezime);
		tablicaPrivatnihKorisnika.setItems(FXCollections.observableArrayList(listaKorisnika));

	}
}
