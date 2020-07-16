package elab3.com.buducamama2.Lekar.Testovi;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import elab3.com.buducamama2.Lekar.DodajPregled;
import elab3.com.buducamama2.R;

public class DoubleTest extends Activity {

    Button ok;
    Test test;
    EditText pappa;
    EditText beta;
    EditText t21;
    EditText t13;
    EditText rizikGodine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_test);

        test=(Test)getIntent().getSerializableExtra("test");


        pappa=findViewById(R.id.edPAPP_a);
        beta=findViewById(R.id.edFree);
        t21=findViewById(R.id.ed21);
        t13=findViewById(R.id.ed13);
        rizikGodine=findViewById(R.id.edgodine);
        if(test==null) test=new Test();
        if (test.getListaParametara().size()!=0){
            pappa.setText(test.getListaParametara().get(0));
            beta.setText(test.getListaParametara().get(1));
            t21.setText(test.getListaParametara().get(2));
            t13.setText(test.getListaParametara().get(3));
            rizikGodine.setText(test.getListaParametara().get(4));


        }



        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width= dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.5));


        ok= findViewById(R.id.buttonDoubleOK);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test.setNaziv("Double test");
                if(test.getListaParametara().size()==0){
                    test.getListaParametara().add(pappa.getText().toString());
                    test.getListaParametara().add(beta.getText().toString());
                    test.getListaParametara().add(t21.getText().toString());
                    test.getListaParametara().add(t13.getText().toString());
                    test.getListaParametara().add(rizikGodine.getText().toString());
                }
                test.getListaParametara().set(0,pappa.getText().toString());
                test.getListaParametara().set(1,beta.getText().toString());
                test.getListaParametara().set(2,t21.getText().toString());
                test.getListaParametara().set(3,t13.getText().toString());
                test.getListaParametara().set(4,rizikGodine.getText().toString());
                DodajPregled.dabl=test;
                finish();
            }
        });

    }

}
