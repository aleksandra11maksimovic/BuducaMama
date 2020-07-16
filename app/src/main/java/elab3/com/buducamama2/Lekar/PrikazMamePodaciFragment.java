package elab3.com.buducamama2.Lekar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

import elab3.com.buducamama2.Lekar.Testovi.Pregled;
import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.R;

public class PrikazMamePodaciFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrikazMamePodaciFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static PrikazMamePodaciFragment newInstance(String param1, String param2) {
        PrikazMamePodaciFragment fragment = new PrikazMamePodaciFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    Majka majka;
    private ImageView slika;
    TextView ime;
    TextView prezime;
    TextView username;
    TextView email;
    TextView nedelja;
    TextView tromesecje;
     EditText subject;
     EditText textPoruke;
    Button posaljiEmail;
    Button otvoriPreglede;
    ArrayList<Pregled> listaPregleda;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_prikaz_mame_podaci, container, false);
        final PrikazMame activity=(PrikazMame)getActivity();
        majka = activity.getMama();
        listaPregleda=new ArrayList<>();
        Query query = FirebaseDatabase.getInstance().getReference("Pregled").child(majka.getUsername());


        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Pregled pregled = ds.getValue(Pregled.class);
                        listaPregleda.add(pregled);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        Log.d("Da vidimo", listaPregleda.size()+"");
        query.addListenerForSingleValueEvent(valueEventListener);
        ime=(TextView)view.findViewById(R.id.txtImeMajke2);
        prezime=(TextView)view.findViewById(R.id.txtPrezimeMajke2);
        username=(TextView)view.findViewById(R.id.txtUsernameMajke2);
        nedelja=(TextView)view.findViewById(R.id.textViewNedeljja);
        tromesecje=(TextView)view.findViewById(R.id.textViewTromesecje);
        email=(TextView)view.findViewById(R.id.txtemailmajke);
        slika=(ImageView)view.findViewById(R.id.imageView16);
        subject=(EditText)view.findViewById(R.id.edSubjectMajka);
        textPoruke=(EditText)view.findViewById(R.id.editTextEmailMajke);
        posaljiEmail=(Button)view.findViewById(R.id.posaljiEmailMajka);
        otvoriPreglede=(Button) view.findViewById(R.id.buttonOtvoriPreglede);
        otvoriPreglede.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(activity, ListaPregleda.class);
                intent.putExtra("majka",majka);
                intent.putExtra("lista",listaPregleda);
                intent.putExtra("ko","lekar");
                startActivity(intent);
            }
        });

        ime.setText(majka.getIme());
        prezime.setText(majka.getPrezime());
        username.setText(majka.getUsername());
        email.setText(majka.getEmail());
        long nedeljaa=majka.nedeljaTrudnoce();
        nedelja.setText(majka.nedeljaTrudnoce()+". nedelja");

        if(nedeljaa<=12){
            tromesecje.setText("1. tromesecje");
            slika.setBackgroundResource(R.drawable.firsttrimestre);
        }
        if(nedeljaa>12&&nedeljaa<=26) {

            tromesecje.setText("2. tromesecje");
            slika.setBackgroundResource(R.drawable.secondtrimestre);
        }
        if(nedeljaa>26&&nedeljaa<=40){

            tromesecje.setText("3. tromesecje");
            slika.setBackgroundResource(R.drawable.thirdtrimestre);
        }
        if(nedeljaa>40){

            tromesecje.setText("Beba je rodjena");
            slika.setBackgroundResource(R.drawable.birth);
        }


        posaljiEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posaljiEmail();
            }
        });

        return view;

    }

    private void posaljiEmail() {
        String kome=email.getText().toString();
        String sub=subject.getText().toString();
        String content=textPoruke.getText().toString();
        String[] komefin=new String[1];
        komefin[0]=kome;
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,komefin);
        intent.putExtra(Intent.EXTRA_SUBJECT,sub);
        intent.putExtra(Intent.EXTRA_TEXT,content);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Izaberite:"));
    }


}
