package elab3.com.buducamama2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import elab3.com.buducamama2.Lekar.Lekar;
import elab3.com.buducamama2.Lekar.PocetnaLekar;
import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.Majka.PocetnaMajka;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<Majka> listaMajki;
    ArrayList<Lekar> listaLekara;
    Activity ova;
    SharedPreferences sharedPreferences;
    Button izadji;
    String id;
    TextView txtulogovanKorisnik;
    TextView udji;
    Majka majka;
    Lekar lekar;
    EditText username;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=FirebaseDatabase.getInstance();
        majka= new Majka();
        lekar=new Lekar();
        listaLekara= new ArrayList<>();


        database= FirebaseDatabase.getInstance();
        ref=database.getReference("Trudnica");
        listaMajki= new ArrayList<>();



        // region Citanje Svih Majki
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    majka= ds.getValue(Majka.class);
                    listaMajki.add(majka);
                    Log.i("Da vidimo", majka.getIme()+majka.getEmail()+majka.getPocetakTrudnoce()+majka.getPassword()+majka.getLekarUsername()+majka.getUsername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Greska", "loadPost:onCancelled", databaseError.toException());
            }
        });

        // endregion
        // region Citanje Svih Lekara
        ref=database.getReference("Lekar");
        listaLekara= new ArrayList<>();
        lekar= new Lekar();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    lekar= ds.getValue(Lekar.class);
                    listaLekara.add(lekar);
                    Log.i("Da vidimo lekar", lekar.getIme()+lekar.getPrezime()+lekar.getPassword()+lekar.getUsername()+lekar.getEmail());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Greska", "loadPost:onCancelled", databaseError.toException());
            }
        });
        sharedPreferences = this.getSharedPreferences("id", MODE_PRIVATE);

        izadji= findViewById(R.id.button2);
        udji=findViewById(R.id.txtUdji);
        txtulogovanKorisnik=findViewById(R.id.txtUlogovanKoricnik);
        id = sharedPreferences.getString("id", null);
        if(id!=null){
            izadji.setVisibility(View.VISIBLE);
            txtulogovanKorisnik.setText("Ulogovan korisnik:");
            udji.setText(id);

        }else{
            izadji.setVisibility(View.INVISIBLE);
            txtulogovanKorisnik.setText("");
            udji.setText("");
        }
        // endregion
        ova=this;

        udji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Size",listaLekara.size()+"");


                if (id != null) {

                    for (Majka m:listaMajki
                    ) {
                        if(m.getUsername().equals(id)){
                            Intent intent= new Intent(ova, PocetnaMajka.class);
                            intent.putExtra("Majka",m);

                            intent.putExtra("ListaLekara",listaLekara);
                            startActivity(intent);
                            finish();
                        }

                    }
                    for (Lekar l:listaLekara){
                        if(l.getUsername().equals(id)){
                            Intent intent= new Intent(ova, PocetnaLekar.class);
                            intent.putExtra("Lekar",l);
                            startActivity(intent);
                            finish();
                        }
                    }




                }

            }
        });
        izadji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //region popup
                final AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                final AlertDialog alertDialog=builder.create();
                builder.setTitle("Log OUT");

                builder.setMessage("Da li ste sigurni da zelite da se odjavite?");
                builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        izadji.setVisibility(View.INVISIBLE);
                        txtulogovanKorisnik.setText("");
                        udji.setText("");
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("id", null);
                        editor.apply();
                    }
                });

                builder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        alertDialog.cancel();
                    }
                });
                builder.create().show();
                //endregion









            }
        });




        login=findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=findViewById(R.id.txtUserName);
                password=findViewById(R.id.txtPass);
                boolean dali=true;

                // region Logovanje Majki
                for (Majka m: listaMajki
                ) {
                    if(m.getUsername().equals(username.getText().toString())){
                        if(m.getPassword().equals(password.getText().toString())){

                            CharSequence txt="Uspesno ste se ulogovali";
                            Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(MainActivity.this, PocetnaMajka.class);
                            intent.putExtra("Majka", m);
                            intent.putExtra("ListaLekara",listaLekara);

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("id", m.getUsername());

                            editor.apply();
                            finish();
                            startActivity(intent);
                            return;

                        }
                        else{
                            dali=false;
                            CharSequence  txt="Pogresna lozinka";
                            Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_SHORT).show();

                        }
                    }

                }
                // endregion
                // region Logovanje Lekara
                for (Lekar l: listaLekara
                ) {
                    if(l.getUsername().equals(username.getText().toString())){
                        if(l.getPassword().equals(password.getText().toString())){

                            CharSequence txt="Uspesno ste se ulogovali";
                            Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(MainActivity.this, PocetnaLekar.class);
                            intent.putExtra("Lekar", l);
                            intent.putExtra("ListaMajki", listaMajki);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("id", l.getUsername());
                            editor.apply();
                            finish();
                            startActivity(intent);
                            return;

                        }
                        else{
                            dali=false;
                            CharSequence  txt="Pogresna lozinka";
                            Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_SHORT).show();

                        }
                    }

                }
                // endregion
                if(dali) {
                    CharSequence txt = "Pogresan username";
                    Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    public void openSignUpTrudnica(View view){
        Intent intent= new Intent(MainActivity.this, SignUpTrudnica.class);
        intent.putExtra("ListaLekara", listaLekara);
        intent.putExtra("ListaMajki", listaMajki);
        finish();
        startActivity(intent);
    }
    public void openSignUpLekar(View view){
        Intent intent= new Intent(MainActivity.this, SignUpLekar.class);
        intent.putExtra("ListaLekara", listaLekara);
        intent.putExtra("ListaMajki", listaMajki);
        finish();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

}
