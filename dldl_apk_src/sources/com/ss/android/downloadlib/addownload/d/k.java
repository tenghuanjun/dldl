package com.ss.android.downloadlib.addownload.d;

import android.content.Context;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class k {
    private static k a;
    private List<i> b;

    public static k a() {
        if (a == null) {
            synchronized (k.class) {
                if (a == null) {
                    a = new k();
                }
            }
        }
        return a;
    }

    private k() {
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        arrayList.add(new j());
        this.b.add(new l());
        this.b.add(new f());
        this.b.add(new a());
    }

    public void a(com.ss.android.downloadad.api.a.b bVar, int i, h hVar) {
        DownloadInfo downloadInfoB;
        List<i> list = this.b;
        if (list == null || list.size() == 0 || bVar == null) {
            hVar.a(bVar);
            return;
        }
        if (!TextUtils.isEmpty(bVar.af())) {
            downloadInfoB = com.ss.android.downloadlib.i.a((Context) null).a(bVar.af(), null, true);
        } else {
            downloadInfoB = com.ss.android.downloadlib.i.a((Context) null).b(bVar.a());
        }
        if (downloadInfoB == null || !"application/vnd.android.package-archive".equals(downloadInfoB.getMimeType())) {
            hVar.a(bVar);
            return;
        }
        boolean z = DownloadSetting.obtain(bVar.s()).optInt("pause_optimise_switch", 0) == 1;
        for (i iVar : this.b) {
            if (z || (iVar instanceof l)) {
                if (iVar.a(bVar, i, hVar)) {
                    return;
                }
            }
        }
        hVar.a(bVar);
    }
}
