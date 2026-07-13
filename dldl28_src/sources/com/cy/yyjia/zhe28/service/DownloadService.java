package com.cy.yyjia.zhe28.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import com.by.sjlr.cq28.wxapi.WXEntryActivity$$ExternalSyntheticApiModelOutline0;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadService.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/cy/yyjia/zhe28/service/DownloadService;", "Landroid/app/Service;", "()V", "DOWNLOAD_SERVER_ID", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "", "onDestroy", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class DownloadService extends Service {
    public static final int $stable = 0;
    private final String DOWNLOAD_SERVER_ID = "com.cy.yyjia.zhe28.service.DownloadService";

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= 26) {
            Object systemService = getApplication().getSystemService("notification");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            WXEntryActivity$$ExternalSyntheticApiModelOutline0.m$1();
            String str = this.DOWNLOAD_SERVER_ID;
            ((NotificationManager) systemService).createNotificationChannel(WXEntryActivity$$ExternalSyntheticApiModelOutline0.m(str, str, 4));
            Notification notificationBuild = new NotificationCompat.Builder(this, this.DOWNLOAD_SERVER_ID).build();
            Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
            if (Build.VERSION.SDK_INT >= 29) {
                startForeground(1, notificationBuild, 1);
            } else {
                startForeground(1, notificationBuild);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // android.app.Service
    public void onDestroy() {
        stopForeground(1);
        super.onDestroy();
    }
}
