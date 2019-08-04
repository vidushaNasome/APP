package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class custom extends AppCompatActivity {

    int tot;
    CheckBox ch;
    Button bt1;
    TextView name;
    TextView price;
    int pr;
    String nm;
    String pr1;
    String totst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        bt1=findViewById(R.id.addtocartbtn);
        name=(TextView) findViewById(R.id.pizza1);
        price=(TextView)findViewById(R.id.price1);
        ch=(CheckBox) findViewById(R.id.checSmall);

        Intent i3=getIntent();
        nm = i3.getStringExtra("msg1");
        pr1 = i3.getStringExtra("msg2");
        try {
            pr = Integer.parseInt(pr1);
            name.setText(nm);
            price.setText("Rs "+pr1+".00");
        }catch(Exception e){}
    }

    @Override
    protected void onResume() {
        super.onResume();
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(custom.this,MainActivity_Oshani.class);
                i.putExtra("msg11",nm);

                totst=String.valueOf(tot);
                i.putExtra("msg22",totst);
                startActivity(i);


            }
        });

        ch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tot=pr+200;

                pr1=String.valueOf(tot);
                price.setText(pr1);
            }
        });
    }
}
