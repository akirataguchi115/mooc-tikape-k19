package lisays;

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
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class LisaysSpringSovellus implements CommandLineRunner {

    Scanner lukija = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(LisaysSpringSovellus.class);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // Toteuta uuden henkilön lisääminen tietokantatauluun Henkilo tänne
        System.out.println("Minkä niminen henkilö lisätään?");
        String nimi = lukija.nextLine();
        jdbcTemplate.update("INSERT INTO Henkilo (nimi) VALUES (?);", nimi);
    }

    private static void alustaTietokantaJaLuoNimia() {
        // mikäli poistat vahingossa tietokannan tai teet siihen muutoksia,
        // voit ajaa tämän metodin jolloin tietokantataulu luodaan uudestaan
        // ja siihen lisätään nimiä

        // palvelu http://listofrandomnames.com on tällaisissa varsin näppärä
        String nimet = "('Love'),"
                + "('Bruce'),"
                + "('Ray'),"
                + "('Fidelia'),"
                + "('Celia'),"
                + "('Mimi'),"
                + "('Monique'),"
                + "('Kristan'),"
                + "('Dane'),"
                + "('Anneliese'),"
                + "('Clemencia'),"
                + "('Jen'),"
                + "('Karen'),"
                + "('Tai'),"
                + "('Jesica'),"
                + "('Marya'),"
                + "('Kiesha'),"
                + "('Elidia'),"
                + "('Takisha'),"
                + "('Cleo')";
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./henkilotietokanta", "sa", "")) {
            conn.prepareStatement("DROP TABLE Henkilo IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Henkilo(id serial, nimi varchar(255));").executeUpdate();
            conn.prepareStatement("INSERT INTO Henkilo (nimi) VALUES " + nimet + ";").executeLargeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LisaysSpringSovellus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
