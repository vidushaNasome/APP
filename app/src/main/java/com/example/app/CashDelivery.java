package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class CashDelivery extends AppCompatActivity {
    Button confirmCash;
    TextView name4,subTot;
    TextView dilCharge;
    String oname,nTot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_delivery);
        confirmCash=findViewById(R.id.btnconcd);

        name4=(TextView)findViewById(R.id.oName);
        subTot=(TextView)findViewById(R.id.netTot1);

        Intent i=getIntent();
        oname=i.getStringExtra("msg11");
        nTot=i.getStringExtra("Net Total");


        name4.setText(nTot);
        subTot.setText(nTot);

    }

    public void open(View view){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure,Confirm this Order");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(CashDelivery.this,"You successfully confirm order",Toast.LENGTH_LONG).show();
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
