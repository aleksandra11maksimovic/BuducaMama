package elab3.com.buducamama2.Majka;

import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import elab3.com.buducamama2.Lekar.Lekar;

public class Majka implements Serializable {

    private int BrojSlikaUltrazvuka;
    private String Email;
    private String Ime;
    private String LekarUsername;
    private String Password;

    private String Prezime;
    private String Username;
    private String pocetakTrudnoce;
    public int getBrojSlikaUltrazvuka() {
        return BrojSlikaUltrazvuka;
    }

    public void setBrojSlikaUltrazvuka(int brojSlikaUltrazvuka) {
        BrojSlikaUltrazvuka = brojSlikaUltrazvuka;
    }


    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    private Date datumPocetka;



    private Lekar lekar;

    public Majka() {
        lekar=new Lekar();
    }

    public Majka(String korisnickoIme, String sifra, String ime, String prezime, String pocetakTrudnoce, String lekarUsername) {
        this.Username = korisnickoIme;
        this.Password = sifra;
        this.Ime = ime;
        this.Prezime = prezime;
        this.pocetakTrudnoce = pocetakTrudnoce;
        this.LekarUsername = lekarUsername;
        this.lekar= new Lekar();
    }
    public Lekar getLekar() {
        return lekar;
    }

    public void setLekar(Lekar lekar) {
        this.lekar = lekar;
    }
    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setUsername(String korisnickoIme) {
        this.Username = korisnickoIme;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String sifra) {
        this.Password = sifra;
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

    public String getPocetakTrudnoce() {
        return pocetakTrudnoce;
    }

    public void setPocetakTrudnoce(String pocetakTrudnoce) {
        this.pocetakTrudnoce = pocetakTrudnoce;
    }

    public String getLekarUsername() {
        return LekarUsername;
    }

    public void setLekarUsername(String lekarUsername) {
        this.LekarUsername = lekarUsername;
    }

    public long nedeljaTrudnoce(){
        Date date= new Date();

            GregorianCalendar c1 = new GregorianCalendar();
            c1.setTime(this.datumPocetka);
            GregorianCalendar c2 = new GregorianCalendar();
            c2.setTime(date);
            for( long i=1; ; i++ ) {
                c1.add( Calendar.WEEK_OF_YEAR, 1 ); // add one day, week, year, etc.
                if( c1.after(c2) )
                    return i;
            }
    }
    public void podesiDatum(){
        ;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.datumPocetka = format.parse(this.pocetakTrudnoce);

        } catch (ParseException e) {
        }
    }
    public void updateMama(Majka mama){
        try{
            FirebaseDatabase database= FirebaseDatabase.getInstance();
            DatabaseReference ref=database.getReference();

            ref.child("Trudnica").child(mama.getUsername()).child("Username").setValue(mama.getUsername());
            ref.child("Trudnica").child(mama.getUsername()).child("Password").setValue(mama.getPassword());
            ref.child("Trudnica").child(mama.getUsername()).child("Ime").setValue(mama.getIme());
            ref.child("Trudnica").child(mama.getUsername()).child("Prezime").setValue(mama.getPrezime());
            ref.child("Trudnica").child(mama.getUsername()).child("BrojSlikaUltrazvuka").setValue(mama.getBrojSlikaUltrazvuka());
            ref.child("Trudnica").child(mama.getUsername()).child("pocetakTrudnoce").setValue(mama.getPocetakTrudnoce());
            ref.child("Trudnica").child(mama.getUsername()).child("LekarUsername").setValue(mama.getLekarUsername());

        } catch (Exception e){

        }
    }


}
