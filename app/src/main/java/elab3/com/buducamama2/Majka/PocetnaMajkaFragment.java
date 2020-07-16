package elab3.com.buducamama2.Majka;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
public class PocetnaMajkaFragment extends Fragment implements View.OnClickListener{
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
    TextView txtimeMajke;
    TextView txtnedeljaTrudnoce;
    SharedPreferences sharedPreferences;
    TextView tip;
    ArrayList<String> listaTips;


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
        Button ultrazvuk=(Button) view.findViewById(R.id.ibultrazvuk);
        Button todo=(Button) view.findViewById(R.id.ibtodo);
        Button faq=(Button) view.findViewById(R.id.ibfaq);
        Button doctor=(Button) view.findViewById(R.id.ibdoctor);
        Button pregled=(Button) view.findViewById(R.id.ibpregledi);
        ultrazvuk.setOnClickListener(this);
        todo.setOnClickListener(this);
        faq.setOnClickListener(this);
        doctor.setOnClickListener(this);
        pregled.setOnClickListener(this);

        //endregion
        //region podesi tip
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



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

            switch (view.getId()) {

                case R.id.ibultrazvuk:
                    Intent intent= new Intent(view.getContext(), UltrazvukActivity.class);
                    intent.putExtra("Majka",majka);
                    startActivity(intent);
                    break;

                case R.id.ibfaq:
                    Intent intent1= new Intent(view.getContext(), FAQActivity.class);

                    startActivity(intent1);
                    break;

                case R.id.ibdoctor:
                    Intent intent2= new Intent(view.getContext(), MojLekar.class);
                    intent2.putExtra("Majka",majka);
                    startActivity(intent2);
                    break;
                case R.id.ibpregledi:
                    Intent intent3= new Intent(view.getContext(), ParametriActivity.class);
                    intent3.putExtra("Majka",majka);
                    startActivity(intent3);
                    break;
                case R.id.ibtodo:
                    Intent intent4= new Intent(view.getContext(), ListaPregleda.class);
                    intent4.putExtra("ko","mama");
                    intent4.putExtra("majka",majka);
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
                    intent4.putExtra("lista",listaPregleda);
                    startActivity(intent4);

                default:
                    break;
            }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
