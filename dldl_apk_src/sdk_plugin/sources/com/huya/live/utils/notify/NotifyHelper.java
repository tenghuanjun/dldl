package com.huya.live.utils.notify;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NotifyHelper {
    private static final String CHANNEL_ID = "GameLive";
    private static final String CHANNEL_NAME = "直播";

    public static NotificationCompat.Builder getNotificationBuilder(Context context) {
        return getNotificationBuilder(context, CHANNEL_ID, CHANNEL_NAME);
    }

    public static NotificationCompat.Builder getNotificationBuilder(Context context, String str, String str2) {
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(str, str2, 2);
            notificationChannel.canBypassDnd();
            notificationChannel.enableVibration(false);
            notificationChannel.getGroup();
            notificationChannel.setBypassDnd(true);
            notificationChannel.setSound(null, null);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
            return new NotificationCompat.Builder(context, CHANNEL_ID);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, null);
        builder.setPriority(-1);
        return builder;
    }
}
