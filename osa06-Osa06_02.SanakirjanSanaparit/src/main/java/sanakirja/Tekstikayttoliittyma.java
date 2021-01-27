package sanakirja;

import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Tekstikayttoliittyma {

    @Autowired
    private Sanakirja sanakirja;

    public void kaynnista(Scanner lukija) {
        System.out.println("Komennot:");
        System.out.println("  lisaa - lisää sanaparin sanakirjaan");
        System.out.println("  kaanna - kysyy sanan ja tulostaa sen käännöksen");
        System.out.println("  lopeta - poistuu käyttöliittymästä");
        System.out.println("");

        while (true) {
            System.out.print("Komento: ");
            String syote = lukija.nextLine();
            if (syote.equals("lopeta")) {
                break;
            }

            hoidaKomento(lukija, syote);
            System.out.println("");
        }

        System.out.println("Hei hei!");
    }

    public void hoidaKomento(Scanner lukija, String komento) {
        if (komento.equals("lisaa")) {
            lisaa(lukija);
        } else if (komento.equals("kaanna")) {
            kaanna(lukija);
        } else {
            System.out.println("Tuntematon komento.");
        }
    }

    public void lisaa(Scanner lukija) {
        System.out.print("Suomeksi: ");
        String sana = lukija.nextLine();
        System.out.print("Käännös: ");
        String kaannos = lukija.nextLine();
        this.sanakirja.lisaa(sana, kaannos);
    }

    public void kaanna(Scanner lukija) {
        System.out.print("Anna sana: ");
        String sana = lukija.nextLine();

        if (this.sanakirja.kaanna(sana) == null) {
            System.out.println("Tuntematon sana!");
        } else {
            System.out.println("Käännös: " + this.sanakirja.kaanna(sana));
        }
    }
}
