package com.ss.android.socialbase.appdownloader;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.lang.ref.SoftReference;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class h {
    private final Queue<Integer> a;
    private boolean b;
    private long c;
    private long d;
    private SoftReference<JumpUnknownSourceActivity> e;
    private Handler f;
    private Runnable g;

    private h() {
        this.a = new ArrayDeque();
        this.b = false;
        this.f = new Handler(Looper.getMainLooper());
        this.g = new Runnable() { // from class: com.ss.android.socialbase.appdownloader.h.1
            @Override // java.lang.Runnable
            public void run() {
                h.this.c();
            }
        };
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.socialbase.appdownloader.h.2
            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() {
                if (h.this.a.isEmpty()) {
                    return;
                }
                long jOptLong = DownloadSetting.obtainGlobal().optLong("install_on_resume_install_interval", com.igexin.push.config.c.l);
                long jCurrentTimeMillis = System.currentTimeMillis() - h.this.d;
                if (jCurrentTimeMillis < jOptLong) {
                    if (h.this.f.hasCallbacks(h.this.g)) {
                        return;
                    }
                    h.this.f.postDelayed(h.this.g, jOptLong - jCurrentTimeMillis);
                } else {
                    h.this.d = System.currentTimeMillis();
                    h.this.c();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        final Integer numPoll;
        if (Build.VERSION.SDK_INT < 29 || AppStatusManager.getInstance().isAppForeground()) {
            synchronized (this.a) {
                numPoll = this.a.poll();
            }
            this.f.removeCallbacks(this.g);
            if (numPoll != null) {
                final Context appContext = DownloadComponentManager.getAppContext();
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    this.f.post(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.h.3
                        @Override // java.lang.Runnable
                        public void run() {
                            h.this.b(appContext, numPoll.intValue(), false);
                        }
                    });
                } else {
                    b(appContext, numPoll.intValue(), false);
                }
                this.f.postDelayed(this.g, 20000L);
                return;
            }
            this.b = false;
        }
    }

    void a(DownloadInfo downloadInfo, String str) {
        if (downloadInfo == null || TextUtils.isEmpty(str)) {
            return;
        }
        c();
    }

    private static class a {
        private static final h a = new h();
    }

    public static h a() {
        return a.a;
    }

    public int a(final Context context, final int i, final boolean z) {
        if (z) {
            return b(context, i, z);
        }
        if (d()) {
            this.f.postDelayed(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.h.4
                @Override // java.lang.Runnable
                public void run() {
                    h.this.a(context, i, z);
                }
            }, 1000L);
            return 1;
        }
        if (AppStatusManager.getInstance().isAppForeground()) {
            Logger.i("leaves", "on Foreground");
            return b(context, i, z);
        }
        if (b.a()) {
            return 1;
        }
        boolean z2 = Build.VERSION.SDK_INT < 29;
        if (this.a.isEmpty() && !this.b && z2) {
            return b(context, i, z);
        }
        int iOptInt = DownloadSetting.obtainGlobal().optInt("install_queue_size", 3);
        synchronized (this.a) {
            while (this.a.size() > iOptInt) {
                this.a.poll();
            }
        }
        if (z2) {
            this.f.removeCallbacks(this.g);
            this.f.postDelayed(this.g, DownloadSetting.obtain(i).optLong("install_queue_timeout", 20000L));
        }
        synchronized (this.a) {
            if (!this.a.contains(Integer.valueOf(i))) {
                this.a.offer(Integer.valueOf(i));
            }
        }
        return 1;
    }

    private boolean d() {
        return System.currentTimeMillis() - this.c < 1000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(Context context, int i, boolean z) {
        int iB = c.b(context, i, z);
        if (iB == 1) {
            this.b = true;
        }
        this.c = System.currentTimeMillis();
        return iB;
    }

    public void a(JumpUnknownSourceActivity jumpUnknownSourceActivity) {
        this.e = new SoftReference<>(jumpUnknownSourceActivity);
    }

    public JumpUnknownSourceActivity b() {
        SoftReference<JumpUnknownSourceActivity> softReference = this.e;
        JumpUnknownSourceActivity jumpUnknownSourceActivity = softReference == null ? null : softReference.get();
        this.e = null;
        return jumpUnknownSourceActivity;
    }
}
