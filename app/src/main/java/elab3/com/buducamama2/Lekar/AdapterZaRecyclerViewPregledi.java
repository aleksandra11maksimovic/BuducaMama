package elab3.com.buducamama2.Lekar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import org.w3c.dom.Text;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import elab3.com.buducamama2.Lekar.Testovi.Pregled;
import elab3.com.buducamama2.Lekar.Testovi.Test;
import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.R;

public class AdapterZaRecyclerViewPregledi extends RecyclerView.Adapter {
    public AdapterZaRecyclerViewPregledi(List<Pregled> listaPregleda, Majka majka, String ko) {
        this.majka=majka;
        this.listaPregleda=listaPregleda;
        this.ko=ko;
    }

    public List<Pregled> getListaPregleda() {
        return listaPregleda;
    }
    Majka majka;
    String ko;

    public void setListaPregleda(List<Pregled> listaPregleda) {
        this.listaPregleda=listaPregleda;
    }

    List<Pregled> listaPregleda;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_pregled, viewGroup, false);
        return new AdapterZaRecyclerViewPregledi.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((AdapterZaRecyclerViewPregledi.ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return listaPregleda.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView txtNaslov;
        private TextView txtNedelja;
        private TextView txtKrvnaSlika;
        private TextView txtPritisak;
        private TextView txtKilaza;
        private TextView txtTestovi;
        private Button buttonDetalji;
        private TextView txtNapomena;
        private TextView txtUltrazvuk;
        private ImageButton buttonDelete;
        private Pregled pregled;
        private ArrayList<String> listaUltr;

        public ListViewHolder(View itemView){
            super(itemView);
            txtNaslov=(TextView)itemView.findViewById(R.id.txtNazivPregleda);
            txtKrvnaSlika=(TextView)itemView.findViewById(R.id.txtKrvnaSlika);
            txtPritisak=(TextView)itemView.findViewById(R.id.txtPritisakk);
            txtNedelja=(TextView)itemView.findViewById(R.id.txtNedelja);
            txtKilaza=(TextView)itemView.findViewById(R.id.txtTezina);
            txtTestovi=(TextView)itemView.findViewById(R.id.txtTestovi);
            txtNapomena=(TextView)itemView.findViewById(R.id.txtNapomena);
            txtUltrazvuk=(TextView)itemView.findViewById(R.id.txtParamUltrazvuka);
            buttonDetalji=(Button)itemView.findViewById(R.id.buttonDetaljiPregleda);
            buttonDetalji.setOnClickListener(this);
            buttonDelete=(ImageButton)itemView.findViewById(R.id.buttonDelete);
            buttonDelete.setOnClickListener(this);
            if(ko!=null && ko.equals("mama")){
                buttonDelete.setVisibility(View.INVISIBLE);
            }



        }
        public void bindView(int position){
            pregled=listaPregleda.get(position);
            txtNaslov.setText(pregled.getNazivPregleda());
            txtNapomena.setText(pregled.getNapomena());
            txtKilaza.setText(pregled.getTezina()+"");
            txtPritisak.setText(pregled.getPritisak());
            txtKrvnaSlika.setText(pregled.getKrvnaSlika());
            txtNedelja.setText(pregled.getNedelja()+". nedelja");
            if(pregled.getListaTestova().size()==0){
                txtTestovi.setText("Nije radjen nijedan test");
            }else {
                for (Test t : pregled.getListaTestova()) {
                    txtTestovi.setText(txtTestovi.getText() + t.getNaziv() + ";");
                }
            }
            final FirebaseDatabase database=FirebaseDatabase.getInstance();
            DatabaseReference ref=database.getReference();
            if(!pregled.getSifraUltrazvuka().equals("")) {
                ref.child("Ultrazvuk").child(pregled.getSifraUltrazvuka()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            listaUltr = new ArrayList<>();
                            for(DataSnapshot ds:dataSnapshot.getChildren()){
                                String s=ds.getValue(String.class);
                                listaUltr.add(s);
                            }
                            for(String st : listaUltr){
                                txtUltrazvuk.setText(txtUltrazvuk.getText()+";"+st);
                            }
                        }else{
                            txtUltrazvuk.setText("Nije radjen ili sinhronizovan!");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }else{
                txtUltrazvuk.setText("Ultrazvuk nije radjen");
            }
        }


        public void onClick(final View view){
            switch (view.getId()){
                case R.id.buttonDetaljiPregleda:
                    notifyDataSetChanged();
                    Intent intent=new Intent(view.getContext(),DodajPregled.class);
                    intent.putExtra("pregled",pregled);
                    intent.putExtra("majka",majka);
                    intent.putExtra("ko",ko);
                    view.getContext().startActivity(intent);
                    break;
                case R.id.buttonDelete:
                    final AlertDialog.Builder builder= new AlertDialog.Builder(view.getContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                    final AlertDialog alertDialog=builder.create();
                    builder.setTitle("!!!");

                    builder.setMessage("Da li ste sigurni da želite da obrišete pregled?");
                    builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            final FirebaseDatabase database= FirebaseDatabase.getInstance();
                            DatabaseReference ref= database.getReference("Pregled").child(majka.getUsername()).child(pregled.getNazivPregleda()+pregled.getNedelja());
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()){
                                        dataSnapshot.getRef().removeValue();
                                        notifyItemRemoved(getAdapterPosition());
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });



                        }
                    });

                    builder.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.cancel();
                        }
                    });
                    builder.create().show();


                    break;

            }


        }


    }
}
