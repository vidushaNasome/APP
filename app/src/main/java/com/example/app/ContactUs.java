package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {

        private Session session;
        String username;
        TextView un1,email,massage;
        Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        session = new Session(getApplicationContext());

        un1=(TextView) findViewById(R.id.namecontact);
        email= findViewById(R.id.emailcontact);
        massage= findViewById(R.id.massegecontact);
        send=findViewById(R.id.contactsendbtn);

        Intent i=getIntent();
        username=session.getusename();
        un1.setText(" "+username);
    }

    @Override
    protected void onResume() {
        super.onResume();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ContactUs.this, menuAct.class);



                Toast.makeText(ContactUs.this, "We Have Recieved Your FeedBack ! ", Toast.LENGTH_SHORT).show();




                startActivity(i);
            }
        });

    }
}
