package elab3.com.buducamama2.Majka;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.R;

public class AdapterZaRecyclerParametr extends RecyclerView.Adapter{

    public AdapterZaRecyclerParametr(List<Parametri> listaParametara) {
        this.listaParametara=listaParametara;
    }

    public List<Parametri> getListaParametara() {
        return listaParametara;
    }

    public void setListaMajki(List<Parametri> listaParametara) {
        this.listaParametara = listaParametara;
    }

    List<Parametri> listaParametara;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recycler_parametri, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return listaParametara.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView kilaza;
        private TextView pritisak;
        private TextView raspolozenje;
        private TextView datum;
        private TextView temperatura;
        private Parametri parametar;

        public ListViewHolder(View itemView){
            super(itemView);
            kilaza=(TextView) itemView.findViewById(R.id.txtkilaza);
            pritisak=(TextView) itemView.findViewById(R.id.txtpritisak);
            raspolozenje=(TextView)itemView.findViewById(R.id.txtraspolozenje);
            datum=(TextView)itemView.findViewById(R.id.txtdatumparametra);
            temperatura=(TextView) itemView.findViewById(R.id.txttemp);
            itemView.setOnClickListener(this);

        }
        public void bindView(int position){
            parametar=listaParametara.get(position);
            temperatura.setText(parametar.getTemperatura()+"");
            datum.setText(parametar.getDatumPrikaz());
            pritisak.setText(parametar.getPritisak());
            kilaza.setText(parametar.getKilaza()+"");
            raspolozenje.setText(parametar.getRaspolozenje());


        }
        public void onClick(View view){
            notifyDataSetChanged();
        }


    }
}
