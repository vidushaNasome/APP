package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class MyAccount extends AppCompatActivity {

    Button cameraBtn,uploadBtn,DisplayD;
    ImageView imgv,photo3;
    DatabaseReference dbRef;
    User us;
    String name,img;
    TextView imagename;
    private Session session;
    String encodedImage;
    byte[] b;
    static Uri capturedImageUri=null;
    ImageButton home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        session = new Session(getApplicationContext());
        name = session.getusename();
        setContentView(R.layout.activity_my_account);
        cameraBtn=(Button)findViewById(R.id.photoBtn);
        imgv=(ImageView)findViewById(R.id.photo);

        uploadBtn=findViewById(R.id.upload);

        DisplayD=findViewById(R.id.displayDet);

        home=findViewById(R.id.bBtnMyAc);
        us=new User();

    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                Calendar cal = Calendar.getInstance();
                File file = new File(Environment.getExternalStorageDirectory(),  (cal.getTimeInMillis()+".jpg"));
                capturedImageUri = Uri.fromFile(file);
                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                i.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
                startActivityForResult(i, 0);
                }catch(Exception e){}

            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    try {
                        dbRef = FirebaseDatabase.getInstance().getReference().child("User");
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("message");
                        us.setUsername(name);
                        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                        us.setImage(encodedImage);
                        dbRef.child(name + "image").setValue(us);
                        System.out.println("image" + name);
                        Toast.makeText(getApplicationContext(), "You Have Successfully Set your Profile picture", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(MyAccount.this,Account.class);
                        startActivity(i);

                    }catch (Exception e){}





            }
        });

        DisplayD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MyAccount.this,Account.class);
                startActivity(i);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MyAccount.this, menuAct.class);
                startActivity(i1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 0) {
            //Bitmap photo = (Bitmap) data.getExtras().get("data");
            //imageView.setImageBitmap(photo);
            try {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                Bitmap bmp = MediaStore.Images.Media.getBitmap( getApplicationContext().getContentResolver(),capturedImageUri);
                imgv.setImageBitmap(bmp);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 1, stream);
                b = stream.toByteArray();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }



}
