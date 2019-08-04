package com.example.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class menuAct extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Session session;
    TextView un;
    String msg;
    ImageButton logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        session = new Session(getApplication());
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Logged in Member", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


       Intent i1=getIntent();
        msg=i1.getStringExtra("userNameMsg");
        System.out.println(msg);
        un=(TextView) findViewById(R.id.editTextid);
        System.out.println(un);
        try {

            un.setText(msg);

        }catch (Exception e){}

        logout=(ImageButton)findViewById(R.id.logout);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent i1 = new Intent(menuAct.this, MenuActivity.class);
            startActivity(i1);

        } else if (id == R.id.nav_slideshow) {
            Intent i = new Intent(menuAct.this, MainActivity_Oshani.class);
            startActivity(i);

        } else if (id == R.id.nav_tools) {
            Intent i = new Intent(menuAct.this, MainActivity_Madara.class);
            startActivity(i);

        } else if (id == R.id.nav_share) {

            String username=session.getusename();
            if(!username.equals("admin")) {
                Intent i = new Intent(menuAct.this, ContactUs.class);
                i.putExtra("un", msg);
                startActivity(i);
            }else{
                Intent i3 = new Intent(menuAct.this, AdminContact.class);
                startActivity(i3);
            }

        } else if (id == R.id.nav_send) {
            Intent i4 = new Intent(menuAct.this, aboutus.class);
            startActivity(i4);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(menuAct.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}
