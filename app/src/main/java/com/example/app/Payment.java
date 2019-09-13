package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Payment extends AppCompatActivity {
    Button btCash,btCard;
    TextView name8;
    TextView price8;
    TextView qunt8;
    String name9;
    String price9;
    String qunt9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);

        btCash=findViewById(R.id.btnCash);
        btCard=findViewById(R.id.btnCard);

        name8=(TextView)findViewById(R.id.nameid8);
        price8=(TextView)findViewById(R.id.priceid8);
        qunt8=(TextView)findViewById(R.id.quntid8);

        Intent i=getIntent();
        name9=i.getStringExtra("msg11");
        price9=i.getStringExtra("msg22");
        qunt9=i.getStringExtra("msg33");

        name8.setText(name9);
        price8.setText(price9);
        qunt8.setText(qunt9);

    }

    @Override
    protected void onResume() {
        super.onResume();
        btCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(Payment.this,CashDelivery.class);
                intent1.putExtra("msg11", name8.getText().toString());
                intent1.putExtra("msg22", price8.getText().toString());
                intent1.putExtra("msg33", qunt8.getText().toString());
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
