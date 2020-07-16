package elab3.com.buducamama2.Majka;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

import elab3.com.buducamama2.Lekar.Lekar;
import elab3.com.buducamama2.R;

public class PocetnaMajka extends AppCompatActivity {

    Majka majka;
    ArrayList<Lekar> listaLekara;
    final Fragment fragment1 = new PocetnaMajkaFragment();
    final Fragment fragment2 = new NalogMajkaFragment();
    SharedPreferences sharedPreferences;
    String datumPoslednjegPristupa;
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selektovaniFragment=null;
            switch (item.getItemId()) {
                case R.id.navigation_pocetna:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;
                case R.id.navigation_mojnalog:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;

            }
            return false;

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna_majka);
        majka = (Majka) getIntent().getSerializableExtra("Majka");
        listaLekara= (ArrayList<Lekar>)(getIntent().getSerializableExtra("ListaLekara"));
        // region Podesavanje Lekara majci
        for (Lekar l: listaLekara
             ) {
            if(l.getUsername().equals(majka.getLekarUsername())){
                majka.setLekar(l);
                break;
            }

        }
        //endregion
        //region randomizer
        sharedPreferences= this.getSharedPreferences("Datum pristupa",MODE_PRIVATE);
        datumPoslednjegPristupa=sharedPreferences.getString("Datum pristupa", null);

        // endregion
        BottomNavigationView navView = findViewById(R.id.nav_viewmajka);
            navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            fm.beginTransaction().add(R.id.fragment, fragment2, "2").hide(fragment2).commit();
            fm.beginTransaction().add(R.id.fragment,fragment1, "1").commit();



    }
    public Majka getMajka(){
        return  this.majka;
    }
    public String getDatumPoslednjegPristupa(){ return this.datumPoslednjegPristupa;}

}
