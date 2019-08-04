package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity_Madara extends AppCompatActivity {

    CustomCalendarView customCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maincalendar);
        customCalendarView = (CustomCalendarView)findViewById(R.id.custom_calndar_view);
    }
}
