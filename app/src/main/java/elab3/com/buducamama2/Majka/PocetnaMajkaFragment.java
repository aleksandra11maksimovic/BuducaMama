package elab3.com.buducamama2.Majka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import elab3.com.buducamama2.Lekar.ListaPregleda;
import elab3.com.buducamama2.Lekar.Testovi.Pregled;
import elab3.com.buducamama2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PocetnaMajkaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PocetnaMajkaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PocetnaMajkaFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Majka majka;
    ArrayList<Pregled> listaPregleda;
    PocetnaMajka activity;
    String datumPrethodnogPristupa;
    long nedelja;
    ImageView imgShare;
    TextView txtimeMajke;
    TextView txtnedeljaTrudnoce;
    SharedPreferences sharedPreferences;
    TextView tip;
    ArrayList<String> listaTips;
    String infoText;
    TextView txtBebaNedelja;
    ImageView imgBeba;
    TextView txtInfoNedelja;
    ImageButton btnimgIInfoNedelja;
    private OnFragmentInteractionListener mListener;

    public PocetnaMajkaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PocetnaMajkaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PocetnaMajkaFragment newInstance(String param1, String param2) {
        PocetnaMajkaFragment fragment = new PocetnaMajkaFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_pocetna_majka, container, false);

        varijable(view);
        //endregion
        //region podesi tip

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("Nedelje").child(nedelja+"");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataSnapshot ds=dataSnapshot;
                if(ds.exists()){
                    infoText=ds.getValue().toString();
                    txtInfoNedelja.setText(infoText);

                }else{
                    infoText="";
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Greska", "loadPost:onCancelled", databaseError.toException());
            }
        });

        popuniListu();
        datumPrethodnogPristupa=activity.getDatumPoslednjegPristupa();
        sharedPreferences= activity.getSharedPreferences("Datum pristupa", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        Date datumPristupa= new Date();
        DateFormat dateFormat= new SimpleDateFormat("dd.MM.yyyy");
        String stringPristupa= dateFormat.format(datumPristupa);

        if(!stringPristupa.equals(datumPrethodnogPristupa)){
            randomizujTip();
        }else{
            tip.setText(sharedPreferences.getString("tip", ""));

        }

        editor.putString("Datum pristupa",stringPristupa);
        editor.apply();
        //enregion
     podesiSlikuIInfo();

      return view;
    }

    private void randomizujTip() {
        int prvi = 0;
        int drugi= listaTips.size();
        int z;
        try{
            Random r= new Random();
            z= r.nextInt((drugi-prvi)+1)+prvi;

            tip.setText(listaTips.get(z));
            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putString("tip", listaTips.get(z));
            editor.apply();
        }catch (Exception e){
            tip.setText("Morate paziti na svoju ishranu");
        }



    }

    private void popuniListu() {
        listaTips.add("Brod pod punim jedrima i trudna žena su najdivnije dve stvari koje sam video. (B. Frenklin)");
        listaTips.add("“Beba je nešto što nosiš u sebi devet meseci, u naručju tri godine, a u svom srcu do kraja života.” Meri Mejson");
        listaTips.add("Ma koliko je moj dan bio loš, dovoljan je jedan pokret u stomaku da sve bude bolje.");
        listaTips.add("“Majke se rađaju u istom trenutku kada i djeca. Ona prije toga nije postojala. Žena je postojala, ali majka nikad. Majka je nešto potpuno novo.” – Rajneesh.");
        listaTips.add("“Majka uvek mora da misli dvaput: jednom za sebe i jednom za svoje dete”, Sofija Loren");
        listaTips.add("“Biti majka znači učiti o snagama koje niste znali da imate i doživjeti strahove za koje niste znali da postoje.” – Linda Wooten");
        listaTips.add("Nikada nećeš razumeti život, kao onda kada počne da nastaje u tebi.");
        listaTips.add("Beba će učiniti ljubav jačom, dane kraćim, noći dužim, račun u banci manjim, kuću srećniju, odeću širu, prošlost zaboravljenu a budućnost vrednu življenja.");
        listaTips.add("“Trudnoća izgleda kao da je smišljena da bi nas pripremila za život majke. Počinjete da se žrtvujete devet meseci pre rođenja deteta kako bi u trenutku kada se ono pojavi bili naviknuti da se zbog njega odričete.” Bret Kielerop");
        listaTips.add("Postoji li moćnije telo, od onog u kome dvasrca kucaju kao jedno?");
        listaTips.add("“Majčinstvo – sva ljubav svijeta počinje i završava s njim.” – Robert Browning");
        listaTips.add("“Biti majka puno radno vrijeme jedan je od najbolje plaćenih poslova. Uzevši u obzir da je plata čista ljubav.” – Mildred B. Vermont.");
        listaTips.add("Tokom trudnoce morate piti vise vode, oko 8-10 čaša dnevno");
        listaTips.add("Tvoje beba je jedina osoba koja zna kako se tvoje srce čuje iznutra");
        listaTips.add("Trudnoća stanje u kom si zaljubljen u dete koje još nisi upoznala.");

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private void varijable(View view){
        activity = (PocetnaMajka) getActivity();
        majka=activity.getMajka();
        majka.podesiDatum();
        nedelja=majka.nedeljaTrudnoce();
        txtimeMajke=(TextView)view.findViewById(R.id.textView10);
        txtnedeljaTrudnoce=(TextView)view.findViewById(R.id.txtNedelja);
        txtnedeljaTrudnoce.setText(nedelja+". nedelja");
        txtimeMajke.setText(majka.getIme()+" "+majka.getPrezime());
        tip=(TextView)view.findViewById(R.id.txtTip);
        listaTips=new ArrayList<>();
        //region Buttoni menija


        txtBebaNedelja= view.findViewById(R.id.txtBebaNedelja);
        imgBeba=view.findViewById(R.id.imgBeba);
        btnimgIInfoNedelja= view.findViewById(R.id.imgInfoNedelja);
        txtInfoNedelja= view.findViewById(R.id.txtInfo);




    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




   public void podesiSlikuIInfo(){

        txtBebaNedelja.setText(txtBebaNedelja.getText().toString() + nedelja+". nedelji");

        btnimgIInfoNedelja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( txtInfoNedelja.getVisibility()==View.VISIBLE){
                    txtInfoNedelja.setVisibility(View.INVISIBLE);
                }else{
                    txtInfoNedelja.setVisibility(View.VISIBLE);
                    txtInfoNedelja.getBackground().setAlpha(120);
                }
            }
        });
        prikaziSliku();

   }
    public void prikaziSliku(){

        if(nedelja<=40){
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("bebePoNedeljama"+"/"+nedelja+".jpg");
            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    Glide.with(PocetnaMajkaFragment.this)
                            .load(uri)
                            .into(imgBeba);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    exception.toString();
                }
            });
            Log.d("SLIKA",FirebaseStorage.getInstance().getReference().child("maja/slika.jpg").getDownloadUrl().toString() );


        }



    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
