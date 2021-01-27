package asiakkaat;

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
public class AsiakasSovellus implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AsiakasSovellus.class);
    }

    @Autowired
    AsiakasDao asiakasDao;

    @Override
    public void run(String... args) throws Exception {
        // voit testata metodejasi täällä
    }

    private static void alustaTietokanta() {
        // mikäli poistat vahingossa tietokannan voit ajaa tämän metodin jolloin 
        // tietokantataulu luodaan uudestaan

        try (Connection conn = DriverManager.getConnection("jdbc:h2:./asiakkaat", "sa", "")) {
            conn.prepareStatement("DROP TABLE Asiakas IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Asiakas (\n"
                    + "    id INTEGER AUTO_INCREMENT PRIMARY KEY,\n"
                    + "    nimi VARCHAR(200),\n"
                    + "    puhelinnumero VARCHAR(20),\n"
                    + "    katuosoite VARCHAR(50),\n"
                    + "    postinumero INTEGER,\n"
                    + "    postitoimipaikka VARCHAR(20)\n"
                    + ");").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AsiakasSovellus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
