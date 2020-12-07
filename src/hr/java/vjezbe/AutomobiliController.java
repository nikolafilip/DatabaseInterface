package hr.java.vjezbe;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Kontroler za Automobili.fxml - ekran za pretragu automobila.
 * 
 * @author Nikola Filip
 *
 */
public class AutomobiliController extends PocetnaStranicaController {
	@FXML
	private TextField opisTF;

	@FXML
	private TextField naslovTF;

	@FXML
	private TextField snagaTF;

	@FXML
	private TextField cijenaTF;

	@FXML
	private TableView<Automobil> tablicaAutomobila;

	@FXML
	private TableColumn<Automobil, String> opis;

	@FXML
	private TableColumn<Automobil, String> naslov;

	@FXML
	private TableColumn<Automobil, Stanje> stanje;

	@FXML
	private TableColumn<Automobil, String> cijena;

	@FXML
	private TableColumn<Automobil, String> snaga;

	/**
	 * Metoda za inicijalizaciju u kojoj se inicijalizira tablica.
	 * 
	 * @throws Exception
	 */
	@FXML
	public void initialize() throws Exception {
		opis.setCellValueFactory(new PropertyValueFactory<Automobil, String>("opis"));
		naslov.setCellValueFactory(new PropertyValueFactory<Automobil, String>("naslov"));
		cijena.setCellValueFactory(new PropertyValueFactory<Automobil, String>("cijena".toString()));
		snaga.setCellValueFactory(new PropertyValueFactory<Automobil, String>("snagaKs".toString()));
		stanje.setCellValueFactory(new PropertyValueFactory<Automobil, Stanje>("stanje"));

		List<Automobil> listaAuta = new ArrayList<>();
		try (Connection connection = BazaPodataka.spajanjeNaBazu()) {
			Statement query = connection.createStatement();
			ResultSet resultSetAutomobili = query
					.executeQuery("SELECT distinct artikl.id, naslov, opis, cijena, snaga, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje inner join tipArtikla on "
							+ "tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Automobil'");

			while (resultSetAutomobili.next()) {
				Long id = resultSetAutomobili.getLong("id");
				String naslov = resultSetAutomobili.getString("naslov");
				String opis = resultSetAutomobili.getString("opis");
				BigDecimal cijena = resultSetAutomobili.getBigDecimal("cijena");
				BigDecimal snagaKs = resultSetAutomobili.getBigDecimal("snaga");
				String stanje = resultSetAutomobili.getString("naziv");

				Automobil newAuto = new Automobil(id, opis, naslov, cijena, Stanje.valueOf(stanje), snagaKs);
				listaAuta.add(newAuto);
			}

		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			throw new BazaPodatakaException(poruka, e);
		}

		tablicaAutomobila.setItems(FXCollections.observableArrayList(listaAuta));

	}

	public static ObservableList<Automobil> listaAuta = FXCollections.observableArrayList();

	/**
	 * Metoda za pretragu automobila.
	 * 
	 * @throws BazaPodatakaException
	 */
	public void pretraziAute() throws BazaPodatakaException {
		String naslov = naslovTF.getText().toLowerCase();
		String opis = opisTF.getText().toLowerCase();
		String snaga = snagaTF.getText();
		String cijena = cijenaTF.getText();

		List<Automobil> listaAuta = BazaPodataka.dohvatiAutomobilePremaKriterijima(naslov, opis, snaga, cijena);

		tablicaAutomobila.setItems(FXCollections.observableArrayList(listaAuta));

	}

}
