package elab3.com.buducamama2.Majka;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parametri implements Comparable<Parametri> {

    private String DatumPrikaz;
    private double Kilaza;
    private String Pritisak;
    private String Raspolozenje;
    private double Temperatura;
    private Date datum;

    private String id;

    public Parametri(double kilaza, String pritisak, String raspolozenje, double temperatura, String id, String datumPrikaz) {
        Kilaza = kilaza;
        Pritisak = pritisak;
        Raspolozenje = raspolozenje;
        Temperatura = temperatura;
        this.id=id;
        DatumPrikaz=datumPrikaz;
    }

    public Parametri() {
    }

    public String getDatumPrikaz() {
        return DatumPrikaz;
    }

    public void setDatumPrikaz(String datumPrikaz) {
        DatumPrikaz = datumPrikaz;
    }

    public double getKilaza() {
        return Kilaza;
    }

    public void setKilaza(double kilaza) {
        Kilaza = kilaza;
    }

    public String getPritisak() {
        return Pritisak;
    }

    public void setPritisak(String pritisak) {
        Pritisak = pritisak;
    }

    public String getRaspolozenje() {
        return Raspolozenje;
    }

    public void setRaspolozenje(String raspolozenje) {
        Raspolozenje = raspolozenje;
    }

    public double getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(double temperatura) {
        Temperatura = temperatura;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void izDateUid(){
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        this.id = dateFormat.format(this.datum);
    }
    public void izStringUDate() {
        try {
            this.datum = new SimpleDateFormat("dd.MM.yyyy").parse(this.DatumPrikaz);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void izDateUStringPrikaz(){
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.DatumPrikaz = dateFormat.format(this.datum);

    }


    @Override
    public int compareTo(Parametri parametri) {
        if (datum == null || parametri.getDatum() == null) {
            return 0;
        }
        return parametri.getDatum().compareTo(this.datum);
    }
}
