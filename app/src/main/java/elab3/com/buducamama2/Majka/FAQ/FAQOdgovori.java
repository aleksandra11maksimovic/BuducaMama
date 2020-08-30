package elab3.com.buducamama2.Majka.FAQ;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import elab3.com.buducamama2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FAQOdgovori.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FAQOdgovori#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FAQOdgovori extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String kategorija;
    private ArrayList<Pitanja> listaPitanja;
    ActivityFAQ activityFAQ;
    RecyclerView recyclerView;
    TextView txtTip;
    Button btnUnesiPitanje;
    public void setKategorija(String kategorija) {

            this.kategorija = kategorija;
            listaPitanja=activityFAQ.dajPitanja(this.kategorija);
            txtTip.setText(this.kategorija);

        AdapterZaRecyclerFAQ adapter=new AdapterZaRecyclerFAQ(listaPitanja);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

    }


    private OnFragmentInteractionListener mListener;

    public FAQOdgovori() {
        kategorija="";
        listaPitanja= new ArrayList<>();
    }


    // TODO: Rename and change types and number of parameters
    public static FAQOdgovori newInstance(String param1, String param2) {
        FAQOdgovori fragment = new FAQOdgovori();
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

        View view= inflater.inflate(R.layout.fragment_faqodgovori, container, false);
        activityFAQ= (ActivityFAQ)getActivity();
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerFAQ);
        txtTip= view.findViewById(R.id.txtFaqTip);
        btnUnesiPitanje= view.findViewById(R.id.btnUnesiFAQPitanje);
        SharedPreferences sharedPreferences= getContext().getSharedPreferences("id", Context.MODE_PRIVATE);
        if(!sharedPreferences.getBoolean("lekar", false)){
            btnUnesiPitanje.setTextSize(TypedValue.COMPLEX_UNIT_DIP,0);
            btnUnesiPitanje.setVisibility(View.INVISIBLE);
        }
        btnUnesiPitanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),UnesiFAQActivity.class);
                intent.putExtra("kategorija",kategorija);
                startActivity(intent);
            }
        });




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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
