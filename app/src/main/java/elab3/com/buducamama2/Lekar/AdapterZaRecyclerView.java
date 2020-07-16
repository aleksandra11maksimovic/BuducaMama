package elab3.com.buducamama2.Lekar;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import elab3.com.buducamama2.Lekar.Testovi.Pregled;
import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.R;

public class AdapterZaRecyclerView extends RecyclerView.Adapter{

    public AdapterZaRecyclerView(List<Majka> listaMajki) {
        this.listaMajki=listaMajki;
    }

    public List<Majka> getListaMajki() {
        return listaMajki;
    }

    public void setListaMajki(List<Majka> listaMajki) {
        this.listaMajki = listaMajki;
    }

    List<Majka> listaMajki;
    ArrayList<Pregled> listaPregleda;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_trudnica_recycler, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return listaMajki.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView username;
        private TextView head;
        private TextView nedelja;
        private TextView datum;
        private ImageView slika;
        private Majka m;

        public ListViewHolder(View itemView){
            super(itemView);
            username=(TextView) itemView.findViewById(R.id.txtUsername);
            head=(TextView) itemView.findViewById(R.id.txtImeIPrezime);
            nedelja=(TextView)itemView.findViewById(R.id.txtBrNed);
            datum=(TextView)itemView.findViewById(R.id.txtDatum);
            slika=(ImageView)itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);

        }
        public void bindView(int position){
            m=listaMajki.get(position);
            username.setText(m.getUsername());
            head.setText(m.getIme()+" "+m.getPrezime());
            long dan=m.nedeljaTrudnoce();
            nedelja.setText(dan+" ");
            datum.setText(m.getPocetakTrudnoce());
            if(dan<=12) slika.setBackgroundResource(R.drawable.firsttrimestre);
            if(dan>12&&dan<=26) slika.setBackgroundResource(R.drawable.secondtrimestre);
            if(dan>26&&dan<=40) slika.setBackgroundResource(R.drawable.thirdtrimestre);
            if(dan>40)slika.setBackgroundResource(R.drawable.birth);


        }
        public void onClick(View view){
            notifyDataSetChanged();
            view.setBackgroundColor(Color.parseColor("#141313"));
            Intent intent = new Intent(view.getContext(), PrikazMame.class);
            intent.putExtra("Majka",m);
            view.getContext().startActivity(intent);
        }


    }
}
