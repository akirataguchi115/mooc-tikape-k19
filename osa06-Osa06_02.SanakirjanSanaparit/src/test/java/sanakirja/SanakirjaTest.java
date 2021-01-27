package sanakirja;

import fi.helsinki.cs.tmc.edutestutils.Points;
import fi.helsinki.cs.tmc.edutestutils.Reflex;
import java.util.List;
import org.junit.Test;

@Points("06-02")
public class SanakirjaTest {

    static final String LUOKKA_SANAPARI = "sanakirja.Sanapari";
    static final String LUOKKA_SANAKIRJA = "sanakirja.Sanakirja";

    @Test
    public void onLuokkaSanapari() {
        Reflex.reflect(LUOKKA_SANAPARI).requirePublic();
        Reflex.reflect(LUOKKA_SANAPARI).constructor().taking(String.class, String.class).requireExists();
        Reflex.reflect(LUOKKA_SANAPARI).method("getSana").returning(String.class).takingNoParams().requireExists();
        Reflex.reflect(LUOKKA_SANAPARI).method("getKaannos").returning(String.class).takingNoParams().requireExists();
    }

    @Test
    public void luokassaSanakirjaOnHaluttuMetodi() {
        Reflex.reflect(LUOKKA_SANAKIRJA).requirePublic();
        Reflex.reflect(LUOKKA_SANAKIRJA).method("sanaparit").returning(List.class).takingNoParams().requireExists();
    }
}
