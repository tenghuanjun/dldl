package com.sqwan.msdk.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AlarmUtil {
    public static void setAlarm(Context context, long j, int i, Intent intent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        PendingIntent broadcast = PendingIntent.getBroadcast(context, i, intent, 268435456);
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setWindow(0, System.currentTimeMillis() + j, 0L, broadcast);
        } else {
            alarmManager.set(0, System.currentTimeMillis() + j, broadcast);
        }
    }

    public static void cancelAlarm(Context context, String str, int i) {
        ((AlarmManager) context.getSystemService("alarm")).cancel(PendingIntent.getBroadcast(context, i, new Intent(str), 268435456));
    }
}
