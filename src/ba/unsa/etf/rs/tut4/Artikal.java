package ba.unsa.etf.rs.tut4;

import java.util.ArrayList;

public class Artikal {
    private String sifra;
    private String naziv;
    private double cijena;
    public Artikal() {

    }

    public void setSifra(String sifra) {
        if(sifra.isEmpty()) throw new IllegalArgumentException("Sifra je prazna!");
        this.sifra = sifra;
    }

    public void setNaziv(String naziv) {
        if(naziv.isEmpty()) throw new IllegalArgumentException("Naziv je prazan!");
        this.naziv = naziv;
    }

    public void setCijena(Double cijena) {
        if(cijena<=0) throw new IllegalArgumentException("Cijena je negativna");
        this.cijena = cijena;
    }

    public String getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public Double getCijena() {
        return cijena;
    }

    public Artikal(String sifra, String naziv, double cijena) {
        if(cijena<=0 || naziv.isEmpty() || sifra.isEmpty()) throw new IllegalArgumentException("Pogresni podaci");
        this.sifra = sifra;
        this.naziv = naziv;
        this.cijena = cijena;
    }

    public Artikal(String ulaz){
        String[] artikal = ulaz.split(",");
        sifra = artikal[0];
        naziv = artikal[1];
        cijena = Double.parseDouble(artikal[2]);
    }

    @Override
    public String toString() {
        return sifra + "," + naziv + "," + cijena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artikal artikal = (Artikal) o;

        if (Double.compare(artikal.getCijena(), getCijena()) != 0) return false;
        if (getSifra() != null ? !getSifra().equals(artikal.getSifra()) : artikal.getSifra() != null) return false;
        return getNaziv() != null ? getNaziv().equals(artikal.getNaziv()) : artikal.getNaziv() == null;
    }

    public static ArrayList<Artikal> izbaciDuplikate(ArrayList<Artikal> art) {
        for (int i=0; i < art.size()-1; i++) {
            for (int j=i+1; j < art.size(); j++) {
                if(art.get(i).equals(art.get(j))) {
                    art.remove(j);
                    j--;
                }
            }
        }

        return art;
    }

}
