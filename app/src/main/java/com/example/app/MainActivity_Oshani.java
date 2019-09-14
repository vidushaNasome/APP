package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity_Oshani extends AppCompatActivity {

    Button btnPlus,payment;
    Button btnQunt;
    TextView name3;
    TextView price3;
    TextView txt1;
    public String name1;
    public String price1;
    public String name11;
    public String price11;
    Session session;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__oshani);
        btnPlus=findViewById(R.id.btnContinue);
        payment=findViewById(R.id.btnPay);
        btnQunt = findViewById(R.id.btnAddQun);

        txt1 = findViewById(R.id.txtdisplayAll);
        name3=(TextView)findViewById(R.id.nameid2);
        price3=(TextView)findViewById(R.id.priceid2);

        Intent i=getIntent();
        session = new Session(getApplicationContext());
        username=session.getusename();
        name11=i.getStringExtra("msg11");
        price11=i.getStringExtra("msg22");

        DatabaseReference displayCart = FirebaseDatabase.getInstance().getReference().child("Cart").child(username);

        displayCart.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.hasChildren()) {

                        name1 = dataSnapshot.child("itemName").getValue().toString();
                        price1 = dataSnapshot.child("price").getValue().toString();

                        name3.setText(name1);
                        price3.setText(price1);


                        System.out.print("Dispaly details"+name1+price1);
                    }
                }catch (Exception e){}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }

    public void showDialog(View view){
        final AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity_Oshani.this);
        View mview=getLayoutInflater().inflate(R.layout.custom_dialog,null);

        final EditText txtInputText=mview.findViewById(R.id.txtInput);
        Button btnCancel=mview.findViewById(R.id.btnCancel);
        Button btnok=mview.findViewById(R.id.btnok);

        alert.setView(mview);

        final AlertDialog alertDialog=alert.create();
        alertDialog.setCanceledOnTouchOutside(false);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        btnok.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                txt1.setText("Quantity:"+txtInputText.getText().toString());
            }
        });

        alertDialog.show();
    }
    @Override
    protected void onResume() {
        super.onResume();

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity_Oshani.this,showProducts.class);
                startActivity(intent);
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity_Oshani.this,checkout.class);
                intent.putExtra("msg11", name3.getText().toString());
                intent.putExtra("msg22", price3.getText().toString());
                intent.putExtra("msg33", txt1.getText().toString());
                startActivity(intent);
            }
        });
    }

}
