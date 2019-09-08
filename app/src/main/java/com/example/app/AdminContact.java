package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminContact extends AppCompatActivity {

    DatabaseReference fbDb = null;
    ImageButton home;
    TextView count1;
    String cn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_contact);
        home=(ImageButton) findViewById(R.id.adminhome);
        count1=(TextView) findViewById(R.id.count);

        if (fbDb == null) {
            fbDb = FirebaseDatabase.getInstance().getReference();
        }


        fbDb.child("User")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // get total available quest
                        int size = (int) dataSnapshot.getChildrenCount();
                        System.out.println("count"+size);
                        cn=String.valueOf(size);
                        count1.setText("Number of Acoounts \n "+cn);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });




    }


    @Override
    protected void onResume() {
        super.onResume();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminContact.this,menuAct.class);
                startActivity(i);

            }
        });

    }
}
