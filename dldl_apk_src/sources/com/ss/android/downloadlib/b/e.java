package com.ss.android.downloadlib.b;

import com.ss.android.downloadlib.addownload.k;
import com.ss.android.socialbase.downloader.common.AppStatusManager;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class e implements AppStatusManager.AppStatusChangeListener {
    private long a;

    @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppBackground() {
    }

    private static class a {
        private static e a = new e();
    }

    public static e a() {
        return a.a;
    }

    private e() {
        this.a = 0L;
        AppStatusManager.getInstance().registerAppSwitchListener(this);
    }

    @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
    public void onAppForeground() {
        this.a = System.currentTimeMillis();
    }

    public void a(final d dVar, final long j) {
        if (dVar == null) {
            return;
        }
        com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.b.e.1
            @Override // java.lang.Runnable
            public void run() {
                if (!AppStatusManager.getInstance().isAppFocus() || System.currentTimeMillis() - e.this.a <= j) {
                    dVar.a(true);
                } else {
                    dVar.a(false);
                }
            }
        }, j);
    }

    public void a(d dVar) {
        a(dVar, 5000L);
    }

    public void b(d dVar) {
        if (dVar == null) {
            return;
        }
        a(dVar, k.j().optInt("check_an_result_delay", 1200) > 0 ? r1 : 1200);
    }
}
