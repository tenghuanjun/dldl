package com.ss.android.downloadlib.addownload.d;

import android.text.TextUtils;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class d {
    private static d a;
    private List<g> b;

    public static d a() {
        if (a == null) {
            synchronized (k.class) {
                if (a == null) {
                    a = new d();
                }
            }
        }
        return a;
    }

    private d() {
        ArrayList arrayList = new ArrayList();
        this.b = arrayList;
        arrayList.add(new e());
        this.b.add(new b());
        this.b.add(new c());
    }

    public void a(com.ss.android.downloadad.api.a.b bVar, int i, h hVar, com.ss.android.downloadlib.addownload.a.c cVar) {
        DownloadInfo downloadInfoB;
        List<g> list = this.b;
        if (list == null || list.size() == 0 || bVar == null) {
            hVar.a(bVar);
        }
        if (!TextUtils.isEmpty(bVar.af())) {
            downloadInfoB = com.ss.android.downloadlib.i.a(com.ss.android.downloadlib.addownload.k.a()).a(bVar.af(), null, true);
        } else {
            downloadInfoB = com.ss.android.downloadlib.i.a(com.ss.android.downloadlib.addownload.k.a()).b(bVar.a());
        }
        if (downloadInfoB == null) {
            downloadInfoB = Downloader.getInstance(com.ss.android.downloadlib.addownload.k.a()).getDownloadInfo(bVar.s());
        }
        if (downloadInfoB == null || !"application/vnd.android.package-archive".equals(downloadInfoB.getMimeType())) {
            hVar.a(bVar);
            return;
        }
        if (new j().a(bVar, i, hVar)) {
            return;
        }
        Iterator<g> it = this.b.iterator();
        while (it.hasNext()) {
            if (it.next().a(bVar, i, hVar, cVar)) {
                return;
            }
        }
        hVar.a(bVar);
    }
}
