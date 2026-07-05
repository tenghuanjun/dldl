package com.ss.android.downloadlib.b;

import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.download.api.model.DeepLink;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.g.l;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a {
    private static final String a = a.class.getSimpleName();

    public static boolean a(com.ss.android.downloadlib.addownload.b.e eVar) {
        boolean z;
        DeepLink deepLink = eVar.b.getDeepLink();
        String openUrl = deepLink == null ? null : deepLink.getOpenUrl();
        JSONObject jSONObjectA = com.ss.android.downloadlib.g.f.a(new JSONObject(), eVar);
        m.a(jSONObjectA, "applink_source", "click_by_sdk");
        com.ss.android.downloadlib.d.a.a().b("applink_click", jSONObjectA, eVar);
        com.ss.android.downloadlib.addownload.b.g gVarA = com.ss.android.downloadlib.g.i.a(openUrl, eVar);
        if (gVarA.a() == 2) {
            if (!TextUtils.isEmpty(openUrl)) {
                b("by_url", gVarA, jSONObjectA, eVar);
            }
            gVarA = com.ss.android.downloadlib.g.i.a(k.a(), eVar.b.getPackageName(), eVar);
        }
        boolean z2 = false;
        if (a(eVar.a) && k.j().optInt("link_ad_click_event") == 1) {
            if (eVar.b instanceof AdDownloadModel) {
                ((AdDownloadModel) eVar.b).setFunnelType(4);
            }
            com.ss.android.downloadlib.d.a.a().a(eVar.a, 0);
            z = true;
        } else {
            z = false;
        }
        int iA = gVarA.a();
        if (iA == 1) {
            b("by_url", jSONObjectA, eVar);
        } else if (iA == 3) {
            a("by_package", jSONObjectA, eVar);
        } else {
            if (iA == 4) {
                a("by_package", gVarA, jSONObjectA, eVar);
            } else {
                com.ss.android.downloadlib.e.c.a().b("AppLinkClick default");
            }
            if (z2 && !z && ((com.ss.android.downloadlib.d.c.a().b() && !com.ss.android.downloadlib.d.c.a().b(eVar.a, eVar.b.getLogExtra())) || com.ss.android.downloadlib.d.c.a().c())) {
                com.ss.android.downloadlib.d.a.a().a(eVar.a, 2);
            }
            return z2;
        }
        z2 = true;
        if (z2) {
            com.ss.android.downloadlib.d.a.a().a(eVar.a, 2);
        }
        return z2;
    }

    public static void a(com.ss.android.downloadad.api.a.b bVar) {
        String strF = bVar.f();
        JSONObject jSONObjectA = com.ss.android.downloadlib.g.f.a(new JSONObject(), bVar);
        m.a(jSONObjectA, "applink_source", "notify_click_by_sdk");
        com.ss.android.downloadlib.d.a.a().b("applink_click", jSONObjectA, bVar);
        com.ss.android.downloadlib.addownload.b.g gVarA = com.ss.android.downloadlib.g.i.a(strF, bVar);
        if (gVarA.a() == 2) {
            if (!TextUtils.isEmpty(strF)) {
                b("notify_by_url", gVarA, jSONObjectA, bVar);
            }
            gVarA = com.ss.android.downloadlib.g.i.a(k.a(), bVar.e(), bVar);
        }
        int iA = gVarA.a();
        if (iA == 1) {
            b("notify_by_url", jSONObjectA, bVar);
            return;
        }
        if (iA == 3) {
            a("notify_by_package", jSONObjectA, bVar);
        } else if (iA == 4) {
            a("notify_by_package", gVarA, jSONObjectA, bVar);
        } else {
            com.ss.android.downloadlib.e.c.a().b("AppLinkClickNotification default");
        }
    }

    public static void b(com.ss.android.downloadad.api.a.b bVar) {
        if (bVar == null) {
            return;
        }
        String strF = DownloadSetting.obtainGlobal().optInt("app_link_opt") == 1 ? bVar.f() : null;
        JSONObject jSONObjectA = com.ss.android.downloadlib.g.f.a(new JSONObject(), bVar);
        m.a(jSONObjectA, "applink_source", "dialog_click_by_sdk");
        com.ss.android.downloadlib.d.a.a().b("applink_click", jSONObjectA, bVar);
        com.ss.android.downloadlib.addownload.b.g gVarA = com.ss.android.downloadlib.g.i.a(strF, bVar);
        if (gVarA.a() == 2) {
            if (!TextUtils.isEmpty(strF)) {
                b("dialog_by_url", gVarA, jSONObjectA, bVar);
            }
            gVarA = com.ss.android.downloadlib.g.i.a(k.a(), bVar.e(), bVar);
        }
        int iA = gVarA.a();
        if (iA == 1) {
            b("dialog_by_url", jSONObjectA, bVar);
            return;
        }
        if (iA == 3) {
            a("dialog_by_package", jSONObjectA, bVar);
        } else if (iA == 4) {
            a("dialog_by_package", gVarA, jSONObjectA, bVar);
        } else {
            com.ss.android.downloadlib.e.c.a().b("AppLinkClickDialog default");
        }
    }

    public static boolean a(String str, com.ss.android.downloadad.api.a.b bVar) {
        if (!com.ss.android.downloadlib.addownload.i.b(bVar.O())) {
            return false;
        }
        if (TextUtils.isEmpty(bVar.f()) && TextUtils.isEmpty(str)) {
            return false;
        }
        DownloadNotificationManager.getInstance().cancelNotification(bVar.s());
        JSONObject jSONObject = new JSONObject();
        com.ss.android.downloadlib.g.f.a(jSONObject, bVar);
        m.a(jSONObject, "applink_source", "auto_click");
        com.ss.android.downloadlib.d.a.a().b("applink_click", bVar);
        com.ss.android.downloadlib.addownload.b.g gVarA = com.ss.android.downloadlib.g.i.a(bVar, bVar.f(), bVar.e());
        int iA = gVarA.a();
        if (iA == 1) {
            b("auto_by_url", jSONObject, bVar);
            return true;
        }
        if (iA == 2) {
            b("auto_by_url", gVarA, jSONObject, bVar);
            return false;
        }
        if (iA == 3) {
            a("auto_by_package", jSONObject, bVar);
            return true;
        }
        if (iA != 4) {
            return false;
        }
        a("auto_by_package", gVarA, jSONObject, bVar);
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.lang.String r8, final org.json.JSONObject r9, final com.ss.android.downloadad.api.a.a r10) {
        /*
            java.lang.String r0 = "applink_source"
            com.ss.android.downloadlib.g.m.a(r9, r0, r8)
            int r0 = r10.t()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = "download_scene"
            com.ss.android.downloadlib.g.m.a(r9, r1, r0)
            com.ss.android.downloadlib.d.a r0 = com.ss.android.downloadlib.d.a.a()
            java.lang.String r1 = "deeplink_app_open"
            r0.b(r1, r9, r10)
            int r0 = r8.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r0) {
                case -1282070764: goto L44;
                case -441514770: goto L3a;
                case -185950114: goto L30;
                case 368401333: goto L26;
                default: goto L25;
            }
        L25:
            goto L4e
        L26:
            java.lang.String r0 = "dialog_by_package"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 3
            goto L4f
        L30:
            java.lang.String r0 = "by_package"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 2
            goto L4f
        L3a:
            java.lang.String r0 = "auto_by_package"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 1
            goto L4f
        L44:
            java.lang.String r0 = "notify_by_package"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 0
            goto L4f
        L4e:
            r0 = -1
        L4f:
            if (r0 == 0) goto L58
            if (r0 == r3) goto L58
            if (r0 == r2) goto L58
            if (r0 == r1) goto L58
            goto L97
        L58:
            org.json.JSONObject r0 = com.ss.android.downloadlib.addownload.k.j()
            java.lang.String r1 = "check_applink_mode"
            int r0 = r0.optInt(r1)
            r0 = r0 & r3
            if (r0 == 0) goto L7b
            java.lang.Integer r8 = java.lang.Integer.valueOf(r3)
            java.lang.String r0 = "check_applink_result_by_sdk"
            com.ss.android.downloadlib.g.m.a(r9, r0, r8)
            com.ss.android.downloadlib.b.e r8 = com.ss.android.downloadlib.b.e.a()
            com.ss.android.downloadlib.b.a$1 r0 = new com.ss.android.downloadlib.b.a$1
            r0.<init>()
            r8.a(r0)
            goto L97
        L7b:
            com.ss.android.download.api.config.c r1 = com.ss.android.downloadlib.addownload.k.c()
            android.content.Context r2 = com.ss.android.downloadlib.addownload.k.a()
            com.ss.android.download.api.download.DownloadModel r3 = r10.u()
            com.ss.android.download.api.download.DownloadController r4 = r10.w()
            com.ss.android.download.api.download.DownloadEventConfig r5 = r10.v()
            java.lang.String r6 = r10.e()
            r7 = r8
            r1.a(r2, r3, r4, r5, r6, r7)
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.b.a.a(java.lang.String, org.json.JSONObject, com.ss.android.downloadad.api.a.a):void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void b(java.lang.String r8, final org.json.JSONObject r9, final com.ss.android.downloadad.api.a.a r10) {
        /*
            java.lang.String r0 = "applink_source"
            com.ss.android.downloadlib.g.m.a(r9, r0, r8)
            int r0 = r10.t()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r1 = "download_scene"
            com.ss.android.downloadlib.g.m.a(r9, r1, r0)
            com.ss.android.downloadlib.d.a r0 = com.ss.android.downloadlib.d.a.a()
            java.lang.String r1 = "deeplink_url_open"
            r0.b(r1, r9, r10)
            int r0 = r8.hashCode()
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r0) {
                case -1721882089: goto L44;
                case -1374618233: goto L3a;
                case -129544387: goto L30;
                case 829750366: goto L26;
                default: goto L25;
            }
        L25:
            goto L4e
        L26:
            java.lang.String r0 = "dialog_by_url"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 3
            goto L4f
        L30:
            java.lang.String r0 = "notify_by_url"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 0
            goto L4f
        L3a:
            java.lang.String r0 = "by_url"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 2
            goto L4f
        L44:
            java.lang.String r0 = "auto_by_url"
            boolean r0 = r8.equals(r0)
            if (r0 == 0) goto L4e
            r0 = 1
            goto L4f
        L4e:
            r0 = -1
        L4f:
            if (r0 == 0) goto L58
            if (r0 == r3) goto L58
            if (r0 == r2) goto L58
            if (r0 == r1) goto L58
            goto L97
        L58:
            org.json.JSONObject r0 = com.ss.android.downloadlib.addownload.k.j()
            java.lang.String r1 = "check_applink_mode"
            int r0 = r0.optInt(r1)
            r0 = r0 & r3
            if (r0 == 0) goto L7b
            java.lang.Integer r8 = java.lang.Integer.valueOf(r3)
            java.lang.String r0 = "check_applink_result_by_sdk"
            com.ss.android.downloadlib.g.m.a(r9, r0, r8)
            com.ss.android.downloadlib.b.e r8 = com.ss.android.downloadlib.b.e.a()
            com.ss.android.downloadlib.b.a$2 r0 = new com.ss.android.downloadlib.b.a$2
            r0.<init>()
            r8.a(r0)
            goto L97
        L7b:
            com.ss.android.download.api.config.c r1 = com.ss.android.downloadlib.addownload.k.c()
            android.content.Context r2 = com.ss.android.downloadlib.addownload.k.a()
            com.ss.android.download.api.download.DownloadModel r3 = r10.u()
            com.ss.android.download.api.download.DownloadController r4 = r10.w()
            com.ss.android.download.api.download.DownloadEventConfig r5 = r10.v()
            java.lang.String r6 = r10.e()
            r7 = r8
            r1.a(r2, r3, r4, r5, r6, r7)
        L97:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.b.a.b(java.lang.String, org.json.JSONObject, com.ss.android.downloadad.api.a.a):void");
    }

    public static void a(String str, com.ss.android.downloadlib.addownload.b.g gVar, JSONObject jSONObject, com.ss.android.downloadad.api.a.a aVar) {
        m.a(jSONObject, "applink_source", str);
        m.a(jSONObject, MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, Integer.valueOf(gVar.b()));
        m.a(jSONObject, "download_scene", Integer.valueOf(aVar.t()));
        com.ss.android.downloadlib.d.a.a().b("deeplink_app_open_fail", jSONObject, aVar);
    }

    public static void b(String str, com.ss.android.downloadlib.addownload.b.g gVar, JSONObject jSONObject, com.ss.android.downloadad.api.a.a aVar) {
        m.a(jSONObject, "applink_source", str);
        m.a(jSONObject, MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, Integer.valueOf(gVar.b()));
        m.a(jSONObject, "download_scene", Integer.valueOf(aVar.t()));
        com.ss.android.downloadlib.d.a.a().b("deeplink_url_open_fail", jSONObject, aVar);
    }

    public static boolean a(com.ss.android.downloadlib.addownload.b.e eVar, int i) {
        JSONObject jSONObject = new JSONObject();
        m.a(jSONObject, "download_scene", Integer.valueOf(eVar.t()));
        com.ss.android.downloadlib.d.a.a().b("market_click_open", jSONObject, eVar);
        com.ss.android.downloadlib.addownload.b.g gVarA = com.ss.android.downloadlib.g.i.a(k.a(), eVar, eVar.b.getPackageName());
        String strA = m.a(gVarA.c(), "open_market");
        int iA = gVarA.a();
        if (iA == 5) {
            a(strA, jSONObject, eVar, true);
        } else {
            if (iA == 6) {
                m.a(jSONObject, MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, Integer.valueOf(gVarA.b()));
                m.a(jSONObject, "download_scene", Integer.valueOf(eVar.t()));
                com.ss.android.downloadlib.d.a.a().b("market_open_failed", jSONObject, eVar);
                return false;
            }
            if (iA != 7) {
                return false;
            }
        }
        com.ss.android.downloadlib.d.a.a().a(eVar.a, i);
        return true;
    }

    public static void a(final String str, final JSONObject jSONObject, final com.ss.android.downloadlib.addownload.b.e eVar, boolean z) {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Exception e) {
                com.ss.android.downloadlib.e.c.a().a(e, "onMarketSuccess");
                return;
            }
        }
        m.a(jSONObject, "applink_source", str);
        m.a(jSONObject, "download_scene", Integer.valueOf(eVar.t()));
        if (z) {
            com.ss.android.downloadlib.d.a.a().b("market_open_success", jSONObject, eVar);
        }
        if ((k.j().optInt("check_applink_mode") & 4) != 0) {
            e.a().b(new d() { // from class: com.ss.android.downloadlib.b.a.3
                @Override // com.ss.android.downloadlib.b.d
                public void a(boolean z2) {
                    if (!z2 && !"open_market".equals(str)) {
                        a.a(com.ss.android.downloadlib.g.i.a(k.a(), Uri.parse("market://details?id=" + eVar.e())), eVar, false);
                    }
                    com.ss.android.downloadlib.d.a.a().a(z2 ? "market_delay_success" : "market_delay_failed", jSONObject, eVar);
                    if (z2) {
                        k.v().a(k.a(), eVar.b, eVar.d, eVar.c, eVar.b.getPackageName(), 2);
                    }
                }
            });
        } else {
            k.c().a(k.a(), eVar.b, eVar.d, eVar.c, eVar.b.getPackageName(), str);
        }
        com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(eVar.b.getPackageName());
        if (bVarA != null) {
            l.a().a(a, "onMarketSuccess", "商店场景,缓存中已有NativeDownloadModel记录,进行复用");
        } else {
            l.a().a(a, "onMarketSuccess", "商店场景,缓存中没有相应的NativeDownloadModel,需要新建");
            bVarA = new com.ss.android.downloadad.api.a.b(eVar.b, eVar.c, eVar.d);
        }
        bVarA.e(2);
        bVarA.f(System.currentTimeMillis());
        bVarA.h(4);
        bVarA.i(2);
        com.ss.android.downloadlib.addownload.b.f.a().a(bVarA);
        l.a().a(a, "onMarketSuccess", "检测到跳商店成功事件,准备开始检测安装行为");
        com.ss.android.downloadlib.h.a().a(eVar, bVarA);
    }

    public static void a(com.ss.android.downloadlib.addownload.b.g gVar, com.ss.android.downloadlib.addownload.b.e eVar, boolean z) {
        String strA = m.a(gVar.c(), "open_market");
        JSONObject jSONObject = new JSONObject();
        m.a(jSONObject, "ttdownloader_type", "backup");
        int iA = gVar.a();
        if (iA == 5) {
            a(strA, jSONObject, eVar, z);
        } else {
            if (iA != 6) {
                return;
            }
            m.a(jSONObject, MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, Integer.valueOf(gVar.b()));
            m.a(jSONObject, "download_scene", Integer.valueOf(eVar.t()));
            com.ss.android.downloadlib.d.a.a().b("market_open_failed", jSONObject, eVar);
        }
    }

    public static boolean a(long j) {
        return com.ss.android.downloadlib.addownload.b.f.a().d(j) == null;
    }
}
