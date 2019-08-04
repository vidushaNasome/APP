package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CardDelivery extends AppCompatActivity {
    TextView txtName,txtTotal;
    String fdName;
    String subTot;
    Button card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_delivery);

        txtName=(TextView)findViewById(R.id.txtCname);
        txtTotal=(TextView)findViewById(R.id.txtSum);


    }

    @Override
    protected void onResume() {
        super.onResume();
        fdName=txtName.getText().toString();
        subTot=txtTotal.getText().toString();
        getIntent().putExtra("Food Name",fdName);
        getIntent().putExtra("Sub Total",subTot);
    }
}
