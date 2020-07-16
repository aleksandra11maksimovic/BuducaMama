package elab3.com.buducamama2.Lekar;

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

import elab3.com.buducamama2.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NalogLekarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NalogLekarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NalogLekarFragment extends Fragment {
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
    PocetnaLekar activity;
    Lekar lekar;
    EditText stariPass;
    EditText noviPass;
    EditText ponovljeniNovi;


    private OnFragmentInteractionListener mListener;

    public NalogLekarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NalogLekarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NalogLekarFragment newInstance(String param1, String param2) {
        NalogLekarFragment fragment = new NalogLekarFragment();
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
        activity = (PocetnaLekar) getActivity();
        View view=inflater.inflate(R.layout.fragment_nalog_lekar, container, false);
        lekar = activity.getLekar();

        stariPass=(EditText) view.findViewById(R.id.etStariPass);
        noviPass=(EditText) view.findViewById(R.id.etNovipass);
        ponovljeniNovi=(EditText) view.findViewById(R.id.etPonovljeniPass);
        txtImeLekara=(TextView) view.findViewById(R.id.txtImeLekar);
        txtPrezime=(TextView)view.findViewById(R.id.TxtPrezimeLekara);
        txtUsername=(TextView)view.findViewById(R.id.txtUsernameLekara);

        popuniPoljaLekara();
        promeniSifru = (Button) view.findViewById(R.id.buttonPromeniSifru);
        promeniSifru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onPromeniSifru(v);
            }
        });
        potvrdiPromenu = (Button) view.findViewById(R.id.buttonPotvrdi);
        potvrdiPromenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onPotvrdi(v);
            }
        });


        cl=view.findViewById(R.id.cntPromenaSifre);
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
    public void popuniPoljaLekara(){
        txtUsername.setText(lekar.getUsername());
        txtImeLekara.setText(lekar.getIme());
        txtPrezime.setText(lekar.getPrezime());
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
        if(!stariPass.getText().toString().equals(lekar.getPassword())){
            Toast.makeText(activity,"Nije dobar stari password",Toast.LENGTH_SHORT).show();
            return;
        }

        if(!noviPass.getText().toString().equals(ponovljeniNovi.getText().toString())){
            Toast.makeText(activity,"Ne poklapaju se nove šifre",Toast.LENGTH_SHORT).show();
            return;
        }
        lekar.setPassword(noviPass.getText().toString());
        try{
            database= FirebaseDatabase.getInstance();
            ref=database.getReference();

            ref.child("Lekar").child(lekar.getUsername()).child("Username").setValue(lekar.getUsername());
            ref.child("Lekar").child(lekar.getUsername()).child("Password").setValue(lekar.getPassword());
            ref.child("Lekar").child(lekar.getUsername()).child("Ime").setValue(lekar.getIme());
            ref.child("Lekar").child(lekar.getUsername()).child("Prezime").setValue(lekar.getPrezime());
            Toast.makeText(activity, "Uspesno ste promenili sifru", Toast.LENGTH_SHORT).show();

        } catch (Exception e){
            Toast.makeText(activity, "Greska prilikom promene naloga", Toast.LENGTH_SHORT).show();
        }
    }
}
