package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class checkout extends AppCompatActivity {
    TextView list;
    Button btnAdd;
    Button btnpayment;
    String name9;
    String price9;
    public String quntity9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        list=findViewById(R.id.txtList);
        btnpayment=findViewById(R.id.btncheck);

        btnAdd=findViewById(R.id.btnAdd);


        Intent i=getIntent();
        name9=i.getStringExtra("msg11");
        price9=i.getStringExtra("msg22");
        quntity9=i.getStringExtra("msg33");

        list.setText("Item Name:"+getIntent().getStringExtra("msg11")+"\n"+"Price:Rs"+getIntent().getStringExtra("msg22")+".00"+"\n"+getIntent().getStringExtra("msg33"));

    }

    @Override
    protected void onResume() {
        super.onResume();
        btnpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(checkout.this,Payment.class);
                i.putExtra("msg11", name9);
                i.putExtra("msg22", price9);
                i.putExtra("msg33",quntity9);
                startActivity(i);
            }
        });
    }
}
