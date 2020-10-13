package com.midooabdaim.ardak.data.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.app.NotificationManager;


import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.midooabdaim.ardak.R;
import com.midooabdaim.ardak.helper.DateTxt;
import com.midooabdaim.ardak.ui.activity.HomeActivity;

import java.util.Calendar;
import java.util.Date;

public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String id = intent.getStringExtra("mid");
        String title = "";
        String content = "";
        if (id.equals(context.getString(R.string.gawafafrom))) {
            title = context.getString(R.string.gawafa);
            content = title + context.getString(R.string.isopen);
        } else if (id.equals(context.getString(R.string.gawafato))) {
            title = context.getString(R.string.gawafa);
            content = title + context.getString(R.string.isclosed);
        } else if (id.equals(context.getString(R.string.romanfrom))) {
            title = context.getString(R.string.roman);
            content = title + context.getString(R.string.isopen);
        } else if (id.equals(context.getString(R.string.romanto))) {
            title = context.getString(R.string.roman);
            content = title + context.getString(R.string.isclosed);
        }

        Uri defaultSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Notification notification = new NotificationCompat.Builder(context, context.getString(R.string.app_name))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_EVENT)
                .build();


        // NotificationManager notii = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationManagerCompat noti = NotificationManagerCompat.from(context);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(context.getString(R.string.default_notification_channel_id), context.getString(R.string.default_notification_channel_id), NotificationManager.IMPORTANCE_HIGH);
            channel.setSound(defaultSound, null);
            channel.enableVibration(true);
            channel.enableLights(true);
            noti.createNotificationChannel(channel);
        }
        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        noti.notify(m, notification);

        //  Intent i = new Intent(context, HomeActivity.class);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setAlarm(Context context, Date date, String id) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        i.putExtra("mid", id);
        PendingIntent pi = null;
        if (id.equals(context.getString(R.string.gawafafrom))) {
            pi = PendingIntent.getBroadcast(context, 1, i, 0);
        } else if (id.equals(context.getString(R.string.gawafato))) {
            pi = PendingIntent.getBroadcast(context, 2, i, 0);
        } else if (id.equals(context.getString(R.string.romanfrom))) {
            pi = PendingIntent.getBroadcast(context, 3, i, 0);
        } else if (id.equals(context.getString(R.string.romanto))) {
            pi = PendingIntent.getBroadcast(context, 4, i, 0);
        }
        Calendar calendar = Calendar.getInstance();
        if (date != null)
            calendar.setTime(date);

        am.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);

    }

    public void cancelAlarm(Context context, String id) {
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = null;
        if (id.equals(context.getString(R.string.gawafa))) {
            pi = PendingIntent.getBroadcast(context, 1, i, 0);
        } else if (id.equals(context.getString(R.string.roman))) {
            pi = PendingIntent.getBroadcast(context, 2, i, 0);
        }
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pi);
    }

}
