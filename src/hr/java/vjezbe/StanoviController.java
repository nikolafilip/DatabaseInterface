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
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Kontroler za Stanovi.fxml - ekran za pretragu stanova.
 * 
 * @author Nikola Filip
 *
 */
public class StanoviController extends PocetnaStranicaController {

	@FXML
	private TextField opisTF;

	@FXML
	private TextField naslovTF;

	@FXML
	private TextField kvadraturaTF;

	@FXML
	private TextField cijenaTF;

	@FXML
	private TableView<Stan> tablicaStanova;

	@FXML
	private TableColumn<Stan, String> opis;

	@FXML
	private TableColumn<Stan, String> naslov;

	@FXML
	private TableColumn<Stan, Stanje> stanje;

	@FXML
	private TableColumn<Stan, String> cijena;

	@FXML
	private TableColumn<Stan, String> kvadratura;

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
		kvadratura.setCellValueFactory(new PropertyValueFactory<Stan, String>("kvadratura".toString()));
		stanje.setCellValueFactory(new PropertyValueFactory<Stan, Stanje>("stanje"));

		List<Stan> listaStanova = new ArrayList<>();
		try (Connection connection = BazaPodataka.spajanjeNaBazu()) {
			Statement query = connection.createStatement();
			ResultSet resultSetStanovi = query
					.executeQuery("SELECT distinct artikl.id, naslov, opis, cijena, kvadratura, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje inner join tipArtikla "
							+ "on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Stan'");

			while (resultSetStanovi.next()) {
				Long id = resultSetStanovi.getLong("id");
				String naslov = resultSetStanovi.getString("naslov");
				String opis = resultSetStanovi.getString("opis");
				BigDecimal cijena = resultSetStanovi.getBigDecimal("cijena");
				Integer kvadratura = resultSetStanovi.getInt("kvadratura");
				String stanje = resultSetStanovi.getString("naziv");

				Stan newStan = new Stan(id, opis, naslov, cijena, Stanje.valueOf(stanje), kvadratura);
				listaStanova.add(newStan);
			}

		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			throw new BazaPodatakaException(poruka, e);
		}

		tablicaStanova.setItems(FXCollections.observableArrayList(listaStanova));
	}

	public static ObservableList<Stan> listaStanova2 = FXCollections.observableArrayList();

	/**
	 * Metoda za pretragu stanova.
	 * 
	 * @throws BazaPodatakaException
	 */
	public void pretraziStanove() throws BazaPodatakaException {
		String naslov = naslovTF.getText().toLowerCase();
		String opis = opisTF.getText().toLowerCase();
		String kvadratura = kvadraturaTF.getText();
		String cijena = cijenaTF.getText();

		List<Stan> stanovi = BazaPodataka.dohvatiStanovePremaKriterijima(naslov, opis, kvadratura, cijena);
		tablicaStanova.setItems(FXCollections.observableArrayList(stanovi));

	}
}
