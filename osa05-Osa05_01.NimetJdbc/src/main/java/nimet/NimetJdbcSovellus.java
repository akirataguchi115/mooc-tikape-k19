package nimet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NimetJdbcSovellus {

    public static void main(String[] args) throws Exception {
        // Toteuta nimien tulostaminen tietokantataulusta Henkilo tänne
        Connection connection = DriverManager.getConnection("jdbc:h2:./henkilotietokanta", "sa", "");
        PreparedStatement stmt = connection.prepareStatement("SELECT nimi FROM Henkilo");
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getString("nimi"));
        }
        
        stmt.close();
        rs.close();
        
        connection.close();
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
            Logger.getLogger(NimetJdbcSovellus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
