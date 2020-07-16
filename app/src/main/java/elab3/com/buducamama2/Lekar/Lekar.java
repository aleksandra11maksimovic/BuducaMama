package elab3.com.buducamama2.Lekar;

import java.io.Serializable;
import java.util.ArrayList;

import elab3.com.buducamama2.Majka.Majka;

public class Lekar implements Serializable {

    private String Email;
    private String Ime;

    private String Password;
    private String Prezime;
    private String Username;


    @Override
    public String toString() {
        return getUsername();
    }

    private ArrayList<Majka> listaMajki;
    public Lekar(String ime, String prezime, String sifra, String korisnickoIme) {
        this.Ime = ime;
        this.Prezime = prezime;
        this.Password = sifra;
        this.Username = korisnickoIme;
        listaMajki=new ArrayList<>();
    }

    public ArrayList<Majka> getListaMajki() {
        return listaMajki;

    }

    public void setListaMajki(ArrayList<Majka> listaMajki) {
        this.listaMajki = listaMajki;
    }
    public Lekar() {
        listaMajki=new ArrayList<>();
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        this.Ime = ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        this.Prezime = prezime;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String sifra) {
        this.Password = sifra;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String korisnickoIme) {
        this.Username = korisnickoIme;
    }

}
