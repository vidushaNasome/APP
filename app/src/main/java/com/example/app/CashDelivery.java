package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CashDelivery extends AppCompatActivity {
    TextView txtName, txtContact, txtaddress, txtcity;
    EditText etName, etPhone, etAddress, etCity;
    Button btnConfirm;
    Button btncustomise;
    public String name;
    cash cashs;
    Session session;
    public String user;

    //Database References
    DatabaseReference dbRef;
    DatabaseReference upRef;
    DatabaseReference shRef;
    DatabaseReference deleteRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_delivery);

        session = new Session(getApplicationContext());
        user = session.getusename();

        txtName = findViewById(R.id.txtyourname);
        txtContact = findViewById(R.id.txtphone);
        txtaddress = findViewById(R.id.txtadress);
        txtcity = findViewById(R.id.txtcity);

        etName = findViewById(R.id.edName);
        etPhone = findViewById(R.id.edPhone);
        etAddress = findViewById(R.id.edAddress);
        etCity = findViewById(R.id.edCity);

        btncustomise = findViewById(R.id.btnCustomise);
        btnConfirm = findViewById(R.id.btnconfirm);

        etName.setText(user);

        cashs = new cash();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCash();
            }
        });
    }
    //Insert method Implementation
    private void addCash() {
        dbRef = FirebaseDatabase.getInstance().getReference().child("cash");

        name = etName.getText().toString();

        try {
            if (TextUtils.isEmpty(etName.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(etAddress.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter a address", Toast.LENGTH_SHORT).show();
            else if (TextUtils.isEmpty(etCity.getText().toString()))
                Toast.makeText(getApplicationContext(), "Please enter a City", Toast.LENGTH_SHORT).show();
            else {
                cashs.setCusName(etName.getText().toString().trim());
                cashs.setCusAddress(etAddress.getText().toString().trim());
                cashs.setCusCity(etCity.getText().toString().trim());
                cashs.setCusPhone(Integer.parseInt(etPhone.getText().toString().trim()));

                //dbRef.push().setValue(cashs);
                dbRef.child(name).setValue(cashs);

                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                clearControls();
            }

        } catch (NumberFormatException ex) {
            Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();

        }

    }
    //clear values
    private void clearControls() {
        etName.setText("");
        etPhone.setText("");
        etAddress.setText("");
        etCity.setText("");
    }

    //Dialog box for edit and delete records
    public void customiseDialog(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(CashDelivery.this);
        View mview = getLayoutInflater().inflate(R.layout.update_dialog, null);

        final EditText etname = mview.findViewById(R.id.edName);
        final EditText etphone = mview.findViewById(R.id.edPhone);
        final EditText etadd = mview.findViewById(R.id.edAddress);
        final EditText etcty = mview.findViewById(R.id.edCity);

        Button btnUpdate = mview.findViewById(R.id.btnEdit);
        Button btnDelete = mview.findViewById(R.id.btnDelete);
        Button btnDidplay= mview.findViewById(R.id.btnShow);
        alert.setView(mview);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        //Retrieve data
        btnDidplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shRef = FirebaseDatabase.getInstance().getReference().child("cash").child(user);
                shRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        try {
                            if (dataSnapshot.hasChildren()) {
                                etname.setText(dataSnapshot.child("cusName").getValue().toString());
                                etphone.setText(dataSnapshot.child("cusPhone").getValue().toString());
                                etadd.setText(dataSnapshot.child("cusAddress").getValue().toString());
                                etcty.setText(dataSnapshot.child("cusCity").getValue().toString());
                            } else
                                Toast.makeText(getApplicationContext(), "No Source to Display", Toast.LENGTH_SHORT).show();

                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        //Edit Data
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upRef=FirebaseDatabase.getInstance().getReference().child("cash");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(user)){
                            try{
                                cashs.setCusName(etname.getText().toString().trim());
                                cashs.setCusAddress(etadd.getText().toString().trim());
                                cashs.setCusCity(etcty.getText().toString().trim());
                                cashs.setCusPhone(Integer.parseInt(etphone.getText().toString().trim()));

                                dbRef=FirebaseDatabase.getInstance().getReference().child("cash").child(user);
                                dbRef.setValue(cashs);
                                clearControls();

                                Toast.makeText(getApplicationContext(),"Data Updated Successfully",Toast.LENGTH_SHORT).show();

                            }catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(), "No source to Display", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        //Delete data
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRef=FirebaseDatabase.getInstance().getReference().child("cash");
                deleteRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(user)){
                            dbRef=FirebaseDatabase.getInstance().getReference().child("cash").child(user);
                            dbRef.removeValue();
                            clearControls();

                            Toast.makeText(getApplicationContext(),"Data Deleted Successfull",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No source to Delete",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        alertDialog.show();
    }

}