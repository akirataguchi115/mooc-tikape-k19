package varausjarjestelma;

public class Huone {

    Integer id;
    String tyyppi;
    Integer numero;
    Integer paivahinta;

    public Huone(Integer id, String tyyppi, Integer numero, Integer paivahinta) {
        this.tyyppi = tyyppi;
        this.numero = numero;
        this.paivahinta = paivahinta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getPaivahinta() {
        return paivahinta;
    }

    public void setPaivahinta(Integer paivahinta) {
        this.paivahinta = paivahinta;
    }

}
