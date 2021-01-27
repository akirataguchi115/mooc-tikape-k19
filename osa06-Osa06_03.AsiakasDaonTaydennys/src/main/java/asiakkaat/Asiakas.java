package asiakkaat;

public class Asiakas {

    Integer id;
    String nimi;
    String puhelinnumero;
    String katuosoite;
    Integer postinumero;
    String postitoimipaikka;

    public Asiakas() {
    }

    public Asiakas(Integer id, String nimi, String puhelinnumero, String katuosoite, Integer postinumero, String postitoimipaikka) {
        this.id = id;
        this.nimi = nimi;
        this.puhelinnumero = puhelinnumero;
        this.katuosoite = katuosoite;
        this.postinumero = postinumero;
        this.postitoimipaikka = postitoimipaikka;
    }

    public Asiakas(String nimi, String puhelinnumero, String katuosoite, Integer postinumero, String postitoimipaikka) {
        this(null, nimi, puhelinnumero, katuosoite, postinumero, postitoimipaikka);
    }
    public Asiakas(String nimi) {
        this.nimi = nimi;
//        this(null, nimi, puhelinnumero, katuosoite, postinumero, postitoimipaikka);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getPuhelinnumero() {
        return puhelinnumero;
    }

    public void setPuhelinnumero(String puhelinnumero) {
        this.puhelinnumero = puhelinnumero;
    }

    public String getKatuosoite() {
        return katuosoite;
    }

    public void setKatuosoite(String katuosoite) {
        this.katuosoite = katuosoite;
    }

    public Integer getPostinumero() {
        return postinumero;
    }

    public void setPostinumero(Integer postinumero) {
        this.postinumero = postinumero;
    }

    public String getPostitoimipaikka() {
        return postitoimipaikka;
    }

    public void setPostitoimipaikka(String postitoimipaikka) {
        this.postitoimipaikka = postitoimipaikka;
    }

    @Override
    public String toString() {
        return "Asiakas{" + "id=" + id + ", nimi=" + nimi + ", puhelinnumero=" + puhelinnumero + ", katuosoite=" + katuosoite + ", postinumero=" + postinumero + ", postitoimipaikka=" + postitoimipaikka + '}';
    }

}
