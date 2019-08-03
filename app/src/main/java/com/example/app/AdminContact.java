package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AdminContact extends AppCompatActivity {

    ImageButton home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_contact);


        home=(ImageButton) findViewById(R.id.adminhome);
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
