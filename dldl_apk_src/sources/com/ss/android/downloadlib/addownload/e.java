package com.ss.android.downloadlib.addownload;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jiguang.h5.PermissionUtils;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.config.OnItemClickListener;
import com.ss.android.download.api.config.t;
import com.ss.android.download.api.download.DownloadController;
import com.ss.android.download.api.download.DownloadEventConfig;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.download.api.download.c;
import com.ss.android.download.api.model.DownloadShortInfo;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.addownload.h;
import com.ss.android.downloadlib.g.n;
import com.ss.android.socialbase.appdownloader.DownloadHandlerService;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.DownloadProcessDispatcher;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class e implements g, n.a {
    private static final String a = e.class.getSimpleName();
    private WeakReference<Context> e;
    private DownloadShortInfo g;
    private DownloadInfo h;
    private c i;
    private boolean k;
    private long l;
    private String m;
    private SoftReference<OnItemClickListener> r;
    private boolean s;
    private SoftReference<IDownloadButtonClickListener> u;
    private final com.ss.android.downloadlib.g.n b = new com.ss.android.downloadlib.g.n(Looper.getMainLooper(), this);
    private final Map<Integer, Object> f = new ConcurrentHashMap();
    private final IDownloadListener j = new h.a(this.b);
    private long n = -1;
    private DownloadModel o = null;
    private DownloadEventConfig p = null;
    private DownloadController q = null;
    private h c = new h(this);
    private d d = new d(this.b);
    private final boolean t = DownloadSetting.obtainGlobal().optBugFix("ttdownloader_callback_twice");

    interface a {
        void a();
    }

    interface b {
        void a(long j);
    }

    @Override // com.ss.android.downloadlib.addownload.g
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e b(Context context) {
        if (context != null) {
            this.e = new WeakReference<>(context);
        }
        k.b(context);
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e b(int i, DownloadStatusChangeListener downloadStatusChangeListener) {
        if (downloadStatusChangeListener != null) {
            if (k.j().optInt("back_use_softref_listener") == 1) {
                this.f.put(Integer.valueOf(i), downloadStatusChangeListener);
            } else if (k.j().optInt("use_weakref_listener") == 1) {
                this.f.put(Integer.valueOf(i), new WeakReference(downloadStatusChangeListener));
            } else {
                this.f.put(Integer.valueOf(i), new SoftReference(downloadStatusChangeListener));
            }
        }
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e b(DownloadModel downloadModel) {
        if (downloadModel != null) {
            if (downloadModel.isAd()) {
                if (downloadModel.getId() <= 0 || TextUtils.isEmpty(downloadModel.getLogExtra())) {
                    com.ss.android.downloadlib.e.c.a().a("setDownloadModel ad error");
                }
            } else if (downloadModel.getId() == 0 && (downloadModel instanceof AdDownloadModel)) {
                com.ss.android.downloadlib.e.c.a().a(false, "setDownloadModel id=0");
                if (DownloadSetting.obtainGlobal().optBugFix("fix_model_id")) {
                    ((AdDownloadModel) downloadModel).setId(downloadModel.getDownloadUrl().hashCode());
                }
            }
            com.ss.android.downloadlib.addownload.b.f.a().a(downloadModel);
            this.n = downloadModel.getId();
            this.o = downloadModel;
            if (i.a(downloadModel)) {
                ((AdDownloadModel) downloadModel).setExtraValue(3L);
                com.ss.android.downloadad.api.a.b bVarD = com.ss.android.downloadlib.addownload.b.f.a().d(this.n);
                if (bVarD != null && bVarD.l() != 3) {
                    bVarD.e(3L);
                    com.ss.android.downloadlib.addownload.b.i.a().a(bVarD);
                }
            }
        }
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e b(DownloadController downloadController) {
        JSONObject extra;
        this.q = downloadController;
        if (com.ss.android.downloadlib.g.e.b(this.o).optInt("force_auto_open") == 1) {
            n().setLinkMode(1);
        }
        if (DownloadSetting.obtainGlobal().optBugFix("fix_show_dialog") && (extra = this.o.getExtra()) != null && extra.optInt("subprocess") > 0) {
            n().setEnableNewActivity(false);
        }
        com.ss.android.downloadlib.addownload.b.f.a().a(this.n, n());
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e b(DownloadEventConfig downloadEventConfig) {
        this.p = downloadEventConfig;
        this.s = m().getDownloadScene() == 0;
        com.ss.android.downloadlib.addownload.b.f.a().a(this.n, m());
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    public g a(OnItemClickListener onItemClickListener) {
        if (onItemClickListener == null) {
            this.r = null;
        } else {
            this.r = new SoftReference<>(onItemClickListener);
        }
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    public void a() {
        this.k = true;
        com.ss.android.downloadlib.addownload.b.f.a().a(this.n, m());
        com.ss.android.downloadlib.addownload.b.f.a().a(this.n, n());
        this.c.a(this.n);
        q();
        if (k.j().optInt("enable_empty_listener", 1) == 1 && this.f.get(Integer.MIN_VALUE) == null) {
            b(Integer.MIN_VALUE, new com.ss.android.download.api.config.a());
        }
    }

    @Override // com.ss.android.downloadlib.addownload.g
    public boolean a(int i) {
        if (i == 0) {
            this.f.clear();
        } else {
            this.f.remove(Integer.valueOf(i));
        }
        if (this.f.isEmpty()) {
            this.k = false;
            this.l = System.currentTimeMillis();
            if (this.h != null) {
                Downloader.getInstance(k.a()).removeTaskMainListener(this.h.getId());
            }
            c cVar = this.i;
            if (cVar != null && cVar.getStatus() != AsyncTask.Status.FINISHED) {
                this.i.cancel(true);
            }
            this.c.a(this.h);
            String str = a;
            StringBuilder sb = new StringBuilder();
            sb.append("onUnbind removeCallbacksAndMessages, downloadUrl:");
            DownloadInfo downloadInfo = this.h;
            sb.append(downloadInfo == null ? "" : downloadInfo.getUrl());
            com.ss.android.downloadlib.g.k.a(str, sb.toString(), null);
            this.b.removeCallbacksAndMessages(null);
            this.g = null;
            this.h = null;
            return true;
        }
        if (this.f.size() == 1 && this.f.containsKey(Integer.MIN_VALUE)) {
            this.c.b(this.h);
        }
        return false;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    public void a(boolean z) {
        if (this.h != null) {
            if (z) {
                com.ss.android.socialbase.appdownloader.c.d dVarB = com.ss.android.socialbase.appdownloader.d.j().b();
                if (dVarB != null) {
                    dVarB.a(this.h);
                }
                Downloader.getInstance(DownloadComponentManager.getAppContext()).cancel(this.h.getId(), true);
                return;
            }
            Intent intent = new Intent(k.a(), (Class<?>) DownloadHandlerService.class);
            intent.setAction("android.ss.intent.action.DOWNLOAD_DELETE");
            intent.putExtra("extra_click_download_ids", this.h.getId());
            k.a().startService(intent);
        }
    }

    @Override // com.ss.android.downloadlib.addownload.g
    public boolean b() {
        return this.k;
    }

    public boolean c() {
        DownloadInfo downloadInfo = this.h;
        return (downloadInfo == null || downloadInfo.getStatus() == 0) ? false : true;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    public long d() {
        return this.l;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    public g a(long j) {
        if (j != 0) {
            DownloadModel downloadModelA = com.ss.android.downloadlib.addownload.b.f.a().a(j);
            if (downloadModelA != null) {
                this.o = downloadModelA;
                this.n = j;
                this.c.a(j);
            }
        } else {
            com.ss.android.downloadlib.e.c.a().a(false, "setModelId");
        }
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    public void b(final int i) {
        if (i != 1 && i != 2) {
            throw new IllegalArgumentException("error actionType");
        }
        this.c.a(this.n);
        if (!com.ss.android.downloadlib.addownload.b.f.a().e(this.n).y()) {
            com.ss.android.downloadlib.e.c.a().a("handleDownload ModelBox !isStrictValid");
        }
        if (this.c.a(i, this.o)) {
            com.ss.android.downloadlib.addownload.compliance.f.a().a(this.c.a, new com.ss.android.downloadlib.addownload.compliance.h() { // from class: com.ss.android.downloadlib.addownload.e.1
                @Override // com.ss.android.downloadlib.addownload.compliance.h
                public void a(String str) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.putOpt("download_miui_new_market", 1);
                        jSONObject.putOpt("download_miui_market_deeplink", str);
                        if (!com.ss.android.downloadlib.g.h.a(e.this.l(), e.this.c.a, str, jSONObject, true, i)) {
                            jSONObject.putOpt("download_miui_jump_market_success", 0);
                            com.ss.android.downloadlib.addownload.compliance.f.a().a(1, e.this.c.a, jSONObject);
                            int i2 = i;
                            if (i2 == 1) {
                                Logger.d(e.a, "miui new rollback fail: handleDownload id:" + e.this.n + ",tryPerformButtonClick:", null);
                                e.this.c(true);
                            } else if (i2 == 2) {
                                Logger.d(e.a, "miui new rollback fail: handleDownload id:" + e.this.n + ",tryPerformButtonClick:", null);
                                e.this.b(true);
                            }
                        } else {
                            jSONObject.putOpt("download_miui_jump_market_success", 1);
                            com.ss.android.downloadlib.addownload.compliance.f.a().a(0, e.this.c.a, jSONObject);
                        }
                    } catch (Exception e) {
                        com.ss.android.downloadlib.e.c.a().a(e, "generate miui new market param error");
                    }
                }

                @Override // com.ss.android.downloadlib.addownload.compliance.h
                public void a() {
                    int i2 = i;
                    if (i2 == 1) {
                        Logger.d(e.a, "miui new get miui deeplink fail: handleDownload id:" + e.this.n + ",tryPerformButtonClick:", null);
                        e.this.c(true);
                        return;
                    }
                    if (i2 != 2) {
                        return;
                    }
                    Logger.d(e.a, "miui new get miui deeplink fail: handleDownload id:" + e.this.n + ",tryPerformButtonClick:", null);
                    e.this.b(true);
                }
            });
            return;
        }
        if (this.c.a(l(), i, this.s)) {
            return;
        }
        boolean zC = c(i);
        if (i == 1) {
            if (zC) {
                return;
            }
            com.ss.android.downloadlib.g.k.a(a, "handleDownload id:" + this.n + ",pIC:", null);
            c(true);
            return;
        }
        if (i == 2 && !zC) {
            com.ss.android.downloadlib.g.k.a(a, "handleDownload id:" + this.n + ",pBC:", null);
            b(true);
        }
    }

    public boolean e() {
        return k.j().optInt("quick_app_enable_switch", 0) == 0 && this.o.getQuickAppModel() != null && !TextUtils.isEmpty(this.o.getQuickAppModel().a()) && com.ss.android.downloadlib.addownload.c.a(this.h) && com.ss.android.downloadlib.g.m.a(l(), new Intent("android.intent.action.VIEW", Uri.parse(this.o.getQuickAppModel().a())));
    }

    private boolean c(int i) {
        if (!e()) {
            return false;
        }
        int i2 = -1;
        String strA = this.o.getQuickAppModel().a();
        if (i == 1) {
            i2 = 5;
        } else if (i == 2) {
            i2 = 4;
        }
        DownloadModel downloadModel = this.o;
        if (downloadModel instanceof AdDownloadModel) {
            ((AdDownloadModel) downloadModel).setFunnelType(3);
        }
        boolean zC = com.ss.android.downloadlib.g.i.c(k.a(), strA);
        if (zC) {
            com.ss.android.downloadlib.d.a.a().a(this.n, i);
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.obj = Long.valueOf(this.o.getId());
            com.ss.android.downloadlib.addownload.c.a().a(this, i2, this.o);
        } else {
            com.ss.android.downloadlib.d.a.a().a(this.n, false, 0);
        }
        return zC;
    }

    public void b(boolean z) {
        e(z);
    }

    private void k() {
        SoftReference<OnItemClickListener> softReference = this.r;
        if (softReference != null && softReference.get() != null) {
            this.r.get().onItemClick(this.o, m(), n());
            this.r = null;
        } else {
            k.c().a(l(), this.o, n(), m());
        }
    }

    public void c(boolean z) {
        if (z) {
            com.ss.android.downloadlib.d.a.a().a(this.n, 1);
        }
        o();
    }

    private void e(boolean z) {
        if (com.ss.android.downloadlib.g.e.b(this.o).optInt("notification_opt_2") == 1 && this.h != null) {
            DownloadNotificationManager.getInstance().cancelNotification(this.h.getId());
        }
        f(z);
    }

    public void f() {
        this.b.post(new Runnable() { // from class: com.ss.android.downloadlib.addownload.e.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator<DownloadStatusChangeListener> it = h.a((Map<Integer, Object>) e.this.f).iterator();
                while (it.hasNext()) {
                    it.next().onInstalled(e.this.r());
                }
            }
        });
    }

    @Override // com.ss.android.downloadlib.g.n.a
    public void a(Message message) {
        if (message != null && this.k && message.what == 3) {
            this.h = (DownloadInfo) message.obj;
            this.c.a(message, r(), this.f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Context l() {
        WeakReference<Context> weakReference = this.e;
        if (weakReference != null && weakReference.get() != null) {
            return this.e.get();
        }
        return k.a();
    }

    private DownloadEventConfig m() {
        DownloadEventConfig downloadEventConfig = this.p;
        return downloadEventConfig == null ? new c.a().a() : downloadEventConfig;
    }

    private DownloadController n() {
        if (this.q == null) {
            this.q = new com.ss.android.download.api.download.b();
        }
        return this.q;
    }

    private void o() {
        com.ss.android.downloadlib.g.k.a(a, "pICD", null);
        if (this.c.d(this.h)) {
            com.ss.android.downloadlib.g.k.a(a, "pICD BC", null);
            f(false);
        } else {
            com.ss.android.downloadlib.g.k.a(a, "pICD IC", null);
            k();
        }
    }

    private void f(final boolean z) {
        DownloadModel downloadModel;
        com.ss.android.downloadlib.g.k.a(a, "pBCD", null);
        if (p()) {
            com.ss.android.downloadlib.addownload.b.e eVarE = com.ss.android.downloadlib.addownload.b.f.a().e(this.n);
            if (this.s) {
                if (i()) {
                    if (d(false) && eVarE.d != null && eVarE.d.isAutoDownloadOnCardShow()) {
                        a(z, true);
                        return;
                    }
                    return;
                }
                a(z, true);
                return;
            }
            if (this.o.isAd() && eVarE.d != null && eVarE.d.enableShowComplianceDialog() && eVarE.b != null && com.ss.android.downloadlib.addownload.compliance.b.a().a(eVarE.b) && com.ss.android.downloadlib.addownload.compliance.b.a().a(eVarE)) {
                return;
            }
            a(z, true);
            return;
        }
        com.ss.android.downloadlib.g.k.a(a, "pBCD continue download, status:" + this.h.getStatus(), null);
        DownloadInfo downloadInfo = this.h;
        if (downloadInfo != null && (downloadModel = this.o) != null) {
            downloadInfo.setOnlyWifi(downloadModel.isNeedWifi());
        }
        final int status = this.h.getStatus();
        final int id = this.h.getId();
        final com.ss.android.downloadad.api.a.b bVarA = com.ss.android.downloadlib.addownload.b.f.a().a(this.h);
        if (status == -2 || status == -1) {
            this.c.a(this.h, z);
            if (bVarA != null) {
                bVarA.h(System.currentTimeMillis());
                bVarA.i(this.h.getCurBytes());
            }
            this.h.setDownloadFromReserveWifi(false);
            this.d.a(new com.ss.android.downloadlib.addownload.b.e(this.n, this.o, m(), n()));
            this.d.a(id, this.h.getCurBytes(), this.h.getTotalBytes(), new a() { // from class: com.ss.android.downloadlib.addownload.e.3
                @Override // com.ss.android.downloadlib.addownload.e.a
                public void a() {
                    if (e.this.d.a()) {
                        return;
                    }
                    e eVar = e.this;
                    eVar.a(id, status, eVar.h);
                }
            });
            if (status == -2 && com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVarA).optInt("show_pause_continue_toast", 0) == 1) {
                com.ss.android.downloadlib.g.a().b().postDelayed(new Runnable() { // from class: com.ss.android.downloadlib.addownload.e.4
                    @Override // java.lang.Runnable
                    public void run() {
                        k.d().a(13, k.a(), e.this.o, "已恢复下载", null, 0);
                    }
                }, 500L);
                return;
            }
            return;
        }
        if (n.a(status)) {
            if (this.o.enablePause()) {
                this.d.a(true);
                com.ss.android.downloadlib.c.h.a().b(com.ss.android.downloadlib.addownload.b.f.a().d(this.n));
                if (com.ss.android.downloadlib.g.e.a((com.ss.android.downloadad.api.a.a) bVarA).optInt("cancel_pause_optimise_switch", 0) == 1) {
                    com.ss.android.downloadlib.addownload.d.d.a().a(bVarA, status, new com.ss.android.downloadlib.addownload.d.h() { // from class: com.ss.android.downloadlib.addownload.e.6
                        @Override // com.ss.android.downloadlib.addownload.d.h
                        public void a(com.ss.android.downloadad.api.a.b bVar) {
                            if (e.this.h == null && DownloadSetting.obtainGlobal().optBugFix("fix_handle_pause")) {
                                e.this.h = Downloader.getInstance(k.a()).getDownloadInfo(id);
                            }
                            e.this.c.a(e.this.h, z);
                            if (e.this.h != null && DownloadUtils.isWifi(k.a()) && e.this.h.isPauseReserveOnWifi()) {
                                e.this.h.stopPauseReserveOnWifi();
                                com.ss.android.downloadlib.d.a.a().a("cancel_pause_reserve_wifi_cancel_on_wifi", bVarA);
                            } else {
                                e eVar = e.this;
                                eVar.a(id, status, eVar.h);
                            }
                        }
                    }, new com.ss.android.downloadlib.addownload.a.c() { // from class: com.ss.android.downloadlib.addownload.e.5
                        @Override // com.ss.android.downloadlib.addownload.a.c
                        public void a() {
                            e.this.a(true);
                        }
                    });
                    return;
                } else {
                    com.ss.android.downloadlib.addownload.d.k.a().a(bVarA, status, new com.ss.android.downloadlib.addownload.d.h() { // from class: com.ss.android.downloadlib.addownload.e.7
                        @Override // com.ss.android.downloadlib.addownload.d.h
                        public void a(com.ss.android.downloadad.api.a.b bVar) {
                            if (e.this.h == null && DownloadSetting.obtainGlobal().optBugFix("fix_handle_pause")) {
                                e.this.h = Downloader.getInstance(k.a()).getDownloadInfo(id);
                            }
                            e.this.c.a(e.this.h, z);
                            if (e.this.h != null && DownloadUtils.isWifi(k.a()) && e.this.h.isPauseReserveOnWifi()) {
                                e.this.h.stopPauseReserveOnWifi();
                                com.ss.android.downloadlib.d.a.a().b("pause_reserve_wifi_cancel_on_wifi", bVarA);
                            } else {
                                e eVar = e.this;
                                eVar.a(id, status, eVar.h);
                            }
                        }
                    });
                    return;
                }
            }
            return;
        }
        this.c.a(this.h, z);
        a(id, status, this.h);
    }

    public void a(boolean z, final boolean z2) {
        if (z) {
            com.ss.android.downloadlib.d.a.a().a(this.n, 2);
        }
        if (!com.ss.android.downloadlib.g.m.a()) {
            if (!com.ss.android.downloadlib.g.j.b(PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE) && !n().enableNewActivity()) {
                this.o.setFilePath(this.c.b());
            }
        } else if (!com.ss.android.downloadlib.g.j.b("android.permission.READ_MEDIA_IMAGES") && !com.ss.android.downloadlib.g.j.b("android.permission.READ_MEDIA_AUDIO") && !com.ss.android.downloadlib.g.j.b("android.permission.READ_MEDIA_VIDEO") && !n().enableNewActivity()) {
            this.o.setFilePath(this.c.b());
        }
        if (com.ss.android.downloadlib.g.e.c(this.o) == 0) {
            com.ss.android.downloadlib.g.k.a(a, "pBCD not start", null);
            this.c.a(new t() { // from class: com.ss.android.downloadlib.addownload.e.8
                @Override // com.ss.android.download.api.config.t
                public void a() {
                    com.ss.android.downloadlib.g.k.a(e.a, "pBCD start download", null);
                    e.this.g(z2);
                }

                @Override // com.ss.android.download.api.config.t
                public void a(String str) {
                    com.ss.android.downloadlib.g.k.a(e.a, "pBCD onDenied", null);
                }
            });
        } else {
            g(z2);
        }
    }

    private boolean p() {
        if (DownloadSetting.obtainGlobal().optBugFix("fix_click_start")) {
            DownloadInfo downloadInfo = this.h;
            if (downloadInfo == null) {
                return true;
            }
            if ((downloadInfo.getStatus() == -3 && this.h.getCurBytes() <= 0) || this.h.getStatus() == 0 || this.h.getStatus() == -4) {
                return true;
            }
            return DownloadUtils.isDownloadSuccessAndFileNotExist(this.h.getStatus(), this.h.getSavePath(), this.h.getName());
        }
        DownloadInfo downloadInfo2 = this.h;
        if (downloadInfo2 == null) {
            return true;
        }
        return !(downloadInfo2.getStatus() == -3 || Downloader.getInstance(k.a()).canResume(this.h.getId())) || this.h.getStatus() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, DownloadInfo downloadInfo) {
        if (DownloadSetting.obtainGlobal().optBugFix("fix_click_start")) {
            if (i2 != -3 && !DownloadProcessDispatcher.getInstance().canResume(i)) {
                a(false, false);
                return;
            } else {
                com.ss.android.socialbase.appdownloader.d.j().a(k.a(), i, i2);
                return;
            }
        }
        com.ss.android.socialbase.appdownloader.d.j().a(k.a(), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(final boolean z) {
        this.d.a(new com.ss.android.downloadlib.addownload.b.e(this.n, this.o, m(), n()));
        this.d.a(0, 0L, 0L, new a() { // from class: com.ss.android.downloadlib.addownload.e.9
            @Override // com.ss.android.downloadlib.addownload.e.a
            public void a() {
                if (e.this.d.a()) {
                    return;
                }
                e.this.h(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(boolean z) {
        Iterator<DownloadStatusChangeListener> it = h.a(this.f).iterator();
        while (it.hasNext()) {
            it.next().onDownloadStart(this.o, n());
        }
        int iA = this.c.a(k.a(), this.j);
        com.ss.android.downloadlib.g.k.a(a, "beginDown id:" + iA, null);
        if (iA != 0) {
            if (this.h != null && !DownloadSetting.obtainGlobal().optBugFix("fix_click_start")) {
                this.c.a(this.h, false);
            } else if (z) {
                this.c.a();
            }
        } else {
            DownloadInfo downloadInfoBuild = new DownloadInfo.Builder(this.o.getDownloadUrl()).build();
            downloadInfoBuild.setStatus(-1);
            a(downloadInfoBuild);
            com.ss.android.downloadlib.d.a.a().a(this.n, new BaseException(2, "start download failed, id=0"));
            com.ss.android.downloadlib.e.c.a().b("beginDown");
        }
        if (this.c.a(c())) {
            com.ss.android.downloadlib.g.k.a(a, "beginDown IC id:" + iA, null);
            k();
        }
    }

    public void g() {
        if (this.f.size() == 0) {
            return;
        }
        Iterator<DownloadStatusChangeListener> it = h.a(this.f).iterator();
        while (it.hasNext()) {
            it.next().onIdle();
        }
        DownloadInfo downloadInfo = this.h;
        if (downloadInfo != null) {
            downloadInfo.setStatus(-4);
        }
    }

    private class c extends AsyncTask<String, Void, DownloadInfo> {
        private c() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public DownloadInfo doInBackground(String... strArr) {
            DownloadInfo downloadInfo = null;
            if (strArr == null) {
                return null;
            }
            if (strArr.length >= 1 && TextUtils.isEmpty(strArr[0])) {
                return null;
            }
            String str = (strArr.length < 3 || TextUtils.isEmpty(strArr[2])) ? "" : strArr[2];
            String str2 = strArr[0];
            if (e.this.o != null && !TextUtils.isEmpty(e.this.o.getFilePath())) {
                if (TextUtils.isEmpty(str)) {
                    downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(str2, e.this.o.getFilePath());
                } else {
                    downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(Downloader.getInstance(k.a()).getDownloadId(str, e.this.o.getFilePath()));
                }
            }
            if (downloadInfo != null) {
                return downloadInfo;
            }
            if (!TextUtils.isEmpty(str)) {
                return com.ss.android.socialbase.appdownloader.d.j().a(k.a(), str);
            }
            return com.ss.android.socialbase.appdownloader.d.j().a(k.a(), str2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPostExecute(DownloadInfo downloadInfo) {
            super.onPostExecute(downloadInfo);
            if (isCancelled() || e.this.o == null) {
                return;
            }
            try {
                com.ss.android.downloadlib.addownload.b.c cVarA = com.ss.android.downloadlib.g.m.a(e.this.o.getPackageName(), e.this.o.getVersionCode(), e.this.o.getVersionName());
                com.ss.android.downloadlib.addownload.b.h.a().a(e.this.o.getVersionCode(), cVarA.b(), com.ss.android.downloadlib.addownload.b.f.a().a(downloadInfo));
                boolean zA = cVarA.a();
                if (downloadInfo != null && downloadInfo.getId() != 0 && (zA || !Downloader.getInstance(k.a()).isDownloadSuccessAndFileNotExist(downloadInfo))) {
                    Downloader.getInstance(k.a()).removeTaskMainListener(downloadInfo.getId());
                    if (e.this.h == null || e.this.h.getStatus() != -4) {
                        e.this.h = downloadInfo;
                        if (e.this.t) {
                            Downloader.getInstance(k.a()).setMainThreadListener(e.this.h.getId(), e.this.j, false);
                        } else {
                            Downloader.getInstance(k.a()).setMainThreadListener(e.this.h.getId(), e.this.j);
                        }
                    } else {
                        e.this.h = null;
                    }
                    e.this.c.a(e.this.h, e.this.r(), h.a((Map<Integer, Object>) e.this.f));
                } else {
                    if (downloadInfo != null && Downloader.getInstance(k.a()).isDownloadSuccessAndFileNotExist(downloadInfo)) {
                        DownloadNotificationManager.getInstance().cancelNotification(downloadInfo.getId());
                        e.this.h = null;
                    }
                    if (e.this.h != null) {
                        Downloader.getInstance(k.a()).removeTaskMainListener(e.this.h.getId());
                        if (e.this.t) {
                            Downloader.getInstance(e.this.l()).setMainThreadListener(e.this.h.getId(), e.this.j, false);
                        } else {
                            Downloader.getInstance(e.this.l()).setMainThreadListener(e.this.h.getId(), e.this.j);
                        }
                    }
                    if (!zA) {
                        Iterator<DownloadStatusChangeListener> it = h.a((Map<Integer, Object>) e.this.f).iterator();
                        while (it.hasNext()) {
                            it.next().onIdle();
                        }
                        e.this.h = null;
                    } else {
                        e.this.h = new DownloadInfo.Builder(e.this.o.getDownloadUrl()).build();
                        e.this.h.setStatus(-3);
                        e.this.c.a(e.this.h, e.this.r(), h.a((Map<Integer, Object>) e.this.f));
                    }
                }
                e.this.c.c(e.this.h);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void a(DownloadInfo downloadInfo) {
        Message messageObtain = Message.obtain();
        messageObtain.what = 3;
        messageObtain.obj = downloadInfo;
        this.b.sendMessage(messageObtain);
    }

    private void q() {
        c cVar = this.i;
        if (cVar != null && cVar.getStatus() != AsyncTask.Status.FINISHED) {
            this.i.cancel(true);
        }
        this.i = new c();
        if (!TextUtils.isEmpty(this.m)) {
            com.ss.android.downloadlib.g.b.a(this.i, this.o.getDownloadUrl(), this.o.getPackageName(), this.m);
        } else {
            com.ss.android.downloadlib.g.b.a(this.i, this.o.getDownloadUrl(), this.o.getPackageName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadShortInfo r() {
        if (this.g == null) {
            this.g = new DownloadShortInfo();
        }
        return this.g;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    public void h() {
        com.ss.android.downloadlib.addownload.b.f.a().f(this.n);
    }

    @Override // com.ss.android.downloadlib.addownload.g
    public g a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.m = str;
        }
        return this;
    }

    @Override // com.ss.android.downloadlib.addownload.g
    public g a(IDownloadButtonClickListener iDownloadButtonClickListener) {
        if (iDownloadButtonClickListener == null) {
            this.u = null;
        } else {
            this.u = new SoftReference<>(iDownloadButtonClickListener);
        }
        return this;
    }

    public boolean i() {
        SoftReference<IDownloadButtonClickListener> softReference = this.u;
        if (softReference == null) {
            return false;
        }
        return i.a(this.o, softReference.get());
    }

    public boolean d(boolean z) {
        SoftReference<IDownloadButtonClickListener> softReference = this.u;
        if (softReference != null && softReference.get() != null) {
            try {
                if (!z) {
                    this.u.get().handleComplianceDialog(true);
                } else {
                    this.u.get().handleMarketFailedComplianceDialog();
                }
                this.u = null;
                return true;
            } catch (Exception unused) {
                com.ss.android.downloadlib.e.c.a().b("mDownloadButtonClickListener has recycled");
                return false;
            }
        }
        com.ss.android.downloadlib.e.c.a().b("mDownloadButtonClickListener has recycled");
        return false;
    }
}
