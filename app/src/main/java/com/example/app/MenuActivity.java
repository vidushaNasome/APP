package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);

        btn1=findViewById(R.id.tc_customize);
        btn2=findViewById(R.id.sd_customize);
        btn3=findViewById(R.id.ps_customize);
        btn4=findViewById(R.id.dc_customize);
        Intent i1 =getIntent();
        Intent i2=getIntent();
        Intent i3=getIntent();

    }


    @Override
    protected void onResume() {
        super.onResume();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MenuActivity.this,custom.class);
                String p="Tandoori Chicken Pizza";
                String price="510";
                i1.putExtra("msg1",p);
                i1.putExtra("msg2",price);
                startActivity(i1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MenuActivity.this,custom.class);
                String p="Sausage Delight Pizza";
                String price="510";
                i2.putExtra("msg1",p);
                i2.putExtra("msg2",price);
                startActivity(i2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(MenuActivity.this,custom.class);
                String p="Paneer Spice Pizza";
                String price="510";
                i3.putExtra("msg1",p);
                i3.putExtra("msg2",price);
                startActivity(i3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(MenuActivity.this,custom.class);
                String p="Develled Chicken Pizza";
                String price="510";
                i3.putExtra("msg1",p);
                i3.putExtra("msg2",price);
                startActivity(i3);
            }
        });
    }
}
