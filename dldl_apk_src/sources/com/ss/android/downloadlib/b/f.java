package com.ss.android.downloadlib.b;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class f {
    private static Handler a = new Handler(Looper.getMainLooper());

    public static void a(final com.ss.android.downloadad.api.a.b bVar, final h hVar) {
        boolean zIsAppForeground = AppStatusManager.getInstance().isAppForeground();
        if (!zIsAppForeground && Build.VERSION.SDK_INT >= 29) {
            m.c();
        }
        boolean zIsAppForeground2 = AppStatusManager.getInstance().isAppForeground();
        boolean z = !zIsAppForeground && zIsAppForeground2;
        if (bVar != null) {
            bVar.l(z);
        }
        hVar.a(z);
        if (bVar == null) {
            return;
        }
        b(bVar, j(bVar));
        if (zIsAppForeground2) {
            return;
        }
        final long jCurrentTimeMillis = System.currentTimeMillis();
        AppStatusManager.getInstance().registerAppSwitchListener(new AppStatusManager.AppStatusChangeListener() { // from class: com.ss.android.downloadlib.b.f.1
            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppBackground() {
            }

            @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
            public void onAppForeground() {
                AppStatusManager.getInstance().unregisterAppSwitchListener(this);
                com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.b.f.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean zC = m.c(bVar.e());
                        long jE = f.e(bVar);
                        if (!zC || jE >= System.currentTimeMillis() - jCurrentTimeMillis) {
                            if (System.currentTimeMillis() - jCurrentTimeMillis > f.h(bVar)) {
                                com.ss.android.downloadlib.d.a.a().a("deeplink_delay_timeout", bVar);
                                return;
                            }
                            bVar.l(true);
                            com.ss.android.downloadlib.d.a.a().a("deeplink_delay_invoke", bVar);
                            hVar.a(true);
                            f.b(bVar, f.j(bVar));
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final com.ss.android.downloadad.api.a.b bVar, final int i) {
        if (i <= 0) {
            return;
        }
        com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.b.f.2
            @Override // java.lang.Runnable
            public void run() {
                int i2 = 1;
                if (m.c(bVar.e())) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        if (!bVar.aa()) {
                            i2 = 2;
                        }
                        jSONObject.putOpt("deeplink_source", Integer.valueOf(i2));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    com.ss.android.downloadlib.d.a.a().a("deeplink_success_2", jSONObject, bVar);
                    return;
                }
                f.b(bVar, i - 1);
            }
        }, i(bVar) * 1000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long h(com.ss.android.downloadad.api.a.b bVar) {
        return com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVar).optLong("app_link_check_timeout", com.alipay.security.mobile.module.deviceinfo.e.a);
    }

    private static int i(com.ss.android.downloadad.api.a.b bVar) {
        return com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVar).optInt("app_link_check_delay", 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int j(com.ss.android.downloadad.api.a.b bVar) {
        return com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVar).optInt("app_link_check_count", 10);
    }

    public static boolean a(com.ss.android.downloadad.api.a.b bVar) {
        return com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVar).optInt("app_link_opt_switch") == 1;
    }

    public static boolean b(com.ss.android.downloadad.api.a.b bVar) {
        return com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVar).optInt("app_link_opt_install_switch") == 1;
    }

    public static boolean c(com.ss.android.downloadad.api.a.b bVar) {
        return com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVar).optInt("app_link_opt_invoke_switch") == 1;
    }

    public static boolean d(com.ss.android.downloadad.api.a.b bVar) {
        return com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVar).optInt("app_link_opt_dialog_switch") == 1;
    }

    public static long e(com.ss.android.downloadad.api.a.b bVar) {
        if (bVar == null) {
            return 3000L;
        }
        return com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVar).optInt("app_link_opt_back_time_limit", 3) * 1000;
    }
}
