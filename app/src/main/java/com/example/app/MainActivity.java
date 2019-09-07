package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Session session;

    TextView un;
    TextView pw;

    String name;
    String password;

    Button login;
    Button register;

    String un_1;
    String pw_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new Session(getApplicationContext());

        un=findViewById(R.id.un_log);
        pw=findViewById(R.id.pw_log);
        login=findViewById(R.id.login_log);
        register=findViewById(R.id.reg_log);



    }

    @Override
    protected void onResume() {
        super.onResume();

        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                un_1=un.getText().toString();
                pw_1=pw.getText().toString();


                DatabaseReference logindf = FirebaseDatabase.getInstance().getReference().child("User").child(un_1);
                logindf.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChildren()){
                            name=dataSnapshot.child("username").getValue().toString();
                            password=dataSnapshot.child("password").getValue().toString();
                            System.out.println(name);
                            System.out.println(password);
                            System.out.println(pw_1);
                            Intent i = new Intent(MainActivity.this, menuAct.class);
                            if(pw_1.equals(password)) {

                                session.setusename(un_1);

                                i.putExtra("userNameMsg",un_1);
                                startActivity(i);
                            }else{
                                Toast.makeText(getApplicationContext(),"Invalid User Name or Password", Toast.LENGTH_LONG).show();


                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, RegisterAct.class);
                startActivity(i1);
            }
        });

    }
}
