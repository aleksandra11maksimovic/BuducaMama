package elab3.com.buducamama2.Lekar.Testovi;

import java.io.Serializable;
import java.util.ArrayList;

public class Test implements Serializable {

    String naziv;
    ArrayList<String> listaParametara;

    public Test() {
        listaParametara=new ArrayList<>();
    }

    public Test(String naziv, ArrayList<String> listaParametara) {
        this.naziv = naziv;
        this.listaParametara = listaParametara;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public ArrayList<String> getListaParametara() {
        return listaParametara;
    }

    public void setListaParametara(ArrayList<String> listaParametara) {
        this.listaParametara = listaParametara;
    }

}
