package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterAct extends AppCompatActivity {

    private Session session;
    TextView un;
    TextView pw;
    TextView email;
    TextView cpw;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        session = new Session(getApplicationContext());

        un=findViewById(R.id.unid_Reg);
        pw=findViewById(R.id.pw_Reg);
        reg=findViewById(R.id.reg_Reg);
        cpw=findViewById(R.id.cpw_Reg);
        email=findViewById(R.id.email_Reg);
    }


    @Override
    protected void onResume() {
        super.onResume();
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username=un.getText().toString();
                String password=pw.getText().toString();
                String cpwd=cpw.getText().toString();
                String eml=email.getText().toString();
                if(username.isEmpty()||password.isEmpty())
                    Toast.makeText(getApplicationContext(), "Please Input Values For Empty Fields", Toast.LENGTH_LONG).show();

                else if(!password.equals(cpwd))
                    Toast.makeText(getApplicationContext(),"Passwords are Mismatching", Toast.LENGTH_LONG).show();
                else if(!Patterns.EMAIL_ADDRESS.matcher(eml).matches()){
                    Toast.makeText(getApplicationContext(),"Invalid email Address", Toast.LENGTH_LONG).show();
                }
                else{
                Intent i = new Intent(RegisterAct.this, menuAct.class);
                    i.putExtra("userName",username);

                    session.setusename(username);
                    Toast.makeText(RegisterAct.this, "You Have Registerd Successfully!", Toast.LENGTH_SHORT).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View toastLayout = inflater.inflate(R.layout.successmsg, (ViewGroup) findViewById(R.id.successMsg));
                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(toastLayout); toast.show();


                    startActivity(i);

                }
            }
        });
    }
}
