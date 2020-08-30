package elab3.com.buducamama2.Majka;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import elab3.com.buducamama2.Lekar.AdapterZaRecyclerView;
import elab3.com.buducamama2.Lekar.Lekar;
import elab3.com.buducamama2.Lekar.PocetnaLekar;
import elab3.com.buducamama2.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ParametriPregledFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ParametriPregledFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Parametri> listaParametara;
    private Parametri param;
    public RecyclerView recyclerView;
    private Majka majka;
    private RecyclerView.Adapter adapter;


    public ParametriPregledFragment() {
        // Required empty public constructor
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View getView() {
        return super.getView();
    }

    public static ParametriPregledFragment newInstance(String param1, String param2) {
        ParametriPregledFragment fragment = new ParametriPregledFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ParametriActivity activity = (ParametriActivity) getActivity();
        majka = activity.getMajka();
        View view=inflater.inflate(R.layout.fragment_parametri_pregled, container, false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView2);
        //region popuniListuParam
        popuniListuParametara();
        //endregion
        AdapterZaRecyclerParametr adapter=new AdapterZaRecyclerParametr(listaParametara);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);



        return view;
    }

    private void popuniListuParametara() {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference ref=database.getReference("Parametri").child(majka.getUsername()+"Parametri");
        listaParametara= new ArrayList<>();
        param= new Parametri();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaParametara.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    param= ds.getValue(Parametri.class);
                    param.izStringUDate();
                    param.izDateUid();
                    listaParametara.add(param);

                }

                Collections.sort(listaParametara);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Greska", "loadPost:onCancelled", databaseError.toException());
            }
        });


    }

    
}
