package elab3.com.buducamama2.Majka.FAQ;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import elab3.com.buducamama2.Majka.Majka;
import elab3.com.buducamama2.R;

public class AdapterZaRecyclerFAQ extends RecyclerView.Adapter{

    public AdapterZaRecyclerFAQ(ArrayList<Pitanja> listaPitanja) {
        this.listaPitanja=listaPitanja;
    }

    public List<Pitanja> getListaParametara() {
        return listaPitanja;
    }

    public void setListaMajki(ArrayList<Pitanja> listaPitanja) {
        this.listaPitanja = listaPitanja;
    }

    ArrayList<Pitanja> listaPitanja;
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recycler_faq, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((ListViewHolder) viewHolder).bindView(i);
    }

    @Override
    public int getItemCount() {
        return listaPitanja.size();
    }

    private class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView txtPitanje;
        private TextView txtOdgovor;
        Pitanja pitanja=new Pitanja("","");


        public ListViewHolder(View itemView){
            super(itemView);
            txtPitanje=(TextView) itemView.findViewById(R.id.txtPitanje);
            txtOdgovor=(TextView) itemView.findViewById(R.id.txtOdgovor);

        }
        public void bindView(int position){
            pitanja=listaPitanja.get(position);
            txtPitanje.setText(pitanja.getPitanje());
            txtOdgovor.setText(pitanja.getOdgovor());

            txtPitanje.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!pitanja.isOtvoreno()){
                        txtPitanje.setTypeface(null, Typeface.BOLD);
                        txtOdgovor.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                        pitanja.setOtvoreno(true);

                    }else{
                        pitanja.setOtvoreno(false);
                        txtOdgovor.setTextSize(TypedValue.COMPLEX_UNIT_SP,0);
                        txtPitanje.setTypeface(null,Typeface.NORMAL);

                    }
                }
            });



        }
        public void onClick(View view){
            notifyDataSetChanged();

        }


    }
}
