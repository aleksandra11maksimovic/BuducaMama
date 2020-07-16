package elab3.com.buducamama2.Lekar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.R;
import elab3.com.buducamama2.R;


public class PocetnaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText etpretraga;
    ImageButton erase;
    Lekar lekar;
    private List<Majka> listaMajki;



    public PocetnaFragment() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        PocetnaLekar activity = (PocetnaLekar) getActivity();
        lekar = activity.getLekar();
        View view=inflater.inflate(R.layout.fragment_pocetna, container, false);
        etpretraga=(EditText)view.findViewById(R.id.etpretraga);

        RecyclerView recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
        popuniListuMajkiLekara();
        AdapterZaRecyclerView adapter=new AdapterZaRecyclerView(listaMajki);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        etpretraga.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                pretrazi();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        erase=(ImageButton)view.findViewById(R.id.butterase);
        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etpretraga.setText("");
            }
        });
        return view;

}

    private void pretrazi() {
        String deo=etpretraga.getText().toString();
        ArrayList<Majka> filtrirana=new ArrayList<>();
        for (Majka m: listaMajki
             ) {
            if(m.getIme().toUpperCase().contains(deo.toUpperCase())||m.getPrezime().toUpperCase().contains(deo.toUpperCase())||m.getUsername().toUpperCase().contains(deo.toUpperCase())){
                filtrirana.add(m);
            }

        }
        RecyclerView recyclerView=(RecyclerView) getView().findViewById(R.id.recyclerView);
        popuniListuMajkiLekara();
        AdapterZaRecyclerView adapter=new AdapterZaRecyclerView(filtrirana);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


    }

    // TODO: Rename method, update argument and hook method into UI event


    public void popuniListuMajkiLekara() {
        listaMajki = new ArrayList<>();
        Query query = FirebaseDatabase.getInstance().getReference("Trudnica").orderByChild("LekarUsername").equalTo(lekar.getUsername());


        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Majka majka = ds.getValue(Majka.class);
                        majka.podesiDatum();
                        listaMajki.add(majka);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);

    }

}
