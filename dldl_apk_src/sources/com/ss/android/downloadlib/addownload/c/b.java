package com.ss.android.downloadlib.addownload.c;

import com.ss.android.downloadlib.addownload.b.f;
import com.ss.android.downloadlib.addownload.b.i;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class b implements Runnable {
    private DownloadInfo a;

    public b(DownloadInfo downloadInfo) {
        this.a = downloadInfo;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        final com.ss.android.downloadad.api.a.b bVarA;
        if (this.a == null || (bVarA = f.a().a(this.a)) == null) {
            return;
        }
        com.ss.android.downloadlib.d.a.a().a("cleanspace_task", bVarA);
        long jLongValue = Double.valueOf((com.ss.android.downloadlib.g.e.a(this.a.getId()) + 1.0d) * this.a.getTotalBytes()).longValue() - this.a.getCurBytes();
        long jB = m.b(0L);
        if (k.n() != null) {
            k.n().e();
        }
        c.a();
        c.b();
        if (com.ss.android.downloadlib.g.e.g(bVarA.s())) {
            c.a(k.a());
        }
        long jB2 = m.b(0L);
        if (jB2 >= jLongValue) {
            bVarA.l("1");
            i.a().a(bVarA);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.putOpt("quite_clean_size", Long.valueOf(jB2 - jB));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            com.ss.android.downloadlib.d.a.a().a("cleanspace_download_after_quite_clean", jSONObject, bVarA);
            Downloader.getInstance(k.a()).restart(this.a.getId());
            return;
        }
        if (k.n() != null) {
            bVarA.d(false);
            d.a().a(bVarA.a(), new e() { // from class: com.ss.android.downloadlib.addownload.c.b.1
            });
            if (k.n().a(this.a.getId(), this.a.getUrl(), true, jLongValue)) {
                bVarA.e(true);
                return;
            }
            return;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt("show_dialog_result", 3);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        com.ss.android.downloadlib.d.a.a().a("cleanspace_window_show", jSONObject2, bVarA);
    }
}
