package elab3.com.buducamama2.Lekar;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import elab3.com.buducamama2.Lekar.Testovi.Pregled;
import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.R;

public class ListaPregleda extends AppCompatActivity {
    Button dodajPregled;
    Majka majka;
    ImageButton refresh;
    TextView imeprezime;
    ArrayList<Pregled> listaPregleda;
    String ko;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pregleda);
        dodajPregled= findViewById(R.id.buttonZaDodavanje);

        majka= (Majka) getIntent().getSerializableExtra("majka");
        listaPregleda=(ArrayList<Pregled>) getIntent().getSerializableExtra("lista");
        ko=getIntent().getStringExtra("ko");
        imeprezime=findViewById(R.id.txtImeIprezimeMajke);
        imeprezime.setText(majka.getIme()+" "+majka.getPrezime());

        Log.d("d",listaPregleda.size()+"");
        dodajPregled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ListaPregleda.this, DodajPregled.class);
                Pregled pregled=null;
                intent.putExtra("majka",majka);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerPregledi);

        AdapterZaRecyclerViewPregledi adapter=new AdapterZaRecyclerViewPregledi(listaPregleda,majka,ko);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        refresh=findViewById(R.id.ButtonRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResume();
            }
        });
        if(ko!=null && ko.equals("mama")){
            dodajPregled.setVisibility(View.INVISIBLE);
        }

    }



    @Override
    protected void onResume() {
        super.onResume();
        listaPregleda= new ArrayList<>();

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference reference=database.getReference("Pregled").child(majka.getUsername());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.d("Da visio","yes");
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Pregled pregled = ds.getValue(Pregled.class);
                        listaPregleda.add(pregled);
                    }
                    Collections.sort(listaPregleda);
                    Collections.reverse(listaPregleda);

                    RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerPregledi);

                    AdapterZaRecyclerViewPregledi adapter=new AdapterZaRecyclerViewPregledi(listaPregleda,majka,ko);
                    recyclerView.setAdapter(adapter);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ListaPregleda.this);
                    recyclerView.setLayoutManager(layoutManager);
                }else{
                    RecyclerView recyclerView=(RecyclerView) findViewById(R.id.recyclerPregledi);

                    AdapterZaRecyclerViewPregledi adapter=new AdapterZaRecyclerViewPregledi(listaPregleda,majka,ko);
                    recyclerView.setAdapter(adapter);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ListaPregleda.this);
                    recyclerView.setLayoutManager(layoutManager);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
