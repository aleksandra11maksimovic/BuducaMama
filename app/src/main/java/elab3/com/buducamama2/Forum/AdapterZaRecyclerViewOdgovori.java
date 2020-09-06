package elab3.com.buducamama2.Forum;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

import elab3.com.buducamama2.R;

public class AdapterZaRecyclerViewOdgovori extends RecyclerView.Adapter {
    ForumTeme tema;
    ArrayList<Odgovor> listaOdgovora;
    Context context;
    String korisnik;

    public AdapterZaRecyclerViewOdgovori(ForumTeme tema, Context context, String korisnik) {
        this.tema = tema;
        this.context=context;
        listaOdgovora= tema.getOdgovori();
        this.korisnik= korisnik;
    }

    public void setTema(ForumTeme tema) {
        this.tema = tema;

        listaOdgovora= tema.getOdgovori();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recycler_odgovor, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return listaOdgovora.size();
    }
    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtOdgovor;
        private TextView txtPostavio;
        private ImageView imbBtnObtisi;
        private ConstraintLayout ctl;
        ImageView imageView;
        Odgovor odgovor;

        public ListViewHolder(View itemView) {
            super(itemView);
            txtOdgovor = itemView.findViewById(R.id.txtOdgovor);
            txtPostavio = itemView.findViewById(R.id.txtPostavioOdgovor);
            imageView = itemView.findViewById(R.id.imgLekar);
            imbBtnObtisi= itemView.findViewById(R.id.imgButtonObrisiOdgovor);
            ctl= itemView.findViewById(R.id.ctlOdgovor);
            itemView.setOnClickListener(this);

        }

        public void bindView(int position) {
            odgovor = listaOdgovora.get(position);
            txtPostavio.setText(odgovor.getPostavio() + "\n" + odgovor.getDatum());
            txtOdgovor.setText(odgovor.getTekst());
            if(!odgovor.isDoktor()){
                imageView.setVisibility(View.INVISIBLE);
                ctl.setBackground(context.getResources().getDrawable(R.drawable.background_odgovori));

            }
            if(korisnik.equals(odgovor.getPostavio())){
                imbBtnObtisi.setVisibility(View.VISIBLE);
                imbBtnObtisi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder builder= new AlertDialog.Builder(view.getContext(), AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
                        final AlertDialog alertDialog=builder.create();
                        builder.setTitle("!!!");

                        builder.setMessage("Da li ste sigurni da želite da obrišete vaš odgovor?");
                        builder.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final FirebaseDatabase database= FirebaseDatabase.getInstance();

                                DatabaseReference ref= database.getReference("ForumTeme").child(tema.getId()).child("odgovori");
                                Query query=  database.getReference("ForumTeme").child(tema.getId()).child("odgovori").orderByChild("id").equalTo(odgovor.getId());
                                query.addValueEventListener(new ValueEventListener() {
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


                    }
                });
            }


        }

        public void onClick(View view) {


        }


    }
}
