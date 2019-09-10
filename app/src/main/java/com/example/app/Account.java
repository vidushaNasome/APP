package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account extends AppCompatActivity {

    TextView username,email,password;
    ImageView img1;
    String img,name1,email1,password1;
    String name;
    private Session session;
    Button upload;
    ImageButton home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

            session = new Session(getApplicationContext());
            name = session.getusename();
            username = (TextView) findViewById(R.id.unid);
            email = findViewById(R.id.emailid);
            img1 = findViewById(R.id.photoD);
            upload = findViewById(R.id.btupload);
            home=(ImageButton)findViewById(R.id.hbtnAcc);


            DatabaseReference logindf = FirebaseDatabase.getInstance().getReference().child("User").child(name + "image");

            logindf.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    try{
                    if (dataSnapshot.hasChildren()) {
                        img = dataSnapshot.child("image").getValue().toString();
                        upload.setVisibility(View.GONE);
                    }

                    byte[] decodedString = Base64.decode(img, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    img1.setImageBitmap(decodedByte);
                    }catch (Exception e){}
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            try{
            DatabaseReference displayDf = FirebaseDatabase.getInstance().getReference().child("User").child(name);
            displayDf.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChildren()) {
                        name1 = dataSnapshot.child("username").getValue().toString();
                        email1 = dataSnapshot.child("email").getValue().toString();
                        password1 = dataSnapshot.child("password").getValue().toString();
                        username.setText(name1);
                        email.setText(email1);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });}catch(Exception e){}
        }

        @Override
        protected void onResume () {
            super.onResume();

            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Account.this, MyAccount.class);
                    startActivity(i);
                }
            });

            home.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i1 = new Intent(Account.this, menuAct.class);
                    startActivity(i1);
                }
            });
        }
    }

