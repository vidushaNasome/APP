package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Payment extends AppCompatActivity {
    Button btCash,btCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);

        btCash=findViewById(R.id.btnCash);
        btCard=findViewById(R.id.btnCard);

    }

    @Override
    protected void onResume() {
        super.onResume();
        btCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Payment.this,CashDelivery.class);
                startActivity(intent1);
            }
        });
        btCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(Payment.this,CardDelivery.class);
                startActivity(intent2);
            }
        });

    }
}
