package elab3.com.buducamama2.Majka;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import elab3.com.buducamama2.Lekar.Lekar;
import elab3.com.buducamama2.Lekar.PocetnaLekar;
import elab3.com.buducamama2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NalogMajkaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NalogMajkaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NalogMajkaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseDatabase database;
    DatabaseReference ref;
    TextView txtImeLekara;
    TextView txtPrezime;
    TextView txtUsername;
    Button promeniSifru;
    Button potvrdiPromenu;
    ConstraintLayout cl;
    PocetnaMajka activity;
    Lekar lekar;
    EditText stariPass;
    EditText noviPass;
    EditText ponovljeniNovi;
    Majka majka;
    private OnFragmentInteractionListener mListener;

    public NalogMajkaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NalogMajkaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NalogMajkaFragment newInstance(String param1, String param2) {
        NalogMajkaFragment fragment = new NalogMajkaFragment();
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
        activity = (PocetnaMajka) getActivity();
        majka=activity.getMajka();
        View view=inflater.inflate(R.layout.fragment_nalog_majka, container, false);
        promeniSifru = (Button) view.findViewById(R.id.buttonPromeniSifruMajke);
        stariPass=(EditText) view.findViewById(R.id.etStariPassMajka);
        noviPass=(EditText) view.findViewById(R.id.etNovipassMajka);
        ponovljeniNovi=(EditText) view.findViewById(R.id.etPonovljeniPassMajka);
        txtImeLekara=(TextView) view.findViewById(R.id.txtImeMajka);
        txtPrezime=(TextView)view.findViewById(R.id.TxtPrezimeMajke);
        txtUsername=(TextView)view.findViewById(R.id.txtUsernameMajka);
        popuniPoljaMajke();
        promeniSifru = (Button) view.findViewById(R.id.buttonPromeniSifruMajke);
        promeniSifru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onPromeniSifru(v);
            }
        });
        potvrdiPromenu = (Button) view.findViewById(R.id.buttonPotvrdiMajka);
        potvrdiPromenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onPotvrdi(v);
            }
        });


        cl=view.findViewById(R.id.cntPromenaSifreMajke);
        return view;
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

    public void onPromeniSifru(View view){
        if(cl.getVisibility()==View.INVISIBLE){

            cl.setVisibility(View.VISIBLE);
            promeniSifru.setText("Odustani");
        }
        else{
            cl.setVisibility(View.INVISIBLE);
            promeniSifru.setText("Promeni šifru");
        }
    }
    public void onPotvrdi(View view){
        if(!stariPass.getText().toString().equals(majka.getPassword())){
            Toast.makeText(activity,"Stara lozinka nije ispravna",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!noviPass.getText().toString().equals(ponovljeniNovi.getText().toString())){
            Toast.makeText(activity,"Ne poklapaju se nove šifre",Toast.LENGTH_SHORT).show();
            return;
        }
        majka.setPassword(noviPass.getText().toString());
        try{
            database= FirebaseDatabase.getInstance();
            ref=database.getReference();

            ref.child("Trudnica").child(majka.getUsername()).child("Username").setValue(majka.getUsername());
            ref.child("Trudnica").child(majka.getUsername()).child("Password").setValue(majka.getPassword());
            ref.child("Trudnica").child(majka.getUsername()).child("Ime").setValue(majka.getIme());
            ref.child("Trudnica").child(majka.getUsername()).child("Prezime").setValue(majka.getPrezime());
            Toast.makeText(activity, "Uspesno ste promenili sifru", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            Toast.makeText(activity, "Greska prilikom kreiranja naloga", Toast.LENGTH_SHORT).show();
        }
    }
    public void popuniPoljaMajke(){
        txtUsername.setText(majka.getUsername());
        txtImeLekara.setText(majka.getIme());
        txtPrezime.setText(majka.getPrezime());
    }
}
