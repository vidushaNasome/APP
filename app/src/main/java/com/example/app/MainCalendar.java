package com.example.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainCalendar extends AppCompatActivity {

    CustomCalendarView customCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maincalendar);

        customCalendarView = (CustomCalendarView)findViewById(R.id.custom_calndar_view);
    }
}
