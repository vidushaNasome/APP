package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class productDetails extends AppCompatActivity {
    String pName,pPrice;
    DatabaseReference dbRef;
    TextView name,price;
    Integer Price12;
    Button add;
    Product pt;
    Button gotopro;

    private void clearControls(){
        name.setText("");
        price.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        gotopro=findViewById(R.id.shv);
        name=findViewById(R.id.txtName);
        price=findViewById(R.id.txtPrice);
        //pId=findViewById(R.id.pId);
        add=findViewById(R.id.btnAdd);
        pt=new Product();
    }

    @Override
    protected void onResume() {
        super.onResume();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                pName=name.getText().toString();
                pPrice=price.getText().toString();
                Price12=Integer.parseInt(pPrice);


                if(pName.isEmpty()|| pPrice.isEmpty())
                    Toast.makeText(getApplicationContext(), "Please Input Values For Empty Fields", Toast.LENGTH_LONG).show();
                else {

                    dbRef= FirebaseDatabase.getInstance().getReference().child("Item");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("message");


                    pt.setName(pName);
                    pt.setPrice(Price12);

                    dbRef.child(pName).setValue(pt);
                    Toast.makeText(getApplicationContext(), "You have Successfully added", Toast.LENGTH_LONG).show();
                    clearControls();

                }

            }
        });

        gotopro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(productDetails.this,showProducts.class);
                startActivity(i);
            }
        });
    }
}
