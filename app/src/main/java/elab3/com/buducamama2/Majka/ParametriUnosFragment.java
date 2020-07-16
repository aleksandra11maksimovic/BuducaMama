package elab3.com.buducamama2.Majka;

import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.shape.ShapePath;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

import elab3.com.buducamama2.R;

public class ParametriUnosFragment extends Fragment implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Majka majka;
    private TextView txtKilaza;
    private TextView txtPritisak;
    private TextView txtTemperatura;
    private TextView txtDatum;

    private TextView txtRaspolozenje;
    private EditText edKilaza;
    private EditText edPritisak;
    private EditText edRaspolozenje;
    private EditText edTemperatura;
    private ImageButton ibKilaza;
    private ImageButton ibPritisak;
    private ImageButton ibRaspolozenje;
    private ImageButton ibTemperatura;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref;
    private Parametri parametri;



    public ParametriUnosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ParametriUnosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ParametriUnosFragment newInstance(String param1, String param2) {
        ParametriUnosFragment fragment = new ParametriUnosFragment();



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
        View view=inflater.inflate(R.layout.fragment_parametri_unos, container, false);
        txtDatum=(TextView) view.findViewById(R.id.txtDanasnjiDatum);
        txtKilaza=(TextView) view.findViewById(R.id.txtPrikazKilaze);
        txtPritisak=(TextView) view.findViewById(R.id.txtPrikazPritiska);
        txtRaspolozenje=(TextView) view.findViewById(R.id.txtPrikazRaspolozenja);
        txtTemperatura=(TextView) view.findViewById(R.id.txtPrikazTemperature);
        edKilaza=(EditText)view.findViewById(R.id.edUnosKilaze);
        edPritisak=(EditText)view.findViewById(R.id.edUnosPritiska);
        edTemperatura=(EditText)view.findViewById(R.id.edUnosTemp);
        edRaspolozenje=(EditText)view.findViewById(R.id.edUnosRasp);
        ibKilaza=(ImageButton)view.findViewById(R.id.ibUnosKilaze);
        ibRaspolozenje=(ImageButton)view.findViewById(R.id.ibUnosRaspolozenja);
        ibTemperatura=(ImageButton)view.findViewById(R.id.ibUnosTemperature);
        ibPritisak=(ImageButton)view.findViewById(R.id.ibUnosPritiska);

        ParametriActivity activity=(ParametriActivity)getActivity();
        majka=activity.getMajka();
        ibKilaza.setOnClickListener(this);

        ibRaspolozenje.setOnClickListener(this);

        ibPritisak.setOnClickListener(this);

        ibTemperatura.setOnClickListener(this);

        parametri=new Parametri();
        parametri.setDatum(new Date());
        parametri.izDateUid();
        parametri.izDateUStringPrikaz();
        txtDatum.setText(parametri.getDatumPrikaz());
        Ucitaj();
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.ibUnosKilaze:
                naKlik(txtKilaza,edKilaza,ibKilaza);
                break;
            case R.id.ibUnosPritiska:
                naKlik(txtPritisak,edPritisak,ibPritisak);
                break;
            case R.id.ibUnosRaspolozenja:
                naKlik(txtRaspolozenje,edRaspolozenje,ibRaspolozenje);
                break;
            case R.id.ibUnosTemperature:
                naKlik(txtTemperatura,edTemperatura,ibTemperatura);
                break;
            default:
                    break;
        }

    }
    public void naKlik(TextView textView, EditText editText, ImageButton imageButton){
        if(textView.getVisibility()==View.VISIBLE){
            textView.setVisibility(View.INVISIBLE);
            editText.setVisibility(View.VISIBLE);
            imageButton.setImageResource(R.drawable.yes);
        }else{

            switch (editText.getId()) {

                case R.id.edUnosKilaze:
                    try{
                        double kilaza=Double.parseDouble(editText.getText().toString());
                        parametri.setKilaza(kilaza);
                    }catch (Exception e){
                        Toast.makeText(getContext(),"Niste pravilno uneli kilazu (samo broj)",Toast.LENGTH_SHORT).show();

                        textView.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.INVISIBLE);
                        imageButton.setImageResource(R.drawable.rightdouble);
                        return;
                    }
                    break;
                case R.id.edUnosPritiska:
                    String pritisak=editText.getText().toString();
                    if(!pritisak.contains("/")){
                        Toast.makeText(getContext(),"Niste pravilno uneli pritisak (gornji/donji)",Toast.LENGTH_SHORT).show();

                        textView.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.INVISIBLE);
                        imageButton.setImageResource(R.drawable.rightdouble);
                        return;
                    }else{
                        parametri.setPritisak(pritisak);
                    }
                    break;
                case R.id.edUnosRasp:
                    String raspolozenje=editText.getText().toString();
                    if(raspolozenje==""){
                        Toast.makeText(getContext(),"Niste uneli nista za raspolozenje",Toast.LENGTH_SHORT).show();

                        textView.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.INVISIBLE);
                        imageButton.setImageResource(R.drawable.rightdouble);
                        return;
                    }
                    else{
                        parametri.setRaspolozenje(raspolozenje);
                    }
                    break;
                case R.id.edUnosTemp:
                    try{
                        double temperatura=Double.parseDouble(editText.getText().toString());
                        parametri.setTemperatura(temperatura);
                    }catch (Exception e){
                        Toast.makeText(getContext(),"Niste pravilno uneli temperaturu (samo broj)",Toast.LENGTH_SHORT).show();

                        textView.setVisibility(View.VISIBLE);
                        editText.setVisibility(View.INVISIBLE);
                        imageButton.setImageResource(R.drawable.rightdouble);
                        return;
                    }
                    break;
                default:
                    break;
            }
            textView.setVisibility(View.VISIBLE);
            editText.setVisibility(View.INVISIBLE);

            ref=database.getReference("Parametri").child(majka.getUsername()+"Parametri").child(parametri.getId());
            ref.child("Temperatura").setValue(parametri.getTemperatura());
            ref.child("Kilaza").setValue(parametri.getKilaza());
            ref.child("Pritisak").setValue(parametri.getPritisak());
            ref.child("Raspolozenje").setValue(parametri.getRaspolozenje());
            ref.child("DatumPrikaz").setValue(parametri.getDatumPrikaz());
            Toast.makeText(getContext(),"Uspesno sacuvano!",Toast.LENGTH_SHORT).show();
            imageButton.setImageResource(R.drawable.rightdouble);
        }
    }
    public void Ucitaj(){
        ref=database.getReference("Parametri").child(majka.getUsername()+"Parametri").child(parametri.getId());

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    parametri = dataSnapshot.getValue(Parametri.class);
                    parametri.izStringUDate();
                    parametri.izDateUid();
                    PodesiTekstualnaPolja();

                }else{
                    parametri.setKilaza(0);
                    parametri.setPritisak("");
                    parametri.setRaspolozenje("");
                    parametri.setTemperatura(0);
                    PodesiTekstualnaPolja();
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    public void PodesiTekstualnaPolja(){
        txtKilaza.setText(parametri.getKilaza() + "");
        txtPritisak.setText(parametri.getPritisak());
        txtRaspolozenje.setText(parametri.getRaspolozenje());
        txtTemperatura.setText(parametri.getTemperatura() + "");
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }
}
