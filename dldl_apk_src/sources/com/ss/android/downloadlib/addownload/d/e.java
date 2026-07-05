package com.ss.android.downloadlib.addownload.d;

import android.text.TextUtils;
import com.ss.android.downloadlib.addownload.n;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class e implements g {
    @Override // com.ss.android.downloadlib.addownload.d.g
    public boolean a(com.ss.android.downloadad.api.a.b bVar, int i, h hVar, com.ss.android.downloadlib.addownload.a.c cVar) {
        DownloadInfo downloadInfoB;
        if (bVar == null) {
            return false;
        }
        if (!TextUtils.isEmpty(bVar.af())) {
            downloadInfoB = com.ss.android.downloadlib.i.a(com.ss.android.downloadlib.addownload.k.a()).a(bVar.af(), null, true);
        } else {
            downloadInfoB = com.ss.android.downloadlib.i.a(com.ss.android.downloadlib.addownload.k.a()).b(bVar.a());
        }
        return n.a(bVar, downloadInfoB, i, hVar, true, cVar);
    }
}
