package elab3.com.buducamama2.Lekar;

import android.content.Intent;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import elab3.com.buducamama2.Lekar.Testovi.Amniocenteza;
import elab3.com.buducamama2.Lekar.Testovi.DoubleTest;
import elab3.com.buducamama2.Lekar.Testovi.OGTT;
import elab3.com.buducamama2.Lekar.Testovi.PapanikolauTest;
import elab3.com.buducamama2.Lekar.Testovi.Pregled;
import elab3.com.buducamama2.Lekar.Testovi.Test;
import elab3.com.buducamama2.Lekar.Testovi.TripleTest;
import elab3.com.buducamama2.Lekar.Testovi.VaginalniBris;
import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.R;

public class DodajPregled extends AppCompatActivity {

    ConstraintLayout ct8;
    ConstraintLayout ct13;
    ConstraintLayout ct27;
    ConstraintLayout ct;

    EditText edNapomena, edNedelja, edTezina, edPritisak, edKrvnaSlika, edNazivPregleda, edSifraPregleda;

    Majka majka;
    ImageButton sinhronizuj;
    Button sacuvaj;
    TextView txtUltrazvuk;
    TextView txtDoubleTest;
    TextView txtAmniocenteza;
    TextView txtOGTT;
    TextView txtCTG;
    TextView txtTripleTest;
    TextView txtVaginalniBris;
    TextView txtPapanikolauTest;
    TextView txtVidiSve;

    TextView txtDoubleTestIspis;
    TextView txtAmniocentezaIspis;
    TextView txtOGTTIspis;
    TextView txtCTGIspis;
    TextView txtTripleTestIspis;
    TextView txtVaginalniBrisIspis;
    TextView txtPapanikolauTestIspis;
    String ko;


    EditText edNedeljaTrudnoce;

    LinearLayout.LayoutParams wrap;
    LinearLayout.LayoutParams nula;


    //region testovi
    public ArrayList<Test> listaTestova;
    public Pregled pregled;

