package elab3.com.buducamama2.Majka;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import elab3.com.buducamama2.R;

public class ParametriActivity extends AppCompatActivity {

    final Fragment fragment1 = new ParametriUnosFragment();
    final Fragment fragment2 = new ParametriPregledFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Majka majka;
    Fragment active = fragment1;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_unos:
                    fm.beginTransaction().hide(active).show(fragment1).commit();
                    active = fragment1;
                    return true;
                case R.id.navigation_pregled:
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
        setContentView(R.layout.activity_parametri);
        majka=(Majka)getIntent().getSerializableExtra("Majka");
        BottomNavigationView navView = findViewById(R.id.topNav);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fm.beginTransaction().add(R.id.frame, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.frame,fragment1, "1").commit();


    }
    public Majka getMajka(){
        return this.majka;
    }
}
