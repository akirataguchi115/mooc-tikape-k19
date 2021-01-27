package varausjarjestelma;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VarausjarjestelmaSovellus implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VarausjarjestelmaSovellus.class);
    }

    @Autowired
    Tekstikayttoliittyma tekstikayttoliittyma;

    @Override
    public void run(String... args) throws Exception {
        Scanner lukija = new Scanner(System.in);
        // tarvittaessa alustetaan tietokanta
        alustaTietokanta();
        tekstikayttoliittyma.kaynnista(lukija);
    }

    private static void alustaTietokanta() {
        // mikäli poistat vahingossa tietokannan tai teet siihen muutoksia,
        // voit ajaa tämän metodin jolloin tietokantataulu luodaan uudestaan

        try (Connection conn = DriverManager.getConnection("jdbc:h2:./hotelliketju", "sa", "")) {
            conn.prepareStatement("DROP TABLE Huone, Tilaus, HuoneTilaus, Lisavarusteet IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Huone(id SERIAL, tyyppi VARCHAR(100), numero SMALLINT, paivahinta INTEGER, PRIMARY KEY (id));").executeUpdate();
            conn.prepareStatement("CREATE TABLE Tilaus (id SERIAL, aloituspvm DATE, lopetuspvm DATE, nimi VARCHAR(100), sahkoposti VARCHAR(319), PRIMARY KEY (id));").executeUpdate();
            conn.prepareStatement("CREATE TABLE HuoneTilaus (huone_id INTEGER, tilaus_id INTEGER, FOREIGN KEY(huone_id) REFERENCES Huone(id), FOREIGN KEY(tilaus_id) REFERENCES Tilaus(id));").executeUpdate();
            conn.prepareStatement("CREATE TABLE Lisavarusteet (id SERIAL, tilaus_id INTEGER,varuste VARCHAR(100),PRIMARY KEY (id),FOREIGN KEY (tilaus_id)REFERENCES Tilaus(id));").executeUpdate();
            conn.prepareStatement("INSERT INTO Huone (tyyppi, numero, paivahinta) VALUES ('Excalibur', 623, 120);").executeLargeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(VarausjarjestelmaSovellus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
