package huonekalut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class HuonekaluSovellus implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HuonekaluSovellus.class);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        // voit testata metodejasi täällä
        Scanner lukija = new Scanner(System.in);
        System.out.println("Tervetuloa!");

        while (true) {
            System.out.println("0 - lopetetaan");
            System.out.println("1 - lisää huonekalu");
            System.out.println("2 - hae huonekalu");
            System.out.println("3 - listaa huonekalut");

            System.out.println("Mitä tehdään?");

            int komento = Integer.valueOf(lukija.nextLine());

            if (komento == 0) {
                break;
            }

            if (komento == 1) {
                System.out.println("Minkä niminen huonekalu lisätään?");
                String nimi = lukija.nextLine();
                jdbcTemplate.update("INSERT INTO Huonekalu (nimi) VALUES ('" + nimi + "');");
            } else if (komento == 2) {
                System.out.println("Millä avaimella etsitään?");
                int id = Integer.valueOf(lukija.nextLine());
                List<String> tulos = jdbcTemplate.query("SELECT * FROM Huonekalu WHERE id = ?",
                        (rs, rowNum) -> rs.getString("nimi"),
                        id);

                if (tulos.isEmpty()) {
                    System.out.print("Avaimella " + id + " ei löytynyt huonekaluja.");
                } else {
                    System.out.println("Löytyi huonekalu: " + tulos.get(0));
                }
            } else if (komento == 3) {
                System.out.println("Listataan huonekalut:");
                jdbcTemplate.query("SELECT * FROM Huonekalu",
                        (rs, rowNum) -> "id: " + rs.getInt("id") + ", nimi: " + rs.getString("nimi"))
                        .forEach(System.out::println);
            }

        }
    }

    private static void alustaTietokanta() {
        // mikäli poistat "vahingossa" tietokannan voit ajaa tämän metodin jolloin 
        // tietokantataulu luodaan uudestaan

        try (Connection conn = DriverManager.getConnection("jdbc:h2:./huonekalut", "sa", "")) {
            conn.prepareStatement("DROP TABLE Huonekalu IF EXISTS;").executeUpdate();
            conn.prepareStatement("CREATE TABLE Huonekalu (\n"
                    + "    id INTEGER AUTO_INCREMENT PRIMARY KEY,\n"
                    + "    nimi VARCHAR(200)\n"
                    + ");").executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HuonekaluSovellus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
