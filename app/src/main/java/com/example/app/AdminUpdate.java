package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminUpdate extends AppCompatActivity {

    TextView pName,pPrice;
    Button delete,update;
    public String name;
    public String price;
    DatabaseReference dbRef;
    Product pt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_update);

        pName=findViewById(R.id.Pname);
        pPrice=findViewById(R.id.pprice);
        delete=findViewById(R.id.btnDelete);
        update=findViewById(R.id.btnUpdate);

        Intent i = getIntent();
        name = i.getStringExtra("msg1");
        price = i.getStringExtra("msg2");

        pName.setText(name);
        pPrice.setText(price);

        pt = new Product();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("Item");
                delRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(name)) {
                            dbRef = FirebaseDatabase.getInstance().getReference().child("Item").child(name);
                            dbRef.removeValue();
                            Toast.makeText(getApplicationContext(), "Data Deleted successfully", Toast.LENGTH_SHORT).show();
                            Intent i5 = new Intent(AdminUpdate.this,showProducts.class);
                            startActivity(i5);
                        } else
                            Toast.makeText(getApplicationContext(), "No source to Delete", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference upRef = FirebaseDatabase.getInstance().getReference().child("Item");
                upRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild(name)){
                            try{
                                pt.setName(pName.getText().toString().trim());
                                pt.setPrice(Integer.parseInt(pPrice.getText().toString().trim()));

                                dbRef =FirebaseDatabase.getInstance().getReference().child("Item").child(name);
                                dbRef.setValue(pt);

                                Toast.makeText(getApplicationContext(),"Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                Intent i6 = new Intent(AdminUpdate.this,showProducts.class);
                                startActivity(i6);
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid Price",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No source to Update",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }


}
