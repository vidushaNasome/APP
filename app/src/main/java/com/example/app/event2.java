package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class event2 extends AppCompatActivity {

    String o1,o2;
    TextView slctType,ttpri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event2);

        Intent i=getIntent();
        o1=i.getStringExtra("op1");
        System.out.println("hhhhhhhhhhhhhhhhh"+o1);

        slctType=findViewById(R.id.selectType);
        ttpri=findViewById(R.id.totprice);

        try {
            if (o1.equals("ab")) {
                System.out.print("hello 1");
                slctType.setText("Congratulations! You Have Selected PLATINUM package.");
                ttpri.setText("Package Total Amount is Rs.150,000 (including VAT)");

            } else {
                System.out.print("hello 2");
                slctType.setText("Congratulations! You Have Selected GOLD package.");
                ttpri.setText("Package Total Amount is Rs.150,000 (including VAT)");
            }
        }catch(Exception e){}
    }

    @Override
    protected void onResume() {
        super.onResume();




    }
}
