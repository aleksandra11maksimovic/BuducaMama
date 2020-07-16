package elab3.com.buducamama2.Lekar;

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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.Majka.Parametri;
import elab3.com.buducamama2.R;


public class PrikazMameParametriFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public PrikazMameParametriFragment() {
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
    public static PrikazMameParametriFragment newInstance(String param1, String param2) {
        PrikazMameParametriFragment fragment = new PrikazMameParametriFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    Majka majka;
    ArrayList<Parametri> listaParametara;
    Parametri param;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PrikazMame activity = (PrikazMame) getActivity();
         majka= activity.getMama();
        View view=inflater.inflate(R.layout.fragment_prikaz_mame_parametri, container, false);
        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recLekarMajka);
        //region popuniListuParam
        popuniListuParametara();
        //endregion
        AdapterZaRecyclerViewMajke adapter=new AdapterZaRecyclerViewMajke(listaParametara);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    private void popuniListuParametara() {
        FirebaseDatabase database= FirebaseDatabase.getInstance();
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("Greska", "loadPost:onCancelled", databaseError.toException());
            }
        });
        Collections.sort(listaParametara);
        Collections.reverse(listaParametara);


    }



}
