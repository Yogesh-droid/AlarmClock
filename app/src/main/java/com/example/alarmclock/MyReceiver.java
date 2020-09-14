package com.example.alarmclock;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyReceiver extends BroadcastReceiver {
private final String CHANNEL_ID="MyChannet";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"inside Receiver",Toast.LENGTH_SHORT).show();
        createChannel(context);
        NotificationManagerCompat manager=NotificationManagerCompat.from(context);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,CHANNEL_ID);
        builder.setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentTitle("WakeUp Message")
                .setContentText("Please wake up its time to get up")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now);

        Intent i1=new Intent(context,MainActivity.class);
        PendingIntent pd=PendingIntent.getActivity(context,0,i1,0);
        builder.setContentIntent(pd);
        manager.notify(1,builder.build());
    }

    private void createChannel(Context context) {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,"helloChannel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Hello Desc");
            NotificationManager notificationManager=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);        }

        }
}
