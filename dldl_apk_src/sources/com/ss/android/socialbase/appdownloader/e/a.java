package com.ss.android.socialbase.appdownloader.e;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.widget.RemoteViews;
import com.ss.android.socialbase.appdownloader.DownloadHandlerService;
import com.ss.android.socialbase.appdownloader.e;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.notification.AbsNotificationItem;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a extends AbsNotificationItem {
    private final Context a;
    private final Resources b;
    private String c;
    private String d;
    private String e;

    public a(Context context, int i, String str, String str2, String str3, String str4) {
        super(i, str);
        this.d = str2;
        this.c = str3;
        this.e = str4;
        Context applicationContext = context.getApplicationContext();
        this.a = applicationContext;
        this.b = applicationContext.getResources();
    }

    @Override // com.ss.android.socialbase.downloader.notification.AbsNotificationItem
    public void updateNotificationItem(DownloadInfo downloadInfo) {
        super.updateNotificationItem(downloadInfo);
        this.d = downloadInfo.getSavePath();
        this.c = downloadInfo.getName();
        this.e = downloadInfo.getExtra();
    }

    @Override // com.ss.android.socialbase.downloader.notification.AbsNotificationItem
    public void updateNotification(BaseException baseException, boolean z) {
        if (this.a == null) {
            return;
        }
        try {
            this.notification = a(baseException, z);
            notify(this.notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:157:0x0446  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.app.Notification a(com.ss.android.socialbase.downloader.exception.BaseException r25, boolean r26) {
        /*
            Method dump skipped, instruction units count: 1425
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.e.a.a(com.ss.android.socialbase.downloader.exception.BaseException, boolean):android.app.Notification");
    }

    private boolean a(BaseException baseException, DownloadSetting downloadSetting, DownloadInfo downloadInfo) {
        return baseException != null && (baseException.getErrorCode() == 1013 || baseException.getErrorCode() == 1049) && downloadInfo != null && "application/vnd.android.package-archive".contains(downloadInfo.getMimeType()) && downloadSetting.optInt(DownloadSettingKeys.NOTIFICATION_TEXT_OPT, 0) == 1;
    }

    private RemoteViews a() {
        RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), e.a());
        if (Build.VERSION.SDK_INT > 20) {
            try {
                if (com.ss.android.socialbase.appdownloader.c.a(this.a)) {
                    remoteViews.setInt(e.f(), "setBackgroundColor", this.a.getResources().getColor(e.r()));
                }
            } catch (Throwable unused) {
            }
        }
        return remoteViews;
    }

    private int a(int i, int i2) {
        if (DownloadSetting.obtain(i2).optInt("notification_opt_2") == 1) {
            return e.v();
        }
        if (i == 1 || i == 4) {
            return e.t();
        }
        if (i == 2) {
            return e.u();
        }
        if (i == 3) {
            return e.v();
        }
        return 0;
    }

    private PendingIntent a(String str, int i, int i2) {
        Intent intent = new Intent(this.a, (Class<?>) DownloadHandlerService.class);
        intent.setAction(str);
        intent.putExtra("extra_click_download_ids", i2);
        intent.putExtra("extra_click_download_type", i);
        intent.putExtra("extra_from_notification", true);
        return PendingIntent.getService(this.a, i2, intent, 201326592);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:13:0x0044
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    private android.support.v4.app.NotificationCompat.Builder b() {
        /*
            r3 = this;
            com.ss.android.socialbase.appdownloader.d r0 = com.ss.android.socialbase.appdownloader.d.j()
            java.lang.String r0 = r0.i()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 26
            if (r1 >= r2) goto L16
            android.support.v4.app.NotificationCompat$Builder r0 = new android.support.v4.app.NotificationCompat$Builder
            android.content.Context r1 = r3.a
            r0.<init>(r1)
            goto L4b
        L16:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L22
            android.content.Context r0 = r3.a
            java.lang.String r0 = com.ss.android.socialbase.appdownloader.c.b(r0)
        L22:
            com.ss.android.socialbase.appdownloader.d r1 = com.ss.android.socialbase.appdownloader.d.j()     // Catch: java.lang.NoSuchMethodError -> L44
            com.ss.android.socialbase.appdownloader.c.m r1 = r1.m()     // Catch: java.lang.NoSuchMethodError -> L44
            if (r1 == 0) goto L3b
            com.ss.android.socialbase.appdownloader.d r1 = com.ss.android.socialbase.appdownloader.d.j()     // Catch: java.lang.NoSuchMethodError -> L44
            com.ss.android.socialbase.appdownloader.c.m r1 = r1.m()     // Catch: java.lang.NoSuchMethodError -> L44
            android.content.Context r2 = r3.a     // Catch: java.lang.NoSuchMethodError -> L44
            android.support.v4.app.NotificationCompat$Builder r0 = r1.a(r2, r0)     // Catch: java.lang.NoSuchMethodError -> L44
            goto L4b
        L3b:
            android.support.v4.app.NotificationCompat$Builder r1 = new android.support.v4.app.NotificationCompat$Builder     // Catch: java.lang.NoSuchMethodError -> L44
            android.content.Context r2 = r3.a     // Catch: java.lang.NoSuchMethodError -> L44
            r1.<init>(r2, r0)     // Catch: java.lang.NoSuchMethodError -> L44
            r0 = r1
            goto L4b
        L44:
            android.support.v4.app.NotificationCompat$Builder r0 = new android.support.v4.app.NotificationCompat$Builder
            android.content.Context r1 = r3.a
            r0.<init>(r1)
        L4b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.e.a.b():android.support.v4.app.NotificationCompat$Builder");
    }

    private int a(int i) {
        if (DownloadSetting.obtain(i).optInt(DownloadSettingKeys.OPT_NOTIFICATION_UI) >= 1) {
            return e.h();
        }
        return e.g();
    }
}
