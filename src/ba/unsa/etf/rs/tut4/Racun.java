package ba.unsa.etf.rs.tut4;

import java.util.ArrayList;

public class Racun {
    private class Stavka {
        private Artikal artikal;
        private int kolicina;

        public Stavka(Artikal artikal, int kolicina) {
            this.artikal = artikal;
            this.kolicina = kolicina;
        }

        public Artikal getArtikal() {
            return artikal;
        }

        public void setArtikal(Artikal artikal) {
            this.artikal = artikal;
        }

        public int getKolicina() {
            return kolicina;
        }

        public void setKolicina(int kolicina) {
            this.kolicina = kolicina;
        }


    }

    private ArrayList<Stavka> stavke;

    public Racun() {
        this.stavke = new ArrayList<>();
    }

    public void dodajStavku(Artikal artikal, int kolicina) {
        Stavka stavka = new Stavka(artikal, kolicina);
        stavke.add(stavka);
    }

    public double ukupanIznos() {
        double iznos=0;
        for (Stavka stavka : stavke) {
            iznos += stavka.getArtikal().getCijena() * stavka.getKolicina();
        }
        return iznos;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Stavka stavka : stavke) {
            output.append(
                    stavka.getArtikal().getSifra())
                    .append(" ").append(stavka.getKolicina())
                    .append(" ").append(stavka.getArtikal().getCijena() * stavka.getKolicina()
            ).append("\n");
        }
        output.append("Ukupan iznos: ").append(ukupanIznos());
        return output.toString();
    }
}