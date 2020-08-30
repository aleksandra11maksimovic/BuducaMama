package elab3.com.buducamama2.Forum;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import elab3.com.buducamama2.Majka.Parametri;

public class ForumTeme implements Serializable, Comparable<ForumTeme> {

    private ArrayList<Odgovor> odgovori;
    private String datum;
    private String pitanje;
    private String postavio;
    private String id;
    Date date;

    public ForumTeme(ArrayList<Odgovor> odgovori, String datum, String pitanje, String postavio, Date date) {
        this.odgovori = odgovori;
        this.datum = datum;
        this.pitanje = pitanje;
        this.postavio = postavio;
        izDateUid(date);
        izDateUStringPrikaz(date);
    }

    public ForumTeme() {
        odgovori= new ArrayList<>();
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

    public ArrayList<Odgovor> getOdgovori() {
        return odgovori;
    }

    public void setOdgovori(ArrayList<Odgovor> odgovori) {
        this.odgovori = odgovori;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public String getPostavio() {
        return postavio;
    }

    public void setPostavio(String postavio) {
        this.postavio = postavio;
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
    public int compareTo(ForumTeme tema) {
        if (date == null || tema.getDate() == null) {
            return 0;
        }
        return tema.getDate().compareTo(this.date);
    }


}