    public static Test papa;
    public static Test ogtt;
    public static Test ctg;
    public static Test dabl;
    public static Test triple;
    public static Test amniocenteza;
    public static Test vaginalniBris;


    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_pregled);
        varijable();
        majka = (Majka) getIntent().getSerializableExtra("majka");
        pregled = (Pregled) getIntent().getSerializableExtra("pregled");
        ko = getIntent().getStringExtra("ko");
        if (ko != null && ko.equals("mama")) {
            sacuvaj.setVisibility(View.INVISIBLE);
        }
        if (pregled == null) {
            listaTestova = new ArrayList<>();
        }
        if (pregled != null) {

            pregledPregleda();

        }

        sacuvaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sacuvajPregled();
            }
        });
        sinhronizuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sinhronizujUltrazvuk();
            }
        });
        listaTestova = new ArrayList<>();

        txtDoubleTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DodajPregled.this, DoubleTest.class);
                intent.putExtra("test", dabl);
                startActivity(intent);
            }
        });
        txtTripleTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DodajPregled.this, TripleTest.class);
                intent.putExtra("test", triple);
                startActivity(intent);
            }
        });
        txtAmniocenteza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DodajPregled.this, Amniocenteza.class);
                intent.putExtra("test", amniocenteza);
                startActivity(intent);
            }
        });
        txtOGTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DodajPregled.this, OGTT.class);
                intent.putExtra("test", ogtt);
                startActivity(intent);
            }
        });
        txtPapanikolauTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DodajPregled.this, PapanikolauTest.class);
                intent.putExtra("test", papa);
                startActivity(intent);
            }
        });
        txtVaginalniBris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(DodajPregled.this, VaginalniBris.class);
                intent.putExtra("test", vaginalniBris);
                startActivity(intent);

            }
        });


        edNedeljaTrudnoce = findViewById(R.id.edNedeljaTrudnoće);
        wrap = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        nula = new LinearLayout.LayoutParams(0, 0);
        ct8.setLayoutParams(nula);
        ct13.setLayoutParams(nula);
        ct27.setLayoutParams(nula);

        //region VidiSve
        txtVidiSve = findViewById(R.id.txtVidiSve);
        txtVidiSve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ct8.getLayoutParams() == nula) {
                    ct8.setLayoutParams(wrap);
                    ct13.setLayoutParams(wrap);
                    ct27.setLayoutParams(wrap);
                    txtVidiSve.setText("Sakrij");
                } else {
                    ct8.setLayoutParams(nula);
                    ct13.setLayoutParams(nula);
                    ct27.setLayoutParams(nula);
                    txtVidiSve.setText("Vidi sve");
                }

            }
        });
        //endregion

        edNedeljaTrudnoce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    switch (Integer.parseInt(edNedeljaTrudnoce.getText().toString())) {
                        case 1:
                        case 2:
                            ct8.setLayoutParams(nula);
                            ct13.setLayoutParams(nula);
                            ct27.setLayoutParams(nula);
                            break;
                        case 8:
                            ct8.setLayoutParams(wrap);
                            break;
                        case 13:
                        case 12:
                        case 14:
                            ct13.setLayoutParams(wrap);
                            break;
                        case 26:
                        case 27:
                        case 28:
                            ct27.setLayoutParams(wrap);
                            break;
                        case 29:
                            txtAmniocenteza.setText(listaTestova.size() + "");
                            break;
                    }
                } catch (Exception e) {
                    ct8.setLayoutParams(nula);
                    ct13.setLayoutParams(nula);
                    ct27.setLayoutParams(nula);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void varijable() {
        edNapomena = findViewById(R.id.edNapomena);
        edNedelja = findViewById(R.id.edNedeljaTrudnoće);
        edTezina = findViewById(R.id.edTezina);
        edPritisak = findViewById(R.id.edPritisak);
        edKrvnaSlika = findViewById(R.id.edKrvnaSlika);
        edNazivPregleda = findViewById(R.id.edNazivPregleda);
        edSifraPregleda = findViewById(R.id.edSifraUltrazvuka);
        ct8 = findViewById(R.id.ct8);
        ct13 = findViewById(R.id.ct13);
        ct27 = findViewById(R.id.ct27);
        txtDoubleTest = findViewById(R.id.txtDoubleTest);
        txtAmniocenteza = findViewById(R.id.txtAmniocenteza);
        txtTripleTest = findViewById(R.id.txtTripleTest);
        txtVaginalniBris = findViewById(R.id.txtVaginalniBris);
        txtOGTT = findViewById(R.id.txtOGTT);
        txtPapanikolauTest = findViewById(R.id.txtPapa);
        txtDoubleTestIspis = findViewById(R.id.txtDoubleTestIspis);
        txtAmniocentezaIspis = findViewById(R.id.txtamniocentezaIspis);
        txtTripleTestIspis = findViewById(R.id.txtTripleTestIspis);
        txtUltrazvuk = findViewById(R.id.txtUltrazvuk);
        txtVaginalniBrisIspis = findViewById(R.id.txtVaginalniBrisIspis);
        txtOGTTIspis = findViewById(R.id.txtOgttIspis);
        txtPapanikolauTestIspis = findViewById(R.id.txtPapaIspis);
        ct = findViewById(R.id.constraintLayout5);
        sacuvaj = findViewById(R.id.ButtonSacuvajPregled);
        sinhronizuj = (ImageButton) findViewById(R.id.buttonSinhronizuj);
    }

    private void pregledPregleda() {
        edNapomena.setEnabled(false);
        edNedelja.setEnabled(false);
        edTezina.setEnabled(false);
        edPritisak.setEnabled(false);
        edKrvnaSlika.setEnabled(false);
        edNazivPregleda.setEnabled(false);
        edSifraPregleda.setEnabled(false);
        edNapomena.setText(pregled.getNapomena());
        edTezina.setText(pregled.getTezina() + "");
        edNedelja.setText(pregled.getNedelja() + "");
        edPritisak.setText(pregled.getPritisak());
        edKrvnaSlika.setText(pregled.getKrvnaSlika());
        edNazivPregleda.setText(pregled.getNazivPregleda());
        edSifraPregleda.setText(pregled.getSifraUltrazvuka());

        for (Test t : pregled.getListaTestova()) {
            if (t.getNaziv().equals("Papanikolau test")) {
                papa = t;
                txtPapanikolauTest.setTypeface(Typeface.DEFAULT_BOLD);
            }
            if (t.getNaziv().equals("OGTT")) {
                ogtt = t;

                txtOGTT.setTypeface(Typeface.DEFAULT_BOLD);
            }
            if (t.getNaziv().equals("Double test")) {
                dabl = t;
                txtDoubleTest.setTypeface(Typeface.DEFAULT_BOLD);
            }
            if (t.getNaziv().equals("Triple test")) {
                triple = t;
                txtTripleTest.setTypeface(Typeface.DEFAULT_BOLD);
            }
            if (t.getNaziv().equals("Vaginalni bris")) {
                vaginalniBris = t;
                txtVaginalniBrisIspis.setTypeface(Typeface.DEFAULT_BOLD);
            }
            if (t.getNaziv().equals("Ctg")) {
                ctg = t;
                txtCTG.setTypeface(Typeface.DEFAULT_BOLD);
            }
        }
        sacuvaj.setText("Izmeni");
        sinhronizuj.setImageResource(R.drawable.infoo);


    }

    ArrayList<String> listaUltr;

    private void sinhronizujUltrazvuk() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        if (!edSifraPregleda.getText().toString().equals("")) {
            ref.child("Ultrazvuk").child(edSifraPregleda.getText().toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        listaUltr = new ArrayList<>();
                        txtUltrazvuk.setText("");
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String s = ds.getValue(String.class);
                            listaUltr.add(s);
                        }
                        for (String st : listaUltr) {
                            txtUltrazvuk.setText(txtUltrazvuk.getText() + "\n" + st);
                        }
                    } else {
                        txtUltrazvuk.setText("Ne postoji ultrazvuk pod ovom sifrom!");
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }

    private void sacuvajPregled() {

        if (sacuvaj.getText().toString().equals("Izmeni")) {
            sacuvaj.setText("Sacuvaj izmene");
            sinhronizuj.setImageResource(R.drawable.syncc);

            edNapomena.setEnabled(true);
            edNedelja.setEnabled(false);
            edTezina.setEnabled(true);
            edPritisak.setEnabled(true);
            edKrvnaSlika.setEnabled(true);
            edNazivPregleda.setEnabled(false);
            edSifraPregleda.setEnabled(true);

            return;
        }

        if (edNazivPregleda.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Morate uneti naziv", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edNedelja.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Morate uneti nedelju pregleda", Toast.LENGTH_SHORT).show();
            return;
        }
        pregled = new Pregled();
        pregled.setNapomena(edNapomena.getText().toString());
        try {

            pregled.setTezina(Double.parseDouble(edTezina.getText().toString()));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Morate uneti pravilno tezinu (decimalni broj)", Toast.LENGTH_SHORT).show();
            return;

        }
        try {

            pregled.setNedelja(Integer.parseInt(edNedelja.getText().toString()));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Morate uneti pravilno nedelju (ceo broj)", Toast.LENGTH_SHORT).show();
            return;

        }
        pregled.setKrvnaSlika(edKrvnaSlika.getText().toString());

        pregled.setPritisak(edPritisak.getText().toString());
        pregled.setNazivPregleda(edNazivPregleda.getText().toString());
        pregled.setSifraUltrazvuka(edSifraPregleda.getText().toString());
        if (papa != null && papa.getListaParametara().size() != 0) listaTestova.add(papa);
        if (ctg != null && ctg.getListaParametara().size() != 0) listaTestova.add(ctg);
        if (dabl != null && dabl.getListaParametara().size() != 0) listaTestova.add(dabl);
        if (triple != null && triple.getListaParametara().size() != 0) listaTestova.add(triple);
        if (amniocenteza != null && amniocenteza.getListaParametara().size() != 0)
            listaTestova.add(amniocenteza);
        if (vaginalniBris != null && vaginalniBris.getListaParametara().size() != 0)
            listaTestova.add(vaginalniBris);
        if (ogtt != null && ogtt.getListaParametara().size() != 0) listaTestova.add(ogtt);
        pregled.setListaTestova(listaTestova);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();


        ref.child("Pregled").child(majka.getUsername()).child(pregled.getNazivPregleda() + pregled.getNedelja()).child("nazivPregleda").setValue(pregled.getNazivPregleda());
        ref.child("Pregled").child(majka.getUsername()).child(pregled.getNazivPregleda() + pregled.getNedelja()).child("napomena").setValue(pregled.getNapomena());
        ref.child("Pregled").child(majka.getUsername()).child(pregled.getNazivPregleda() + pregled.getNedelja()).child("pritisak").setValue(pregled.getPritisak());
        ref.child("Pregled").child(majka.getUsername()).child(pregled.getNazivPregleda() + pregled.getNedelja()).child("tezina").setValue(pregled.getTezina());
        ref.child("Pregled").child(majka.getUsername()).child(pregled.getNazivPregleda() + pregled.getNedelja()).child("sifraUltrazvuka").setValue(pregled.getSifraUltrazvuka());
        ref.child("Pregled").child(majka.getUsername()).child(pregled.getNazivPregleda() + pregled.getNedelja()).child("nedelja").setValue(pregled.getNedelja());
        ref.child("Pregled").child(majka.getUsername()).child(pregled.getNazivPregleda() + pregled.getNedelja()).child("listaTestova").setValue(pregled.getListaTestova());
        ref.child("Pregled").child(majka.getUsername()).child(pregled.getNazivPregleda() + pregled.getNedelja()).child("krvnaSlika").setValue(pregled.getKrvnaSlika());

        Toast.makeText(getApplicationContext(), "Uspešno sačuvan pregled!", Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (papa != null && !papa.equals("")) {
            txtPapanikolauTest.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (ctg != null && !ctg.equals("")) {
            txtCTG.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (ogtt != null && !ogtt.equals("")) {
            txtOGTT.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (amniocenteza != null && !amniocenteza.equals("")) {
            txtAmniocenteza.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (dabl != null) {
            txtDoubleTest.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (triple != null && !triple.equals("")) {
            txtTripleTest.setTypeface(Typeface.DEFAULT_BOLD);
        }
        if (vaginalniBris != null && !vaginalniBris.equals("")) {
            txtVaginalniBris.setTypeface(Typeface.DEFAULT_BOLD);
        }


    }

}
