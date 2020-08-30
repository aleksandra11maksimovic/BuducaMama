package elab3.com.buducamama2.Forum;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

import elab3.com.buducamama2.R;

public class PostaviOdgovorActivity extends AppCompatActivity {

    EditText etOdgovor;
    Button btnPostaviOdgovor;
    ForumTeme tema;
    SharedPreferences sharedPreferences;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postavi_odgovor);
        etOdgovor= findViewById(R.id.etPostaviOdgovor);
        btnPostaviOdgovor= findViewById(R.id.btnPostaviOdgovor);
        sharedPreferences=getSharedPreferences("id",MODE_PRIVATE);
        tema= (ForumTeme) getIntent().getSerializableExtra("Tema");

        btnPostaviOdgovor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String datum= new Date()+"";
                boolean isLekar= sharedPreferences.getBoolean("lekar",false);
                String postavio= sharedPreferences.getString("id", null);
                String tekst= etOdgovor.getText().toString();

                    database = FirebaseDatabase.getInstance();
                    ref = database.getReference();

                    Odgovor odgovor= new Odgovor(datum,isLekar,postavio,tekst,new Date());
                    tema.getOdgovori().add(odgovor);
                    ref.child("ForumTeme").child(tema.getId()).child("odgovori").setValue(tema.getOdgovori());
                    Toast.makeText(getApplicationContext(), "Uspesno ste uneli temu", Toast.LENGTH_SHORT).show();
                    onBackPressed();




            }
        });

    }
}
