package com.example.ren.reading;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

/**
 * Created by Ren on 2017. 11. 6..
 */

public class AlarmReceiver extends BroadcastReceiver {

    private static final int MY_NOTIFICATION_ID = 0;
    NotificationManager notificationManager;
    Notification notify;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent myIntent = new Intent(context, StartActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, myIntent, 0);

        notify = new NotificationCompat.Builder(context)
                .setContentTitle("독서를 탐하다")
                .setContentText("독서할 시간이 되었습니다! 오늘도 함께 독서해볼까요?")
                .setTicker("독서를 탐하다에서 알림이 도착했습니다")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND) //PLAY DEFAULT SOUND
                .setAutoCancel(true) // REMOVE ALARM NOTIFICATION JUST BY SWIPE
                .setSmallIcon(R.drawable.splash) //SHOWED IN STATUS BAR
                .build();

        notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, notify);
    }
}
