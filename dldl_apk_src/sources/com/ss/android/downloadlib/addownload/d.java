package com.ss.android.downloadlib.addownload;

import android.os.Handler;
import com.ss.android.downloadlib.addownload.e;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.network.IFetchHttpHeadInfoListener;
import com.ss.android.socialbase.downloader.network.connectionpool.DownloadPreconnecter;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class d {
    private Handler a;
    private com.ss.android.downloadlib.addownload.b.e b;
    private AtomicBoolean c = new AtomicBoolean(false);
    private AtomicBoolean d = new AtomicBoolean(false);

    d(Handler handler) {
        this.a = handler;
    }

    public void a(com.ss.android.downloadlib.addownload.b.e eVar) {
        this.b = eVar;
    }

    public boolean a() {
        return this.d.get();
    }

    public void a(boolean z) {
        this.d.set(z);
    }

    void a(final int i, final long j, long j2, final e.a aVar) {
        this.d.set(false);
        if (aVar == null) {
            return;
        }
        if (!com.ss.android.downloadlib.g.e.f(i) || !com.ss.android.downloadlib.g.e.e(i)) {
            aVar.a();
            return;
        }
        long jC = com.ss.android.downloadlib.g.e.c(i);
        this.c.set(false);
        final String downloadUrl = this.b.b.getDownloadUrl();
        com.ss.android.downloadad.api.a.b bVarB = com.ss.android.downloadlib.addownload.b.f.a().b(downloadUrl);
        if (bVarB == null) {
            bVarB = new com.ss.android.downloadad.api.a.b(this.b.b, this.b.c, this.b.d, 0);
            com.ss.android.downloadlib.addownload.b.f.a().a(bVarB);
        }
        final com.ss.android.downloadad.api.a.b bVar = bVarB;
        bVar.e(false);
        if (k.n() != null) {
            k.n().a(bVar.b());
        }
        com.ss.android.downloadlib.addownload.c.d.a().a(bVar.a());
        boolean zD = com.ss.android.downloadlib.g.e.d(i);
        if (j2 > 0) {
            a(i, downloadUrl, j2, bVar, j, aVar);
        } else if (zD) {
            a(downloadUrl, bVar, new e.b() { // from class: com.ss.android.downloadlib.addownload.d.1
                @Override // com.ss.android.downloadlib.addownload.e.b
                public void a(long j3) throws Throwable {
                    d.this.a(i, downloadUrl, j3, bVar, j, aVar);
                }
            });
        } else {
            jC = 0;
        }
        this.a.postDelayed(new Runnable() { // from class: com.ss.android.downloadlib.addownload.d.2
            @Override // java.lang.Runnable
            public void run() {
                if (d.this.c.get()) {
                    return;
                }
                d.this.c.set(true);
                aVar.a();
            }
        }, jC);
    }

    private void a(String str, com.ss.android.downloadad.api.a.b bVar, final e.b bVar2) {
        if (bVar2 == null) {
            return;
        }
        DownloadPreconnecter.asyncFetchHttpHeadInfo(str, new IFetchHttpHeadInfoListener() { // from class: com.ss.android.downloadlib.addownload.d.3
            @Override // com.ss.android.socialbase.downloader.network.IFetchHttpHeadInfoListener
            public void onFetchFinished(Map<String, String> map) {
                if (d.this.c.get()) {
                    return;
                }
                d.this.c.set(true);
                long jA = d.this.a(map);
                if (jA > 0) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.putOpt("apk_size", Long.valueOf(jA));
                        jSONObject.putOpt("available_space", Long.valueOf(d.d()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                bVar2.a(jA);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long a(Map<String, String> map) {
        if (map != null && map.size() != 0) {
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if ("content-length".equalsIgnoreCase(key)) {
                        return Long.parseLong(value);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, long j, final com.ss.android.downloadad.api.a.b bVar, long j2, final e.a aVar) throws Throwable {
        this.c.set(true);
        boolean zA = false;
        if (j > 0) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("apk_size", Long.valueOf(j));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            long jLongValue = (Double.valueOf((com.ss.android.downloadlib.g.e.a(i) + 1.0d) * j).longValue() + com.ss.android.downloadlib.g.e.b(i)) - j2;
            long jD = d();
            if (jD < jLongValue) {
                a(bVar, jSONObject, jLongValue, jD);
                a(bVar);
                long jD2 = d();
                if (jD2 < jLongValue) {
                    bVar.d(true);
                    final String strA = bVar.a();
                    com.ss.android.downloadlib.addownload.c.d.a().a(strA, new com.ss.android.downloadlib.addownload.c.e() { // from class: com.ss.android.downloadlib.addownload.d.4
                    });
                    zA = a(i, bVar, str, jLongValue);
                    if (zA) {
                        bVar.e(true);
                    }
                } else {
                    b(bVar, jSONObject, jD, jD2);
                }
            }
        }
        if (zA) {
            return;
        }
        this.a.post(new Runnable() { // from class: com.ss.android.downloadlib.addownload.d.5
            @Override // java.lang.Runnable
            public void run() {
                aVar.a();
            }
        });
    }

    private boolean a(int i, com.ss.android.downloadad.api.a.b bVar, String str, long j) {
        if (!com.ss.android.downloadlib.g.e.f(i)) {
            return false;
        }
        if (k.n() != null) {
            return k.n().a(i, str, true, j);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("show_dialog_result", 3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.ss.android.downloadlib.d.a.a().a("cleanspace_window_show", jSONObject, bVar);
        return false;
    }

    public static boolean a(final DownloadInfo downloadInfo, long j) {
        int id = downloadInfo.getId();
        boolean zA = false;
        if (!com.ss.android.downloadlib.g.e.f(id)) {
            return false;
        }
        if (k.n() != null && (zA = k.n().a(id, downloadInfo.getUrl(), false, j))) {
            com.ss.android.downloadlib.addownload.c.d.a().a(downloadInfo.getUrl(), new com.ss.android.downloadlib.addownload.c.e() { // from class: com.ss.android.downloadlib.addownload.d.6
            });
        }
        return zA;
    }

    public static JSONObject a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("clean_space_install_params", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public static void a(int i) {
        if (com.ss.android.downloadlib.g.e.f(i) && k.n() != null && k.n().b()) {
            k.n().c();
        }
    }

    public static long b() {
        if (k.n() != null) {
            return k.n().a();
        }
        return 0L;
    }

    private static void a(com.ss.android.downloadad.api.a.b bVar) throws Throwable {
        long jD = d();
        if (k.n() != null) {
            k.n().e();
        }
        com.ss.android.downloadlib.addownload.c.c.a();
        com.ss.android.downloadlib.addownload.c.c.b();
        if (com.ss.android.downloadlib.g.e.g(bVar.s())) {
            com.ss.android.downloadlib.addownload.c.c.a(k.a());
        }
        long jD2 = d();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("quite_clean_size", Long.valueOf(jD2 - jD));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.ss.android.downloadlib.d.a.a().a("clean_quite_finish", jSONObject, bVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long d() {
        return com.ss.android.downloadlib.g.m.b(0L);
    }

    private void a(com.ss.android.downloadad.api.a.b bVar, JSONObject jSONObject, long j, long j2) {
        try {
            jSONObject.putOpt("available_space", Long.valueOf(j2));
            jSONObject.putOpt("apk_download_need_size", Long.valueOf(j));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.ss.android.downloadlib.d.a.a().a("clean_space_no_enough_for_download", jSONObject, bVar);
    }

    private void b(com.ss.android.downloadad.api.a.b bVar, JSONObject jSONObject, long j, long j2) {
        bVar.l("1");
        com.ss.android.downloadlib.addownload.b.i.a().a(bVar);
        try {
            jSONObject.putOpt("quite_clean_size", Long.valueOf(j2 - j));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.ss.android.downloadlib.d.a.a().a("cleanspace_download_after_quite_clean", jSONObject, bVar);
    }
}
