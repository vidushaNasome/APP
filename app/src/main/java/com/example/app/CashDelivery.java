package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class CashDelivery extends AppCompatActivity {
    TextView dlist;
    TextView txtName,txtContact,txtaddress,txtcity;
    EditText etName,etPhone,etAddress,etCity;
    Button btnConfirm;
    Button btnSearch;
    ListView listViewcash;

    cash cashs;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_delivery);

        dbRef= FirebaseDatabase.getInstance().getReference("cashs");

        txtName=findViewById(R.id.txtyourname);
        txtContact=findViewById(R.id.txtphone);
        txtaddress=findViewById(R.id.txtadress);
        txtcity=findViewById(R.id.txtcity);

        etName=findViewById(R.id.edName);
        etPhone=findViewById(R.id.edPhone);
        etAddress=findViewById(R.id.edAddress);
        etCity=findViewById(R.id.edCity);

        btnConfirm=findViewById(R.id.btnconfirm);
        btnSearch=findViewById(R.id.btnsearch);
        dlist=findViewById(R.id.txtAll);

        cashs=new cash();

        dlist.setText("Item Name:"+getIntent().getStringExtra("msg11")+"\n"+"Price:"+getIntent().getStringExtra("msg22")+"\n"+"Quantity"+getIntent().getStringExtra("msg33"));


        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCash();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetails();
            }
        });

    }

    private void addCash(){
        dbRef=FirebaseDatabase.getInstance().getReference().child("cash");

        try{
            if (TextUtils.isEmpty(etName.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter your name",Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(etAddress.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter a address",Toast.LENGTH_SHORT).show();
            else if(TextUtils.isEmpty(etCity.getText().toString()))
                Toast.makeText(getApplicationContext(),"Please enter a City",Toast.LENGTH_SHORT).show();
            else{
                cashs.setCusName(etName.getText().toString().trim());
                cashs.setCusAddress(etAddress.getText().toString().trim());
                cashs.setCusCity(etCity.getText().toString().trim());
                cashs.setCusPhone(Integer.parseInt(etPhone.getText().toString().trim()));

                dbRef.push().setValue(cashs);

                Toast.makeText(getApplicationContext(),"Data Saved Successfully",Toast.LENGTH_SHORT).show();
                clearControls();
            }

        }catch (NumberFormatException ex){
            Toast.makeText(getApplicationContext(),"Invalid Contact Number",Toast.LENGTH_SHORT).show();

        }

    }

    private void showDetails(){
        DatabaseReference readRef= (DatabaseReference) FirebaseDatabase.getInstance().getReference().child("cash").child(String.valueOf(etName));
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()){
                    etName.setText(dataSnapshot.child("cusName").getValue().toString());
                    etPhone.setText(dataSnapshot.child("cusPhone").getValue().toString());
                    etAddress.setText(dataSnapshot.child("cusAddress").getValue().toString());
                    etCity.setText(dataSnapshot.child("cusCity").getValue().toString());

                }
                else
                    Toast.makeText(getApplicationContext(),"No source to Display",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void clearControls(){
        etName.setText("");
        etPhone.setText("");
        etAddress.setText("");
        etCity.setText("");
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
