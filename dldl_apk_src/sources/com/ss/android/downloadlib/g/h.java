package com.ss.android.downloadlib.g;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.ss.android.download.api.config.r;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.android.downloadad.api.constant.AdBaseConstants;
import com.ss.android.downloadad.api.download.AdDownloadController;
import com.ss.android.downloadlib.activity.JumpKllkActivity;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class h {
    private static final String a = h.class.getSimpleName();

    public static com.ss.android.downloadlib.addownload.b.g a(Context context, Uri uri) {
        Intent intent;
        if (!com.ss.android.socialbase.appdownloader.f.e.c() && (context == null || uri == null || !"market".equals(uri.getScheme()))) {
            return new com.ss.android.downloadlib.addownload.b.g(6, 12);
        }
        try {
            String strJ = com.ss.android.socialbase.appdownloader.f.e.j();
            if (com.ss.android.socialbase.appdownloader.f.e.q() && (TextUtils.isEmpty(strJ) || !m.e(context, strJ))) {
                strJ = "com.huawei.appmarket";
                Uri.Builder builderBuildUpon = uri.buildUpon();
                builderBuildUpon.scheme("market");
                intent = new Intent("android.intent.action.VIEW", builderBuildUpon.build());
            } else {
                intent = new Intent("android.intent.action.VIEW", uri);
            }
            if (!m.a(context, intent)) {
                return new com.ss.android.downloadlib.addownload.b.g(6, 13);
            }
            if (m.e(context, strJ) && !com.ss.android.socialbase.appdownloader.f.e.g()) {
                intent.setPackage(strJ);
            }
            if (DownloadSetting.obtainGlobal().optBugFix("fix_jump_market")) {
                intent.addFlags(335544320);
            } else if (!(context instanceof Activity)) {
                intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            }
            if (DownloadSetting.obtainGlobal().optInt("test_jump_market_failed") == 1) {
                com.ss.android.downloadlib.e.c.a().a(false, "jump market error");
                return new com.ss.android.downloadlib.addownload.b.g(6, 25);
            }
            intent.putExtra("start_only_for_android", true);
            context.startActivity(intent);
            return new com.ss.android.downloadlib.addownload.b.g(5);
        } catch (Exception unused) {
            return new com.ss.android.downloadlib.addownload.b.g(6, 14);
        }
    }

    public static com.ss.android.downloadlib.addownload.b.g a(final Context context, Uri uri, com.ss.android.downloadlib.addownload.b.e eVar) {
        if (context == null || !com.ss.android.downloadlib.b.j.a(uri)) {
            return new com.ss.android.downloadlib.addownload.b.g(6, 12);
        }
        try {
            final Intent intent = new Intent("android.intent.action.VIEW", uri);
            if (!m.a(context, intent)) {
                return new com.ss.android.downloadlib.addownload.b.g(6, 13);
            }
            String strJ = com.ss.android.socialbase.appdownloader.f.e.j();
            if (m.e(context, strJ)) {
                intent.setPackage(strJ);
            }
            intent.addFlags(335544320);
            if (DownloadSetting.obtainGlobal().optInt("test_jump_market_failed") == 1 && "local_test".equals(com.ss.android.downloadlib.addownload.k.k().c)) {
                com.ss.android.downloadlib.e.c.a().a(false, "jump market error");
                return new com.ss.android.downloadlib.addownload.b.g(6, 25);
            }
            intent.putExtra("start_only_for_android", true);
            long jOptLong = com.ss.android.downloadlib.addownload.k.j().optLong("market_jump_delay", 1000L);
            if (jOptLong > 0 && eVar != null && eVar.e != null && !eVar.e.ae()) {
                com.ss.android.downloadlib.g.a().b().post(new Runnable() { // from class: com.ss.android.downloadlib.g.h.1
                    @Override // java.lang.Runnable
                    public void run() {
                        com.ss.android.downloadlib.addownload.k.d().a(8, com.ss.android.downloadlib.addownload.k.a(), null, "浏览器跳转失败，正在前往应用商店", null, 0);
                    }
                });
            }
            com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.g.h.2
                @Override // java.lang.Runnable
                public void run() {
                    context.startActivity(intent);
                }
            }, jOptLong);
            return new com.ss.android.downloadlib.addownload.b.g(5);
        } catch (Exception unused) {
            return new com.ss.android.downloadlib.addownload.b.g(6, 14);
        }
    }

    public static com.ss.android.downloadlib.addownload.b.g a(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return new com.ss.android.downloadlib.addownload.b.g(6, 11);
        }
        if (com.ss.android.socialbase.appdownloader.f.e.g() && m.e(context, "com.sec.android.app.samsungapps")) {
            return d(context, str);
        }
        return a(context, com.ss.android.download.api.c.a.a(str));
    }

    public static com.ss.android.downloadlib.addownload.b.g a(Context context, com.ss.android.downloadlib.addownload.b.e eVar, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return new com.ss.android.downloadlib.addownload.b.g(6, 11);
        }
        if (com.ss.android.socialbase.appdownloader.f.e.g() && m.e(context, "com.sec.android.app.samsungapps")) {
            return d(context, str);
        }
        if (!eVar.b.isAd() || !eVar.d.enableAM()) {
            return a(context, Uri.parse("market://details?id=" + str));
        }
        JSONArray jSONArrayOptJSONArray = com.ss.android.downloadlib.addownload.k.j().optJSONArray("am_plans");
        if (com.ss.android.socialbase.appdownloader.f.e.e() && com.ss.android.socialbase.appdownloader.f.a.a(jSONArrayOptJSONArray, "am_3")) {
            return b(context, eVar, str);
        }
        if (com.ss.android.socialbase.appdownloader.f.e.f() && com.ss.android.socialbase.appdownloader.f.a.a(jSONArrayOptJSONArray, "am_2")) {
            d(context, eVar, str);
            return new com.ss.android.downloadlib.addownload.b.g(7, "am_m2");
        }
        if (com.ss.android.socialbase.appdownloader.f.e.d() && com.ss.android.socialbase.appdownloader.f.a.a(jSONArrayOptJSONArray, "am_5")) {
            g(context, eVar, str);
            return new com.ss.android.downloadlib.addownload.b.g(7, "am_v1");
        }
        if (com.ss.android.socialbase.appdownloader.f.e.e() && com.ss.android.socialbase.appdownloader.f.a.a(jSONArrayOptJSONArray, "am_7") && (eVar.d instanceof AdDownloadController) && ((AdDownloadController) eVar.d).enableOppoAutoDownload()) {
            return c(context, eVar, str);
        }
        if (com.ss.android.socialbase.appdownloader.f.e.d() && com.ss.android.socialbase.appdownloader.f.a.a(jSONArrayOptJSONArray, "am_8") && m.a(m.c(context, "com.bbk.appstore"), "8.7.2.0") >= 0) {
            h(context, eVar, str);
            return new com.ss.android.downloadlib.addownload.b.g(7, "am_v2");
        }
        if ((com.ss.android.socialbase.appdownloader.f.e.a() || com.ss.android.socialbase.appdownloader.f.e.b()) && com.ss.android.socialbase.appdownloader.f.a.a(jSONArrayOptJSONArray, "am_9")) {
            e(context, eVar, str);
            return new com.ss.android.downloadlib.addownload.b.g(7, "am_hr");
        }
        if ((com.ss.android.socialbase.appdownloader.f.e.a() || com.ss.android.socialbase.appdownloader.f.e.b()) && com.ss.android.socialbase.appdownloader.f.a.a(jSONArrayOptJSONArray, "am_10")) {
            f(context, eVar, str);
            return new com.ss.android.downloadlib.addownload.b.g(7, "am_hr2");
        }
        return a(context, Uri.parse("market://details?id=" + str));
    }

    private static com.ss.android.downloadlib.addownload.b.g d(Context context, String str) {
        try {
            Uri uri = Uri.parse("https://www.samsungapps.com/appquery/appDetail.as?appId=" + str);
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.app.samsungapps", "com.sec.android.app.samsungapps.Main");
            intent.setData(uri);
            if (!(context instanceof Activity)) {
                intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            }
            intent.putExtra("start_only_for_android", true);
            context.startActivity(intent);
            return new com.ss.android.downloadlib.addownload.b.g(5);
        } catch (Exception unused) {
            return new com.ss.android.downloadlib.addownload.b.g(6, 14);
        }
    }

    public static boolean a(Context context, com.ss.android.downloadlib.addownload.b.e eVar, String str, JSONObject jSONObject, boolean z, int i) {
        m.a(jSONObject, "download_scene", Integer.valueOf(eVar.t()));
        com.ss.android.downloadlib.d.a.a().b("market_click_open", jSONObject, eVar);
        com.ss.android.downloadlib.addownload.b.g gVarA = a(context, Uri.parse(str));
        String strA = m.a(gVarA.c(), "open_market");
        int iA = gVarA.a();
        if (iA == 5) {
            com.ss.android.downloadlib.b.a.a(strA, jSONObject, eVar, true);
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
        if (z) {
            com.ss.android.downloadlib.d.a.a().a(eVar.a, i);
        }
        return true;
    }

    private static com.ss.android.downloadlib.addownload.b.g b(Context context, com.ss.android.downloadlib.addownload.b.e eVar, String str) {
        Intent intent = new Intent(context, (Class<?>) JumpKllkActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra(com.igexin.push.core.d.c.c, str);
        intent.putExtra("id", eVar.a);
        intent.putExtra("start_only_for_android", true);
        JSONObject jSONObject = new JSONObject();
        try {
            context.startActivity(intent);
            return new com.ss.android.downloadlib.addownload.b.g(7, "am_kllk2");
        } catch (Throwable unused) {
            b(eVar, jSONObject, 1, 3, "market://details?id=" + str);
            return a(context, Uri.parse("market://details?id=" + str));
        }
    }

    private static com.ss.android.downloadlib.addownload.b.g c(Context context, com.ss.android.downloadlib.addownload.b.e eVar, String str) {
        Intent intent = new Intent(context, (Class<?>) JumpKllkActivity.class);
        intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intent.putExtra("dl", true);
        intent.putExtra(com.igexin.push.core.d.c.c, str);
        intent.putExtra("id", eVar.a);
        if (Build.VERSION.SDK_INT >= 29) {
            intent.putExtra("bk", "com.heytap.browser");
        } else if (m.e(context, "com.android.browser")) {
            intent.putExtra("bk", "com.android.browser");
        } else if (m.e(context, "com.coloros.browser")) {
            intent.putExtra("bk", "com.coloros.browser");
        } else {
            return a(context, Uri.parse("market://details?id=" + str));
        }
        intent.putExtra("start_only_for_android", true);
        JSONObject jSONObject = new JSONObject();
        try {
            context.startActivity(intent);
            return new com.ss.android.downloadlib.addownload.b.g(7, "am_kllk3");
        } catch (Throwable unused) {
            b(eVar, jSONObject, 1, 3, "market://details?id=" + str);
            return a(context, Uri.parse("market://details?id=" + str));
        }
    }

    public static void a(Context context, String str, long j, boolean z) {
        JSONObject jSONObject = new JSONObject();
        com.ss.android.downloadlib.addownload.b.e eVarE = com.ss.android.downloadlib.addownload.b.f.a().e(j);
        try {
            JSONObject jSONObjectJ = com.ss.android.downloadlib.addownload.k.j();
            String strOptString = jSONObjectJ.optString(com.igexin.push.core.d.c.d);
            String strA = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("aa"), strOptString);
            String strA2 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("ac"), strOptString);
            String strA3 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("af"), strOptString);
            boolean zA = com.ss.android.socialbase.appdownloader.f.a.a(jSONObjectJ, context, strA2);
            StringBuilder sb = new StringBuilder(String.format(strA, str, strA3, strA2));
            Intent intent = new Intent("android.intent.action.VIEW");
            String strJ = com.ss.android.socialbase.appdownloader.f.e.j();
            if (m.e(context, strJ)) {
                intent.setPackage(strJ);
            }
            if (z) {
                sb.append(com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("ae"), strOptString));
            } else {
                intent.addFlags(335544320);
            }
            m.a(jSONObject, "mf", Boolean.valueOf(zA));
            m.a(jSONObject, "if", Boolean.valueOf(z));
            intent.setData(Uri.parse(sb.toString()));
            intent.putExtra("start_only_for_android", true);
            context.startActivity(intent);
            com.ss.android.downloadlib.b.a.a("am_kllk2", jSONObject, eVarE, true);
            if (zA) {
                b(eVarE, jSONObject, -1, 3, sb.toString());
            } else {
                b(eVarE, jSONObject, 3, 3, sb.toString());
            }
        } catch (Exception unused) {
            com.ss.android.downloadlib.b.a.a(a(com.ss.android.downloadlib.addownload.k.a(), Uri.parse("market://details?id=" + str)), eVarE, true);
            b(eVarE, jSONObject, 2, 3, "market://details?id=" + str);
        }
    }

    public static void a(final Context context, String str, long j, String str2, boolean z) {
        JSONObject jSONObject = new JSONObject();
        com.ss.android.downloadlib.addownload.b.e eVarE = com.ss.android.downloadlib.addownload.b.f.a().e(j);
        try {
            JSONObject jSONObjectJ = com.ss.android.downloadlib.addownload.k.j();
            String strOptString = jSONObjectJ.optString(com.igexin.push.core.d.c.d);
            String strA = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("br"), strOptString);
            String strA2 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("bs_1"), strOptString);
            String strA3 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("bs_2"), strOptString);
            String strA4 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("bs_3"), strOptString);
            String strA5 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("bt"), strOptString);
            String strA6 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("bu"), strOptString);
            StringBuilder sb = new StringBuilder(String.format("https://", new Object[0]));
            sb.append(strA);
            sb.append(strA2);
            sb.append(strA3);
            sb.append(strA4);
            sb.append(strA5);
            sb.append(strA6);
            final Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage(str2);
            if (z) {
                sb.append("pkg=" + str);
                sb.append("&dl=true");
            } else {
                intent.addFlags(335544320);
            }
            m.a(jSONObject, "dl", Boolean.valueOf(z));
            intent.setData(Uri.parse(sb.toString()));
            intent.putExtra("start_only_for_android", true);
            long jOptLong = com.ss.android.downloadlib.addownload.k.j().optLong("oppo_browser_jump_delay", 1000L);
            if (jOptLong > 0) {
                com.ss.android.downloadlib.g.a().b().post(new Runnable() { // from class: com.ss.android.downloadlib.g.h.3
                    @Override // java.lang.Runnable
                    public void run() {
                        com.ss.android.downloadlib.addownload.k.d().a(12, com.ss.android.downloadlib.addownload.k.a(), null, "正在前往浏览器下载", null, 0);
                    }
                });
            }
            com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.g.h.4
                @Override // java.lang.Runnable
                public void run() {
                    context.startActivity(intent);
                }
            }, jOptLong);
            if (eVarE.e != null) {
                eVarE.e.v(true);
            }
            com.ss.android.downloadlib.b.a.a("am_kllk3", jSONObject, eVarE, true);
            b(eVarE, jSONObject, -1, 7, sb.toString());
        } catch (Exception unused) {
            if (eVarE.e != null) {
                eVarE.e.v(false);
            }
            com.ss.android.downloadlib.b.a.a(a(com.ss.android.downloadlib.addownload.k.a(), Uri.parse("market://details?id=" + str), eVarE), eVarE, true);
            b(eVarE, jSONObject, 2, 7, "market://details?id=" + str);
        }
    }

    private static boolean a(Activity activity, String str, HashMap<String, String> map) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=" + str));
        intent.putExtra("start_only_for_android", true);
        intent.putExtra("param", map);
        String strJ = com.ss.android.socialbase.appdownloader.f.e.j();
        if (m.e(com.ss.android.downloadlib.addownload.k.a(), strJ)) {
            intent.setPackage(strJ);
        }
        if (!m.a(com.ss.android.downloadlib.addownload.k.a(), intent)) {
            return false;
        }
        try {
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            com.ss.android.downloadlib.e.c.a().a(e, "start v1");
            return false;
        }
    }

    private static void d(final Context context, final com.ss.android.downloadlib.addownload.b.e eVar, final String str) {
        com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.g.h.5
            @Override // java.lang.Runnable
            public void run() {
                com.ss.android.downloadlib.b.a.a(h.a(context, Uri.parse("market://details?id=" + str)), eVar, true);
                JSONObject jSONObject = new JSONObject();
                try {
                    JSONObject jSONObjectJ = com.ss.android.downloadlib.addownload.k.j();
                    Thread.sleep(jSONObjectJ.optInt("m2_delay_millis", 1000));
                    com.ss.android.downloadlib.a.a.a.a().a(context, true);
                    com.ss.android.downloadlib.a.a.b bVar = new com.ss.android.downloadlib.a.a.b();
                    bVar.a = 1;
                    bVar.b = 0;
                    String strA = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("v"), jSONObjectJ.optString(com.igexin.push.core.d.c.d));
                    bVar.c = String.format(strA, str);
                    com.ss.android.downloadlib.a.a.a.a().a(bVar, (com.ss.android.downloadlib.a.a.d) null);
                    com.ss.android.downloadlib.a.a.a.a().b();
                    h.b(eVar, jSONObject, -1, 2, String.format(strA, str));
                } catch (Throwable th) {
                    th.printStackTrace();
                    h.b(eVar, jSONObject, 1, 2, "market://details?id=" + str);
                }
            }
        });
    }

    private static void e(final Context context, final com.ss.android.downloadlib.addownload.b.e eVar, final String str) {
        com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.g.h.6
            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObjectJ = com.ss.android.downloadlib.addownload.k.j();
                final JSONObject jSONObject = new JSONObject();
                try {
                    String strOptString = jSONObjectJ.optString(com.igexin.push.core.d.c.d);
                    String strA = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("bw"), strOptString);
                    String strA2 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("bx"), strOptString);
                    String strA3 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("by"), strOptString);
                    Uri.Builder builder = new Uri.Builder();
                    builder.scheme("https").authority(strA).appendPath(strA2).appendQueryParameter(strA3, str);
                    com.ss.android.downloadlib.addownload.k.e().a("GET", builder.build().toString(), null, new r() { // from class: com.ss.android.downloadlib.g.h.6.1
                        /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
                        @Override // com.ss.android.download.api.config.r
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public void a(java.lang.String r6) {
                            /*
                                r5 = this;
                                boolean r0 = android.text.TextUtils.isEmpty(r6)
                                r1 = 1
                                if (r0 != 0) goto L2c
                                java.lang.String r6 = com.ss.android.downloadlib.g.h.a(r6)
                                boolean r0 = android.text.TextUtils.isEmpty(r6)
                                if (r0 != 0) goto L2c
                                java.lang.String r6 = com.ss.android.downloadlib.g.h.b(r6)
                                boolean r0 = android.text.TextUtils.isEmpty(r6)
                                if (r0 != 0) goto L2c
                                com.ss.android.downloadlib.g.h$6 r0 = com.ss.android.downloadlib.g.h.AnonymousClass6.this
                                android.content.Context r0 = r2
                                com.ss.android.downloadlib.g.h$6 r2 = com.ss.android.downloadlib.g.h.AnonymousClass6.this
                                com.ss.android.downloadlib.addownload.b.e r2 = r3
                                com.ss.android.downloadlib.g.h$6 r3 = com.ss.android.downloadlib.g.h.AnonymousClass6.this
                                java.lang.String r3 = r1
                                com.ss.android.downloadlib.g.h.a(r0, r2, r3, r6)
                                r6 = 1
                                goto L2d
                            L2c:
                                r6 = 0
                            L2d:
                                if (r6 != 0) goto L77
                                com.ss.android.downloadlib.g.h$6 r6 = com.ss.android.downloadlib.g.h.AnonymousClass6.this
                                android.content.Context r6 = r2
                                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                                r0.<init>()
                                java.lang.String r2 = "market://details?id="
                                r0.append(r2)
                                com.ss.android.downloadlib.g.h$6 r3 = com.ss.android.downloadlib.g.h.AnonymousClass6.this
                                java.lang.String r3 = r1
                                r0.append(r3)
                                java.lang.String r0 = r0.toString()
                                android.net.Uri r0 = android.net.Uri.parse(r0)
                                com.ss.android.downloadlib.addownload.b.g r6 = com.ss.android.downloadlib.g.h.a(r6, r0)
                                com.ss.android.downloadlib.g.h$6 r0 = com.ss.android.downloadlib.g.h.AnonymousClass6.this
                                com.ss.android.downloadlib.addownload.b.e r0 = r3
                                com.ss.android.downloadlib.b.a.a(r6, r0, r1)
                                com.ss.android.downloadlib.g.h$6 r6 = com.ss.android.downloadlib.g.h.AnonymousClass6.this
                                com.ss.android.downloadlib.addownload.b.e r6 = r3
                                org.json.JSONObject r0 = r2
                                r1 = 10
                                r3 = 9
                                java.lang.StringBuilder r4 = new java.lang.StringBuilder
                                r4.<init>()
                                r4.append(r2)
                                com.ss.android.downloadlib.g.h$6 r2 = com.ss.android.downloadlib.g.h.AnonymousClass6.this
                                java.lang.String r2 = r1
                                r4.append(r2)
                                java.lang.String r2 = r4.toString()
                                com.ss.android.downloadlib.g.h.a(r6, r0, r1, r3, r2)
                            L77:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.g.h.AnonymousClass6.AnonymousClass1.a(java.lang.String):void");
                        }

                        @Override // com.ss.android.download.api.config.r
                        public void a(Throwable th) {
                            com.ss.android.downloadlib.b.a.a(h.a(context, Uri.parse("market://details?id=" + str)), eVar, true);
                            m.a(jSONObject, "ttdownloader_message", th != null ? th.getMessage() : "null");
                            h.b(eVar, jSONObject, 11, 9, "market://details?id=" + str);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    h.b(eVar, jSONObject, 4, 9, "market://details?id=" + str);
                }
            }
        });
    }

    private static void f(Context context, com.ss.android.downloadlib.addownload.b.e eVar, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            TTDelegateActivity.a(str, eVar.a);
        } catch (Exception unused) {
            com.ss.android.downloadlib.b.a.a(a(context, Uri.parse("market://details?id=" + str)), eVar, true);
            b(eVar, jSONObject, 13, 10, "market://details?id=" + str);
        }
    }

    private static void g(final Context context, final com.ss.android.downloadlib.addownload.b.e eVar, final String str) {
        com.ss.android.downloadlib.d.a().a(new Runnable() { // from class: com.ss.android.downloadlib.g.h.7
            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObjectJ = com.ss.android.downloadlib.addownload.k.j();
                String strOptString = jSONObjectJ.optString(com.igexin.push.core.d.c.d);
                final JSONObject jSONObject = new JSONObject();
                String strA = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("x"), strOptString);
                JSONObject jSONObject2 = new JSONObject();
                m.a(jSONObject2, "t", "v");
                m.a(jSONObject2, com.igexin.push.core.d.c.c, str);
                byte[] bytes = jSONObject2.toString().getBytes();
                com.ss.android.downloadlib.addownload.k.e().a(strA, com.ss.android.downloadlib.addownload.k.s().a(bytes, bytes.length), "application/octet-stream;tt-data=a", 0, new r() { // from class: com.ss.android.downloadlib.g.h.7.1
                    @Override // com.ss.android.download.api.config.r
                    public void a(String str2) {
                        h.b(context, str, str2, eVar, jSONObject);
                    }

                    @Override // com.ss.android.download.api.config.r
                    public void a(Throwable th) {
                        com.ss.android.downloadlib.b.a.a(h.a(context, Uri.parse("market://details?id=" + str)), eVar, true);
                        m.a(jSONObject, "ttdownloader_message", th != null ? th.getMessage() : "null");
                        h.b(eVar, jSONObject, 7, 5, "market://details?id=" + str);
                    }
                });
            }
        });
    }

    private static void h(Context context, com.ss.android.downloadlib.addownload.b.e eVar, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            TTDelegateActivity.a(str, eVar.a, "need_comment");
        } catch (Exception unused) {
            com.ss.android.downloadlib.b.a.a(a(context, Uri.parse("market://details?id=" + str)), eVar, true);
            b(eVar, jSONObject, 9, 8, "market://details?id=" + str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, String str, String str2, com.ss.android.downloadlib.addownload.b.e eVar, JSONObject jSONObject) {
        m.a(jSONObject, "ttdownloader_type", (Object) 5);
        try {
            String strA = com.ss.android.socialbase.appdownloader.f.c.a(new JSONObject(str2).optString("a"));
            if (!TextUtils.isEmpty(strA)) {
                TTDelegateActivity.a(str, eVar.a, strA, jSONObject);
            } else {
                com.ss.android.downloadlib.b.a.a(a(context, Uri.parse("market://details?id=" + str)), eVar, true);
                b(eVar, jSONObject, 5, 5, "market://details?id=" + str);
            }
        } catch (Exception unused) {
            com.ss.android.downloadlib.b.a.a(a(context, Uri.parse("market://details?id=" + str)), eVar, true);
            b(eVar, jSONObject, 6, 5, "market://details?id=" + str);
        }
    }

    public static void a(Activity activity, String str, long j, String str2, String str3) {
        JSONObject jSONObject;
        int i;
        try {
            jSONObject = new JSONObject(str3);
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
        }
        com.ss.android.downloadlib.addownload.b.e eVarE = com.ss.android.downloadlib.addownload.b.f.a().e(j);
        try {
            JSONObject jSONObjectJ = com.ss.android.downloadlib.addownload.k.j();
            boolean zA = com.ss.android.socialbase.appdownloader.f.a.a(jSONObjectJ, activity, com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("bg"), jSONObjectJ.optString(com.igexin.push.core.d.c.d)));
            HashMap<String, String> mapB = m.b(new JSONObject(str2));
            if (zA && !mapB.isEmpty() && a(activity, str, mapB)) {
                b(eVarE, jSONObject, -1, 5, "market://details?id=" + str);
                com.ss.android.downloadlib.b.a.a("am_v1", jSONObject, eVarE, true);
                return;
            }
            if (zA) {
                i = mapB.isEmpty() ? 1 : 2;
            } else {
                i = 3;
            }
            b(eVarE, jSONObject, i, 5, "market://details?id=" + str);
            com.ss.android.downloadlib.b.a.a(a((Context) activity, Uri.parse("market://details?id=" + str)), eVarE, true);
        } catch (Exception unused2) {
            com.ss.android.downloadlib.b.a.a(a(com.ss.android.downloadlib.addownload.k.a(), Uri.parse("market://details?id=" + str)), eVarE, true);
            b(eVarE, jSONObject, 4, 5, "market://details?id=" + str);
        }
    }

    public static void a(Activity activity, String str, long j, String str2) {
        com.ss.android.downloadlib.addownload.b.e eVarE = com.ss.android.downloadlib.addownload.b.f.a().e(j);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObjectJ = com.ss.android.downloadlib.addownload.k.j();
        String strA = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("bv"), jSONObjectJ.optString(com.igexin.push.core.d.c.d));
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("market").authority(BaseConstants.MARKET_URI_AUTHORITY_DETAIL).appendQueryParameter("id", str);
        if (!TextUtils.isEmpty(strA)) {
            builder.appendQueryParameter(strA, str2);
        }
        if (a(activity, builder.build())) {
            b(eVarE, jSONObject, -1, 8, "market://details?id=" + str);
            com.ss.android.downloadlib.b.a.a("am_v2", jSONObject, eVarE, true);
            return;
        }
        b(eVarE, jSONObject, 2, 8, "market://details?id=" + str);
        com.ss.android.downloadlib.b.a.a(a((Context) activity, Uri.parse("market://details?id=" + str)), eVarE, true);
    }

    public static boolean a(Activity activity, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(uri);
        intent.putExtra("start_only_for_android", true);
        String strJ = com.ss.android.socialbase.appdownloader.f.e.j();
        if (m.e(com.ss.android.downloadlib.addownload.k.a(), strJ)) {
            intent.setPackage(strJ);
        }
        if (!m.a(com.ss.android.downloadlib.addownload.k.a(), intent)) {
            return false;
        }
        try {
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            com.ss.android.downloadlib.e.c.a().a(e, "start v2");
            return false;
        }
    }

    public static boolean b(Activity activity, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(uri);
        intent.putExtra("start_only_for_android", true);
        String strJ = com.ss.android.socialbase.appdownloader.f.e.j();
        if (m.e(com.ss.android.downloadlib.addownload.k.a(), strJ)) {
            intent.setPackage(strJ);
        }
        if (!m.a(com.ss.android.downloadlib.addownload.k.a(), intent)) {
            return false;
        }
        try {
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            com.ss.android.downloadlib.e.c.a().a(e, "start HM1");
            return false;
        }
    }

    public static boolean c(Activity activity, Uri uri) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(uri);
        intent.putExtra("start_only_for_android", true);
        String strJ = com.ss.android.socialbase.appdownloader.f.e.j();
        if (m.e(com.ss.android.downloadlib.addownload.k.a(), strJ)) {
            intent.setPackage(strJ);
        }
        if (!m.a(com.ss.android.downloadlib.addownload.k.a(), intent)) {
            return false;
        }
        try {
            activity.startActivity(intent);
            return true;
        } catch (Exception e) {
            com.ss.android.downloadlib.e.c.a().a(e, "start HM2");
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(com.ss.android.downloadlib.addownload.b.e eVar, JSONObject jSONObject, int i, int i2, String str) {
        m.a(jSONObject, MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE, Integer.valueOf(i));
        m.a(jSONObject, "ttdownloader_type", Integer.valueOf(i2));
        m.a(jSONObject, "rmu", str);
        m.a(jSONObject, com.ss.android.socialbase.appdownloader.f.e.j(), Integer.valueOf(m.b(com.ss.android.downloadlib.addownload.k.a(), com.ss.android.socialbase.appdownloader.f.e.j())));
        com.ss.android.downloadlib.d.a.a().b("am_result", jSONObject, eVar);
    }

    static com.ss.android.downloadlib.addownload.b.g b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return new com.ss.android.downloadlib.addownload.b.g(4, 11);
        }
        if (context == null) {
            context = com.ss.android.downloadlib.addownload.k.a();
        }
        Intent intentG = m.g(context, str);
        if (intentG == null) {
            return new com.ss.android.downloadlib.addownload.b.g(4, 22);
        }
        intentG.putExtra("start_only_for_android", true);
        try {
            context.startActivity(intentG);
            return new com.ss.android.downloadlib.addownload.b.g(3);
        } catch (Exception unused) {
            return new com.ss.android.downloadlib.addownload.b.g(4, 23);
        }
    }

    static com.ss.android.downloadlib.addownload.b.g a(Context context, String str, com.ss.android.downloadad.api.a.a aVar) {
        Intent intentA = a(context, aVar, str, 1, (String) null);
        if (intentA != null) {
            l.a().a(a, "tryOpenByPackage", "成功构造了跳转中转Activity的intent");
            com.ss.android.downloadlib.addownload.b.g gVarA = a(intentA, true, context, str, aVar);
            if (gVarA.a() == 3) {
                return gVarA;
            }
        }
        Intent intentG = m.g(context, str);
        if (intentG == null) {
            return new com.ss.android.downloadlib.addownload.b.g(4, 22);
        }
        return a(intentG, false, context, str, aVar);
    }

    private static com.ss.android.downloadlib.addownload.b.g a(Intent intent, boolean z, Context context, String str, com.ss.android.downloadad.api.a.a aVar) {
        if (Build.VERSION.SDK_INT >= 26 && com.ss.android.downloadlib.addownload.k.j().optInt("open_package_mode") == 1 && com.ss.android.downloadlib.addownload.k.m() != null && com.ss.android.downloadlib.addownload.k.m().a() && aVar.q() && !z) {
            TTDelegateActivity.b(str, aVar);
            return new com.ss.android.downloadlib.addownload.b.g(3);
        }
        intent.putExtra("start_only_for_android", true);
        try {
            context.startActivity(intent);
            return new com.ss.android.downloadlib.addownload.b.g(3);
        } catch (Exception e) {
            if (z) {
                l.a().b(a, "realTryOpenByPackage", "调起中转Activity出现异常，可能是没接转化SDK，回退普通调起" + e.getMessage());
                return new com.ss.android.downloadlib.addownload.b.g(8, 23);
            }
            l.a().b(a, "realTryOpenByPackage", "包名调起失败了，抛出异常" + e.getMessage());
            return new com.ss.android.downloadlib.addownload.b.g(4, 23);
        }
    }

    static com.ss.android.downloadlib.addownload.b.g a(String str, com.ss.android.downloadad.api.a.a aVar) {
        return a(com.ss.android.downloadlib.addownload.k.a(), str, aVar);
    }

    static com.ss.android.downloadlib.addownload.b.g b(String str, com.ss.android.downloadad.api.a.a aVar) {
        if (TextUtils.isEmpty(str)) {
            return new com.ss.android.downloadlib.addownload.b.g(2, 21);
        }
        Context contextA = com.ss.android.downloadlib.addownload.k.a();
        Intent intentA = null;
        String packageName = aVar.u().getPackageName();
        if (!TextUtils.isEmpty(packageName)) {
            l.a().a(a, "tryOpenByUrl", "获取到跳转中转Activity的intent");
            intentA = a(contextA, aVar, packageName, 2, str);
            if (intentA != null) {
                com.ss.android.downloadlib.addownload.b.g gVarA = a(contextA, intentA, aVar, true, str);
                if (gVarA.a() == 1) {
                    return gVarA;
                }
            }
        }
        intentA.setData(Uri.parse(str));
        intentA.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        intentA.putExtra(AdBaseConstants.MARKET_OPEN_INTENT_OPEN_URL, str);
        intentA.putExtra("start_only_for_android", true);
        if (DownloadSetting.obtainGlobal().optBugFix("fix_app_link_flag")) {
            intentA.addFlags(67108864);
        }
        return a(contextA, intentA, aVar, false, str);
    }

    private static com.ss.android.downloadlib.addownload.b.g a(Context context, Intent intent, com.ss.android.downloadad.api.a.a aVar, boolean z, String str) {
        if (context == null) {
            context = com.ss.android.downloadlib.addownload.k.a();
        }
        if (m.b(context, intent)) {
            if (com.ss.android.downloadlib.addownload.k.j().optInt("open_url_mode") == 0 && com.ss.android.downloadlib.addownload.k.m() != null && com.ss.android.downloadlib.addownload.k.m().a() && Build.VERSION.SDK_INT >= 26 && aVar.q() && !z) {
                TTDelegateActivity.a(str, aVar);
                return new com.ss.android.downloadlib.addownload.b.g(1);
            }
            try {
                context.startActivity(intent);
                return new com.ss.android.downloadlib.addownload.b.g(1);
            } catch (Exception e) {
                if (z) {
                    l.a().b(a, "realTryOpenByUrl", "商店直投注入clickId优化url调起场景，抛出异常，没接转化SDK，回退普通调起" + e.getMessage());
                    return new com.ss.android.downloadlib.addownload.b.g(9);
                }
                l.a().b(a, "realTryOpenByUrl", "url调起失败了，抛出异常" + e.getMessage());
                return new com.ss.android.downloadlib.addownload.b.g(2);
            }
        }
        return new com.ss.android.downloadlib.addownload.b.g(2, 24);
    }

    static com.ss.android.downloadlib.addownload.b.g a(com.ss.android.downloadad.api.a.b bVar, String str, String str2) {
        com.ss.android.downloadlib.addownload.b.g gVarB = b(str, bVar);
        return (com.ss.android.downloadlib.b.f.a(bVar) && gVarB.a() == 2) ? a(str2, bVar) : gVarB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, com.ss.android.downloadlib.addownload.b.e eVar, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            TTDelegateActivity.b(str, eVar.a, str2);
        } catch (Exception unused) {
            com.ss.android.downloadlib.b.a.a(a(context, Uri.parse("market://details?id=" + str)), eVar, true);
            b(eVar, jSONObject, 12, 9, "market://details?id=" + str);
        }
    }

    public static void b(Activity activity, String str, long j, String str2) {
        com.ss.android.downloadlib.addownload.b.e eVarE = com.ss.android.downloadlib.addownload.b.f.a().e(j);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObjectJ = com.ss.android.downloadlib.addownload.k.j();
        String strOptString = jSONObjectJ.optString(com.igexin.push.core.d.c.d);
        String strA = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("bz"), strOptString);
        String strA2 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString(com.igexin.push.core.b.ab), strOptString);
        String strA3 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("cb"), strOptString);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("hiapplink").authority("com.huawei.appmarket");
        if (!TextUtils.isEmpty(strA)) {
            builder.appendQueryParameter(strA, str2);
        }
        if (!TextUtils.isEmpty(strA2) && !TextUtils.isEmpty(strA3)) {
            builder.appendQueryParameter(strA2, strA3);
        }
        if (b(activity, builder.build())) {
            b(eVarE, jSONObject, -1, 9, "market://details?id=" + str);
            com.ss.android.downloadlib.b.a.a("am_hr", jSONObject, eVarE, true);
            return;
        }
        b(eVarE, jSONObject, 2, 9, "market://details?id=" + str);
        com.ss.android.downloadlib.b.a.a(a((Context) activity, Uri.parse("market://details?id=" + str)), eVarE, true);
    }

    public static void a(Activity activity, String str, long j) {
        com.ss.android.downloadlib.addownload.b.e eVarE = com.ss.android.downloadlib.addownload.b.f.a().e(j);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObjectJ = com.ss.android.downloadlib.addownload.k.j();
        String strOptString = jSONObjectJ.optString(com.igexin.push.core.d.c.d);
        String strA = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString(com.igexin.push.core.b.ab), strOptString);
        String strA2 = com.ss.android.socialbase.appdownloader.f.c.a(jSONObjectJ.optString("cc"), strOptString);
        StringBuilder sb = new StringBuilder("market://details?id=");
        if (!TextUtils.isEmpty(strA) && !TextUtils.isEmpty(strA2)) {
            sb.append(str);
            sb.append(com.alipay.sdk.sys.a.b);
            sb.append(strA);
            sb.append("=");
            sb.append(strA2);
        }
        if (c(activity, Uri.parse(sb.toString()))) {
            b(eVarE, jSONObject, -1, 10, "market://details?id=" + str);
            com.ss.android.downloadlib.b.a.a("am_hr2", jSONObject, eVarE, true);
            return;
        }
        b(eVarE, jSONObject, 2, 10, "market://details?id=" + str);
        com.ss.android.downloadlib.b.a.a(a((Context) activity, Uri.parse("market://details?id=" + str)), eVarE, true);
    }

    static boolean c(Context context, String str) {
        if (context == null) {
            return false;
        }
        try {
            Uri uri = Uri.parse(str);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(uri);
            intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            intent.putExtra(AdBaseConstants.MARKET_OPEN_INTENT_OPEN_URL, str);
            intent.putExtra("start_only_for_android", true);
            context.startActivity(intent);
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c(String str) {
        Matcher matcher = Pattern.compile("<input[\\s\\S]*>\\n").matcher(str);
        String strGroup = matcher.find() ? matcher.group() : "";
        if (!strGroup.equals(null) && strGroup.length() > 0) {
            for (String str2 : strGroup.split("\\n")) {
                if (str2.startsWith("<input")) {
                    for (String str3 : str2.split("\\s")) {
                        if (str3.startsWith("value")) {
                            return str3.substring(7, str3.length() - 1);
                        }
                    }
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(String str) {
        List<String> pathSegments = Uri.parse(str).getPathSegments();
        if (pathSegments.size() > 0) {
            return pathSegments.get(pathSegments.size() - 1);
        }
        return null;
    }

    private static Intent a(Context context, com.ss.android.downloadad.api.a.a aVar, String str, int i, String str2) {
        if (!aVar.c() || aVar.w() == null || aVar.w().getDownloadMode() != 2 || aVar.u() == null || e.a(aVar).optInt("app_link_market_open_add_info", 0) != 1) {
            return null;
        }
        String strC = com.ss.android.downloadlib.addownload.i.c(aVar.u());
        String strD = com.ss.android.downloadlib.addownload.i.d(aVar.u());
        Intent intent = new Intent();
        intent.setClassName(str, AdBaseConstants.MARKET_OPEN_BRIDGE_ACTIVITY);
        ResolveInfo resolveInfoResolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (TextUtils.isEmpty(strC) || resolveInfoResolveActivity == null) {
            return null;
        }
        intent.putExtra(AdBaseConstants.MARKET_OPEN_CLICK_ID, strC);
        if (!(context instanceof Activity)) {
            intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        }
        if (!TextUtils.isEmpty(strD)) {
            intent.putExtra(AdBaseConstants.MARKET_OPEN_INTENT_EXTRA, strD);
        }
        if (i == 2 && !TextUtils.isEmpty(str2)) {
            intent.putExtra(AdBaseConstants.MARKET_OPEN_INTENT_OPEN_URL, str2);
        }
        return intent;
    }
}
