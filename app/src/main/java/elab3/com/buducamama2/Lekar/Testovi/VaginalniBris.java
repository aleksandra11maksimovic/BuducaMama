package elab3.com.buducamama2.Lekar.Testovi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import elab3.com.buducamama2.Lekar.DodajPregled;
import elab3.com.buducamama2.Lekar.ListaPregleda;
import elab3.com.buducamama2.R;

public class VaginalniBris extends Activity {

    EditText grupa;
    EditText napomena;
    Test test;
    Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaginalni_bris);

        grupa=findViewById(R.id.edGrupaVag);
        napomena=findViewById(R.id.edNapomenaVag);
        test=(Test)getIntent().getSerializableExtra("test");
        if(test==null) test=new Test();
        if(test.getListaParametara().size()!=0){
            grupa.setText(test.getListaParametara().get(0));
            napomena.setText(test.getListaParametara().get(1));
        }
        ok=findViewById(R.id.buttonVaginalniOK);


        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width= dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.5));
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test.setNaziv("Vaginalni bris");
                ArrayList<String> listaParam=new ArrayList<>();
                listaParam.add(grupa.getText().toString());
                listaParam.add(napomena.getText().toString());
                test.setListaParametara(listaParam);
                DodajPregled.vaginalniBris=test;
                finish();
            }
        });
    }
}
