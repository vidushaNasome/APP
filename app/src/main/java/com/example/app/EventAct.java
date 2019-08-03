package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;

public class EventAct extends AppCompatActivity implements View.OnClickListener {

    Button bt1,bt2,bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        bt1=findViewById(R.id.buttonevhome);
        bt2=findViewById(R.id.party11);
        bt3=findViewById(R.id.party22);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i1=new Intent(EventAct.this,event2.class);

        switch (view.getId()){
            case(R.id.buttonevhome):Intent i=new Intent(EventAct.this,menuAct.class);
                                    startActivity(i);
                                    break;

            case(R.id.party11): i1.putExtra("opt1","platinum");
                                startActivity(i1);
                                break;

            case(R.id.party22): i1.putExtra("opt2","gold");
                                startActivity(i1);
                                break;
        }
    }
}
