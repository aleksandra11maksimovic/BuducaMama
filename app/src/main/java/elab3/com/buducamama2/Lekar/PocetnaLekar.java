package elab3.com.buducamama2.Lekar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ResourceBundle;

import elab3.com.buducamama2.R;

public class PocetnaLekar extends AppCompatActivity {
    private TextView mTextMessage;
    Lekar lekar;
    SharedPreferences sharedPreferences;
    final Fragment fragment1 = new PocetnaFragment();
    final Fragment fragment2 = new NalogLekarFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
        setContentView(R.layout.activity_pocetna_lekar);
        lekar = (Lekar) getIntent().getSerializableExtra("Lekar");
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_pocetna);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fm.beginTransaction().add(R.id.fragment, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragment,fragment1, "1").commit();
    }
    public Lekar getLekar(){
        return  this.lekar;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
