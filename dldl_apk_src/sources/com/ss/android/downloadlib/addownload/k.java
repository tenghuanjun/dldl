package com.ss.android.downloadlib.addownload;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import com.ss.android.download.api.config.o;
import com.ss.android.download.api.config.p;
import com.ss.android.download.api.config.q;
import com.ss.android.download.api.config.s;
import com.ss.android.download.api.config.u;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.model.a;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import java.io.File;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class k {
    public static final JSONObject a = new JSONObject();
    private static Context b;
    private static com.ss.android.download.api.config.g c;
    private static com.ss.android.download.api.config.c d;
    private static com.ss.android.download.api.config.l e;
    private static com.ss.android.download.api.config.h f;
    private static com.ss.android.download.api.config.i g;
    private static com.ss.android.download.api.config.j h;
    private static com.ss.android.download.api.model.a i;
    private static com.ss.android.download.api.config.b j;
    private static com.ss.android.socialbase.appdownloader.c.h k;
    private static com.ss.android.download.api.config.d l;
    private static com.ss.android.download.api.config.e m;
    private static o n;
    private static com.ss.android.download.api.config.k o;
    private static u p;
    private static com.ss.android.download.api.config.n q;
    private static com.ss.android.download.api.config.m r;
    private static p s;
    private static com.ss.android.download.api.b.a t;
    private static q u;
    private static s v;

    public static String o() {
        return "1.7.0";
    }

    public static void a(Context context) {
        if (context == null || context.getApplicationContext() == null) {
            throw new IllegalArgumentException("Context is null");
        }
        b = context.getApplicationContext();
    }

    public static void b(Context context) {
        if (b != null || context == null || context.getApplicationContext() == null) {
            return;
        }
        b = context.getApplicationContext();
    }

    public static Context a() {
        Context context = b;
        if (context != null) {
            return context;
        }
        throw new IllegalArgumentException("Context is null");
    }

    public static void a(com.ss.android.download.api.config.g gVar) {
        c = gVar;
    }

    public static void a(com.ss.android.download.api.config.l lVar) {
        e = lVar;
    }

    public static void a(com.ss.android.download.api.config.h hVar) {
        f = hVar;
    }

    public static void a(com.ss.android.download.api.config.i iVar) {
        g = iVar;
    }

    public static void a(com.ss.android.download.api.config.j jVar) {
        h = jVar;
    }

    public static void a(com.ss.android.download.api.model.a aVar) {
        i = aVar;
    }

    public static void a(com.ss.android.download.api.config.b bVar) {
        j = bVar;
    }

    public static com.ss.android.download.api.config.g b() {
        return c;
    }

    public static com.ss.android.download.api.config.c c() {
        if (d == null) {
            d = new com.ss.android.download.api.config.c() { // from class: com.ss.android.downloadlib.addownload.k.1
                @Override // com.ss.android.download.api.config.c
                public void a(Context context, DownloadModel downloadModel, DownloadController downloadController, DownloadEventConfig downloadEventConfig) {
                }

                @Override // com.ss.android.download.api.config.c
                public void a(Context context, DownloadModel downloadModel, DownloadController downloadController, DownloadEventConfig downloadEventConfig, String str, String str2) {
                }
            };
        }
        return d;
    }

    public static com.ss.android.download.api.config.l d() {
        if (e == null) {
            e = new com.ss.android.download.api.a.a();
        }
        return e;
    }

    public static com.ss.android.download.api.config.h e() {
        return f;
    }

    public static com.ss.android.download.api.config.i f() {
        if (g == null) {
            g = new com.ss.android.download.api.a.b();
        }
        return g;
    }

    public static com.ss.android.socialbase.appdownloader.c.h g() {
        if (k == null) {
            k = new com.ss.android.socialbase.appdownloader.c.h() { // from class: com.ss.android.downloadlib.addownload.k.2
                @Override // com.ss.android.socialbase.appdownloader.c.h
                public void a(DownloadInfo downloadInfo, BaseException baseException, int i2) {
                }
            };
        }
        return k;
    }

    public static o h() {
        return n;
    }

    public static p i() {
        if (s == null) {
            s = new p() { // from class: com.ss.android.downloadlib.addownload.k.3
                @Override // com.ss.android.download.api.config.p
                public void a(String str, int i2, JSONObject jSONObject) {
                }
            };
        }
        return s;
    }

    public static JSONObject j() {
        com.ss.android.download.api.config.j jVar = h;
        if (jVar == null || jVar.a() == null) {
            return a;
        }
        return h.a();
    }

    public static com.ss.android.download.api.model.a k() {
        if (i == null) {
            i = new a.C0086a().a();
        }
        return i;
    }

    public static com.ss.android.download.api.config.m l() {
        return r;
    }

    public static com.ss.android.download.api.config.b m() {
        return j;
    }

    public static com.ss.android.download.api.config.n n() {
        return q;
    }

    public static void a(String str) {
        com.ss.android.socialbase.appdownloader.d.j().a(str);
    }

    public static com.ss.android.download.api.config.d p() {
        return l;
    }

    public static com.ss.android.download.api.config.e q() {
        return m;
    }

    public static com.ss.android.download.api.config.k r() {
        return o;
    }

    public static void a(q qVar) {
        u = qVar;
    }

    public static q s() {
        return u;
    }

    public static u t() {
        return p;
    }

    public static void a(com.ss.android.download.api.b.a aVar) {
        t = aVar;
    }

    public static com.ss.android.download.api.b.a u() {
        if (t == null) {
            t = new com.ss.android.download.api.b.a() { // from class: com.ss.android.downloadlib.addownload.k.4
                @Override // com.ss.android.download.api.b.a
                public void a(Throwable th, String str) {
                }
            };
        }
        return t;
    }

    public static s v() {
        if (v == null) {
            v = new s() { // from class: com.ss.android.downloadlib.addownload.k.5
                @Override // com.ss.android.download.api.config.s
                public void a(Context context, DownloadModel downloadModel, DownloadController downloadController, DownloadEventConfig downloadEventConfig, String str, int i2) {
                }
            };
        }
        return v;
    }

    public static String w() {
        try {
            int i2 = a().getApplicationInfo().targetSdkVersion;
            if (Build.VERSION.SDK_INT >= 29 && ((i2 == 29 && !Environment.isExternalStorageLegacy()) || i2 > 29)) {
                return a().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
            }
            return Environment.getExternalStorageDirectory().getPath() + File.separator + j().optString("default_save_dir_name", "ByteDownload");
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean x() {
        return (c == null || f == null || h == null || j == null || u == null) ? false : true;
    }
}
