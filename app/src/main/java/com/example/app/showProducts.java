package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.ArrayUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class showProducts extends ListActivity {

    ArrayList<Product> arrPro=new ArrayList<>();
    private ArrayAdapter mAdapter;
    String[] ob;
    char[] product=new char[100];
    Stack st=new Stack();
    public String ptot;
    public String sendName;
    DatabaseReference getprice;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);


        DatabaseReference getDetails = FirebaseDatabase.getInstance().getReference().child("Item");

        getDetails.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {

                    Product ob=new Product();
                   /*String.valueOf(dsp.child("name").getValue()));
                    ob.add(String.valueOf(dsp.child("price").getValue()));*/

                   ob.setName(String.valueOf(dsp.child("name").getValue()));
                   System.out.println(String.valueOf(dsp.child("name").getValue()));
                   ob.setPrice(Integer.parseInt(String.valueOf(dsp.child("price").getValue())));
                   System.out.println(String.valueOf(dsp.child("price").getValue()));
                   arrPro.add(ob);


                }

                System.out.println("size"+arrPro.size());
                ob=new String[arrPro.size()];
                for(int i=0;i<arrPro.size();i++){
                    Product pr=new Product();
                    pr =arrPro.get(i);
                    ob[i]=pr.getName();
                    System.out.println(ob[i]);
                }


                display();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }
    private void display(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ob);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Object o = l.getItemAtPosition(position);
        String pen = o.toString();
       // Toast.makeText(getApplicationContext(), "You have chosen the pen: " + " " + pen, Toast.LENGTH_LONG).show();

        /*char[] charArray = pen.toCharArray();
        int k=0;

        for(char x:charArray){
            if( (x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z')){
                product[k]=x;

                System.out.print(x+"h");
                k++;
            }else{
                break;
            }


        }*/

        sendName = pen;



        Toast.makeText(getApplicationContext(), "You have chosen" + sendName+"********", Toast.LENGTH_LONG).show();
        gotonextpage();


    }

    private void gotonextpage(){

         getprice = FirebaseDatabase.getInstance().getReference().child("Item").child(sendName);

        getprice.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChildren()) {
                    ptot = dataSnapshot.child("price").getValue().toString();
                    next();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void next(){
        Intent i=new Intent(showProducts.this,custom.class);
        i.putExtra("msg1",sendName);
        i.putExtra("msg2",ptot);

        System.out.print("\n\n\n"+sendName+ptot);

        startActivity(i);

    }

}
