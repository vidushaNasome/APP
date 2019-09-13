package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class AdminUpdate extends AppCompatActivity {

    public String name;
    public String price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update);

        Intent i=getIntent();
        name=i.getStringExtra("msg1");
        price=i.getStringExtra("msg2");

    }
}
