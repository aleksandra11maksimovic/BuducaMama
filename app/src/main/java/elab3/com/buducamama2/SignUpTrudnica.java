package elab3.com.buducamama2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import elab3.com.buducamama2.Lekar.Lekar;
import elab3.com.buducamama2.Majka.Majka;

public class SignUpTrudnica extends AppCompatActivity {

    private EditText etIme;
    private EditText etPrezime;
    private EditText etPassword;
    private EditText etUsername;
    private EditText etPonovljeniPass;
    private EditText etDatum;
    private EditText etEmail;
    Spinner lekar;
    Majka novaTrudnica;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<Lekar> listaLekara;
    ArrayList<Majka> listaMajki;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_trudnica);
        listaMajki=(ArrayList<Majka>)(getIntent().getSerializableExtra("ListaMajki"));
        listaLekara= (ArrayList<Lekar>)(getIntent().getSerializableExtra("ListaLekara"));
        ArrayAdapter userAdapter = new ArrayAdapter<>(this, R.layout.spinner,R.id.IzaberiteLekara, listaLekara);
        lekar = (Spinner) findViewById(R.id.spinner2);
        lekar.setAdapter(userAdapter);

    }
    public void kreirajNalog(View view){
        etIme=findViewById(R.id.etIme);
        etPassword=findViewById(R.id.etPassword);
        etPrezime=findViewById(R.id.etprezime);
        etPonovljeniPass=findViewById(R.id.etPonovljeni);
        etDatum=findViewById(R.id.etDatum);
        etUsername=findViewById(R.id.etUsername);
        etEmail=findViewById(R.id.editMail);
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
        if(etDatum.getText().equals("")||etDatum.getText()==null){
            CharSequence text="Niste uneli datum";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;
        }
        if(!(etPassword.getText().toString().equals(etPonovljeniPass.getText().toString()))){
            CharSequence text="Ne poklapaju se passwordi";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;
        }
        // region validacijaDatuma
        if(etDatum.getText().toString().length()!=10){
            CharSequence text="Niste dobro uneli datum";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;
        }
        if(!etEmail.getText().toString().contains("@")){
            CharSequence text="Nije dobar mail";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;
        }
        Date date;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            date = format.parse(etDatum.getText().toString());

        } catch (ParseException e) {
            e.printStackTrace();
            CharSequence text="Niste dobro uneli datum";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;
        }
        // endregion
        novaTrudnica= new Majka();
        novaTrudnica.setIme(etIme.getText().toString());
        novaTrudnica.setPrezime(etPrezime.getText().toString());
        novaTrudnica.setUsername(etUsername.getText().toString());
        novaTrudnica.setPassword(etPassword.getText().toString());
        novaTrudnica.setDatumPocetka(date);
        novaTrudnica.setPocetakTrudnoce(format.format(date));
        novaTrudnica.setEmail(etEmail.getText().toString());

        // region Podesavanje lekara


        Lekar lekar = (Lekar) ( (Spinner) findViewById(R.id.spinner2) ).getSelectedItem();
        if(lekar==null){
            CharSequence text="Niste izabrali lekara";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            return;
        }
        novaTrudnica.setLekar(lekar);
        novaTrudnica.setLekarUsername(lekar.getUsername());
        novaTrudnica.setBrojSlikaUltrazvuka(0);
        // endregion
        // region Unos nove trudnice u bazu
        try{
            database= FirebaseDatabase.getInstance();
            ref=database.getReference();

            ref.child("Trudnica").child(novaTrudnica.getUsername()).child("Username").setValue(novaTrudnica.getUsername());
            ref.child("Trudnica").child(novaTrudnica.getUsername()).child("Password").setValue(novaTrudnica.getPassword());
            ref.child("Trudnica").child(novaTrudnica.getUsername()).child("Ime").setValue(novaTrudnica.getIme());
            ref.child("Trudnica").child(novaTrudnica.getUsername()).child("Prezime").setValue(novaTrudnica.getPrezime());
            ref.child("Trudnica").child(novaTrudnica.getUsername()).child("pocetakTrudnoce").setValue(novaTrudnica.getPocetakTrudnoce());
            ref.child("Trudnica").child(novaTrudnica.getUsername()).child("LekarUsername").setValue(novaTrudnica.getLekarUsername());
            ref.child("Trudnica").child(novaTrudnica.getUsername()).child("BrojSlikaUltrazvuka").setValue(novaTrudnica.getBrojSlikaUltrazvuka());
            ref.child("Trudnica").child(novaTrudnica.getUsername()).child("Email").setValue(novaTrudnica.getEmail());
            Toast.makeText(getApplicationContext(), "Uspesno ste kreirali nalog", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
            Intent intent=new Intent(this, MainActivity.class);
            startActivity(intent);

        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Greska prilikom kreiranja naloga", Toast.LENGTH_SHORT).show();
        }

        //endregion
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);

    }
}
