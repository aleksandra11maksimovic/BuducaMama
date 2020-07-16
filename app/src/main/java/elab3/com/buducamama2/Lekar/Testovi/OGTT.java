package elab3.com.buducamama2.Lekar.Testovi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

import elab3.com.buducamama2.Lekar.DodajPregled;
import elab3.com.buducamama2.R;

public class OGTT extends Activity {

    Button ok;
    EditText glukozaPre;
    EditText glukoza30;
    EditText glukoza60;
    EditText glukoza90;
    EditText glukoza120;
    EditText insulinPre;
    EditText insulin30;
    EditText insulin60;
    EditText insulin90;
    EditText insulin120;
    EditText napomena;
    Test test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogtt);
        glukozaPre=findViewById(R.id.edGlukozaPre);
        glukoza30=findViewById(R.id.edGlukoza30);
        glukoza60=findViewById(R.id.edGlukoza60);
        glukoza90=findViewById(R.id.edGlukoza90);
        glukoza120=findViewById(R.id.edGlukoza120);
        insulinPre=findViewById(R.id.edInsulinPre);
        insulin30=findViewById(R.id.edInsulin30);
        insulin60=findViewById(R.id.edInsulin60);
        insulin90=findViewById(R.id.edInsulin90);
        insulin120=findViewById(R.id.edInsulin120);
        napomena=findViewById(R.id.edNapomenaOgtt2);
        ok= findViewById(R.id.buttonOGTTOK);
        test=(Test)getIntent().getSerializableExtra("test");

        if(test==null) test=new Test();
        if(test.getListaParametara().size()!=0){
            glukozaPre.setText(test.getListaParametara().get(0));
            glukoza30.setText(test.getListaParametara().get(1));
            glukoza60.setText(test.getListaParametara().get(2));
            glukoza90.setText(test.getListaParametara().get(3));
            glukoza120.setText(test.getListaParametara().get(4));
            insulinPre.setText(test.getListaParametara().get(5));
            insulin30.setText(test.getListaParametara().get(6));
            insulin60.setText(test.getListaParametara().get(7));
            insulin90.setText(test.getListaParametara().get(8));
            insulin120.setText(test.getListaParametara().get(9));
            napomena.setText(test.getListaParametara().get(10));
        }



        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width= dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test.setNaziv("Ogtt");
                test.setListaParametara(new ArrayList<String>());

                ArrayList<String> listaParam = new ArrayList<>();
                listaParam.add(glukozaPre.getText().toString());
                listaParam.add(glukoza30.getText().toString());
                listaParam.add(glukoza60.getText().toString());
                listaParam.add(glukoza90.getText().toString());
                listaParam.add(glukoza120.getText().toString());
                listaParam.add(insulinPre.getText().toString());
                listaParam.add(insulin30.getText().toString());
                listaParam.add(insulin60.getText().toString());
                listaParam.add(insulin90.getText().toString());
                listaParam.add(insulin120.getText().toString());
                listaParam.add(napomena.getText().toString());
                test.setListaParametara(listaParam);
                DodajPregled.ogtt=test;
                finish();


            }
        });
    }
}
