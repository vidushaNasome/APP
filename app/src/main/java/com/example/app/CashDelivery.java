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
    cash cashs;

    DatabaseReference dbRef;
    DatabaseReference upRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_delivery);

        dbRef = FirebaseDatabase.getInstance().getReference("cashs");

        txtName = findViewById(R.id.txtyourname);
        txtContact = findViewById(R.id.txtphone);
        txtaddress = findViewById(R.id.txtadress);
        txtcity = findViewById(R.id.txtcity);

        etName = findViewById(R.id.edName);
        etPhone = findViewById(R.id.edPhone);
        etAddress = findViewById(R.id.edAddress);
        etCity = findViewById(R.id.edCity);


        btnConfirm = findViewById(R.id.btnconfirm);

        cashs = new cash();

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCash();
            }
        });

    }

    private void addCash() {
        dbRef = FirebaseDatabase.getInstance().getReference().child("cash");
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

                dbRef.push().setValue(cashs);

                Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                clearControls();
            }

        } catch (NumberFormatException ex) {
            Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_SHORT).show();

        }

    }

    private void clearControls() {
        etName.setText("");
        etPhone.setText("");
        etAddress.setText("");
        etCity.setText("");
    }

    public void customDialog(View view) {
        final AlertDialog.Builder alert = new AlertDialog.Builder(CashDelivery.this);
        View mview = getLayoutInflater().inflate(R.layout.update_dialog, null);

        final EditText etname = mview.findViewById(R.id.edName);
        final EditText etphone = mview.findViewById(R.id.edPhone);
        final EditText etadd = mview.findViewById(R.id.edAddress);
        final EditText etcty = mview.findViewById(R.id.edCity);

        Button btnUpdate = mview.findViewById(R.id.btnEdit);
        Button btnDelete = mview.findViewById(R.id.btnDelete);

        alert.setView(mview);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        upRef = FirebaseDatabase.getInstance().getReference().child("cash");
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iName = etname.getText().toString();
                String iPhone = etphone.getText().toString();
                String iAddress = etadd.getText().toString();
                String iCity = etcty.getText().toString();
                editData(iName, iPhone, iAddress, iCity);
            }
        });
        alertDialog.show();
    }

    private void editData(String strName, final String strPhone, final String strAddress, final String strCity){
        Query editQuery=upRef.orderByChild("etname").equalTo(strName);
        editQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot edtData:dataSnapshot.getChildren()){
                    edtData.getRef().child("content").setValue(strPhone);
                    edtData.getRef().child("content").setValue(strAddress);
                    edtData.getRef().child("content").setValue(strCity);
                }
                Toast.makeText(getApplicationContext(), "Data Update Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}