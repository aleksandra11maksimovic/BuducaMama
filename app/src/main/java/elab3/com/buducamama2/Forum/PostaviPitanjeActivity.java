package elab3.com.buducamama2.Forum;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import elab3.com.buducamama2.MainActivity;
import elab3.com.buducamama2.R;

public class PostaviPitanjeActivity extends AppCompatActivity {

    EditText etPitanje;
    Button btnPostavi;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postavi_pitanje);
        etPitanje = findViewById(R.id.etPitanje);
        btnPostavi = findViewById(R.id.btnPostaviPit);

        btnPostavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pitanje = etPitanje.getText().toString();
                String datum = new Date() + "";
                ArrayList<Odgovor> odgovori = new ArrayList<>();
                try {
                    database = FirebaseDatabase.getInstance();
                    ref = database.getReference();
                    SharedPreferences sharedPreferences= getSharedPreferences("id", MODE_PRIVATE);
                    String username=sharedPreferences.getString("id", null);
                    ForumTeme novaTema = new ForumTeme(odgovori, datum, pitanje,username, new Date());
                    ref.child("ForumTeme").child(novaTema.getId()).child("datum").setValue(novaTema.getDatum());
                    ref.child("ForumTeme").child(novaTema.getId()).child("postavio").setValue(novaTema.getPostavio());
                    ref.child("ForumTeme").child(novaTema.getId()).child("pitanje").setValue(novaTema.getPitanje());

                    Toast.makeText(getApplicationContext(), "Uspesno ste uneli temu", Toast.LENGTH_SHORT).show();
                    onBackPressed();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Greska prilikom unosa teme", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
