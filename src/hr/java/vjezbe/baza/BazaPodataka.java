package hr.java.vjezbe.baza;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.iznimke.BazaPodatakaException;

/**
 * Klasa u kojoj se nalaze operacije nad bazom podataka
 * 
 * @author Nikola
 *
 */
public class BazaPodataka {
	private static final String DATABASE_FILE = "database.properties";

	private static final Logger logger = LoggerFactory.getLogger(BazaPodataka.class);

	/**
	 * Metoda za spajanje na bazu. Podaci za spajanje nalaze se u datoteki
	 * database.properties.
	 * 
	 * @return
	 * @throws BazaPodatakaException
	 * @throws IOException
	 * @throws SQLException
	 */
	public static Connection spajanjeNaBazu() throws BazaPodatakaException, IOException, SQLException {
		Properties svojstva = new Properties();

		svojstva.load(new FileReader(DATABASE_FILE));

		String url = svojstva.getProperty("url");
		String username = svojstva.getProperty("username");
		String password = svojstva.getProperty("password");

		Connection veza = DriverManager.getConnection(url, username, password);

		return veza;
	}

	/**
	 * Metoda za dohvat stanova iz baze podataka prema unesenim parametrima.
	 * 
	 * @param naslov
	 * @param opis
	 * @param kvadratura
	 * @param cijena
	 * @return
	 * @throws BazaPodatakaException
	 */
	public static List<Stan> dohvatiStanovePremaKriterijima(String naslov, String opis, String kvadratura,
			String cijena) throws BazaPodatakaException {
		List<Stan> listaStanova = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id, naslov, opis, cijena, kvadratura, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Stan'");

			if (naslov != null && naslov.isEmpty() == false)
				sqlUpit.append(" AND lower(artikl.naslov) LIKE '%" + naslov + "%'");

			if (opis != null && opis.isEmpty() == false)
				sqlUpit.append(" AND lower(artikl.opis) LIKE '%" + opis + "%'");

			if (cijena != null && cijena.isEmpty() == false)
				sqlUpit.append(" AND artikl.cijena = " + cijena);

			if (kvadratura != null && kvadratura.isEmpty() == false)
				sqlUpit.append(" AND artikl.kvadratura = " + kvadratura);

			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String Naslov = resultSet.getString("naslov");
				String Opis = resultSet.getString("opis");
				BigDecimal Cijena = resultSet.getBigDecimal("cijena");
				Integer Kvadratura = resultSet.getInt("kvadratura");
				String stanje = resultSet.getString("naziv");

				Stan newStan = new Stan(id, Opis, Naslov, Cijena, Stanje.valueOf(stanje), Kvadratura);
				listaStanova.add(newStan);
			}

		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaStanova;
	}

	/**
	 * Metoda za pohranu novog stana u bazu podataka.
	 * 
	 * @param stan
	 * @throws BazaPodatakaException
	 */
	public static void pohraniNoviStan(Stan stan) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Kvadratura, idStanje, idTipArtikla) "
							+ "values (?, ?, ?, ?, ?, 3);");
			preparedStatement.setString(1, stan.getNaslov());
			preparedStatement.setString(2, stan.getOpis());
			preparedStatement.setBigDecimal(3, stan.getCijena());
			preparedStatement.setInt(4, stan.getKvadratura());
			preparedStatement.setLong(5, (stan.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	/**
	 * Metoda za dohvat automobila iz baze podataka prema unesenim parametrima.
	 * 
	 * @param naslov
	 * @param opis
	 * @param snagaKs
	 * @param cijena
	 * @return
	 * @throws BazaPodatakaException
	 */
	public static List<Automobil> dohvatiAutomobilePremaKriterijima(String naslov, String opis, String snagaKs,
			String cijena) throws BazaPodatakaException {
		List<Automobil> listaAutomobila = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"SELECT distinct artikl.id, naslov, opis, cijena, snaga, stanje.naziv "
							+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
							+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Automobil'");

			if (naslov != null && naslov.isEmpty() == false)
				sqlUpit.append(" AND lower(artikl.naslov) LIKE '%" + naslov + "%'");

			if (opis != null && opis.isEmpty() == false)
				sqlUpit.append(" AND lower(artikl.opis) LIKE '%" + opis + "%'");

			if (cijena != null && cijena.isEmpty() == false)
				sqlUpit.append(" AND artikl.cijena = " + cijena);

			if (snagaKs != null && snagaKs.isEmpty() == false)
				sqlUpit.append(" AND artikl.snaga = " + snagaKs);

			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String Naslov = resultSet.getString("naslov");
				String Opis = resultSet.getString("opis");
				BigDecimal Cijena = resultSet.getBigDecimal("cijena");
				BigDecimal SnagaKs = resultSet.getBigDecimal("snaga");
				String stanje = resultSet.getString("naziv");

				Automobil newAutomobil = new Automobil(id, Opis, Naslov, Cijena, Stanje.valueOf(stanje), SnagaKs);
				listaAutomobila.add(newAutomobil);
			}

		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaAutomobila;
	}

	/**
	 * Metoda za pohranu novog automobila u bazu podataka.
	 * 
	 * @param automobil
	 * @throws BazaPodatakaException
	 */
	public static void pohraniNoviAutomobil(Automobil automobil) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
					.prepareStatement("insert into artikl(Naslov, Opis, Cijena, Snaga, idStanje, idTipArtikla) "
							+ "values (?, ?, ?, ?, ?, 1);");
			preparedStatement.setString(1, automobil.getNaslov());
			preparedStatement.setString(2, automobil.getOpis());
			preparedStatement.setBigDecimal(3, automobil.getCijena());
			preparedStatement.setBigDecimal(4, automobil.getSnagaKs());
			preparedStatement.setLong(5, (automobil.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	/**
	 * Metoda za dohvat usluga iz baze podataka prema unesenim parametrima.
	 * 
	 * @param naslov
	 * @param opis
	 * @param cijena
	 * @return
	 * @throws BazaPodatakaException
	 */
	public static List<Usluga> dohvatiUslugePremaKriterijima(String naslov, String opis, String cijena)
			throws BazaPodatakaException {
		List<Usluga> listaUsluga = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT distinct artikl.id, naslov, opis, cijena, stanje.naziv "
					+ "FROM artikl inner join stanje on stanje.id = artikl.idStanje "
					+ "inner join tipArtikla on tipArtikla.id = artikl.idTipArtikla WHERE tipArtikla.naziv = 'Usluga'");

			if (naslov != null && naslov.isEmpty() == false)
				sqlUpit.append(" AND lower(artikl.naslov) LIKE '%" + naslov + "%'");

			if (opis != null && opis.isEmpty() == false)
				sqlUpit.append(" AND lower(artikl.opis) LIKE '%" + opis + "%'");

			if (cijena != null && cijena.isEmpty() == false)
				sqlUpit.append(" AND artikl.cijena = " + cijena);

			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String Naslov = resultSet.getString("naslov");
				String Opis = resultSet.getString("opis");
				BigDecimal Cijena = resultSet.getBigDecimal("cijena");
				String stanje = resultSet.getString("naziv");

				Usluga newUsluga = new Usluga(id, Opis, Naslov, Cijena, Stanje.valueOf(stanje));
				listaUsluga.add(newUsluga);
			}

		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaUsluga;
	}

	/**
	 * Metoda za pohranu nove usluge u bazu podataka.
	 * 
	 * @param usluga
	 * @throws BazaPodatakaException
	 */
	public static void pohraniNovuUslugu(Usluga usluga) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into artikl(Naslov, Opis, Cijena, idStanje, idTipArtikla) " + "values (?, ?, ?, ?, 2);");
			preparedStatement.setString(1, usluga.getNaslov());
			preparedStatement.setString(2, usluga.getOpis());
			preparedStatement.setBigDecimal(3, usluga.getCijena());
			preparedStatement.setLong(4, (usluga.getStanje().ordinal() + 1));
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	/**
	 * Metoda za dohvat privatnog korisnika iz baze podataka prema unesenim
	 * parametrima.
	 * 
	 * @param email
	 * @param telefon
	 * @param ime
	 * @param prezime
	 * @return
	 * @throws BazaPodatakaException
	 */
	public static List<PrivatniKorisnik> dohvatiPrivatnogKorisnikaPremaKriterijima(String email, String telefon,
			String ime, String prezime) throws BazaPodatakaException {
		List<PrivatniKorisnik> listaKorisnika = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"select distinct korisnik.id, ime, prezime, email, telefon from korisnik inner join tipKorisnika "
							+ "on tipKorisnika.id = korisnik.idTipKorisnika where tipKorisnika.naziv = 'PrivatniKorisnik'");

			if (ime != null && ime.isEmpty() == false)
				sqlUpit.append(" AND lower(korisnik.ime) LIKE '%" + ime + "%'");

			if (prezime != null && prezime.isEmpty() == false)
				sqlUpit.append(" AND lower(korisnik.prezime) LIKE '%" + prezime + "%'");

			if (email != null && email.isEmpty() == false)
				sqlUpit.append(" AND lower(korisnik.email) LIKE '%" + email + "%'");

			if (telefon != null && telefon.isEmpty() == false)
				sqlUpit.append(" AND lower(korisnik.telefon) LIKE '%" + telefon + "%'");

			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String Ime = resultSet.getString("ime");
				String Prezime = resultSet.getString("prezime");
				String Email = resultSet.getString("email");
				String Telefon = resultSet.getString("telefon");

				PrivatniKorisnik noviKorisnik = new PrivatniKorisnik(id, Email, Telefon, Ime, Prezime);
				listaKorisnika.add(noviKorisnik);
			}

		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaKorisnika;
	}

	/**
	 * Metoda za pohranu novog privatnog korisnika u bazu podataka.
	 * 
	 * @param korisnik
	 * @throws BazaPodatakaException
	 */
	public static void pohraniNovogPrivatnogKorisnika(PrivatniKorisnik korisnik) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into korisnik(Ime, Prezime, Email, Telefon, idTipKorisnika) " + "values (?, ?, ?, ?, 1);");
			preparedStatement.setString(1, korisnik.getIme());
			preparedStatement.setString(2, korisnik.getPrezime());
			preparedStatement.setString(3, korisnik.getEmail());
			preparedStatement.setString(4, korisnik.getTelefon());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

	/**
	 * Metoda za dohvat poslovnih korisnika iz baze podataka prema unesenim
	 * parametrima.
	 * 
	 * @param email
	 * @param telefon
	 * @param naziv
	 * @param web
	 * @return
	 * @throws BazaPodatakaException
	 */
	public static List<PoslovniKorisnik> dohvatiPoslovnogKorisnikaPremaKriterijima(String email, String telefon,
			String naziv, String web) throws BazaPodatakaException {
		List<PoslovniKorisnik> listaKorisnika = new ArrayList<>();
		try (Connection connection = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder(
					"select distinct korisnik.id, korisnik.naziv, web, email, telefon from korisnik inner join tipKorisnika "
							+ "on tipKorisnika.id = korisnik.idTipKorisnika where tipKorisnika.naziv = 'PoslovniKorisnik'");

			if (naziv != null && naziv.isEmpty() == false)
				sqlUpit.append(" AND lower(korisnik.naziv) LIKE '%" + naziv + "%'");

			if (web != null && web.isEmpty() == false)
				sqlUpit.append(" AND lower(korisnik.web) LIKE '%" + web + "%'");

			if (email != null && email.isEmpty() == false)
				sqlUpit.append(" AND lower(korisnik.email) LIKE '%" + email + "%'");

			if (telefon != null && telefon.isEmpty() == false)
				sqlUpit.append(" AND lower(korisnik.telefon) LIKE '%" + telefon + "%'");

			Statement query = connection.createStatement();
			ResultSet resultSet = query.executeQuery(sqlUpit.toString());

			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String Naziv = resultSet.getString("naziv");
				String Web = resultSet.getString("web");
				String Email = resultSet.getString("email");
				String Telefon = resultSet.getString("telefon");

				PoslovniKorisnik noviKorisnik = new PoslovniKorisnik(id, Email, Telefon, Naziv, Web);
				listaKorisnika.add(noviKorisnik);
			}

		} catch (SQLException | IOException e) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, e);
			throw new BazaPodatakaException(poruka, e);
		}
		return listaKorisnika;
	}

	/**
	 * Metoda za pohranu novog poslovnog korisnika u bazu podataka.
	 * 
	 * @param korisnik
	 * @throws BazaPodatakaException
	 */
	public static void pohraniNovogPoslovnogKorisnika(PoslovniKorisnik korisnik) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement(
					"insert into korisnik(Naziv, Web, Email, Telefon, idTipKorisnika) " + "values (?, ?, ?, ?, 2);");
			preparedStatement.setString(1, korisnik.getNaziv());
			preparedStatement.setString(2, korisnik.getWeb());
			preparedStatement.setString(3, korisnik.getEmail());
			preparedStatement.setString(4, korisnik.getTelefon());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}
	}

}
