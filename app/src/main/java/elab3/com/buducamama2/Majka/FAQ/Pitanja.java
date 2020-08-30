package elab3.com.buducamama2.Majka.FAQ;

public class Pitanja {

    private String pitanje;
    private String odgovor;
    private boolean otvoreno;

    public Pitanja( String pitanje, String odgovor) {
        this.pitanje = pitanje;
        this.odgovor = odgovor;
        otvoreno=false;
    }

    public String getPitanje() {
        return pitanje;
    }

    public void setPitanje(String pitanje) {
        this.pitanje = pitanje;
    }

    public String getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(String odgovor) {
        this.odgovor = odgovor;
    }

    public boolean isOtvoreno() {
        return otvoreno;
    }

    public void setOtvoreno(boolean otvoreno) {
        this.otvoreno = otvoreno;
    }
}
