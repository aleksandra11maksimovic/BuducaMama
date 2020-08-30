package elab3.com.buducamama2.Majka;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import elab3.com.buducamama2.Forum.ForumActivity;
import elab3.com.buducamama2.Lekar.ListaPregleda;
import elab3.com.buducamama2.Lekar.Testovi.Pregled;
import elab3.com.buducamama2.Majka.FAQ.ActivityFAQ;
import elab3.com.buducamama2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GlavniMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GlavniMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GlavniMenuFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    ArrayList<Pregled> listaPregleda;
    PocetnaMajka activity;
    Majka majka;

    public GlavniMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GlavniMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GlavniMenuFragment newInstance(String param1, String param2) {
        GlavniMenuFragment fragment = new GlavniMenuFragment();
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
        View view = inflater.inflate(R.layout.fragment_glavni_menu, container, false);
        varijable(view);

        return view;
    }

    private void varijable(View view) {

        activity = (PocetnaMajka) getActivity();
        majka = activity.getMajka();
        Button ultrazvuk = (Button) view.findViewById(R.id.ibultrazvuk);
        Button todo = (Button) view.findViewById(R.id.ibparametri);
        Button faq = (Button) view.findViewById(R.id.ibfaq);
        Button doctor = (Button) view.findViewById(R.id.ibdoctor);
        Button pregled = (Button) view.findViewById(R.id.ibpregledi);
        Button forum = (Button) view.findViewById(R.id.ibforum);
        ultrazvuk.setOnClickListener(this);
        todo.setOnClickListener(this);
        faq.setOnClickListener(this);
        doctor.setOnClickListener(this);
        pregled.setOnClickListener(this);
        forum.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ibforum:
                Intent intent4 = new Intent(getContext(), ForumActivity.class);
                intent4.putExtra("Majka", majka);
                startActivity(intent4);
                break;

            case R.id.ibultrazvuk:
                Intent intent = new Intent(view.getContext(), UltrazvukActivity.class);
                intent.putExtra("Majka", majka);
                startActivity(intent);
                break;

            case R.id.ibfaq:
                Intent intent1 = new Intent(view.getContext(), ActivityFAQ.class);

                startActivity(intent1);
                break;

            case R.id.ibdoctor:
                Intent intent2 = new Intent(view.getContext(), MojLekar.class);
                intent2.putExtra("Majka", majka);
                startActivity(intent2);
                break;
            case R.id.ibpregledi:
                Intent intent5 = new Intent(view.getContext(), ListaPregleda.class);
                intent5.putExtra("ko", "mama");
                intent5.putExtra("majka", majka);
                listaPregleda = new ArrayList<>();
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
                intent5.putExtra("lista", listaPregleda);
                startActivity(intent5);

                break;
            case R.id.ibparametri:
                Intent intent3 = new Intent(view.getContext(), ParametriActivity.class);
                intent3.putExtra("Majka", majka);
                startActivity(intent3);
                break;
            default:
                break;
        }

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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
