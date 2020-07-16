package elab3.com.buducamama2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import elab3.com.buducamama2.Lekar.Lekar;
import elab3.com.buducamama2.Majka.Majka;

public class SignUpLekar extends AppCompatActivity {


    private EditText etIme;
    private EditText etPrezime;
    private EditText etPassword;
    private EditText etUsername;
    private EditText etPonovljeniPass;
    private EditText etEmail;
    private ArrayList<Majka> listaMajki;
    private ArrayList<Lekar> listaLekara;
    Lekar novLekar;
    FirebaseDatabase database;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_lekar);
        listaMajki=(ArrayList<Majka>)(getIntent().getSerializableExtra("ListaMajki"));
        listaLekara= (ArrayList<Lekar>)(getIntent().getSerializableExtra("ListaLekara"));
    }
    public void kreirajNalog(View view){
        etIme=findViewById(R.id.etIme);
        etPassword=findViewById(R.id.etPassword);
        etPrezime=findViewById(R.id.etprezime);
        etPonovljeniPass=findViewById(R.id.etPonovljeni);
        etUsername=findViewById(R.id.etUsername);
        etEmail=findViewById(R.id.etEmail);
        if(etIme.getText().toString().equals("")||etIme.getText()==null){
            CharSequence text="Niste uneli ime";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;
        }
        if(etPrezime.getText().toString().equals("")||etPrezime.getText()==null){
            CharSequence text="Niste uneli prezime";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;

        }
        if(etUsername.getText().toString().equals("")||etUsername.getText()==null){
            CharSequence text="Niste uneli username";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;

        }
        for (Majka m: listaMajki
        ) {
            if(m.getUsername().equals(etUsername.getText().toString())){
                Toast.makeText(getApplicationContext(), "Postoji korisnik sa tim username-om", Toast.LENGTH_SHORT).show();
                return;
            }

        }
        for (Lekar l: listaLekara
        ) {
            if(l.getUsername().equals(etUsername.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Postoji korisnik sa tim username-om", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if(etPassword.getText().toString().equals("")||etPassword.getText()==null){
            CharSequence text="Niste uneli password";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;
        }

        if(!(etPassword.getText().toString().equals(etPonovljeniPass.getText().toString()))){
            CharSequence text="Ne poklapaju se passwordi";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;
        }
        if(!etEmail.getText().toString().contains("@")){
            CharSequence text="Nije dobar mail";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;
        }
        novLekar= new Lekar();
        novLekar.setIme(etIme.getText().toString());
        novLekar.setPrezime(etPrezime.getText().toString());
        novLekar.setUsername(etUsername.getText().toString());
        novLekar.setPassword(etPassword.getText().toString());
        novLekar.setEmail(etEmail.getText().toString());



        // region Unos novog Lekara u bazu
        try{
            database= FirebaseDatabase.getInstance();
            ref=database.getReference();

            ref.child("Lekar").child(novLekar.getUsername()).child("Username").setValue(novLekar.getUsername());
            ref.child("Lekar").child(novLekar.getUsername()).child("Password").setValue(novLekar.getPassword());
            ref.child("Lekar").child(novLekar.getUsername()).child("Ime").setValue(novLekar.getIme());
            ref.child("Lekar").child(novLekar.getUsername()).child("Prezime").setValue(novLekar.getPrezime());
            ref.child("Lekar").child(novLekar.getUsername()).child("Email").setValue(novLekar.getEmail());
            Toast.makeText(getApplicationContext(), "Uspesno ste kreirali nalog", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this, MainActivity.class);
            startActivity(intent);

        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Greska prilikom kreiranja naloga", Toast.LENGTH_SHORT).show();
        }
        //endregion
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
