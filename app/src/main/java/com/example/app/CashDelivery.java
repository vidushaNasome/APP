package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

public class CashDelivery extends AppCompatActivity {
    Button confirmCash;
    EditText t1,t2,t3,t4;
    String s1,s2,s3,s4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_delivery);
        confirmCash=findViewById(R.id.btnconcd);
        t1=(EditText)findViewById(R.id.cName);
        t2=(EditText)findViewById(R.id.cPhone);
        t3=(EditText)findViewById(R.id.cAddress);
        t4=(EditText)findViewById(R.id.cCity);

    }

    @Override
    protected void onResume() {
        super.onResume();
        confirmCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1=t1.getText().toString();
                s2=t2.getText().toString();
                s3=t3.getText().toString();
                s4=t4.getText().toString();

                Intent i=new Intent(CashDelivery.this,DisplayCash.class);

                i.putExtra("Customer Name",s1);
                i.putExtra("Phone Number",s2);
                i.putExtra("Home Address",s3);
                i.putExtra("City Name",s4);

                startActivity(i);
            }
        });

    }

    public void displayToast(String msge)
    {
        Context context=getApplicationContext();
        CharSequence text="Order confirmation Successfull";
        int duration=Toast.LENGTH_SHORT;
        Toast toast=Toast.makeText(context,text,duration);
        toast.show();
        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,0);

    }
}
