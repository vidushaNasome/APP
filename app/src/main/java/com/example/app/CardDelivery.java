package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

        Intent i=getIntent();
        fdName=i.getStringExtra("Food Name");
        subTot=i.getStringExtra("Net Total");


        txtName.setText(fdName);
        txtTotal.setText(subTot);
    }
    public void open(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure,Confirm this Order");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(CardDelivery.this,"You successfully confirm order",Toast.LENGTH_LONG).show();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
