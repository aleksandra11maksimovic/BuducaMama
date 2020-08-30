package elab3.com.buducamama2.Forum;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Odgovor implements Serializable, Comparable<Odgovor>{

    private String datum;

    private boolean isDoktor;
    private String postavio;
    private String tekst;
    private String id;

    private Date date;

    public Odgovor() {
    }

    public Odgovor(String datum, boolean doktor, String postavio, String tekst, Date date) {
        this.datum = datum;
        this.isDoktor = doktor;
        this.postavio = postavio;
        this.tekst = tekst;
        izDateUid(date);
        izDateUStringPrikaz(date);
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public boolean isDoktor() {
        return isDoktor;
    }

    public void setDoktor(boolean doktor) {
        this.isDoktor = doktor;
    }

    public String getPostavio() {
        return postavio;
    }

    public void setPostavio(String postavio) {
        this.postavio = postavio;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void izDateUid(Date date){
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
        this.id = dateFormat.format(date);
    }
    public void izStringUDate() {
        try {
            this.date = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(this.datum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void izDateUStringPrikaz(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        this.datum = dateFormat.format(date);

    }
    @Override
    public int compareTo(Odgovor odgovor) {
        if (date == null || odgovor.getDate() == null) {
            return 0;
        }
        return odgovor.getDate().compareTo(this.date);
    }

}
