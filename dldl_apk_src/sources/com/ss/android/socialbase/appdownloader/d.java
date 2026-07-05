package com.ss.android.socialbase.appdownloader;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.igexin.sdk.PushConsts;
import com.ss.android.socialbase.appdownloader.c.j;
import com.ss.android.socialbase.appdownloader.c.m;
import com.ss.android.socialbase.appdownloader.c.n;
import com.ss.android.socialbase.appdownloader.c.o;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.depend.IDownloadCompleteHandler;
import com.ss.android.socialbase.downloader.depend.IDownloadDepend;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener;
import com.ss.android.socialbase.downloader.depend.IInstallAppHandler;
import com.ss.android.socialbase.downloader.depend.IOpenInstallerListener;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.downloader.IReserveWifiStatusListener;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.impls.RetryScheduler;
import com.ss.android.socialbase.downloader.logger.Logger;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.DownloadTask;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.monitor.DownloadMonitorHelper;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class d {
    private static final String a = d.class.getSimpleName();
    private static volatile d b = null;
    private static boolean f = false;
    private static boolean g = false;
    private static boolean h = false;
    private String c;
    private String d;
    private Future i;
    private int j;
    private com.ss.android.socialbase.appdownloader.c.c m;
    private com.ss.android.socialbase.appdownloader.c.d n;
    private com.ss.android.socialbase.appdownloader.c.h o;
    private com.ss.android.socialbase.appdownloader.c.g p;
    private m q;
    private com.ss.android.socialbase.appdownloader.c.f r;
    private j s;
    private IInstallAppHandler t;
    private IOpenInstallerListener u;
    private o v;
    private DownloadReceiver e = new DownloadReceiver();
    private boolean k = false;
    private boolean l = false;

    public com.ss.android.socialbase.appdownloader.c.c a() {
        return this.m;
    }

    public com.ss.android.socialbase.appdownloader.c.d b() {
        return this.n;
    }

    public com.ss.android.socialbase.appdownloader.c.h c() {
        return this.o;
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.d = str;
    }

    public String d() {
        return this.d;
    }

    public com.ss.android.socialbase.appdownloader.c.f e() {
        return this.r;
    }

    public boolean f() {
        return DownloadSetting.getGlobalSettings().optInt(DownloadSettingKeys.PACKAGE_FLAG_CONFIG, 1) == 1;
    }

    public j g() {
        return this.s;
    }

    public void a(j jVar) {
        this.s = jVar;
    }

    public File h() {
        return Downloader.getInstance(DownloadComponentManager.getAppContext()).getGlobalSaveDir();
    }

    public String i() {
        return this.c;
    }

    private d() {
    }

    public static d j() {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d();
                }
            }
        }
        return b;
    }

    @Deprecated
    public void a(Context context, String str, com.ss.android.socialbase.appdownloader.c.c cVar, com.ss.android.socialbase.appdownloader.c.d dVar, com.ss.android.socialbase.appdownloader.c.h hVar) {
        if (cVar != null) {
            this.m = cVar;
        }
        if (dVar != null) {
            this.n = dVar;
        }
        if (hVar != null) {
            this.o = hVar;
        }
        c(context);
    }

    private void c(Context context) {
        if (context == null || f) {
            return;
        }
        DownloadConstants.setMimeApk("application/vnd.android.package-archive");
        DownloadComponentManager.setAppContext(context);
        DownloadComponentManager.setDownloadLaunchHandler(new com.ss.android.socialbase.appdownloader.d.b());
        if (this.l) {
            DownloadComponentManager.submitScheduleTask(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.d.1
                @Override // java.lang.Runnable
                public void run() {
                    d.this.s();
                }
            }, 5L, TimeUnit.SECONDS);
        } else {
            s();
        }
        t();
        f = true;
    }

    public void b(String str) {
        Downloader.getInstance(DownloadComponentManager.getAppContext()).setDefaultSavePath(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        synchronized (this.e) {
            if (g) {
                return;
            }
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE);
                intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
                intentFilter.addAction("android.ss.intent.action.DOWNLOAD_COMPLETE");
                IntentFilter intentFilter2 = new IntentFilter();
                intentFilter2.addAction("android.intent.action.MEDIA_MOUNTED");
                intentFilter2.addDataScheme("file");
                DownloadComponentManager.getAppContext().registerReceiver(this.e, intentFilter);
                DownloadComponentManager.getAppContext().registerReceiver(this.e, intentFilter2);
                g = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            Logger.d(a, "registerDownloadReceiver mIsRegistered:" + g);
        }
    }

    public void k() {
        if (DownloadSetting.obtainGlobal().optInt("enable_app_install_receiver", 1) <= 0) {
            Logger.d(a, "disable app install receiver");
            return;
        }
        synchronized (this.e) {
            try {
                if (h) {
                    return;
                }
                try {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
                    intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
                    intentFilter.addDataScheme("package");
                    DownloadComponentManager.getAppContext().registerReceiver(this.e, intentFilter);
                    h = true;
                    if (this.v != null) {
                        this.v.a();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                Logger.d(a, "tryRegisterTempAppInstallDownloadReceiver mIsAppInstallRegistered:" + g);
            } finally {
                b(0);
            }
        }
    }

    private void b(int i) {
        this.j = DownloadSetting.obtainGlobal().optInt("app_install_keep_receiver_time_s", 60);
        Logger.d(a, "tryUnRegisterTempAppInstallDownloadReceiver mAppInstallReceiverKeepTime:" + this.j);
        if (this.j <= 0) {
            return;
        }
        if (i > 0) {
            this.j = i;
        }
        Future future = this.i;
        if (future != null) {
            try {
                future.cancel(true);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        this.i = DownloadComponentManager.submitScheduleTask(new Runnable() { // from class: com.ss.android.socialbase.appdownloader.d.2
            @Override // java.lang.Runnable
            public void run() {
                Logger.d(d.a, "registerDownloadReceiver tryUnRegisterTempAppInstallDownloadReceiver run inner");
                d.this.l();
                d.this.s();
            }
        }, this.j, TimeUnit.SECONDS);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0024 A[Catch: all -> 0x001e, TryCatch #1 {, blocks: (B:4:0x0003, B:6:0x0007, B:8:0x0014, B:10:0x0018, B:16:0x0024, B:17:0x0029, B:15:0x0021), top: B:22:0x0003, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void l() {
        /*
            r3 = this;
            com.ss.android.socialbase.appdownloader.DownloadReceiver r0 = r3.e
            monitor-enter(r0)
            boolean r1 = com.ss.android.socialbase.appdownloader.d.g     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            if (r1 == 0) goto L24
            android.content.Context r1 = com.ss.android.socialbase.downloader.downloader.DownloadComponentManager.getAppContext()     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            com.ss.android.socialbase.appdownloader.DownloadReceiver r2 = r3.e     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            r1.unregisterReceiver(r2)     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            boolean r1 = com.ss.android.socialbase.appdownloader.d.h     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            if (r1 == 0) goto L24
            com.ss.android.socialbase.appdownloader.c.o r1 = r3.v     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            if (r1 == 0) goto L24
            com.ss.android.socialbase.appdownloader.c.o r1 = r3.v     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            r1.b()     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            goto L24
        L1e:
            r1 = move-exception
            goto L32
        L20:
            r1 = move-exception
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L1e
        L24:
            r1 = 0
            com.ss.android.socialbase.appdownloader.d.g = r1     // Catch: java.lang.Throwable -> L1e
            com.ss.android.socialbase.appdownloader.d.h = r1     // Catch: java.lang.Throwable -> L1e
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L1e
            java.lang.String r0 = com.ss.android.socialbase.appdownloader.d.a
            java.lang.String r1 = "registerDownloadReceiver unRegisterDownloadReceiver"
            com.ss.android.socialbase.downloader.logger.Logger.d(r0, r1)
            return
        L32:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L1e
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.d.l():void");
    }

    private void t() {
        if (Build.VERSION.SDK_INT >= 21) {
            RetryScheduler.setRetryScheduleHandler(new RetryScheduler.RetryScheduleHandler() { // from class: com.ss.android.socialbase.appdownloader.d.3
                @Override // com.ss.android.socialbase.downloader.impls.RetryScheduler.RetryScheduleHandler
                public void scheduleRetry(DownloadInfo downloadInfo, long j, boolean z, int i) {
                    RetryJobSchedulerService.a(downloadInfo, j, z, i);
                }

                @Override // com.ss.android.socialbase.downloader.impls.RetryScheduler.RetryScheduleHandler
                public void cancelRetry(int i) {
                    RetryJobSchedulerService.a(i);
                }
            });
        }
    }

    public static boolean a(Context context, int i) {
        return c.a(context, i, true) == 1;
    }

    public void a(Context context, int i, int i2) {
        try {
            switch (i2) {
                case -4:
                case -1:
                    Downloader.getInstance(context).restart(i);
                    break;
                case -3:
                    c.a(context, i, true);
                    break;
                case -2:
                    Downloader.getInstance(context).resume(i);
                    break;
                case 0:
                case 6:
                default:
                    return;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8:
                    Downloader.getInstance(context).pause(i);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int a(f fVar) {
        int i;
        String str;
        int i2;
        JSONObject jSONObject;
        DownloadInfo downloadInfo;
        if (fVar == null || fVar.b() == null) {
            return 0;
        }
        try {
            List<HttpHeader> listA = a(fVar.f());
            String strC = fVar.c();
            if (TextUtils.isEmpty(strC)) {
                return 0;
            }
            final int iU = fVar.u();
            final boolean z = iU == 0;
            String strN = fVar.N();
            final String strD = fVar.d();
            if (TextUtils.isEmpty(strN)) {
                strN = c.a(strC, strD, fVar.n(), z);
            }
            if (strN.length() > 255) {
                strN = strN.substring(strN.length() - 255);
            }
            if (TextUtils.isEmpty(strD)) {
                strD = strN;
            }
            String strN2 = fVar.n();
            if (strN.endsWith(".apk") && !c.c(fVar.n())) {
                strN2 = "application/vnd.android.package-archive";
            }
            String strE = fVar.e();
            if (TextUtils.isEmpty(fVar.e())) {
                strE = c.b();
            }
            String str2 = strE;
            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(strN)) {
                String strU = fVar.U();
                if (TextUtils.isEmpty(strU)) {
                    strU = strC;
                }
                int downloadId = DownloadComponentManager.getDownloadId(strU, str2);
                if (DownloadSetting.obtain(fVar.ag()).optBugFix(DownloadSettingKeys.BugFix.FIX_RESUME_TASK_OVERRIDE_SETTINGS) && (downloadInfo = Downloader.getInstance(DownloadComponentManager.getAppContext()).getDownloadInfo(downloadId)) != null) {
                    try {
                        fVar.a(new JSONObject(downloadInfo.getDownloadSettingString()));
                    } catch (Throwable unused) {
                    }
                }
                DownloadSetting.addTaskDownloadSetting(downloadId, fVar.ag());
                boolean zM = fVar.M();
                boolean z2 = (DownloadSetting.obtain(downloadId).optInt("modify_force", 1) == 1 && !zM && DownloadUtils.isFileExist(str2, strN) && Downloader.getInstance(fVar.b()).getDownloadInfo(downloadId) == null) ? true : zM;
                IDownloadListener iDownloadListenerL = fVar.l();
                if (iDownloadListenerL != null || (!fVar.g() && !fVar.h())) {
                    i = downloadId;
                    str = str2;
                } else if (fVar.p() != null) {
                    iDownloadListenerL = new com.ss.android.socialbase.appdownloader.e.b(fVar.p());
                    i = downloadId;
                    str = str2;
                } else {
                    i = downloadId;
                    str = str2;
                    iDownloadListenerL = new com.ss.android.socialbase.appdownloader.e.b(fVar.b(), downloadId, strD, str, strN, fVar.m());
                }
                IDownloadDepend iDownloadDependP = fVar.P();
                if (iDownloadDependP == null) {
                    iDownloadDependP = new IDownloadDepend() { // from class: com.ss.android.socialbase.appdownloader.d.4
                        @Override // com.ss.android.socialbase.downloader.depend.IDownloadDepend
                        public void monitorLogSend(DownloadInfo downloadInfo2, BaseException baseException, int i3) {
                            if (d.this.o != null) {
                                d.this.o.a(downloadInfo2, baseException, i3);
                            }
                        }
                    };
                }
                List<IDownloadCompleteHandler> downloadCompleteHandlers = DownloadComponentManager.getDownloadCompleteHandlers();
                if (!downloadCompleteHandlers.isEmpty()) {
                    Iterator<IDownloadCompleteHandler> it = downloadCompleteHandlers.iterator();
                    while (it.hasNext()) {
                        fVar.a(it.next());
                    }
                }
                String strM = fVar.m();
                try {
                    if (!TextUtils.isEmpty(strM)) {
                        jSONObject = new JSONObject(strM);
                    } else {
                        jSONObject = new JSONObject();
                    }
                    jSONObject.put("auto_install_with_notification", fVar.i());
                    jSONObject.put(DownloadConstants.AUTO_INSTALL_WITHOUT_NOTIFICATION, fVar.h());
                    strM = jSONObject.toString();
                } catch (Throwable unused2) {
                }
                boolean z3 = fVar.g() || fVar.h();
                if (!z3 || DownloadSetting.obtain(i).optInt(DownloadSettingKeys.OPT_NOTIFICATION_UI) < 1) {
                    i2 = i;
                } else {
                    i2 = i;
                    com.ss.android.socialbase.appdownloader.e.c.a().a(i2, fVar.ad());
                }
                final DownloadTask autoInstall = Downloader.with(fVar.b()).url(strC).backUpUrls(fVar.ae()).name(strN).title(strD).savePath(str).onlyWifi(fVar.j()).extraHeaders(listA).depend(iDownloadDependP).retryCount(fVar.y()).backUpUrlRetryCount(fVar.z()).showNotification(z3).extra(strM).mimeType(strN2).minProgressTimeMsInterval(fVar.F()).maxProgressCount(fVar.G()).mainThreadListener(fVar.k()).notificationListener(iDownloadListenerL).notificationEventListener(a(fVar.Q())).force(z2).autoResumed(fVar.s()).showNotificationForAutoResumed(fVar.t()).chunkStategy(fVar.q()).chunkAdjustCalculator(fVar.r()).needHttpsToHttpRetry(fVar.o()).packageName(fVar.v()).md5(fVar.w()).expectFileLength(fVar.x()).needRetryDelay(fVar.A()).retryDelayTimeArray(fVar.B()).needDefaultHttpServiceBackUp(fVar.C()).needReuseFirstConnection(fVar.D()).needReuseChunkRunnable(fVar.H()).needIndependentProcess(fVar.I()).enqueueType(fVar.L()).monitorDepend(fVar.O()).retryDelayTimeCalculator(fVar.E()).headConnectionAvailable(fVar.J()).fileUriProvider(fVar.R()).diskSpaceHandler(fVar.af()).needChunkDowngradeRetry(fVar.K()).notificationClickCallback(fVar.S()).downloadSetting(fVar.ag()).iconUrl(fVar.ad()).needSDKMonitor(fVar.Y()).monitorScene(fVar.Z()).extraMonitorStatus(fVar.aa()).executorGroup(fVar.W()).throttleNetSpeed(fVar.X()).distinctDirectory(fVar.ab()).taskKey(fVar.U()).setAutoInstall(fVar.ac());
                if (autoInstall != null && !fVar.T().isEmpty()) {
                    autoInstall.setDownloadCompleteHandlers(fVar.T());
                }
                if (autoInstall != null) {
                    if (z3 && fVar.V() && fVar.a() != null && !fVar.a().isFinishing() && !com.ss.android.socialbase.appdownloader.e.d.a()) {
                        com.ss.android.socialbase.appdownloader.e.d.a(fVar.a(), new n() { // from class: com.ss.android.socialbase.appdownloader.d.5
                            @Override // com.ss.android.socialbase.appdownloader.c.n
                            public void a() {
                                Logger.d(d.a, "notification permission granted, start download :" + strD);
                                d.this.a(autoInstall, iU, z);
                            }

                            @Override // com.ss.android.socialbase.appdownloader.c.n
                            public void b() {
                                Logger.d(d.a, "notification permission denied, start download :" + strD);
                                d.this.a(autoInstall, iU, z);
                            }
                        });
                    } else {
                        Logger.d(a, "notification permission need not request, start download :" + strD);
                        a(autoInstall, iU, z);
                        autoInstall.getDownloadInfo();
                    }
                }
                return i2;
            }
            return 0;
        } catch (Throwable th) {
            DownloadMonitorHelper.monitorSendWithTaskMonitor(fVar.O(), null, new BaseException(1003, DownloadUtils.getErrorMsgWithTagPrefix(th, "addDownloadTask")), 0);
            Logger.e(a, String.format("add download task error:%s", th));
            return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DownloadTask downloadTask, int i, boolean z) {
        if (downloadTask == null) {
            return;
        }
        downloadTask.download();
        DownloadInfo downloadInfo = downloadTask.getDownloadInfo();
        if (downloadInfo != null) {
            downloadInfo.setAntiHijackErrorCode(i);
        }
        if (downloadInfo == null || !z) {
            return;
        }
        downloadInfo.setSavePathRedirected(z);
    }

    private List<HttpHeader> a(List<HttpHeader> list) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        if (list != null && list.size() > 0) {
            for (HttpHeader httpHeader : list) {
                if (httpHeader != null && !TextUtils.isEmpty(httpHeader.getName()) && !TextUtils.isEmpty(httpHeader.getValue())) {
                    if (httpHeader.getName().equals("User-Agent")) {
                        z = true;
                    }
                    arrayList.add(new HttpHeader(httpHeader.getName(), httpHeader.getValue()));
                }
            }
        }
        if (!z) {
            arrayList.add(new HttpHeader("User-Agent", com.ss.android.socialbase.appdownloader.b.a.a));
        }
        return arrayList;
    }

    public String a(String str, String str2) {
        return (TextUtils.isEmpty(str) || !str.endsWith(".apk") || c.c(str2)) ? str2 : "application/vnd.android.package-archive";
    }

    private IDownloadNotificationEventListener a(final com.ss.android.socialbase.appdownloader.c.e eVar) {
        if (eVar == null) {
            return null;
        }
        return new IDownloadNotificationEventListener() { // from class: com.ss.android.socialbase.appdownloader.d.6
            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public void onNotificationEvent(int i, DownloadInfo downloadInfo, String str, String str2) {
                if (i != 1 && i != 3) {
                    switch (i) {
                        case 8:
                            eVar.a(i, downloadInfo.getPackageName(), str, str2);
                            break;
                        case 9:
                            eVar.a(DownloadComponentManager.getAppContext(), str);
                            break;
                        case 10:
                            eVar.a(downloadInfo);
                            break;
                    }
                }
                eVar.a(i, str, downloadInfo.getStatus(), downloadInfo.getDownloadTime());
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public boolean interceptAfterNotificationSuccess(boolean z) {
                return eVar.a(z);
            }

            @Override // com.ss.android.socialbase.downloader.depend.IDownloadNotificationEventListener
            public String getNotifyProcessName() {
                return eVar.a();
            }
        };
    }

    public DownloadInfo a(Context context, String str) {
        if (!TextUtils.isEmpty(str) && context != null) {
            try {
                DownloadInfo downloadInfoA = a(context, str, h());
                if (downloadInfoA == null) {
                    downloadInfoA = a(context, str, context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS));
                }
                if (downloadInfoA == null) {
                    downloadInfoA = a(context, str, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
                }
                if (downloadInfoA == null) {
                    downloadInfoA = a(context, str, context.getFilesDir());
                }
                return (downloadInfoA == null && DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.BUGFIX_GET_DOWNLOAD_INFO_BY_LIST)) ? b(context, str) : downloadInfoA;
            } catch (Throwable th) {
                Logger.d(a, String.format("getAppDownloadInfo error:%s", th.getMessage()));
            }
        }
        return null;
    }

    private DownloadInfo a(Context context, String str, File file) {
        if (context == null || TextUtils.isEmpty(str) || file == null) {
            return null;
        }
        return Downloader.getInstance(context).getDownloadInfo(str, file.getAbsolutePath());
    }

    private DownloadInfo b(Context context, String str) {
        List<DownloadInfo> downloadInfoList = Downloader.getInstance(context).getDownloadInfoList(str);
        if (downloadInfoList == null) {
            return null;
        }
        for (DownloadInfo downloadInfo : downloadInfoList) {
            if (downloadInfo != null && downloadInfo.isSavePathRedirected()) {
                return downloadInfo;
            }
        }
        return null;
    }

    public List<DownloadInfo> a(Context context) {
        return Downloader.getInstance(context).getUnCompletedDownloadInfosWithMimeType("application/vnd.android.package-archive");
    }

    public List<DownloadInfo> b(Context context) {
        return Downloader.getInstance(context).getDownloadingDownloadInfosWithMimeType("application/vnd.android.package-archive");
    }

    public m m() {
        return this.q;
    }

    public com.ss.android.socialbase.appdownloader.c.g n() {
        return this.p;
    }

    public void a(com.ss.android.socialbase.appdownloader.c.g gVar) {
        this.p = gVar;
    }

    public IReserveWifiStatusListener o() {
        return Downloader.getInstance(DownloadComponentManager.getAppContext()).getReserveWifiStatusListener();
    }

    public void a(IReserveWifiStatusListener iReserveWifiStatusListener) {
        Downloader.getInstance(DownloadComponentManager.getAppContext()).setReserveWifiStatusListener(iReserveWifiStatusListener);
    }

    public void a(IInstallAppHandler iInstallAppHandler) {
        this.t = iInstallAppHandler;
    }

    public IInstallAppHandler p() {
        return this.t;
    }

    public void a(IOpenInstallerListener iOpenInstallerListener) {
        this.u = iOpenInstallerListener;
    }

    public IOpenInstallerListener q() {
        return this.u;
    }

    public void a(int i) {
        if (DownloadSetting.obtainGlobal().optInt("enable_app_install_receiver", 1) <= 0) {
            return;
        }
        k();
        b(i);
    }

    public void a(o oVar) {
        this.v = oVar;
    }

    public void a(boolean z) {
        this.l = z;
    }
}
