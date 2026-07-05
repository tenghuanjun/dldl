package com.ss.android.downloadlib.b;

import android.os.Build;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.logger.Logger;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class c {
    public static void a(final com.ss.android.downloadad.api.a.b bVar, final com.ss.android.downloadlib.guide.install.a aVar) {
        boolean zIsAppForeground = AppStatusManager.getInstance().isAppForeground();
        if (!zIsAppForeground && Build.VERSION.SDK_INT >= 29) {
            m.c();
        }
        boolean zIsAppForeground2 = AppStatusManager.getInstance().isAppForeground();
        if (!zIsAppForeground && zIsAppForeground2 && bVar != null) {
            bVar.l(true);
        }
        aVar.a();
        Logger.d("AppInstallOptimiseHelper", "AppInstallOptimiseHelper-->isAppForegroundSecond:::" + zIsAppForeground2);
        if (zIsAppForeground2) {
            return;
        }
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.downloadlib.b.c.1
            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() {
                Logger.d("AppInstallOptimiseHelper", "AppInstallOptimiseHelper-->onAppForeground");
                AppStatusManager.getInstance().unregisterAppSwitchListener(this);
                if (m.b(bVar)) {
                    return;
                }
                bVar.m(true);
                com.ss.android.downloadlib.d.a.a().a("install_delay_invoke", bVar);
                aVar.a();
            }
        });
    }
}
