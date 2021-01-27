package sanakirja;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sanakirja {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void lisaa(String sana, String kaannos) {
        jdbcTemplate.update("INSERT INTO Sanasto (sana, kaannos) VALUES (?,?)", sana, kaannos);
    }

    public String kaanna(String sana) {
        String kaannos = jdbcTemplate.query(
                "SELECT * FROM Sanasto WHERE sana = ?",
                (rs, rowNum) -> rs.getString("kaannos"), sana).get(0);
        if (kaannos.isEmpty()) {
            return null;
        }
        return kaannos;
    }

    public int sanojenLukumaara() {
        List<String> lista = jdbcTemplate.query(
                "SELECT COUNT(sana) FROM Sanasto",
                (rs, rowNum) -> rs.getString("COUNT(sana)"));
        return Integer.valueOf(lista.get(0));
    }

    public List<String> kaannoksetListana() {
        List<String> lista = new ArrayList<>();
        lista.addAll(jdbcTemplate.query(
                "SELECT sana, kaannos FROM Sanasto",
                (rs, rowNum) -> rs.getString("sana") + " = " + rs.getString("kaannos")));
        return lista;
    }
}
