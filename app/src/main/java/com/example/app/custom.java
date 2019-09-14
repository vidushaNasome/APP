package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class custom extends AppCompatActivity implements View.OnClickListener {

    Session session;
    public String user;
    public  int tot;
    CheckBox ch;
    CheckBox chicken;
    CheckBox cheese;
    CheckBox sussage;
    CheckBox small;
    CheckBox large;
    CheckBox extra;
    Button bt1;
    TextView name;
    TextView price;
    int pr;
    String nm;
    String pr1;
    String totst;
    int total;
    DatabaseReference dbRef;
    DatabaseReference dbRef1;
    MyBasket mb;
    MyBasket mb1;
    public String OverallTotal;
    public int netTotal;
    DatabaseReference totPrice;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        bt1=findViewById(R.id.addtocartbtn);
        bt1.setOnClickListener(this);
        session = new Session(getApplicationContext());
        user=session.getusename();

        mb=new MyBasket();
        chicken=findViewById(R.id.checkChicken);
        chicken.setOnClickListener(this);

        cheese=findViewById(R.id.checkExtraCheese);
        cheese.setOnClickListener(this);

        sussage=findViewById(R.id.checkSausage);
        sussage.setOnClickListener(this);

        small=findViewById(R.id.checSmall);
        small.setOnClickListener(this);

        extra=findViewById(R.id.checkMedium);
        extra.setOnClickListener(this);

        large=findViewById(R.id.checkLarge);
        large.setOnClickListener(this);


        name=(TextView) findViewById(R.id.pizza1);
        price=(TextView)findViewById(R.id.price1);
        ch=(CheckBox) findViewById(R.id.checSmall);

        Intent i3=getIntent();
        nm = i3.getStringExtra("msg1");
        pr1 = i3.getStringExtra("msg2");

        System.out.print("data"+nm+pr1);
        try {
            pr = Integer.parseInt(pr1);
            tot=pr;
            name.setText(nm);
            price.setText("Rs "+pr1+".00");
        }catch(Exception e){}


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case (R.id.addtocartbtn):
                 i = new Intent(custom.this, MainActivity_Oshani.class);
                i.putExtra("msg11", nm);
                totst = String.valueOf(tot);
                i.putExtra("msg22", totst);

                totPrice = FirebaseDatabase.getInstance().getReference().child("Cart");

                totPrice.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(user)) {

                            totPrice =FirebaseDatabase.getInstance().getReference().child("Cart").child(user);
                            totPrice.removeValue();

                            dbRef= FirebaseDatabase.getInstance().getReference().child("Cart");


                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("message");


                            mb.setUername(user);
                            mb.setItemName(nm);
                            mb.setPrice(tot);
                            dbRef.child(user).setValue(mb);


                        }else{
                            dbRef= FirebaseDatabase.getInstance().getReference().child("Cart");


                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("message");


                            mb.setUername(user);
                            mb.setItemName(nm);
                            mb.setPrice(tot);
                            dbRef.child(user).setValue(mb);

                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Toast.makeText(custom.this, "Item added to the cart!", Toast.LENGTH_SHORT).show();
                LayoutInflater inflater = getLayoutInflater();
                View toastLayout = inflater.inflate(R.layout.successmsg, (ViewGroup) findViewById(R.id.successMsg));
                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(toastLayout); toast.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        startActivity(i);
                        finish();
                    }
                }, 400);


                startActivity(i);
                break;


            case(R.id.checkChicken):
                if(((CheckBox) view).isChecked()){
                    tot=tot+1000;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                else{
                    tot=tot-1000;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                break;
            case(R.id.checkExtraCheese):
                if(((CheckBox) view).isChecked()){
                    tot=tot+500;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                else{
                    tot=tot-500;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                break;
            case(R.id.checkSausage):
                if(((CheckBox) view).isChecked()){
                    tot=tot+300;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                else{
                    tot=tot-300;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                break;
            case(R.id.checSmall):
                if(((CheckBox) view).isChecked()){
                    tot=tot+0;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                else{
                    tot=tot-0;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                break;
            case(R.id.checkMedium):
                if(((CheckBox) view).isChecked()){
                    tot=tot+500;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                else{
                    tot=tot-500;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                break;
            case(R.id.checkLarge):
                if(((CheckBox) view).isChecked()){
                    tot=tot+600;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                else{
                    tot=tot-600;
                    System.out.println("total 2="+tot);
                    totst=String.valueOf(tot);
                    price.setText("Rs "+totst+".00");

                }
                break;

        }

    }



}
