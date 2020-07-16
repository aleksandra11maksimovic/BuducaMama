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
import elab3.com.buducamama2.R;

public class Amniocenteza extends Activity {

    Test test;
    Button ok;
    EditText rezultati;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amniocenteza);
        ok= findViewById(R.id.buttonAmnio);
        rezultati=findViewById(R.id.edRezultatiAmnio);
        test=(Test)getIntent().getSerializableExtra("test");
        if(test==null) test=new Test();
        if(test.getListaParametara().size()!=0){
            rezultati.setText(test.getListaParametara().get(0));
        }


        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width= dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.4));
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test.setNaziv("Amniocenteza");
                ArrayList<String> listaParam=new ArrayList<>();
                listaParam.add(rezultati.getText().toString());
                test.setListaParametara(listaParam);
                DodajPregled.amniocenteza=test;
                finish();
            }
        });

    }
}
