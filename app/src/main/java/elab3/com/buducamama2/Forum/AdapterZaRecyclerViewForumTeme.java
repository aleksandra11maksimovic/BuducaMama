package elab3.com.buducamama2.Forum;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import elab3.com.buducamama2.Lekar.AdapterZaRecyclerView;
import elab3.com.buducamama2.Lekar.PrikazMame;
import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.R;

public class AdapterZaRecyclerViewForumTeme extends RecyclerView.Adapter {
    public AdapterZaRecyclerViewForumTeme(ArrayList<ForumTeme> listaForumTema) {

        this.listaForumTema = listaForumTema;
    }

    public ArrayList<ForumTeme> getListForumTema() {
        return listaForumTema;
    }

    public void setListaMajki(ArrayList<ForumTeme> listaForumTema) {
        this.listaForumTema = listaForumTema;
    }

    ArrayList<ForumTeme> listaForumTema;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recycler_forum, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);

    }

    @Override
    public int getItemCount() {
        return listaForumTema.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtPitanje;
        private TextView txtBrojOdgovora;
        private TextView txtPostavio;
        private TextView txtBrojOdgovoraLekara;

        private ForumTeme tema;

        public ListViewHolder(View itemView) {
            super(itemView);
            txtPitanje = itemView.findViewById(R.id.txtForumPitanje);
            txtBrojOdgovora = itemView.findViewById(R.id.txtForumOdgovori);
            txtBrojOdgovoraLekara = itemView.findViewById(R.id.txtForumOdgovoriLekara);
            txtPostavio = itemView.findViewById(R.id.txtForumPostavio);
            itemView.setOnClickListener(this);

        }

        public void bindView(int position) {
            tema = listaForumTema.get(position);
            txtPostavio.setText(tema.getPostavio() + "\n" + tema.getDatum());
            txtBrojOdgovora.setText("Ukupno odgovora: " + (tema.getOdgovori().size()));
            txtPitanje.setText(tema.getPitanje());
            int brojOdgovoraLekara=0;
            for (Odgovor o: tema.getOdgovori()
                 ) {
                if(o.isDoktor()) brojOdgovoraLekara++;
            }
            txtBrojOdgovoraLekara.setText(brojOdgovoraLekara+" odgovora lekara");

        }

        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ForumPitanjeActivity.class);
            intent.putExtra("Pitanje", tema);
            view.getContext().startActivity(intent);

        }


    }

}
