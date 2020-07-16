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

public class PapanikolauTest extends Activity {

    Button ok;
    EditText txtTest;
    Test test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papanikolau_test);
        test=(Test)getIntent().getSerializableExtra("test");

        if(test==null) test=new Test();
        txtTest=findViewById(R.id.edTestPapa);
        if(test.getListaParametara().size()!=0){
            txtTest.setText(test.getListaParametara().get(0));
        }

        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width= dm.widthPixels;
        int height= dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.3));

        ok= findViewById(R.id.buttonPapaOK);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test.setNaziv("Papanikolau test");
                if(test.getListaParametara().size()==0){
                    test.getListaParametara().add(txtTest.getText().toString());
                }
                else{
                    test.getListaParametara().set(0, txtTest.getText().toString());
                }

                DodajPregled.papa=test;
                finish();
            }
        });


    }

}
