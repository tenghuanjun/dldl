package com.sq.sdk.tool.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class AlarmUtil {
    public static void setAlarm(long j, int i, Intent intent) {
        Context applicationContext = SQContextWrapper.getApplicationContext();
        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService("alarm");
        PendingIntent broadcast = PendingIntent.getBroadcast(applicationContext, i, intent, 268435456);
        if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setWindow(0, System.currentTimeMillis() + j, 0L, broadcast);
        } else {
            alarmManager.set(0, System.currentTimeMillis() + j, broadcast);
        }
    }
}
