package elab3.com.buducamama2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import elab3.com.buducamama2.Lekar.Lekar;
import elab3.com.buducamama2.Majka.Majka;

public class PocetnaStranaLekar extends AppCompatActivity {

    Lekar lekar;
    List<Majka> listaMajki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna_strana_lekar);
        lekar = (Lekar) getIntent().getSerializableExtra("Lekar");
        listaMajki= (ArrayList<Majka>)(getIntent().getSerializableExtra("ListaMajki"));
        // region Dodavanje liste majki lekaru
        for (Majka m : listaMajki
             ) {
            if(m.getLekarUsername().equals(lekar.getUsername())){
                lekar.getListaMajki().add(m);

            }

        }
        //endregion


    }
}
