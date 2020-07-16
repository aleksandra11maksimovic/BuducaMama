package elab3.com.buducamama2.Lekar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import elab3.com.buducamama2.Lekar.Testovi.Pregled;
import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.Majka.Parametri;
import elab3.com.buducamama2.Majka.ParametriPregledFragment;
import elab3.com.buducamama2.Majka.ParametriUnosFragment;
import elab3.com.buducamama2.R;


public class PrikazMame extends AppCompatActivity {

    private Majka mama;
    private ArrayList<Parametri> listaParametara;
    private Parametri parametar;
    private FirebaseDatabase db;
    private ArrayList<Pregled> listaPregleda;
    private DatabaseReference dbref;

    final Fragment fragment1 = new PrikazMamePodaciFragment();
    final Fragment fragment2 = new PrikazMameParametriFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Majka majka;
    Fragment active = fragment1;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_podaci:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;
                case R.id.navigation_parametri:
                    fm.beginTransaction().hide(active).show(fragment2).commit();
                    active = fragment2;
                    return true;
            }
            return false;

        }
    };
    String ko;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_mame);
        mama=(Majka)getIntent().getSerializableExtra("Majka");
        listaPregleda=(ArrayList<Pregled>) getIntent().getSerializableExtra("lista");
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fm.beginTransaction().add(R.id.frame2, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.frame2,fragment1, "1").commit();


    }

    public Majka getMama(){
        return this.mama;
    }
    public ArrayList<Pregled> getListaPregleda(){ return this.listaPregleda;}


}
