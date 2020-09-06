package elab3.com.buducamama2.Forum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.R;

public class ForumPitanjeActivity extends AppCompatActivity {
    ForumTeme tema;
    TextView txtPostavio;
    TextView txtPitanje;
    Button btnPostaviOdgovor;
    RecyclerView recyclerView;
    Majka majka;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_pitanje);

        tema= (ForumTeme) getIntent().getSerializableExtra("Pitanje");
        txtPostavio= findViewById(R.id.txtPostavioOdgovor);
        txtPitanje= findViewById(R.id.txtPitanjeOdgolvor);
        recyclerView= findViewById(R.id.recyclerViewOdgovori);
        btnPostaviOdgovor= findViewById(R.id.btnOdgovori);

        txtPostavio.setText(tema.getPostavio()+"\n"+tema.getDatum());
        txtPitanje.setText(tema.getPitanje());
        btnPostaviOdgovor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ForumPitanjeActivity.this, PostaviOdgovorActivity.class);
                intent.putExtra("Tema", tema);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences= getSharedPreferences("id", MODE_PRIVATE);

        AdapterZaRecyclerViewOdgovori adapterZaRecyclerViewOdgovori= new AdapterZaRecyclerViewOdgovori(tema,this, sharedPreferences.getString("id",""));
        recyclerView.setAdapter(adapterZaRecyclerViewOdgovori);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }
}
