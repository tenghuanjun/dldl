package com.ss.android.downloadlib.addownload.d;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class c implements g {
    private static com.ss.android.downloadlib.addownload.a.d a;
    private static com.ss.android.downloadlib.addownload.a.c b;

    public static com.ss.android.downloadlib.addownload.a.d a() {
        return a;
    }

    public static com.ss.android.downloadlib.addownload.a.c b() {
        return b;
    }

    public static void a(com.ss.android.downloadlib.addownload.a.c cVar) {
        b = cVar;
    }

    @Override // com.ss.android.downloadlib.addownload.d.g
    public boolean a(final com.ss.android.downloadad.api.a.b bVar, int i, final h hVar, final com.ss.android.downloadlib.addownload.a.c cVar) {
        DownloadInfo downloadInfoB;
        String str;
        if (bVar == null || !a(bVar)) {
            return false;
        }
        if (!TextUtils.isEmpty(bVar.af())) {
            downloadInfoB = com.ss.android.downloadlib.i.a(com.ss.android.downloadlib.addownload.k.a()).a(bVar.af(), null, true);
        } else {
            downloadInfoB = com.ss.android.downloadlib.i.a(com.ss.android.downloadlib.addownload.k.a()).b(bVar.a());
        }
        if (downloadInfoB == null) {
            return false;
        }
        long curBytes = downloadInfoB.getCurBytes();
        long totalBytes = downloadInfoB.getTotalBytes();
        if (curBytes < 0 || totalBytes <= 0) {
            return false;
        }
        final int iA = com.ss.android.downloadlib.addownload.j.a(downloadInfoB.getId(), (int) ((100 * curBytes) / totalBytes));
        final int i2 = (int) (curBytes / 1048576);
        boolean z = iA > a(bVar.s());
        a = new com.ss.android.downloadlib.addownload.a.d() { // from class: com.ss.android.downloadlib.addownload.d.c.1
            @Override // com.ss.android.downloadlib.addownload.a.d
            public void a() {
                com.ss.android.downloadlib.addownload.a.d unused = c.a = null;
                c.this.a(iA, i2, i2, bVar, "download_percent_cancel", "confirm");
            }

            @Override // com.ss.android.downloadlib.addownload.a.d
            public void b() {
                com.ss.android.downloadlib.addownload.a.d unused = c.a = null;
                c.this.a(iA, i2, i2, bVar, "download_percent_cancel", "cancel");
                hVar.a(bVar);
            }
        };
        String strA = m.a(com.ss.android.downloadlib.addownload.j.a(bVar.s(), curBytes, totalBytes));
        if (z) {
            str = String.format("该任务已下载%s，仅需%s即可下载完成，是否继续？", strA, m.a(totalBytes - curBytes));
        } else {
            str = String.format("该任务已下载%s，即将下载完成，是否继续下载？", strA);
        }
        String str2 = str;
        if (cVar != null) {
            a(new com.ss.android.downloadlib.addownload.a.c() { // from class: com.ss.android.downloadlib.addownload.d.c.2
                @Override // com.ss.android.downloadlib.addownload.a.c
                public void a() {
                    com.ss.android.downloadlib.addownload.a.d unused = c.a = null;
                    c.this.a(iA, i2, i2, bVar, "download_percent_cancel", RequestParameters.SUBRESOURCE_DELETE);
                    cVar.a();
                }
            });
        }
        TTDelegateActivity.b(bVar, str2, "继续", "暂停", "删除");
        return true;
    }

    private int a(int i) {
        return DownloadSetting.obtain(i).optInt("cancel_pause_optimise_download_percent_value", 50);
    }

    private boolean a(com.ss.android.downloadad.api.a.a aVar) {
        return com.ss.android.downloadlib.g.e.a(aVar).optInt("cancel_pause_optimise_download_percent_retain_switch", 0) == 1 && aVar.q();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, int i3, com.ss.android.downloadad.api.a.b bVar, String str, String str2) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("pause_optimise_type", str);
            jSONObject.putOpt("pause_optimise_action", str2);
            jSONObject.putOpt("download_percent", Integer.valueOf(i));
            jSONObject.putOpt("download_current_bytes", Integer.valueOf(i2));
            jSONObject.putOpt("download_total_bytes", Integer.valueOf(i3));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        com.ss.android.downloadlib.d.a.a().a("pause_cancel_optimise", jSONObject, bVar);
    }
}
