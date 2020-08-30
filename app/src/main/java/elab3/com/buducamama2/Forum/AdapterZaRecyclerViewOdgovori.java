package elab3.com.buducamama2.Forum;

import android.content.Context;
import android.content.Intent;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

import elab3.com.buducamama2.R;

public class AdapterZaRecyclerViewOdgovori extends RecyclerView.Adapter {
    ForumTeme tema;
    ArrayList<Odgovor> listaOdgovora;
    Context context;

    public AdapterZaRecyclerViewOdgovori(ForumTeme tema, Context context) {
        this.tema = tema;
        this.context=context;
        listaOdgovora= tema.getOdgovori();
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
        private ConstraintLayout ctl;
        ImageView imageView;
        Odgovor odgovor;

        public ListViewHolder(View itemView) {
            super(itemView);
            txtOdgovor = itemView.findViewById(R.id.txtOdgovor);
            txtPostavio = itemView.findViewById(R.id.txtPostavioOdgovor);
            imageView = itemView.findViewById(R.id.imgLekar);
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

        }

        public void onClick(View view) {


        }


    }
}
