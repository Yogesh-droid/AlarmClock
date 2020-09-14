package com.example.alarmclock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
AlarmManager alarmManager;
TimePicker timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker=findViewById(R.id.timepicker);
        alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
    }

    public void startAlarm(View view) {
        Toast.makeText(getApplicationContext(),"start",Toast.LENGTH_SHORT).show();
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
        calendar.set(Calendar.MINUTE,timePicker.getCurrentMinute());

        Intent intent=new Intent();
        intent.setAction("com.yog.wakeUp.Message");
        intent.addCategory("android.intent.category.DEFAULT");
        PendingIntent pd=PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.setExact(AlarmManager.RTC,calendar.getTimeInMillis(),pd);
    }

    public void stopAlarm(View view) {
        Toast.makeText(getApplicationContext(),"stop",Toast.LENGTH_SHORT).show();
        Intent intent=new Intent();
        intent.setAction("com.yog.wakeUp.Message");
        intent.addCategory("android.intent.category.DEFAULT");
        PendingIntent pd=PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.cancel(pd);
    }
}