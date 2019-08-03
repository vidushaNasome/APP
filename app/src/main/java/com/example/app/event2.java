package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class event2 extends AppCompatActivity {

    String o1,o2;
    TextView slctType,ttpri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event2);

        Intent i1=getIntent();
        o1=i1.getStringExtra("opt1");
        o2=i1.getStringExtra("opt2");

        slctType=(TextView) findViewById(R.id.selectType);
        ttpri=(TextView) findViewById(R.id.totprice);

        try {
            if (o1.equals("platinum")) {

                slctType.setText("Congratulations! You Have Selected PLATINUM package.");
                ttpri.setText("Package Total Amount is Rs.150,000 (+including VAT)");


            }
            if(o2.equals("gold")) {

                slctType.setText("Congratulations! You Have Selected GOLD package.");
                ttpri.setText("Package Total Amount is Rs.150,000 (+including VAT)");
            }
        }catch(Exception e){}
    }

    @Override
    protected void onResume() {
        super.onResume();




    }
}
