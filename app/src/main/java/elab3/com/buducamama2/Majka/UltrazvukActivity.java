package elab3.com.buducamama2.Majka;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import elab3.com.buducamama2.R;

public class UltrazvukActivity extends AppCompatActivity {

    FirebaseStorage storage;
    StorageReference storageRef;
    ImageButton btnTake;
    String pathToFile;
    ImageView imageView;
    ImageView galerija;
    ImageButton btnGallery;
    int brojSlika;
    ConstraintLayout cl;
    int brojacSlike=1;
    Button btnDodaj;
    Uri imageUri;
    private static final int PICK_IMAGE=100;
    Majka majka;
    ImageButton buttonLeft;
    ImageButton buttonRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultrazvuk);
        cl=(ConstraintLayout)findViewById(R.id.constraintLayout3);
        majka=(Majka) getIntent().getSerializableExtra("Majka");
        brojSlika=majka.getBrojSlikaUltrazvuka();
        btnTake=findViewById(R.id.buttonTacePic);
        buttonLeft=(ImageButton) findViewById(R.id.buttonLeft);
        buttonRight=(ImageButton) findViewById(R.id.buttonRight);
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pomeriUlevoSliku();
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pomeriUdesnoSliku();
            }
        });
        galerija=findViewById(R.id.imageView5);
        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }
        btnTake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchPictureTakerAction();
            }
        });
        imageView=findViewById(R.id.uploadImage);

        //region galerija
        btnGallery=(ImageButton)findViewById(R.id.buttonGalerija);
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        //endregion
        btnDodaj=findViewById(R.id.buttonDodajSlikuUbazu);
        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dodajUBazu();
            }
        });
        if(brojSlika>0){
            prikaziSliku(1);
            buttonLeft.setEnabled(false);

        }

    }



    private void openGallery() {
        Intent gallery=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode==1){
                Bitmap bitmap = BitmapFactory.decodeFile(pathToFile);
                imageView.setImageBitmap(bitmap);
            }
            if(requestCode==PICK_IMAGE){
                imageUri=data.getData();
                imageView.setImageURI(imageUri);
            }
        }
    }

    private void dispatchPictureTakerAction() {
        Intent takePic= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePic.resolveActivity(getPackageManager())!=null){
            File photoFile=null;
            photoFile=createPhotoFile();
            if(photoFile!=null){
                pathToFile=photoFile.getAbsolutePath();
                Uri photoURI= FileProvider.getUriForFile(UltrazvukActivity.this, "com.thecodecity.cameraanroid.fileprovider",photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                startActivityForResult(takePic,1);
            }
        }
    }

    private File createPhotoFile() {
        String name=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File storageDir= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image=null;
        try {
            image=File.createTempFile(name,".jpg",storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void dodajUBazu(){
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        String ime=podesiImeSlike();
        StorageReference imagesRef = storageRef.child(ime);


        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = imagesRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(UltrazvukActivity.this, "Slika je sačuvana", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(UltrazvukActivity.this, "Čuvanje slike u toku...", Toast.LENGTH_SHORT).show();
        majka.setBrojSlikaUltrazvuka(brojSlika);
        majka.updateMama(majka);


    }
    public String podesiImeSlike(){
        String ime;
        brojSlika+=1;
        ime=majka.getUsername()+"/"+(brojSlika)+".jpg";
        return ime;
    }
    public void prikaziSliku(int brojSlike){

        StorageReference storageReference =FirebaseStorage.getInstance().getReference().child(majka.getUsername()+"/"+brojSlike+".jpg");
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                ImageView image = (ImageView)findViewById(R.id.imageView5);
                Glide.with(UltrazvukActivity.this)
                        .load(uri)
                        .into(image);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                exception.toString();
            }
        });
        Log.d("SLIKA",FirebaseStorage.getInstance().getReference().child("maja/slika.jpg").getDownloadUrl().toString() );

        ;

    }
    public void pomeriUdesnoSliku(){
        if(brojSlika>brojacSlike){
            brojacSlike++;
            prikaziSliku(brojacSlike);
            buttonLeft.setEnabled(true);
            if(brojacSlike==brojSlika){
                buttonRight.setEnabled(false);
            }
        }else{

        }
    }
    public void pomeriUlevoSliku(){
        if(brojacSlike>1){
            brojacSlike--;
            prikaziSliku(brojacSlike);
            buttonRight.setEnabled(true);
            if(brojacSlike==1){
                buttonLeft.setEnabled(false);
            }
        }
    }
    public void onPromeniSifru(View view){
        if(cl.getVisibility()==View.INVISIBLE){

            cl.setVisibility(View.VISIBLE);
        }
        else{
            cl.setVisibility(View.INVISIBLE);
        }
    }
}
