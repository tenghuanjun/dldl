package com.ss.android.downloadlib;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.config.OnItemClickListener;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class g {
    private static volatile g a;
    private long f;
    private final List<com.ss.android.downloadlib.addownload.g> c = new CopyOnWriteArrayList();
    private final Map<String, com.ss.android.downloadlib.addownload.g> d = new ConcurrentHashMap();
    private final CopyOnWriteArrayList<Object> e = new CopyOnWriteArrayList<>();
    private final Handler b = new Handler(Looper.getMainLooper());

    private g() {
    }

    public static g a() {
        if (a == null) {
            synchronized (g.class) {
                if (a == null) {
                    a = new g();
                }
            }
        }
        return a;
    }

    public void a(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel) {
        com.ss.android.downloadlib.addownload.g gVar;
        if (downloadModel == null || TextUtils.isEmpty(downloadModel.getDownloadUrl())) {
            return;
        }
        boolean z = k.j().optInt("filter_download_url_key", 0) == 1;
        String strA = com.ss.android.downloadlib.addownload.f.a().a(downloadModel.getDownloadUrl());
        if (z && !TextUtils.isEmpty(strA)) {
            gVar = this.d.get(strA);
            if (downloadModel instanceof AdDownloadModel) {
                AdDownloadModel adDownloadModel = (AdDownloadModel) downloadModel;
                if (TextUtils.isEmpty(adDownloadModel.getTaskKey())) {
                    adDownloadModel.setTaskKey(strA);
                }
            }
        } else {
            gVar = this.d.get(downloadModel.getDownloadUrl());
        }
        if (gVar != null) {
            gVar.b(context).b(i, downloadStatusChangeListener).b(downloadModel).a();
            return;
        }
        if (this.c.isEmpty()) {
            if (z) {
                if (!TextUtils.isEmpty(strA)) {
                    b(context, i, downloadStatusChangeListener, downloadModel, strA);
                    return;
                }
                String strA2 = com.ss.android.downloadlib.addownload.f.a().a(downloadModel);
                if (TextUtils.isEmpty(strA2)) {
                    c(context, i, downloadStatusChangeListener, downloadModel);
                    return;
                }
                b(context, i, downloadStatusChangeListener, downloadModel, strA2);
                if (downloadModel instanceof AdDownloadModel) {
                    AdDownloadModel adDownloadModel2 = (AdDownloadModel) downloadModel;
                    if (TextUtils.isEmpty(adDownloadModel2.getTaskKey())) {
                        adDownloadModel2.setTaskKey(strA2);
                        return;
                    }
                    return;
                }
                return;
            }
            c(context, i, downloadStatusChangeListener, downloadModel);
            return;
        }
        if (z) {
            if (!TextUtils.isEmpty(strA)) {
                a(context, i, downloadStatusChangeListener, downloadModel, strA);
                return;
            }
            String strA3 = com.ss.android.downloadlib.addownload.f.a().a(downloadModel);
            if (TextUtils.isEmpty(strA3)) {
                b(context, i, downloadStatusChangeListener, downloadModel);
                return;
            }
            a(context, i, downloadStatusChangeListener, downloadModel, strA3);
            if (downloadModel instanceof AdDownloadModel) {
                AdDownloadModel adDownloadModel3 = (AdDownloadModel) downloadModel;
                if (TextUtils.isEmpty(adDownloadModel3.getTaskKey())) {
                    adDownloadModel3.setTaskKey(strA3);
                    return;
                }
                return;
            }
            return;
        }
        b(context, i, downloadStatusChangeListener, downloadModel);
    }

    public com.ss.android.downloadlib.addownload.e a(String str) {
        com.ss.android.downloadlib.addownload.g gVar;
        Map<String, com.ss.android.downloadlib.addownload.g> map = this.d;
        if (map != null && map.size() != 0 && !TextUtils.isEmpty(str)) {
            if (k.j().optInt("filter_download_url_key", 0) == 1) {
                gVar = this.d.get(com.ss.android.downloadlib.addownload.f.a().a(str));
            } else {
                gVar = this.d.get(str);
            }
            if (gVar instanceof com.ss.android.downloadlib.addownload.e) {
                return (com.ss.android.downloadlib.addownload.e) gVar;
            }
        }
        return null;
    }

    private synchronized void b(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel) {
        if (this.c.size() <= 0) {
            c(context, i, downloadStatusChangeListener, downloadModel);
        } else {
            com.ss.android.downloadlib.addownload.g gVarRemove = this.c.remove(0);
            gVarRemove.b(context).b(i, downloadStatusChangeListener).b(downloadModel).a();
            this.d.put(downloadModel.getDownloadUrl(), gVarRemove);
        }
    }

    private synchronized void a(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel, String str) {
        if (this.c.size() <= 0) {
            b(context, i, downloadStatusChangeListener, downloadModel, str);
        } else {
            com.ss.android.downloadlib.addownload.g gVarRemove = this.c.remove(0);
            gVarRemove.b(context).b(i, downloadStatusChangeListener).b(downloadModel).a(str).a();
            this.d.put(str, gVarRemove);
            com.ss.android.downloadlib.addownload.f.a().a(str, downloadModel.getDownloadUrl());
        }
    }

    private void c(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel) {
        if (downloadModel == null) {
            return;
        }
        com.ss.android.downloadlib.addownload.e eVar = new com.ss.android.downloadlib.addownload.e();
        eVar.b(context).b(i, downloadStatusChangeListener).b(downloadModel).a();
        this.d.put(downloadModel.getDownloadUrl(), eVar);
    }

    private void b(Context context, int i, DownloadStatusChangeListener downloadStatusChangeListener, DownloadModel downloadModel, String str) {
        if (downloadModel == null) {
            return;
        }
        com.ss.android.downloadlib.addownload.e eVar = new com.ss.android.downloadlib.addownload.e();
        eVar.b(context).b(i, downloadStatusChangeListener).b(downloadModel).a(str).a();
        this.d.put(str, eVar);
        com.ss.android.downloadlib.addownload.f.a().a(str, downloadModel.getDownloadUrl());
    }

    public void a(String str, int i) {
        com.ss.android.downloadlib.addownload.g gVar;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        boolean z = k.j().optInt("filter_download_url_key", 0) == 1;
        String strA = com.ss.android.downloadlib.addownload.f.a().a(str);
        if (z && !TextUtils.isEmpty(strA)) {
            gVar = this.d.get(strA);
        } else {
            gVar = this.d.get(str);
        }
        if (gVar != null) {
            if (gVar.a(i)) {
                this.c.add(gVar);
                if (z && !TextUtils.isEmpty(strA)) {
                    this.d.remove(strA);
                    com.ss.android.downloadlib.addownload.f.a().b(strA);
                } else {
                    this.d.remove(str);
                }
            }
            c();
        }
    }

    public void a(String str, boolean z) {
        com.ss.android.downloadlib.addownload.g gVar;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        boolean z2 = k.j().optInt("filter_download_url_key", 0) == 1;
        String strA = com.ss.android.downloadlib.addownload.f.a().a(str);
        if (z2 && !TextUtils.isEmpty(strA)) {
            gVar = this.d.get(strA);
        } else {
            gVar = this.d.get(str);
        }
        if (gVar != null) {
            gVar.a(z);
        }
    }

    public void a(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController) {
        a(str, j, i, downloadEventConfig, downloadController, (OnItemClickListener) null, null);
    }

    public void a(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController, IDownloadButtonClickListener iDownloadButtonClickListener) {
        a(str, j, i, downloadEventConfig, downloadController, (OnItemClickListener) null, iDownloadButtonClickListener);
    }

    public void a(String str, long j, int i, DownloadEventConfig downloadEventConfig, DownloadController downloadController, OnItemClickListener onItemClickListener, IDownloadButtonClickListener iDownloadButtonClickListener) {
        com.ss.android.downloadlib.addownload.g gVar;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        boolean z = k.j().optInt("filter_download_url_key", 0) == 1;
        String strA = com.ss.android.downloadlib.addownload.f.a().a(str);
        if (z && !TextUtils.isEmpty(strA)) {
            gVar = this.d.get(strA);
        } else {
            gVar = this.d.get(str);
        }
        if (gVar != null) {
            gVar.a(j).b(downloadEventConfig).b(downloadController).a(onItemClickListener).a(iDownloadButtonClickListener).b(i);
        }
    }

    public void a(com.ss.android.download.api.download.a.a aVar) {
        if (aVar != null) {
            if (DownloadSetting.obtainGlobal().optBugFix("fix_listener_oom", false)) {
                this.e.add(new SoftReference(aVar));
            } else {
                this.e.add(aVar);
            }
        }
    }

    private void c() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.f < com.alipay.security.mobile.module.deviceinfo.e.a) {
            return;
        }
        this.f = jCurrentTimeMillis;
        if (this.c.isEmpty()) {
            return;
        }
        d();
    }

    private void d() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        for (com.ss.android.downloadlib.addownload.g gVar : this.c) {
            if (!gVar.b() && jCurrentTimeMillis - gVar.d() > com.alipay.security.mobile.module.deviceinfo.e.a) {
                gVar.h();
                arrayList.add(gVar);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        this.c.removeAll(arrayList);
    }

    public void a(final DownloadModel downloadModel, final DownloadController downloadController, final DownloadEventConfig downloadEventConfig) {
        this.b.post(new Runnable() { // from class: com.ss.android.downloadlib.g.1
            @Override // java.lang.Runnable
            public void run() {
                for (Object obj : g.this.e) {
                    if (obj instanceof com.ss.android.download.api.download.a.a) {
                        ((com.ss.android.download.api.download.a.a) obj).a(downloadModel, downloadController, downloadEventConfig);
                    } else if (obj instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) obj;
                        if (softReference.get() instanceof com.ss.android.download.api.download.a.a) {
                            ((com.ss.android.download.api.download.a.a) softReference.get()).a(downloadModel, downloadController, downloadEventConfig);
                        }
                    }
                }
            }
        });
    }

    public void a(final DownloadInfo downloadInfo, final BaseException baseException, final String str) {
        this.b.post(new Runnable() { // from class: com.ss.android.downloadlib.g.2
            @Override // java.lang.Runnable
            public void run() {
                for (Object obj : g.this.e) {
                    if (obj instanceof com.ss.android.download.api.download.a.a) {
                        ((com.ss.android.download.api.download.a.a) obj).a(downloadInfo, baseException, str);
                    } else if (obj instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) obj;
                        if (softReference.get() instanceof com.ss.android.download.api.download.a.a) {
                            ((com.ss.android.download.api.download.a.a) softReference.get()).a(downloadInfo, baseException, str);
                        }
                    }
                }
            }
        });
    }

    public void a(final DownloadInfo downloadInfo, final String str) {
        this.b.post(new Runnable() { // from class: com.ss.android.downloadlib.g.3
            @Override // java.lang.Runnable
            public void run() {
                for (Object obj : g.this.e) {
                    if (obj instanceof com.ss.android.download.api.download.a.a) {
                        ((com.ss.android.download.api.download.a.a) obj).a(downloadInfo, str);
                    } else if (obj instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) obj;
                        if (softReference.get() instanceof com.ss.android.download.api.download.a.a) {
                            ((com.ss.android.download.api.download.a.a) softReference.get()).a(downloadInfo, str);
                        }
                    }
                }
            }
        });
    }

    public void b(final DownloadInfo downloadInfo, final String str) {
        this.b.post(new Runnable() { // from class: com.ss.android.downloadlib.g.4
            @Override // java.lang.Runnable
            public void run() {
                for (Object obj : g.this.e) {
                    if (obj instanceof com.ss.android.download.api.download.a.a) {
                        ((com.ss.android.download.api.download.a.a) obj).b(downloadInfo, str);
                    } else if (obj instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) obj;
                        if (softReference.get() instanceof com.ss.android.download.api.download.a.a) {
                            ((com.ss.android.download.api.download.a.a) softReference.get()).b(downloadInfo, str);
                        }
                    }
                }
            }
        });
    }

    public void a(final DownloadInfo downloadInfo) {
        this.b.post(new Runnable() { // from class: com.ss.android.downloadlib.g.5
            @Override // java.lang.Runnable
            public void run() {
                for (Object obj : g.this.e) {
                    if (obj instanceof com.ss.android.download.api.download.a.a) {
                        ((com.ss.android.download.api.download.a.a) obj).a(downloadInfo);
                    } else if (obj instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) obj;
                        if (softReference.get() instanceof com.ss.android.download.api.download.a.a) {
                            ((com.ss.android.download.api.download.a.a) softReference.get()).a(downloadInfo);
                        }
                    }
                }
            }
        });
    }

    public Handler b() {
        return this.b;
    }
}
