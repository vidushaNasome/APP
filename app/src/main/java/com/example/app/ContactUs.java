package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ContactUs extends AppCompatActivity {

        private Session session;
        String username,email1,massage1;
        TextView un1,email,massage;
        Button send;
        ImageButton home;
        DatabaseReference dbRef;
        ContactMsg cmsg;
        Boolean conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        session = new Session(getApplicationContext());

        un1=(TextView) findViewById(R.id.namecontact);
        email= findViewById(R.id.emailcontact);
        massage= findViewById(R.id.massegecontact);
        send=findViewById(R.id.contactsendbtn);
        home=findViewById(R.id.contactHome);

        Intent i=getIntent();
        username=session.getusename();
        un1.setText(" "+username);
        cmsg=new ContactMsg();
    }

    @Override
    protected void onResume() {
        super.onResume();
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //dbRef= FirebaseDatabase.getInstance().getReference().child("ContactMsg");

                String username=un1.getText().toString();
                String email1=email.getText().toString();
                String massage1=massage.getText().toString();

                conn = isOnline();
                if (conn == true) {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{ "teamblossom0@gmail.com"});

                    email.putExtra(Intent.EXTRA_SUBJECT, email1);
                    email.putExtra(Intent.EXTRA_TEXT, "USERNAME: "+username+"----------------------------\n"+massage1);

                    //need this to prompts email client only
                    email.setType("message/rfc822");

                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    /*FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("message");


                    cmsg.setUsername(un1.getText().toString().trim());
                    cmsg.setEmail(email.getText().toString().trim());
                    cmsg.setMassage(massage.getText().toString().trim());
                    dbRef.child(username+"cu").setValue(cmsg);


                    Intent i = new Intent(ContactUs.this, menuAct.class);
                    Toast.makeText(ContactUs.this, "We Have Recieved Your FeedBack ! ", Toast.LENGTH_SHORT).show();
                    startActivity(i);*/
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ContactUs.this, menuAct.class);
                startActivity(i);
            }
        });
    }

    public boolean isOnline(){
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
