package elab3.com.buducamama2.Majka;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ultrazvuk {




    private Bitmap Slika;
    private String UsernameMajke;
    private String Datum;
    private Date datumPravi;

    public Ultrazvuk() {
    }

    public void podesiDatum(){

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.datumPravi = format.parse(this.Datum);

        } catch (ParseException e) {
        }
    }


    public Bitmap getSlika() {
        return Slika;
    }

    public void setSlika(Bitmap slika) {
        this.Slika = slika;
    }

    public String getUsernameMajke() {
        return UsernameMajke;
    }

    public void setUsernameMajke(String usernameMajke) {
        this.UsernameMajke = usernameMajke;
    }

    public String getDatum() {
        return Datum;
    }

    public void setDatum(String datum) {
        this.Datum = datum;
    }

    public Date getDatumPravi() {
        return datumPravi;
    }

    public void setDatumPravi(Date datumPravi) {
        this.datumPravi = datumPravi;
    }

}
