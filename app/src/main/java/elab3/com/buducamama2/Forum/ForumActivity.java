package elab3.com.buducamama2.Forum;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import elab3.com.buducamama2.Lekar.AdapterZaRecyclerView;
import elab3.com.buducamama2.Lekar.Testovi.Pregled;
import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.Majka.Parametri;
import elab3.com.buducamama2.R;

public class ForumActivity extends AppCompatActivity {

    ArrayList<ForumTeme> listaTema;
    ArrayList<ForumTeme> filtritanaLista;
    Button btnPostaviPitanje;
    EditText etPretraga;
    RecyclerView recyclerView;
    AdapterZaRecyclerViewForumTeme adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        listaTema=new ArrayList<>();
        filtritanaLista=new ArrayList<>();
        btnPostaviPitanje= findViewById(R.id.btnPostaviPitanje);
        etPretraga=  findViewById(R.id.etPretragaTema);
       recyclerView=(RecyclerView) findViewById(R.id.recyclerViewTeme);
         FirebaseDatabase database=FirebaseDatabase.getInstance();
         DatabaseReference ref=database.getReference("ForumTeme");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listaTema.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    ForumTeme tema= ds.getValue(ForumTeme.class);
                    tema.izStringUDate();
                    tema.setId(ds.getKey());
                    listaTema.add(tema);

                }
                Collections.sort(listaTema);
                adapter=new AdapterZaRecyclerViewForumTeme(listaTema);
                recyclerView.setAdapter(adapter);
                RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ForumActivity.this);
                recyclerView.setLayoutManager(layoutManager);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Greska", "loadPost:onCancelled", databaseError.toException());
            }
        });



        btnPostaviPitanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ForumActivity.this, PostaviPitanjeActivity.class);

                startActivity(intent);
            }
        });

        etPretraga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    pretrazi();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void podesiRecycler(ArrayList<ForumTeme> lista) {
        adapter.setListaMajki(lista);
        adapter.notifyDataSetChanged();

    }

    private void pretrazi() {
        filtritanaLista= new ArrayList<>();
        String deo=etPretraga.getText().toString();
        for (ForumTeme f: listaTema
        ) {
            if(f.getPitanje().toUpperCase().contains(deo.toUpperCase())){
                filtritanaLista.add(f);
            }

        }
        podesiRecycler(filtritanaLista);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
