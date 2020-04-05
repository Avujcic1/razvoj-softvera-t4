package ba.unsa.etf.rs.tut4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static ba.unsa.etf.rs.tut4.Artikal.izbaciDuplikate;

public class Controller {

    public Button dugme;
    public TextArea prosljedi;
    public TextArea prikazi;
    public TextArea rezultat;
    public ChoiceBox<String> sviArtikli;
    public Spinner<Integer> kolicina;
    public Button dugmeDodaj;
    public Tab temp1, temp2;

    ArrayList<Artikal> filter = new ArrayList<>();
    String svi_artikli = "";

    public void dodaj() {
        rezultat.setText("");
        Artikal trenutni =  new Artikal();
        for(Artikal i : filter) {
            if(i.getSifra().equals(sviArtikli.getValue())) trenutni = i;
        }
        DecimalFormat format = new DecimalFormat("#,###,###,##0.00");
        double cijena = trenutni.getCijena() * kolicina.getValue();
        svi_artikli = svi_artikli.concat(sviArtikli.getValue() + "    " + kolicina.getValue() + "   " + format.format(cijena) + "\n");
        String[] cijene = svi_artikli.split("\\s+");
        double suma = 0;
        for(int i=2; i<cijene.length; i=i+3) {
            suma += Double.parseDouble(cijene[i]);
        }
        rezultat.appendText(svi_artikli + "UKUPNO:      " + format.format(suma) + "\n");
    }

    public void pokupi() {
        prikazi.clear();
        String tekst = prosljedi.getText();
        if(tekst.isEmpty()) prikazi.appendText("Pogresan unos Artikala!");
        String[] s = tekst.split("\n");

        ArrayList<Artikal> lista = new ArrayList<>();
        for (String i : s) {
            lista.add(new Artikal(i));
        }
        filter = izbaciDuplikate(lista);
        ObservableList<String> sifre = FXCollections.observableArrayList();
        for(Artikal i : filter){
            prikazi.appendText(i + "\n");
            sifre.add(i.getSifra());
        }
        sviArtikli.setItems(sifre);
    }
}
