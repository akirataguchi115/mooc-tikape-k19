package varausjarjestelma;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class HuoneDao implements Dao<Huone, Integer> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void create(Huone huone) throws SQLException {
        jdbcTemplate.update("INSERT INTO Huone"
                + " (tyyppi, numero, paivahinta)"
                + " values (?, ?, ?)",
                huone.getTyyppi(), huone.getNumero(), huone.getPaivahinta());
    }

    @Override
    public Huone read(Integer key) throws SQLException {
        List<Huone> huoneet = jdbcTemplate.query(
                "SELECT * FROM Huone WHERE id = ?",
                (rs, rowNum) -> new Huone(rs.getInt("id"),
                        rs.getString("tyyppi"),
                        rs.getInt("numero"),
                        rs.getInt("paivahinta")),
                key);
        if (huoneet.isEmpty()) {
            return null;
        }
        return huoneet.get(0);
    }

    @Override
    public Huone update(Huone object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Huone> list() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
