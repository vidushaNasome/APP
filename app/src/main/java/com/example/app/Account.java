package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account extends AppCompatActivity {

    TextView username,email,password,cpassword;
    //TextView newpassword;
    ImageView img1;
    String img,name1,email1,password1;
    String currentpw;
    String name;
    private Session session;
    Button upload,update,delete;
    ImageButton home;
    DatabaseReference deleteRef;
    DatabaseReference deleteRefImage;
    DatabaseReference checkpw;
    DatabaseReference updatepwEml;
    DatabaseReference updateeml;
    User us;
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
            delete=findViewById(R.id.deleteBtn);
            update=findViewById(R.id.updateBtn);
            cpassword=findViewById(R.id.cpw);
            //newpassword=findViewById(R.id.npw);


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

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                     deleteRef=FirebaseDatabase.getInstance().getReference().child("User");
                     deleteRefImage=FirebaseDatabase.getInstance().getReference().child("User");
                    deleteRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(name)){
                                deleteRef =FirebaseDatabase.getInstance().getReference().child("User").child(name);
                                deleteRef.removeValue();

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                    deleteRefImage.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            try{
                            if(dataSnapshot.hasChild(name)){
                                deleteRefImage =FirebaseDatabase.getInstance().getReference().child("User").child(name+"image");
                                deleteRefImage.removeValue();

                            }}catch(Exception e){}
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    Toast.makeText(getApplicationContext(), "You Have Successfully Deleted Your Account!", Toast.LENGTH_LONG).show();
                    Intent newOb =new Intent(Account.this,MainActivity.class);
                    startActivity(newOb);

                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    us=new User();

                    checkpw = FirebaseDatabase.getInstance().getReference().child("User").child(name);
                    checkpw.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChildren()) {
                                currentpw = dataSnapshot.child("password").getValue().toString();
                                if(currentpw.equals(cpassword.getText().toString())){

                                        //update email and email is not null
                                        final String email10=email.getText().toString();
                                        if(Patterns.EMAIL_ADDRESS.matcher(email10).matches()){
                                        updateeml=FirebaseDatabase.getInstance().getReference().child("User");
                                        updateeml.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                us.setPassword(currentpw);
                                                us.setEmail(email10);
                                                us.setUsername(name);
                                                updateeml.child(name).setValue(us);
                                                Toast.makeText(getApplicationContext(), "You have successfully updated your Email Address!", Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });}else{
                                            Toast.makeText(getApplicationContext(), "Please enter a valid Email Address!", Toast.LENGTH_LONG).show();
                                        }




                                }else{
                                    Toast.makeText(getApplicationContext(), "Incorrect password!", Toast.LENGTH_LONG).show();
                                    Intent ik=new Intent(Account.this,Account.class);
                                    startActivity(ik);
                                }
                            }

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }
            });

        }
    }

