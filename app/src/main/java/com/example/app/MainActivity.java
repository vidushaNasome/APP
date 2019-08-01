package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView un;
    TextView pw;

    Button login;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        un=findViewById(R.id.un_log);
        pw=findViewById(R.id.pw_log);
        login=findViewById(R.id.login_log);
        register=findViewById(R.id.reg_log);

    }

    @Override
    protected void onResume() {
        super.onResume();

        login.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String un_1=un.getText().toString();
                String pw_1=pw.getText().toString();

                Intent i = new Intent(MainActivity.this, menuAct.class);

                if(un_1.equals("vidusha")&&pw_1.equals("1234")||un_1.equals("admin")&&pw_1.equals("1234")) {
                    i.putExtra("userNameMsg",un_1);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Invalid User Name or Password", Toast.LENGTH_LONG).show();


                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this, RegisterAct.class);
                startActivity(i1);
            }
        });

    }
}
