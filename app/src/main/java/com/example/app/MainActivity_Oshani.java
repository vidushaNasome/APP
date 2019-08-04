package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_Oshani extends AppCompatActivity {
    Button btnPlus,payment;
    TextView name3;
    TextView price3;
    String name1;
    String price1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__oshani);
        btnPlus=findViewById(R.id.btnContinue);
        payment=findViewById(R.id.btnPay);


        name3=(TextView)findViewById(R.id.nameid2);
        price3=(TextView)findViewById(R.id.priceid2);

        Intent i=getIntent();
        name1=i.getStringExtra("msg11");
        price1=i.getStringExtra("msg22");

        name3.setText(name1);
        price3.setText(price1);


    }

    @Override
    protected void onResume() {
        super.onResume();

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity_Oshani.this,MenuActivity.class);
                startActivity(intent);
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity_Oshani.this,Payment.class);
                startActivity(intent);
            }
        });
    }
}
