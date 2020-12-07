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
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Kontroler za Usluge.fxml - ekran za pretragu usluga.
 * 
 * @author Nikola Filip
 *
 */
public class UslugeController extends PocetnaStranicaController {
	@FXML
	private TextField opisTF;

	@FXML
	private TextField naslovTF;

	@FXML
	private TextField cijenaTF;

	@FXML
	private TableView<Usluga> tablicaUsluga;

	@FXML
	private TableColumn<Stan, String> opis;

	@FXML
	private TableColumn<Stan, String> naslov;

	@FXML
	private TableColumn<Stan, Stanje> stanje;

	@FXML
	private TableColumn<Stan, String> cijena;

	/**
	 * Metoda za inicijalizaciju u kojoj se inicijalizira tablica.
	 * 
	 * @throws Exception
	 */
	@FXML
	public void initialize() throws Exception {
		opis.setCellValueFactory(new PropertyValueFactory<Stan, String>("opis"));
		naslov.setCellValueFactory(new PropertyValueFactory<Stan, String>("naslov"));
		cijena.setCellValueFactory(new PropertyValueFactory<Stan, String>("cijena".toString()));
		stanje.setCellValueFactory(new PropertyValueFactory<Stan, Stanje>("stanje"));

		List<Usluga> listaUsluga = new ArrayList<>();
		try (Connection connection = BazaPodataka.spajanjeNaBazu()) {
			Statement query = connection.createStatement();
			ResultSet resultSetUsluge = query
					.executeQuery("SELECT distinct artikl.id, naslov, opis, cijena, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Usluga'");

			while (resultSetUsluge.next()) {
				Long id = resultSetUsluge.getLong("id");
				String naslov = resultSetUsluge.getString("naslov");
				String opis = resultSetUsluge.getString("opis");
				BigDecimal cijena = resultSetUsluge.getBigDecimal("cijena");
				String stanje = resultSetUsluge.getString("naziv");

				Usluga newUsluga = new Usluga(id, opis, naslov, cijena, Stanje.valueOf(stanje));
				listaUsluga.add(newUsluga);
			}

		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			throw new BazaPodatakaException(poruka, e);
		}

		tablicaUsluga.setItems(FXCollections.observableArrayList(listaUsluga));
	}

	public static ObservableList<Usluga> listaUsluga = FXCollections.observableArrayList();

	/**
	 * Metoda za pretragu usluga.
	 * 
	 * @throws BazaPodatakaException
	 */
	public void pretraziUsluge() throws BazaPodatakaException {
		String naslov = naslovTF.getText().toLowerCase();
		String opis = opisTF.getText().toLowerCase();
		String cijena = cijenaTF.getText();

		List<Usluga> listaUsluga = BazaPodataka.dohvatiUslugePremaKriterijima(naslov, opis, cijena);

		tablicaUsluga.setItems(FXCollections.observableArrayList(listaUsluga));

	}

}
