package elab3.com.buducamama2.Lekar.Testovi;

import java.io.Serializable;
import java.util.ArrayList;

public class Pregled implements Serializable, Comparable<Pregled> {
    private String nazivPregleda;
    private int nedelja;
    private String pritisak;
    private String krvnaSlika;
    private double tezina;
    private ArrayList<Test> listaTestova;
    private String napomena;
    private String sifraUltrazvuka;

    public Pregled() {
        listaTestova= new ArrayList<>();
    }

    public String getKrvnaSlika() {
        return krvnaSlika;
    }

    public void setKrvnaSlika(String krvnaSlika) {
        this.krvnaSlika = krvnaSlika;
    }

    public String getNazivPregleda() {
        return nazivPregleda;
    }

    public void setNazivPregleda(String nazivPregleda) {
        this.nazivPregleda = nazivPregleda;
    }

    public int getNedelja() {
        return nedelja;
    }

    public void setNedelja(int nedelja) {
        this.nedelja = nedelja;
    }

    public String getPritisak() {
        return pritisak;
    }

    public void setPritisak(String pritisak) {
        this.pritisak = pritisak;
    }

    public double getTezina() {
        return tezina;
    }

    public void setTezina(double tezina) {
        this.tezina = tezina;
    }

    public ArrayList<Test> getListaTestova() {
        return listaTestova;
    }

    public void setListaTestova(ArrayList<Test> listaTestova) {
        this.listaTestova = listaTestova;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public String getSifraUltrazvuka() {
        return sifraUltrazvuka;
    }

    public void setSifraUltrazvuka(String sifraUltrazvuka) {
        this.sifraUltrazvuka = sifraUltrazvuka;
    }

    @Override
    public int compareTo(Pregled pregled) {
        return this.nedelja-pregled.nedelja;
    }
}
