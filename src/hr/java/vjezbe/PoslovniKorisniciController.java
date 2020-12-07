package hr.java.vjezbe;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Kontroler za PoslovniKorisnici.fxml - ekran za pretragu poslovnih korisnika.
 * 
 * @author Nikola Filip
 *
 */
public class PoslovniKorisniciController extends PocetnaStranicaController {

	@FXML
	private TextField nazivTF;

	@FXML
	private TextField webTF;

	@FXML
	private TextField emailTF;

	@FXML
	private TextField telefonTF;

	@FXML
	private TableView<PoslovniKorisnik> tablicaPoslovnihKorisnika;

	@FXML
	private TableColumn<PoslovniKorisnik, String> naziv;

	@FXML
	private TableColumn<PoslovniKorisnik, String> web;

	@FXML
	private TableColumn<PoslovniKorisnik, String> email;

	@FXML
	private TableColumn<PoslovniKorisnik, String> telefon;

	/**
	 * Metoda za inicijalizaciju u kojoj se inicijalizira tablica.
	 * 
	 * @throws Exception
	 */
	@FXML
	public void initialize() throws Exception {
		naziv.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("naziv"));
		web.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("web"));
		email.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("email"));
		telefon.setCellValueFactory(new PropertyValueFactory<PoslovniKorisnik, String>("telefon"));

		List<PoslovniKorisnik> korisnici = new ArrayList<>();
		try (Connection connection = BazaPodataka.spajanjeNaBazu()) {
			Statement query = connection.createStatement();
			ResultSet resultSetKorisnici = query.executeQuery(
					"select distinct korisnik.id, korisnik.naziv, web, email, telefon from korisnik inner join tipKorisnika "
							+ "on tipKorisnika.id = korisnik.idTipKorisnika where tipKorisnika.naziv = 'PoslovniKorisnik'");

			while (resultSetKorisnici.next()) {
				Long id = resultSetKorisnici.getLong("id");
				String Naziv = resultSetKorisnici.getString("naziv");
				String Web = resultSetKorisnici.getString("web");
				String Email = resultSetKorisnici.getString("email");
				String Telefon = resultSetKorisnici.getString("telefon");

				PoslovniKorisnik noviKorisnik = new PoslovniKorisnik(id, Email, Telefon, Naziv, Web);
				korisnici.add(noviKorisnik);
			}

		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			throw new BazaPodatakaException(poruka, e);
		}

		tablicaPoslovnihKorisnika.setItems(FXCollections.observableArrayList(korisnici));
	}

	public static ObservableList<PoslovniKorisnik> listaPoslovnihKorisnika = FXCollections.observableArrayList();

	/**
	 * Metoda za pretragu poslovnih korisnika.
	 * 
	 * @throws BazaPodatakaException
	 */
	public void pretraziPoslovneKorisnike() throws BazaPodatakaException {
		String naziv = nazivTF.getText().toLowerCase();
		String web = webTF.getText().toLowerCase();
		String email = emailTF.getText().toLowerCase();
		String telefon = telefonTF.getText();

		List<PoslovniKorisnik> listaKorisnika = BazaPodataka.dohvatiPoslovnogKorisnikaPremaKriterijima(email, telefon,
				naziv, web);
		tablicaPoslovnihKorisnika.setItems(FXCollections.observableArrayList(listaKorisnika));

	}

}
