package com.ss.android.downloadlib.addownload;

import android.content.Context;
import android.os.Environment;
import android.os.Message;
import android.text.TextUtils;
import com.jiguang.h5.PermissionUtils;
import com.ss.android.download.api.config.t;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.download.api.download.DownloadStatusChangeListener;
import com.ss.android.download.api.model.DownloadShortInfo;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.downloadlib.g.j;
import com.ss.android.downloadlib.g.n;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.depend.AbsDownloadExtListener;
import com.ss.android.socialbase.downloader.depend.IDownloadListener;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.model.HttpHeader;
import com.ss.android.socialbase.downloader.notification.DownloadNotificationManager;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.File;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class h implements n.a {
    public com.ss.android.downloadlib.addownload.b.e a;
    private long b;
    private boolean c = false;
    private e d;
    private b e;

    public interface b {
        void a(DownloadInfo downloadInfo);
    }

    @Override // com.ss.android.downloadlib.g.n.a
    public void a(Message message) {
    }

    public h(e eVar) {
        this.d = eVar;
    }

    public void a(long j) {
        this.b = j;
        com.ss.android.downloadlib.addownload.b.e eVarE = com.ss.android.downloadlib.addownload.b.f.a().e(j);
        this.a = eVarE;
        if (eVarE.x()) {
            com.ss.android.downloadlib.e.c.a().a("setAdId ModelBox notValid");
        }
    }

    public void a(DownloadInfo downloadInfo) {
        this.c = false;
        b(downloadInfo);
    }

    public void b(DownloadInfo downloadInfo) {
        b bVar = this.e;
        if (bVar != null) {
            bVar.a(downloadInfo);
            this.e = null;
        }
    }

    boolean a(Context context, int i, boolean z) {
        if (com.ss.android.downloadlib.g.m.a(this.a.b)) {
            com.ss.android.downloadad.api.a.b bVarD = com.ss.android.downloadlib.addownload.b.f.a().d(this.a.a);
            if (bVarD != null) {
                DownloadNotificationManager.getInstance().cancelNotification(bVarD.s());
            }
            return com.ss.android.downloadlib.b.a.a(this.a);
        }
        if (a(i) && !TextUtils.isEmpty(this.a.b.getPackageName()) && k.j().optInt("disable_market") != 1) {
            if (com.ss.android.downloadlib.b.a.a(this.a, i)) {
                return true;
            }
            return this.d.i() && this.d.d(true);
        }
        if (!z || this.a.d.getDownloadMode() != 4 || this.d.e()) {
            return false;
        }
        this.d.c(true);
        return true;
    }

    public boolean a(int i) {
        if (this.a.d.getDownloadMode() == 2 && i == 2) {
            return true;
        }
        return this.a.d.getDownloadMode() == 2 && i == 1 && k.j().optInt("disable_lp_if_market", 0) == 1;
    }

    public boolean a(int i, DownloadModel downloadModel) {
        return com.ss.android.socialbase.appdownloader.f.e.c() && a(i) && !com.ss.android.downloadlib.g.m.a(downloadModel);
    }

    boolean a(boolean z) {
        return !z && this.a.d.getDownloadMode() == 1;
    }

    private boolean c() {
        return d() && e();
    }

    private boolean d() {
        return (this.a.b == null || TextUtils.isEmpty(this.a.b.getPackageName()) || TextUtils.isEmpty(this.a.b.getDownloadUrl())) ? false : true;
    }

    private boolean e() {
        return this.a.d.isAddToDownloadManage();
    }

    void a(final t tVar) {
        if (!TextUtils.isEmpty(this.a.b.getFilePath())) {
            String filePath = this.a.b.getFilePath();
            if (filePath.startsWith(Environment.getDataDirectory().getAbsolutePath())) {
                tVar.a();
                return;
            } else {
                try {
                    if (filePath.startsWith(k.a().getExternalCacheDir().getParent())) {
                        tVar.a();
                        return;
                    }
                } catch (Exception unused) {
                }
            }
        }
        b(new t() { // from class: com.ss.android.downloadlib.addownload.h.1
            @Override // com.ss.android.download.api.config.t
            public void a() {
                tVar.a();
            }

            @Override // com.ss.android.download.api.config.t
            public void a(String str) {
                k.d().a(1, k.a(), h.this.a.b, "您已禁止使用存储权限，请授权后再下载", null, 1);
                com.ss.android.downloadlib.d.a.a().b(h.this.b, 1);
                tVar.a(str);
            }
        });
    }

    private void b(final t tVar) {
        if (com.ss.android.downloadlib.g.j.b(PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
            if (tVar != null) {
                tVar.a();
                return;
            }
            return;
        }
        String str = "android.permission.READ_MEDIA_IMAGES";
        if (!com.ss.android.downloadlib.g.m.a()) {
            str = PermissionUtils.PERMISSION_READ_EXTERNAL_STORAGE;
        } else if (com.ss.android.downloadlib.g.j.b("android.permission.READ_MEDIA_IMAGES") || com.ss.android.downloadlib.g.j.b("android.permission.READ_MEDIA_AUDIO") || com.ss.android.downloadlib.g.j.b("android.permission.READ_MEDIA_VIDEO")) {
            if (tVar != null) {
                tVar.a();
                return;
            }
            return;
        }
        com.ss.android.downloadlib.g.j.a(new String[]{str}, new j.a() { // from class: com.ss.android.downloadlib.addownload.h.2
            @Override // com.ss.android.downloadlib.g.j.a
            public void a() {
                t tVar2 = tVar;
                if (tVar2 != null) {
                    tVar2.a();
                }
            }

            @Override // com.ss.android.downloadlib.g.j.a
            public void a(String str2) {
                t tVar2 = tVar;
                if (tVar2 != null) {
                    tVar2.a(str2);
                }
            }
        });
    }

    void a(Message message, DownloadShortInfo downloadShortInfo, Map<Integer, Object> map) {
        b bVar;
        if (message == null || message.what != 3) {
            return;
        }
        DownloadInfo downloadInfo = (DownloadInfo) message.obj;
        if (message.arg1 != 1 && message.arg1 != 6 && message.arg1 == 2) {
            if (downloadInfo.getIsFirstDownload()) {
                com.ss.android.downloadlib.g.a().a(this.a.b, this.a.d, this.a.c);
                downloadInfo.setFirstDownload(false);
            }
            com.ss.android.downloadlib.d.a.a().a(downloadInfo);
        }
        downloadShortInfo.updateFromNewDownloadInfo(downloadInfo);
        j.a(downloadShortInfo);
        int iA = com.ss.android.socialbase.appdownloader.c.a(downloadInfo.getStatus());
        long totalBytes = downloadInfo.getTotalBytes();
        int curBytes = totalBytes > 0 ? (int) ((downloadInfo.getCurBytes() * 100) / totalBytes) : 0;
        if ((totalBytes > 0 || DownloadSetting.obtainGlobal().optBugFix("fix_click_start")) && (bVar = this.e) != null) {
            bVar.a(downloadInfo);
            this.e = null;
        }
        for (DownloadStatusChangeListener downloadStatusChangeListener : a(map)) {
            if (iA != 1) {
                if (iA == 2) {
                    downloadStatusChangeListener.onDownloadPaused(downloadShortInfo, j.a(downloadInfo.getId(), curBytes));
                } else if (iA == 3) {
                    if (downloadInfo.getStatus() == -4) {
                        downloadStatusChangeListener.onIdle();
                    } else if (downloadInfo.getStatus() == -1) {
                        downloadStatusChangeListener.onDownloadFailed(downloadShortInfo);
                    } else if (downloadInfo.getStatus() == -3) {
                        if (com.ss.android.downloadlib.g.m.a(this.a.b)) {
                            downloadStatusChangeListener.onInstalled(downloadShortInfo);
                        } else {
                            downloadStatusChangeListener.onDownloadFinished(downloadShortInfo);
                        }
                    }
                }
            } else if (downloadInfo.getStatus() != 11) {
                downloadStatusChangeListener.onDownloadActive(downloadShortInfo, j.a(downloadInfo.getId(), curBytes));
            } else {
                Iterator<com.ss.android.download.api.download.a> it = b(map).iterator();
                while (it.hasNext()) {
                    it.next().a(downloadInfo);
                }
            }
        }
    }

    void c(DownloadInfo downloadInfo) {
        if (!i.a(this.a.b) || this.c) {
            return;
        }
        com.ss.android.downloadlib.d.a.a().a("file_status", (downloadInfo == null || !com.ss.android.downloadlib.g.m.b(downloadInfo.getTargetFilePath())) ? 2 : 1, this.a);
        this.c = true;
    }

    void a() {
        if (this.e == null) {
            this.e = new b() { // from class: com.ss.android.downloadlib.addownload.h.3
                @Override // com.ss.android.downloadlib.addownload.h.b
                public void a(DownloadInfo downloadInfo) {
                    com.ss.android.downloadlib.d.a.a().a(h.this.b, 2, downloadInfo);
                }
            };
        }
    }

    boolean d(DownloadInfo downloadInfo) {
        return f() || e(downloadInfo);
    }

    private boolean e(DownloadInfo downloadInfo) {
        return !com.ss.android.downloadlib.g.m.a(this.a.b) && f(downloadInfo);
    }

    private boolean f() {
        return com.ss.android.downloadlib.g.m.a(this.a.b) && i.a(this.a.d.getLinkMode());
    }

    private boolean f(DownloadInfo downloadInfo) {
        return downloadInfo != null && downloadInfo.getStatus() == -3 && DownloadUtils.isFileExist(downloadInfo.getSavePath(), downloadInfo.getName());
    }

    int a(Context context, IDownloadListener iDownloadListener) {
        HttpHeader httpHeaderA;
        if (context == null) {
            return 0;
        }
        Map<String, String> headers = this.a.b.getHeaders();
        ArrayList arrayList = new ArrayList();
        if (k.j().optInt("enable_send_click_id_in_apk", 1) == 1 && !TextUtils.isEmpty(this.a.b.getLogExtra()) && (httpHeaderA = a(this.a.b.getLogExtra())) != null) {
            arrayList.add(httpHeaderA);
        }
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                if (entry != null) {
                    arrayList.add(new HttpHeader(entry.getKey(), entry.getValue()));
                }
            }
        }
        String strA = com.ss.android.downloadlib.g.d.a(String.valueOf(this.a.b.getId()), this.a.b.getNotificationJumpUrl(), this.a.b.isShowToast(), String.valueOf(this.a.b.getModelType()));
        DownloadSetting downloadSettingB = com.ss.android.downloadlib.g.e.b(this.a.b);
        JSONObject jSONObjectA = com.ss.android.downloadlib.g.e.a(this.a.b);
        if (!this.a.d.enableAH()) {
            jSONObjectA = com.ss.android.downloadlib.g.m.a(jSONObjectA);
            com.ss.android.downloadlib.g.m.a(jSONObjectA, DownloadSettingKeys.KEY_AH_PLANS, new JSONArray());
        }
        int executorGroup = this.a.b.getExecutorGroup();
        if (this.a.b.isAd() || i.b(this.a.b)) {
            executorGroup = 4;
        }
        String strA2 = a(downloadSettingB);
        DownloadInfo downloadInfo = Downloader.getInstance(k.a()).getDownloadInfo(DownloadComponentManager.getDownloadId(this.a.b.getDownloadUrl(), strA2));
        if (downloadInfo != null && 3 == this.a.b.getModelType()) {
            downloadInfo.setFirstDownload(true);
        }
        com.ss.android.socialbase.appdownloader.f fVarO = new com.ss.android.socialbase.appdownloader.f(context, this.a.b.getDownloadUrl()).b(this.a.b.getBackupUrls()).a(this.a.b.getName()).e(strA).a(arrayList).a(this.a.b.isShowNotification()).c(this.a.b.isNeedWifi()).b(this.a.b.getFileName()).c(strA2).k(this.a.b.getAppIcon()).h(this.a.b.getMd5()).j(this.a.b.getSdkMonitorScene()).a(this.a.b.getExpectFileLength()).a(iDownloadListener).l(this.a.b.needIndependentProcess() || downloadSettingB.optInt(MonitorConstants.EXTRA_DOWNLOAD_NEED_INDEPENDENT_PROCESS, 0) == 1).a(this.a.b.getDownloadFileUriProvider()).b(this.a.b.autoInstallWithoutNotification()).g(this.a.b.getPackageName()).d(1000).e(100).a(jSONObjectA).i(true).j(true).b(downloadSettingB.optInt(MonitorConstants.EXTRA_DOWNLOAD_RETRY_COUNT, 5)).c(downloadSettingB.optInt("backup_url_retry_count", 0)).j(true).m(downloadSettingB.optInt("need_head_connection", 0) == 1).d(downloadSettingB.optInt("need_https_to_http_retry", 0) == 1).h(downloadSettingB.optInt(MonitorConstants.EXTRA_NEED_CHUNK_DOWNGRADE_RETRY, 1) == 1).g(downloadSettingB.optInt(MonitorConstants.EXTRA_DOWNLOAD_NEED_RETRY_DELAY, 0) == 1).i(downloadSettingB.optString("retry_delay_time_array")).k(downloadSettingB.optInt("need_reuse_runnable", 0) == 1).f(executorGroup).p(this.a.b.isAutoInstall()).o(this.a.b.distinctDir());
        if (!TextUtils.isEmpty(this.a.b.getMimeType())) {
            fVarO.f(this.a.b.getMimeType());
        } else {
            fVarO.f("application/vnd.android.package-archive");
        }
        if (downloadSettingB.optInt("notification_opt_2", 0) == 1) {
            fVarO.a(false);
            fVarO.b(true);
        }
        com.ss.android.downloadlib.addownload.c.a aVar = null;
        if (downloadSettingB.optInt("clear_space_use_disk_handler", 0) == 1) {
            aVar = new com.ss.android.downloadlib.addownload.c.a();
            fVarO.a(aVar);
        }
        if ((this.a.b instanceof AdDownloadModel) && !TextUtils.isEmpty(((AdDownloadModel) this.a.b).getTaskKey())) {
            fVarO.d(((AdDownloadModel) this.a.b).getTaskKey());
        }
        int iA = i.a(this.a, c(), fVarO);
        if (aVar != null) {
            aVar.a(iA);
        }
        return iA;
    }

    private String a(DownloadSetting downloadSetting) {
        boolean zB;
        if (!TextUtils.isEmpty(this.a.b.getFilePath())) {
            return this.a.b.getFilePath();
        }
        DownloadInfo downloadInfoA = com.ss.android.socialbase.appdownloader.d.j().a(k.a(), this.a.b.getDownloadUrl());
        if (!com.ss.android.downloadlib.g.m.a()) {
            zB = com.ss.android.downloadlib.g.j.b(PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE);
        } else {
            zB = com.ss.android.downloadlib.g.j.b("android.permission.READ_MEDIA_IMAGES") || com.ss.android.downloadlib.g.j.b("android.permission.READ_MEDIA_AUDIO") || com.ss.android.downloadlib.g.j.b("android.permission.READ_MEDIA_VIDEO");
        }
        String strB = b();
        if (downloadInfoA != null && !TextUtils.isEmpty(downloadInfoA.getSavePath())) {
            String savePath = downloadInfoA.getSavePath();
            if (zB || savePath.startsWith(Environment.getDataDirectory().getAbsolutePath())) {
                return savePath;
            }
            try {
                if (!TextUtils.isEmpty(strB)) {
                    if (savePath.startsWith(strB)) {
                        return savePath;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Downloader.getInstance(DownloadComponentManager.getAppContext()).cancel(downloadInfoA.getId());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("ttdownloader_code", Integer.valueOf(zB ? 1 : 2));
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        com.ss.android.downloadlib.d.a.a().a("label_external_permission", jSONObject, this.a);
        String strB2 = null;
        try {
            strB2 = com.ss.android.socialbase.appdownloader.c.b();
        } catch (Exception unused) {
        }
        int iA = com.ss.android.downloadlib.g.e.a(downloadSetting);
        if (iA != 0) {
            if (iA == 4 || (!zB && iA == 2)) {
                File filesDir = k.a().getFilesDir();
                if (!filesDir.exists()) {
                    filesDir.mkdirs();
                }
                if (filesDir.exists()) {
                    return filesDir.getAbsolutePath();
                }
            } else if ((iA == 3 || (!zB && iA == 1)) && !TextUtils.isEmpty(strB)) {
                return strB;
            }
        }
        return strB2;
    }

    public String b() {
        File externalFilesDir = k.a().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
        if (externalFilesDir == null) {
            return null;
        }
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        if (externalFilesDir.exists()) {
            return externalFilesDir.getAbsolutePath();
        }
        return null;
    }

    void a(DownloadInfo downloadInfo, boolean z) {
        if (this.a.b == null || downloadInfo == null || downloadInfo.getId() == 0) {
            return;
        }
        int status = downloadInfo.getStatus();
        if (status == -1 || status == -4 || i.a(this.a.b)) {
            com.ss.android.downloadlib.d.a.a().a(this.b, 2);
        } else if (z && com.ss.android.downloadlib.d.c.a().c() && (status == -2 || status == -3)) {
            com.ss.android.downloadlib.d.a.a().a(this.b, 2);
        }
        switch (status) {
            case -4:
            case -1:
                a();
                com.ss.android.downloadlib.addownload.b.f.a().a(new com.ss.android.downloadad.api.a.b(this.a.b, this.a.c, this.a.d, downloadInfo.getId()));
                break;
            case -3:
                if (com.ss.android.downloadlib.g.m.a(this.a.b)) {
                    com.ss.android.downloadlib.e.c.a().b("SUCCESSED isInstalledApp");
                    break;
                } else {
                    com.ss.android.downloadlib.d.a.a().a(this.b, 5, downloadInfo);
                    if (z && com.ss.android.downloadlib.d.c.a().b() && !com.ss.android.downloadlib.d.c.a().b(this.b, this.a.b.getLogExtra())) {
                        com.ss.android.downloadlib.d.a.a().a(this.b, 2);
                        break;
                    }
                }
                break;
            case -2:
                com.ss.android.downloadlib.d.a.a().a(this.b, 4, downloadInfo);
                if (z && com.ss.android.downloadlib.d.c.a().b() && !com.ss.android.downloadlib.d.c.a().b(this.b, this.a.b.getLogExtra())) {
                    com.ss.android.downloadlib.d.a.a().a(this.b, 2);
                    break;
                }
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 8:
                com.ss.android.downloadlib.d.a.a().a(this.b, 3, downloadInfo);
                break;
        }
    }

    void a(DownloadInfo downloadInfo, DownloadShortInfo downloadShortInfo, List<DownloadStatusChangeListener> list) {
        if (list.isEmpty()) {
            return;
        }
        if (downloadInfo == null || downloadShortInfo == null) {
            Iterator<DownloadStatusChangeListener> it = list.iterator();
            while (it.hasNext()) {
                it.next().onIdle();
            }
            return;
        }
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        int curBytes = downloadInfo.getTotalBytes() > 0 ? (int) ((downloadInfo.getCurBytes() * 100) / downloadInfo.getTotalBytes()) : 0;
        int i = curBytes >= 0 ? curBytes : 0;
        downloadShortInfo.updateFromNewDownloadInfo(downloadInfo);
        j.a(downloadShortInfo);
        for (DownloadStatusChangeListener downloadStatusChangeListener : list) {
            switch (downloadInfo.getStatus()) {
                case -4:
                case 0:
                    if (com.ss.android.downloadlib.g.m.a(this.a.b)) {
                        downloadShortInfo.status = -3;
                        downloadStatusChangeListener.onInstalled(downloadShortInfo);
                    } else {
                        downloadStatusChangeListener.onIdle();
                    }
                    break;
                case -3:
                    if (com.ss.android.downloadlib.g.m.a(this.a.b)) {
                        downloadStatusChangeListener.onInstalled(downloadShortInfo);
                    } else {
                        downloadStatusChangeListener.onDownloadFinished(downloadShortInfo);
                    }
                    break;
                case -2:
                    downloadStatusChangeListener.onDownloadPaused(downloadShortInfo, j.a(downloadInfo.getId(), i));
                    break;
                case -1:
                    downloadStatusChangeListener.onDownloadFailed(downloadShortInfo);
                    break;
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 7:
                case 8:
                    downloadStatusChangeListener.onDownloadActive(downloadShortInfo, j.a(downloadInfo.getId(), i));
                    break;
                case 11:
                    if (downloadStatusChangeListener instanceof com.ss.android.download.api.download.a) {
                        ((com.ss.android.download.api.download.a) downloadStatusChangeListener).a(downloadInfo);
                    } else {
                        downloadStatusChangeListener.onDownloadActive(downloadShortInfo, j.a(downloadInfo.getId(), i));
                    }
                    break;
            }
        }
    }

    static class a extends AbsDownloadExtListener {
        private com.ss.android.downloadlib.g.n a;

        a(com.ss.android.downloadlib.g.n nVar) {
            this.a = nVar;
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onPrepare(DownloadInfo downloadInfo) {
            a(downloadInfo, 1);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onStart(DownloadInfo downloadInfo) {
            a(downloadInfo, 2);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onProgress(DownloadInfo downloadInfo) {
            a(downloadInfo, 4);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onPause(DownloadInfo downloadInfo) {
            a(downloadInfo, -2);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onSuccessed(DownloadInfo downloadInfo) {
            a(downloadInfo, -3);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onFailed(DownloadInfo downloadInfo, BaseException baseException) {
            a(downloadInfo, -1);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadListener, com.ss.android.socialbase.downloader.depend.IDownloadListener
        public void onCanceled(DownloadInfo downloadInfo) {
            a(downloadInfo, -4);
        }

        @Override // com.ss.android.socialbase.downloader.depend.AbsDownloadExtListener, com.ss.android.socialbase.downloader.depend.IDownloadExtListener
        public void onWaitingDownloadCompleteHandler(DownloadInfo downloadInfo) {
            a(downloadInfo, 11);
        }

        private void a(DownloadInfo downloadInfo, int i) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 3;
            messageObtain.obj = downloadInfo;
            messageObtain.arg1 = i;
            this.a.sendMessage(messageObtain);
        }
    }

    public static List<DownloadStatusChangeListener> a(Map<Integer, Object> map) {
        ArrayList arrayList = new ArrayList();
        if (map != null && !map.isEmpty()) {
            for (Object obj : map.values()) {
                if (obj instanceof DownloadStatusChangeListener) {
                    arrayList.add((DownloadStatusChangeListener) obj);
                } else {
                    if (obj instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) obj;
                        if (softReference.get() instanceof DownloadStatusChangeListener) {
                            arrayList.add((DownloadStatusChangeListener) softReference.get());
                        }
                    }
                    if (obj instanceof WeakReference) {
                        WeakReference weakReference = (WeakReference) obj;
                        if (weakReference.get() instanceof DownloadStatusChangeListener) {
                            arrayList.add((DownloadStatusChangeListener) weakReference.get());
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public static List<com.ss.android.download.api.download.a> b(Map<Integer, Object> map) {
        ArrayList arrayList = new ArrayList();
        if (map != null && !map.isEmpty()) {
            for (Object obj : map.values()) {
                if (obj instanceof com.ss.android.download.api.download.a) {
                    arrayList.add((com.ss.android.download.api.download.a) obj);
                } else {
                    if (obj instanceof SoftReference) {
                        SoftReference softReference = (SoftReference) obj;
                        if (softReference.get() instanceof com.ss.android.download.api.download.a) {
                            arrayList.add((com.ss.android.download.api.download.a) softReference.get());
                        }
                    }
                    if (obj instanceof WeakReference) {
                        WeakReference weakReference = (WeakReference) obj;
                        if (weakReference.get() instanceof com.ss.android.download.api.download.a) {
                            arrayList.add((com.ss.android.download.api.download.a) weakReference.get());
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private HttpHeader a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return new HttpHeader("clickid", new JSONObject(str).optString("clickid"));
        } catch (JSONException e) {
            k.u().a(e, "parseLogExtra Error");
            return null;
        }
    }
}
