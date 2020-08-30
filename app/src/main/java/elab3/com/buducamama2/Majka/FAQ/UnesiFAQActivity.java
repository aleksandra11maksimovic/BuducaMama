package elab3.com.buducamama2.Majka.FAQ;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import elab3.com.buducamama2.R;

public class UnesiFAQActivity extends AppCompatActivity {

    private String kategorija;
    private Button btnUnesi;
    private EditText etPitanje;
    private EditText etOdgovor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unesi_faq);
        kategorija= getIntent().getStringExtra("kategorija");
        etOdgovor = findViewById(R.id.etNoviOdgovor);
        etPitanje = findViewById(R.id.etNovoPitanje);
        btnUnesi = findViewById(R.id.btnUnesiPitanje);

        btnUnesi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
                DatabaseReference reference= firebaseDatabase.getReference();
                reference.child("FAQ").child(kategorija).child(etPitanje.getText().toString()).setValue(etOdgovor.getText().toString());
                Toast.makeText(UnesiFAQActivity.this, "Uspešno ste uneli novo pitanje", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
