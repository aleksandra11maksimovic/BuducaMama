package elab3.com.buducamama2.Majka.FAQ;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import elab3.com.buducamama2.R;

public class ActivityFAQ extends AppCompatActivity {

    final Fragment fragment1 = new FAQPocetnaFragment();
    final FAQOdgovori fragment2 = new FAQOdgovori();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = fragment1;

    private ArrayList<Pitanja> listaPitanjaNavike;
    private ArrayList<Pitanja> listaPitanjaIshrana;
    private ArrayList<Pitanja> listaPitanjaProblemi;
    private ArrayList<Pitanja> listaPitanjaPorodjaj;
    ActivityFAQ activityFAQ;
    FirebaseDatabase database;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq2);
        fm.beginTransaction().add(R.id.fragmentFAQ, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.fragmentFAQ,fragment1, "1").commit();

        listaPitanjaPorodjaj= new ArrayList<>();
        listaPitanjaNavike= new ArrayList<>();
        listaPitanjaProblemi= new ArrayList<>();
        listaPitanjaIshrana= new ArrayList<>();


        database= FirebaseDatabase.getInstance();

        ref=database.getReference("FAQ").child("Navike");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String pitanje=ds.getKey().toString();
                    String odgovor=ds.getValue().toString();
                    Pitanja pitanja= new Pitanja(pitanje, odgovor);
                    listaPitanjaNavike.add(pitanja);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Greska", "loadPost:onCancelled", databaseError.toException());
            }
        });

        ref=database.getReference("FAQ").child("Ishrana");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String pitanje=ds.getKey().toString();
                    String odgovor=ds.getValue().toString();
                    Pitanja pitanja= new Pitanja(pitanje, odgovor);
                    listaPitanjaIshrana.add(pitanja);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Greska", "loadPost:onCancelled", databaseError.toException());
            }
        });

        ref=database.getReference("FAQ").child("Porodjaj");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String pitanje=ds.getKey().toString();
                    String odgovor=ds.getValue().toString();
                    Pitanja pitanja= new Pitanja(pitanje, odgovor);
                    listaPitanjaPorodjaj.add(pitanja);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Greska", "loadPost:onCancelled", databaseError.toException());
            }
        });

        ref=database.getReference("FAQ").child("Problemi");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String pitanje=ds.getKey().toString();
                    String odgovor=ds.getValue().toString();
                    Pitanja pitanja= new Pitanja(pitanje, odgovor);
                    listaPitanjaProblemi.add(pitanja);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Greska", "loadPost:onCancelled", databaseError.toException());
            }
        });

    }

    public ArrayList<Pitanja> dajPitanja(String kategorija){
        if(kategorija.equals("Navike")){
            return listaPitanjaNavike;
        }
        if(kategorija.equals("Problemi")){
            return listaPitanjaProblemi;
        }
        if(kategorija.equals("Porodjaj")){
            return listaPitanjaPorodjaj;
        }
        if(kategorija.equals("Ishrana")){
            return listaPitanjaIshrana;
        }
        return new ArrayList<Pitanja>();
    }
    public void prebaciFragment(String kategorija){
        fragment2.setKategorija(kategorija);
        fm.beginTransaction().hide(active).show(fragment2).commit();
        active = fragment2;

    }

    @Override
    public void onBackPressed() {
        if(active.equals(fragment2)){
            fm.beginTransaction().hide(active).show(fragment1).commit();
            active = fragment1;
        }else{

            super.onBackPressed();
        }
    }
}
