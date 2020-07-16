package elab3.com.buducamama2.Majka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import elab3.com.buducamama2.R;

public class MojLekar extends AppCompatActivity {
    private Majka majka;
    TextView txtImeL;
    TextView txtPrezimeL;
    TextView txtUsername;
    TextView txtEmail;
    private EditText etSubject;
    private EditText etTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moj_lekar);
        majka=(Majka)getIntent().getSerializableExtra("Majka");
        txtPrezimeL=findViewById(R.id.txtPrezimeLekara2);
        txtImeL=findViewById(R.id.txtImeLekar2);
        txtUsername=findViewById(R.id.txtUsernameLekara2);
        txtEmail=findViewById(R.id.txtemaillekara);
        txtPrezimeL.setText(majka.getLekar().getPrezime());
        txtImeL.setText(majka.getLekar().getIme());
        txtUsername.setText(majka.getLekar().getUsername());
        txtEmail.setText(majka.getLekar().getEmail());
        etSubject=findViewById(R.id.edSubject);
        etTextEmail=findViewById(R.id.editTextEmail);
        Button send=findViewById(R.id.posaljiEmail);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                posaljiMail();
            }
        });




    }

    private void posaljiMail() {
        String kome=txtEmail.getText().toString();
        String subject=etSubject.getText().toString();
        String content=etTextEmail.getText().toString();
        String[] komefin=new String[1];
        komefin[0]=kome;
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,komefin);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,content);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Izaberite:"));
    }


}
