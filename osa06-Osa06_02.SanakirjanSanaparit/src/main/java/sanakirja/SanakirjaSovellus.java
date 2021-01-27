package sanakirja;

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
public class SanakirjaSovellus implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SanakirjaSovellus.class);
    }

    @Autowired
    Tekstikayttoliittyma tekstikayttoliittyma;

    @Override
    public void run(String... args) throws Exception {
        Scanner lukija = new Scanner(System.in);
        tekstikayttoliittyma.kaynnista(lukija);
    }

    private static void alustaTietokanta() {
        // mikäli poistat vahingossa tietokannan voit ajaa tämän metodin jolloin 
        // tietokantataulu luodaan uudestaan

        try (Connection conn = DriverManager.getConnection("jdbc:h2:./sanakirja", "sa", "")) {
            conn.prepareStatement("DROP TABLE Sanasto IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Sanasto(id serial, sana varchar(255), kaannos varchar(255));").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanakirjaSovellus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
