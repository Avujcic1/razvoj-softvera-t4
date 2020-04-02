package ba.unsa.etf.rs.tut4;


import ba.unsa.etf.rs.tut4.Artikal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import static ba.unsa.etf.rs.tut4.Artikal.izbaciDuplikate;

public class Controller {

    public Button dugme;
    public TextArea pokupiTekst;
    public TextArea prikaziTekst;
    public TextArea konacniRacun;
    public ChoiceBox<String> sviArtikli;
    public Spinner<Integer> kolicina;

    ArrayList<Artikal> filter = new ArrayList<>();
    public void pokupi() {
        prikaziTekst.clear();
        ArrayList<Artikal> lista = new ArrayList<>();
        String sav_tekst=pokupiTekst.getText();
        String[] s=sav_tekst.split("\n");
        if(s.length==0) {
            prikaziTekst.appendText("Pogresan unos artikala!");
            return;
        }
        for (String value : s) {
            lista.add(new Artikal(value));
        }
        filter = izbaciDuplikate(lista);
        ObservableList<String> sifre = FXCollections.observableArrayList();
        for(Artikal x: filter){
            prikaziTekst.appendText(x + "\n");
            sifre.add(x.getSifra());
        }
        sviArtikli.setItems(sifre);
    }
    String svi_artikli = "";
    public void dodaj() {
        konacniRacun.setText("");
        Artikal trenutni =  new Artikal();
        for(Artikal a: filter) if(a.getSifra().equals(sviArtikli.getValue())) trenutni = a;
        DecimalFormat dvije_decimale = new DecimalFormat("#,###,###,##0.00");
        double cijena = trenutni.getCijena()*kolicina.getValue();
        svi_artikli=svi_artikli.concat(sviArtikli.getValue() + "    " + kolicina.getValue() + "   "
                + dvije_decimale.format(cijena) + "\n");
        String[] cijene= svi_artikli.split("\\s+");
        double suma = 0;
        for(int i=2; i<cijene.length; i=i+3) suma+=Double.parseDouble(cijene[i]);
        konacniRacun.appendText(svi_artikli + "UKUPNO:      " + dvije_decimale.format(suma) + "\n");
    }
}
