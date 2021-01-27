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
        // tehdään viidennen osan viidennessä tehtävässä
    }

    public String kaanna(String sana) {
        // tehdään viidennen osan viidennessä tehtävässä
        return null;
    }

    public int sanojenLukumaara() {
        // tehdään viidennen osan viidennessä tehtävässä
        return 0;
    }

    public List<String> kaannoksetListana() {
        List<String> lista = new ArrayList<>();
        // tehdään viidennen osan viidennessä tehtävässä
        return lista;
    }

    public List<Sanapari> sanaparit() {
        return jdbcTemplate.query(
                "SELECT sana, kaannos FROM Sanasto",
                (rs, rowNum) -> new Sanapari(rs.getString("sana"), rs.getString("kaannos")));
    }

}
