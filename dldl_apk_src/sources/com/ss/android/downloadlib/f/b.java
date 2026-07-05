package com.ss.android.downloadlib.f;

import com.ss.android.downloadlib.addownload.b.f;
import com.ss.android.socialbase.appdownloader.c.i;
import com.ss.android.socialbase.appdownloader.c.j;
import com.ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class b implements j {
    @Override // com.ss.android.socialbase.appdownloader.c.j
    public void a(DownloadInfo downloadInfo, i iVar) {
        com.ss.android.downloadad.api.a.b bVarA;
        if (downloadInfo != null && (bVarA = f.a().a(downloadInfo)) != null) {
            downloadInfo.setLinkMode(bVarA.O());
        }
        if (iVar != null) {
            iVar.a();
        }
    }
}
