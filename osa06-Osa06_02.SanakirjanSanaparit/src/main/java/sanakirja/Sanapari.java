package sanakirja;

public class Sanapari {

    private String sana;
    private String kaannos;

    public Sanapari(String sana, String kaannos) {
        this.sana = sana;
        this.kaannos = kaannos;
    }

    public String getSana() {
        return this.sana;
    }

    public String getKaannos() {
        return this.kaannos;
    }

}
