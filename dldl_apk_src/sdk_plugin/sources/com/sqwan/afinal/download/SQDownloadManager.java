package com.sqwan.afinal.download;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.sq.tool.network.SqHttpCallback;
import com.sq.tool.network.SqRequest;
import com.sq.tool.sqtools.detector.common.SqTrackCommonKey;
import com.sqnetwork.voly.VolleyError;
import com.sqwan.afinal.download.SQDownloadTask;
import com.sqwan.afinal.download.bean.DownloadBean;
import com.sqwan.common.request.CommonParamsV3;
import com.sqwan.common.util.AppInstallUtil;
import com.sqwan.common.util.LogUtil;
import com.sqwan.msdk.api.SQUpdateManager;
import com.sqwan.msdk.utils.ImageCacheManager;
import com.sqwan.msdk.views.SQApkDownloadDialog;
import com.sy37sdk.account.UrlConstant;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SQDownloadManager {
    public static String DOWNLOAD = "1";
    public static String DOWNLOADING = "2";
    public static String INSTALL = "3";
    public static String OPEN_APP = "4";
    private static SQDownloadManager mInstance;
    private Context mContext;
    private SQApkDownloadDialog mDialog;
    private DialogStateUpdater mDialogUpdater;
    private SQDownloadTask mDownloadTask;
    public String targetPath = "";
    private String mPacakgeName = "";
    private List<DownloadBean> mDownloadBeans = new ArrayList();
    private Map<String, Long> taskIdMap = new HashMap();
    private Map<String, DownloadTaskInfo> downloadTasks = new HashMap();
    private CopyOnWriteArrayList<DownloadStateListener> listeners = new CopyOnWriteArrayList<>();

    public interface DialogStateUpdater {
        void dismissDialog();

        void updateDialogState(String str);
    }

    public enum DownloadState {
        IDLE,
        DOWNLOADING,
        PAUSED,
        COMPLETED,
        FAILED,
        CANCELLED
    }

    public interface DownloadStateListener {
        void onProgressChanged(String str, int i, long j, long j2);

        void onStateChanged(String str, DownloadState downloadState);
    }

    public static class DownloadTaskInfo {
        public DownloadBean downloadBean;
        public long downloadId;
        public String errorMessage;
        public String filePath;
        public String taskId;
        public DownloadState state = DownloadState.IDLE;
        public int progress = 0;
        public long currentSize = 0;
        public long totalSize = 0;
    }

    private SQDownloadManager() {
    }

    public static SQDownloadManager getInstance() {
        if (mInstance == null) {
            mInstance = new SQDownloadManager();
        }
        return mInstance;
    }

    public SQDownloadManager init(Context context) {
        this.mContext = context;
        this.targetPath = SQUpdateManager.getSDPath(context);
        if (this.mDownloadTask == null) {
            SQDownloadTask sQDownloadTask = new SQDownloadTask(context);
            this.mDownloadTask = sQDownloadTask;
            sQDownloadTask.addListener(new SQDownloadTask.DownloadListener() { // from class: com.sqwan.afinal.download.SQDownloadManager.1
                @Override // com.sqwan.afinal.download.SQDownloadTask.DownloadListener
                public void onStateChanged(long j, SQDownloadTask.DownloadState downloadState) {
                    SQDownloadManager.this.handleDownloadStateChanged(j, downloadState);
                }

                @Override // com.sqwan.afinal.download.SQDownloadTask.DownloadListener
                public void onProgressChanged(long j, int i, long j2, long j3) {
                    SQDownloadManager.this.handleDownloadProgressChanged(j, i, j2, j3);
                }

                @Override // com.sqwan.afinal.download.SQDownloadTask.DownloadListener
                public void onDownloadCompleted(long j, String str) {
                    SQDownloadManager.this.handleDownloadCompleted(j, str);
                }

                @Override // com.sqwan.afinal.download.SQDownloadTask.DownloadListener
                public void onDownloadFailed(long j, int i, String str) {
                    SQDownloadManager.this.handleDownloadFailed(j, i, str);
                }
            });
        }
        LogUtil.d("SQDownloadManager initialized");
        return mInstance;
    }

    public String startDownload(DownloadBean downloadBean) {
        if (this.mContext == null) {
            LogUtil.e("SQDownloadManager not initialized");
            return null;
        }
        if (downloadBean == null || TextUtils.isEmpty(downloadBean.getDownload_apk_url())) {
            LogUtil.e("Invalid download bean or empty download url");
            return null;
        }
        String strGenerateTaskId = generateTaskId(downloadBean);
        DownloadTaskInfo downloadTaskInfo = this.downloadTasks.get(strGenerateTaskId);
        if (downloadTaskInfo != null && downloadTaskInfo.state == DownloadState.DOWNLOADING) {
            LogUtil.w("Task already downloading: " + strGenerateTaskId);
            return strGenerateTaskId;
        }
        if (downloadTaskInfo != null) {
            if (downloadTaskInfo.downloadId != 0) {
                SQDownloadTask sQDownloadTask = this.mDownloadTask;
                if (sQDownloadTask != null) {
                    sQDownloadTask.cancelDownload(downloadTaskInfo.downloadId);
                }
                this.taskIdMap.remove(strGenerateTaskId);
            }
            LogUtil.d("Cleaning up old task: " + strGenerateTaskId + ", old state: " + downloadTaskInfo.state);
        }
        DownloadTaskInfo downloadTaskInfo2 = new DownloadTaskInfo();
        downloadTaskInfo2.taskId = strGenerateTaskId;
        downloadTaskInfo2.downloadBean = downloadBean;
        downloadTaskInfo2.state = DownloadState.DOWNLOADING;
        downloadTaskInfo2.progress = 0;
        downloadTaskInfo2.currentSize = 0L;
        downloadTaskInfo2.totalSize = 0L;
        downloadTaskInfo2.filePath = this.targetPath + downloadBean.getPackage_name() + ".apk";
        downloadTaskInfo2.errorMessage = null;
        this.downloadTasks.put(strGenerateTaskId, downloadTaskInfo2);
        notifyStateChanged(strGenerateTaskId, DownloadState.DOWNLOADING);
        notifyProgressChanged(strGenerateTaskId, 0, 0L, 0L);
        File file = new File(this.targetPath + downloadBean.getPackage_name() + ".apk");
        if (file.exists()) {
            file.delete();
        }
        long jStartDownload = this.mDownloadTask.startDownload(downloadBean.getDownload_apk_url(), downloadBean.getPackage_name() + ".apk", "正在下载: " + downloadBean.getName(), "正在下载应用...", this.targetPath);
        if (jStartDownload != -1) {
            downloadTaskInfo2.downloadId = jStartDownload;
            this.taskIdMap.put(strGenerateTaskId, Long.valueOf(jStartDownload));
            LogUtil.d("Started download task: " + strGenerateTaskId + ", downloadId: " + jStartDownload);
        } else {
            downloadTaskInfo2.state = DownloadState.FAILED;
            downloadTaskInfo2.errorMessage = "启动下载失败";
            notifyStateChanged(strGenerateTaskId, DownloadState.FAILED);
        }
        return strGenerateTaskId;
    }

    public void cancelDownload(String str) {
        DownloadTaskInfo downloadTaskInfo = this.downloadTasks.get(str);
        if (downloadTaskInfo != null) {
            if (this.mDownloadTask != null && downloadTaskInfo.downloadId != 0) {
                this.mDownloadTask.cancelDownload(downloadTaskInfo.downloadId);
            }
            downloadTaskInfo.state = DownloadState.CANCELLED;
            notifyStateChanged(str, DownloadState.CANCELLED);
            this.downloadTasks.remove(str);
            this.taskIdMap.remove(str);
        }
    }

    public DownloadTaskInfo getDownloadTaskInfo(String str) {
        return this.downloadTasks.get(str);
    }

    public void setDialogUpdater(DialogStateUpdater dialogStateUpdater) {
        this.mDialogUpdater = dialogStateUpdater;
    }

    public void registerListener(DownloadStateListener downloadStateListener) {
        if (downloadStateListener == null || this.listeners.contains(downloadStateListener)) {
            return;
        }
        this.listeners.add(downloadStateListener);
    }

    public void unregisterListener(DownloadStateListener downloadStateListener) {
        if (downloadStateListener != null) {
            this.listeners.remove(downloadStateListener);
        }
    }

    private String generateTaskId(DownloadBean downloadBean) {
        return downloadBean.getPackage_name() + "_" + downloadBean.getId();
    }

    private void notifyStateChanged(final String str, final DownloadState downloadState) {
        for (final DownloadStateListener downloadStateListener : this.listeners) {
            Context context = this.mContext;
            if (context instanceof Activity) {
                ((Activity) context).runOnUiThread(new Runnable() { // from class: com.sqwan.afinal.download.-$$Lambda$SQDownloadManager$NQFhPQpdGIZCaoi2Yw9n7GpLIt0
                    @Override // java.lang.Runnable
                    public final void run() {
                        downloadStateListener.onStateChanged(str, downloadState);
                    }
                });
            }
        }
    }

    private void notifyProgressChanged(final String str, final int i, final long j, final long j2) {
        if (this.listeners.isEmpty()) {
            return;
        }
        Context context = this.mContext;
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            for (final DownloadStateListener downloadStateListener : this.listeners) {
                activity.runOnUiThread(new Runnable() { // from class: com.sqwan.afinal.download.-$$Lambda$SQDownloadManager$baEcZ3eTq50OjsZl9AsVzXS9wFM
                    @Override // java.lang.Runnable
                    public final void run() {
                        SQDownloadManager.lambda$notifyProgressChanged$1(downloadStateListener, str, i, j, j2);
                    }
                });
            }
        }
    }

    static /* synthetic */ void lambda$notifyProgressChanged$1(DownloadStateListener downloadStateListener, String str, int i, long j, long j2) {
        try {
            downloadStateListener.onProgressChanged(str, i, j, j2);
        } catch (Exception e) {
            LogUtil.e("进度通知异常: " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDownloadStateChanged(long j, SQDownloadTask.DownloadState downloadState) {
        DownloadTaskInfo downloadTaskInfo;
        DownloadState downloadState2;
        String strFindTaskIdByDownloadId = findTaskIdByDownloadId(j);
        if (strFindTaskIdByDownloadId == null || (downloadTaskInfo = this.downloadTasks.get(strFindTaskIdByDownloadId)) == null) {
            return;
        }
        switch (AnonymousClass5.$SwitchMap$com$sqwan$afinal$download$SQDownloadTask$DownloadState[downloadState.ordinal()]) {
            case 1:
                downloadState2 = DownloadState.IDLE;
                break;
            case 2:
                downloadState2 = DownloadState.DOWNLOADING;
                break;
            case 3:
                downloadState2 = DownloadState.PAUSED;
                break;
            case 4:
                downloadState2 = DownloadState.COMPLETED;
                updateDialogForCompleted(downloadTaskInfo.downloadBean);
                break;
            case 5:
                downloadState2 = DownloadState.FAILED;
                updateDialogForFailed();
                break;
            case 6:
                downloadState2 = DownloadState.CANCELLED;
                updateDialogForCancelled();
                this.downloadTasks.remove(strFindTaskIdByDownloadId);
                this.taskIdMap.remove(strFindTaskIdByDownloadId);
                break;
            default:
                downloadState2 = DownloadState.IDLE;
                break;
        }
        downloadTaskInfo.state = downloadState2;
        notifyStateChanged(strFindTaskIdByDownloadId, downloadState2);
    }

    /* JADX INFO: renamed from: com.sqwan.afinal.download.SQDownloadManager$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$sqwan$afinal$download$SQDownloadTask$DownloadState;

        static {
            int[] iArr = new int[SQDownloadTask.DownloadState.values().length];
            $SwitchMap$com$sqwan$afinal$download$SQDownloadTask$DownloadState = iArr;
            try {
                iArr[SQDownloadTask.DownloadState.PENDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sqwan$afinal$download$SQDownloadTask$DownloadState[SQDownloadTask.DownloadState.RUNNING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sqwan$afinal$download$SQDownloadTask$DownloadState[SQDownloadTask.DownloadState.PAUSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$sqwan$afinal$download$SQDownloadTask$DownloadState[SQDownloadTask.DownloadState.SUCCESSFUL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$sqwan$afinal$download$SQDownloadTask$DownloadState[SQDownloadTask.DownloadState.FAILED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$sqwan$afinal$download$SQDownloadTask$DownloadState[SQDownloadTask.DownloadState.CANCELLED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private void updateDialogForCompleted(DownloadBean downloadBean) {
        DialogStateUpdater dialogStateUpdater = this.mDialogUpdater;
        if (dialogStateUpdater == null || downloadBean == null) {
            return;
        }
        dialogStateUpdater.updateDialogState(INSTALL);
        SQUpdateManager.installApk(this.mContext, new File(this.targetPath + downloadBean.getPackage_name() + ".apk"));
    }

    private void updateDialogForFailed() {
        DialogStateUpdater dialogStateUpdater = this.mDialogUpdater;
        if (dialogStateUpdater != null) {
            dialogStateUpdater.updateDialogState(DOWNLOAD);
        }
    }

    private void updateDialogForCancelled() {
        DialogStateUpdater dialogStateUpdater = this.mDialogUpdater;
        if (dialogStateUpdater != null) {
            dialogStateUpdater.updateDialogState(DOWNLOAD);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDownloadProgressChanged(long j, int i, long j2, long j3) {
        DownloadTaskInfo downloadTaskInfo;
        String strFindTaskIdByDownloadId = findTaskIdByDownloadId(j);
        if (strFindTaskIdByDownloadId == null || (downloadTaskInfo = this.downloadTasks.get(strFindTaskIdByDownloadId)) == null) {
            return;
        }
        downloadTaskInfo.progress = i;
        downloadTaskInfo.currentSize = j2;
        downloadTaskInfo.totalSize = j3;
        notifyProgressChanged(strFindTaskIdByDownloadId, i, j2, j3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDownloadCompleted(long j, String str) {
        DownloadTaskInfo downloadTaskInfo;
        String strFindTaskIdByDownloadId = findTaskIdByDownloadId(j);
        if (strFindTaskIdByDownloadId == null || (downloadTaskInfo = this.downloadTasks.get(strFindTaskIdByDownloadId)) == null) {
            return;
        }
        if (checkApkExists(downloadTaskInfo.downloadBean.getDownload_apk_md5(), downloadTaskInfo.downloadBean.getPackage_name())) {
            downloadTaskInfo.state = DownloadState.COMPLETED;
            downloadTaskInfo.progress = 100;
            downloadTaskInfo.filePath = str;
            notifyStateChanged(strFindTaskIdByDownloadId, DownloadState.COMPLETED);
            return;
        }
        downloadTaskInfo.state = DownloadState.FAILED;
        downloadTaskInfo.errorMessage = "MD5校验失败";
        notifyStateChanged(strFindTaskIdByDownloadId, DownloadState.FAILED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleDownloadFailed(long j, int i, String str) {
        DownloadTaskInfo downloadTaskInfo;
        String strFindTaskIdByDownloadId = findTaskIdByDownloadId(j);
        if (strFindTaskIdByDownloadId == null || (downloadTaskInfo = this.downloadTasks.get(strFindTaskIdByDownloadId)) == null) {
            return;
        }
        downloadTaskInfo.state = DownloadState.FAILED;
        downloadTaskInfo.errorMessage = str;
        notifyStateChanged(strFindTaskIdByDownloadId, DownloadState.FAILED);
    }

    private String findTaskIdByDownloadId(long j) {
        for (Map.Entry<String, Long> entry : this.taskIdMap.entrySet()) {
            if (entry.getValue().longValue() == j) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void destroy() {
        SQDownloadTask sQDownloadTask = this.mDownloadTask;
        if (sQDownloadTask != null) {
            sQDownloadTask.destroy();
            this.mDownloadTask = null;
        }
        this.downloadTasks.clear();
        this.taskIdMap.clear();
        this.listeners.clear();
        LogUtil.d("SQDownloadManager destroyed");
    }

    public void showDialog(String str) {
        if (this.mContext == null) {
            LogUtil.e("Download没有初始化！");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("showDialog: tempId为空");
            return;
        }
        DownloadBean downloadBeanFindDownloadBeanById = findDownloadBeanById(str);
        if (downloadBeanFindDownloadBeanById == null) {
            LogUtil.e("showDialog: 未找到tempId对应的DownloadBean，tempId=" + str);
            return;
        }
        if (TextUtils.isEmpty(downloadBeanFindDownloadBeanById.getDownload_apk_url())) {
            LogUtil.e("showDialog: download_apk_url为空，不显示对话框");
            return;
        }
        LogUtil.i("showDialog: 找到对应的DownloadBean，id=" + downloadBeanFindDownloadBeanById.getId() + ", name=" + downloadBeanFindDownloadBeanById.getName() + ", packageName=" + downloadBeanFindDownloadBeanById.getPackage_name());
        showDialogWithBean(downloadBeanFindDownloadBeanById);
    }

    private void showDialogWithBean(final DownloadBean downloadBean) {
        final String str;
        if (this.mContext == null) {
            LogUtil.e("showDialogWithBean: Context为空，无法显示对话框");
            return;
        }
        if (!checkImagesCached(downloadBean)) {
            LogUtil.w("showDialogWithBean: 图片未全部缓存，不显示对话框");
            return;
        }
        String package_name = downloadBean.getPackage_name();
        if (AppInstallUtil.getAppInstallResult(this.mContext, package_name)) {
            str = OPEN_APP;
        } else {
            String download_apk_md5 = downloadBean.getDownload_apk_md5();
            if (!TextUtils.isEmpty(download_apk_md5) && checkApkExists(download_apk_md5, package_name)) {
                str = INSTALL;
                LogUtil.i("showDialogWithBean: APK文件已存在且MD5匹配，设置类型为安装 - " + package_name);
            } else {
                str = DOWNLOAD;
                LogUtil.i("showDialogWithBean: APK文件不存在或MD5不匹配，设置类型为下载 - " + package_name);
            }
        }
        this.mPacakgeName = downloadBean.getPackage_name();
        Context context = this.mContext;
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.sqwan.afinal.download.SQDownloadManager.2
                @Override // java.lang.Runnable
                public void run() {
                    SQDownloadManager.this.mDialog = new SQApkDownloadDialog(SQDownloadManager.this.mContext);
                    SQDownloadManager.this.mDialog.setDownloadBean(downloadBean);
                    SQDownloadManager.this.mDialog.setType(str);
                    SQDownloadManager.this.mDialog.show();
                }
            });
        }
        LogUtil.i("showDialogWithBean: 对话框显示成功，类型=" + str + ", packageName=" + package_name);
    }

    private boolean checkImagesCached(DownloadBean downloadBean) {
        if (downloadBean == null) {
            LogUtil.e("checkImagesCached: downloadBean为空");
            return false;
        }
        List<String> imageUrlsFromBean = getImageUrlsFromBean(downloadBean);
        if (imageUrlsFromBean.isEmpty()) {
            LogUtil.d("checkImagesCached: 没有需要检查的图片URL");
            return true;
        }
        boolean zIsAllImagesCached = ImageCacheManager.getInstance(this.mContext).isAllImagesCached(imageUrlsFromBean);
        LogUtil.d("checkImagesCached: 图片缓存检查结果: " + zIsAllImagesCached + ", 图片数量: " + imageUrlsFromBean.size());
        return zIsAllImagesCached;
    }

    private List<String> getImageUrlsFromBean(DownloadBean downloadBean) {
        ArrayList arrayList = new ArrayList();
        if (downloadBean != null) {
            if (!TextUtils.isEmpty(downloadBean.getBackground_img_url())) {
                arrayList.add(downloadBean.getBackground_img_url());
            }
            if (!TextUtils.isEmpty(downloadBean.getDownload_button_url())) {
                arrayList.add(downloadBean.getDownload_button_url());
            }
            if (!TextUtils.isEmpty(downloadBean.getClose_button_url())) {
                arrayList.add(downloadBean.getClose_button_url());
            }
        }
        return arrayList;
    }

    private DownloadBean findDownloadBeanById(String str) {
        List<DownloadBean> list = this.mDownloadBeans;
        if (list == null || list.isEmpty()) {
            LogUtil.e("findDownloadBeanById: DownloadBean列表为空");
            return null;
        }
        try {
            int i = Integer.parseInt(str);
            for (DownloadBean downloadBean : this.mDownloadBeans) {
                if (downloadBean != null && downloadBean.getId() == i) {
                    LogUtil.d("findDownloadBeanById: 找到匹配的DownloadBean，id=" + i);
                    return downloadBean;
                }
            }
            LogUtil.w("findDownloadBeanById: 未找到id=" + str + "的DownloadBean");
            return null;
        } catch (NumberFormatException unused) {
            LogUtil.e("findDownloadBeanById: tempId格式错误，无法转换为数字: " + str);
            return null;
        }
    }

    public void setDownloadBeans(List<DownloadBean> list) {
        if (list == null) {
            this.mDownloadBeans.clear();
            LogUtil.i("setDownloadBeans: 清空DownloadBean列表");
            return;
        }
        this.mDownloadBeans.clear();
        this.mDownloadBeans.addAll(list);
        LogUtil.i("setDownloadBeans: 设置DownloadBean列表，数量: " + list.size());
    }

    public void resume() {
        DownloadTaskInfo value;
        String key;
        if (this.mContext == null) {
            LogUtil.e("Download没有初始化！");
            return;
        }
        if (this.mDialog == null || TextUtils.isEmpty(this.mPacakgeName)) {
            LogUtil.i("弹窗没开启，不处理");
            return;
        }
        Iterator<Map.Entry<String, DownloadTaskInfo>> it = this.downloadTasks.entrySet().iterator();
        while (true) {
            value = null;
            if (!it.hasNext()) {
                key = null;
                break;
            }
            Map.Entry<String, DownloadTaskInfo> next = it.next();
            value = next.getValue();
            if (value != null && value.downloadBean != null && this.mPacakgeName.equals(value.downloadBean.getPackage_name())) {
                key = next.getKey();
                break;
            }
        }
        if (AppInstallUtil.getAppInstallResult(this.mContext, this.mPacakgeName)) {
            this.mDialog.setType(OPEN_APP);
            return;
        }
        if (value != null && (value.state == DownloadState.DOWNLOADING || value.state == DownloadState.PAUSED)) {
            if (checkTaskStillActiveInSystem(value.downloadId)) {
                this.mDialog.setType(DOWNLOADING);
                LogUtil.i("resume: 保持下载中状态，taskId=" + key + ", state=" + value.state);
                return;
            }
            LogUtil.i("resume: 任务已被杀死，重置为下载状态，taskId=" + key);
            value.state = DownloadState.CANCELLED;
            this.downloadTasks.remove(key);
            this.taskIdMap.remove(key);
            DownloadBean downloadBeanFindDownloadBeanByPackageName = findDownloadBeanByPackageName(this.mPacakgeName);
            if (downloadBeanFindDownloadBeanByPackageName != null) {
                String download_apk_md5 = downloadBeanFindDownloadBeanByPackageName.getDownload_apk_md5();
                if (!TextUtils.isEmpty(download_apk_md5) && checkApkExists(download_apk_md5, this.mPacakgeName)) {
                    this.mDialog.setType(INSTALL);
                    return;
                } else {
                    this.mDialog.setType(DOWNLOAD);
                    return;
                }
            }
            this.mDialog.setType(DOWNLOAD);
            return;
        }
        DownloadBean downloadBeanFindDownloadBeanByPackageName2 = findDownloadBeanByPackageName(this.mPacakgeName);
        if (downloadBeanFindDownloadBeanByPackageName2 != null) {
            String download_apk_md52 = downloadBeanFindDownloadBeanByPackageName2.getDownload_apk_md5();
            if (!TextUtils.isEmpty(download_apk_md52) && checkApkExists(download_apk_md52, this.mPacakgeName)) {
                this.mDialog.setType(INSTALL);
                return;
            } else {
                this.mDialog.setType(DOWNLOAD);
                return;
            }
        }
        this.mDialog.setType(DOWNLOAD);
    }

    private boolean checkTaskStillActiveInSystem(long j) {
        Context context = this.mContext;
        if (context != null && this.mDownloadTask != null) {
            try {
                DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
                if (downloadManager == null) {
                    return false;
                }
                DownloadManager.Query query = new DownloadManager.Query();
                boolean z = true;
                query.setFilterById(j);
                Cursor cursorQuery = null;
                try {
                    cursorQuery = downloadManager.query(query);
                    if (cursorQuery == null || !cursorQuery.moveToFirst()) {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return false;
                    }
                    int i = cursorQuery.getInt(cursorQuery.getColumnIndex("status"));
                    if (i != 2 && i != 4 && i != 1) {
                        z = false;
                    }
                    return z;
                } finally {
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                }
            } catch (Exception e) {
                LogUtil.e("checkTaskStillActiveInSystem: 检查任务状态异常 " + e.getMessage());
            }
        }
        return false;
    }

    private DownloadBean findDownloadBeanByPackageName(String str) {
        List<DownloadBean> list = this.mDownloadBeans;
        if (list != null && !list.isEmpty() && !TextUtils.isEmpty(str)) {
            for (DownloadBean downloadBean : this.mDownloadBeans) {
                if (downloadBean != null && str.equals(downloadBean.getPackage_name())) {
                    return downloadBean;
                }
            }
        }
        return null;
    }

    public boolean checkApkExists(String str, String str2) {
        if (this.mContext == null) {
            LogUtil.e("Download没有初始化！");
            return false;
        }
        String str3 = this.targetPath + str2 + ".apk";
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            LogUtil.w("checkApkExists: MD5或文件路径为空");
            return false;
        }
        File file = new File(str3);
        if (!file.exists()) {
            LogUtil.d("checkApkExists: 文件不存在 " + str3);
            return false;
        }
        try {
            String fileMd5 = getFileMd5(file);
            boolean zEqualsIgnoreCase = str.equalsIgnoreCase(fileMd5);
            StringBuilder sb = new StringBuilder();
            sb.append("checkApkExists: 文件MD5校验");
            sb.append(zEqualsIgnoreCase ? "成功" : "失败");
            sb.append(" 预期=");
            sb.append(str);
            sb.append(" 实际=");
            sb.append(fileMd5);
            LogUtil.i(sb.toString());
            return zEqualsIgnoreCase;
        } catch (Exception e) {
            LogUtil.e("checkApkExists: MD5计算失败 " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private String getFileMd5(File file) throws Exception {
        DigestInputStream digestInputStream;
        Throwable th;
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            digestInputStream = new DigestInputStream(fileInputStream, MessageDigest.getInstance("MD5"));
            try {
                while (digestInputStream.read(new byte[262144]) > 0) {
                }
                byte[] bArrDigest = digestInputStream.getMessageDigest().digest();
                StringBuilder sb = new StringBuilder();
                for (byte b : bArrDigest) {
                    sb.append(String.format("%02X", Byte.valueOf(b)));
                }
                String lowerCase = sb.toString().toLowerCase();
                closeQuietly(fileInputStream);
                closeQuietly(digestInputStream);
                return lowerCase;
            } catch (Throwable th2) {
                th = th2;
                closeQuietly(fileInputStream);
                closeQuietly(digestInputStream);
                throw th;
            }
        } catch (Throwable th3) {
            digestInputStream = null;
            th = th3;
        }
    }

    private void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                LogUtil.e("关闭流异常:" + e.toString());
            }
        }
    }

    public void clearOtherApk(String str) {
        Context context = this.mContext;
        if (context == null) {
            LogUtil.e("Download没有初始化！");
            return;
        }
        String sDPath = SQUpdateManager.getSDPath(context);
        if (sDPath != null && !"".endsWith(sDPath)) {
            String str2 = sDPath + str + ".apk";
            File file = new File(str2);
            if (file.exists()) {
                if (file.delete()) {
                    LogUtil.i("已删除指定apk文件: " + str2);
                    return;
                }
                LogUtil.e("删除指定文件失败: " + str2);
                return;
            }
            LogUtil.d("指定文件不存在: " + str2);
            return;
        }
        LogUtil.e("请您检查设备存储盘情况");
    }

    public void getTempsData() {
        LogUtil.i("getTempsData: 开始请求模板数据");
        SqRequest.of(UrlConstant.URL_GET_POPS_UP).formParams(new HashMap()).addParamsTransformer(new CommonParamsV3()).signV3().get(new SqHttpCallback<JSONObject>() { // from class: com.sqwan.afinal.download.SQDownloadManager.3
            @Override // com.sq.tool.network.SqHttpCallback
            public void onSuccess(JSONObject jSONObject) {
                StringBuilder sb = new StringBuilder();
                sb.append("getTempsData: 响应数据: ");
                sb.append(jSONObject != null ? jSONObject.toString() : AbstractJsonLexerKt.NULL);
                LogUtil.d(sb.toString());
                SQDownloadManager.this.parseAndSetDownloadBeans(jSONObject);
            }

            @Override // com.sq.tool.network.SqHttpCallback
            public void onFailure(int i, String str, VolleyError volleyError) {
                LogUtil.e("getTempsData: 请求失败 - code=" + i + ", errorMsg=" + str);
            }

            @Override // com.sdk.sq.net.SqRequestCallback
            public void onResponseStateError(int i, int i2, String str, String str2) {
                LogUtil.e("getTempsData: 响应状态错误 " + i + ", state=" + i2 + ", msg=" + str);
            }
        });
        LogUtil.d("getTempsData: 网络请求已发送，URL=" + UrlConstant.URL_GET_POPS_UP);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseAndSetDownloadBeans(JSONObject jSONObject) {
        if (jSONObject == null) {
            LogUtil.w("parseAndSetDownloadBeans: jsonObject为空");
            return;
        }
        try {
            if (jSONObject.has("popup_conf_array")) {
                JSONArray jSONArray = jSONObject.getJSONArray("popup_conf_array");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    DownloadBean downloadBean = parseDownloadBean(jSONArray.getJSONObject(i));
                    if (downloadBean != null) {
                        arrayList.add(downloadBean);
                        LogUtil.d("parseAndSetDownloadBeans: 成功解析DownloadBean - id=" + downloadBean.getId() + ", name=" + downloadBean.getName() + ", package=" + downloadBean.getPackage_name());
                    }
                }
                setDownloadBeans(arrayList);
                preloadImages(this.mContext, this.mDownloadBeans);
                LogUtil.i("parseAndSetDownloadBeans: 解析完成，成功设置 " + arrayList.size() + " 个DownloadBean");
                return;
            }
            LogUtil.e("parseAndSetDownloadBeans: 响应中没有找到popup_conf_array字段");
        } catch (JSONException e) {
            LogUtil.e("parseAndSetDownloadBeans: JSON解析异常: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e2) {
            LogUtil.e("parseAndSetDownloadBeans: 解析异常: " + e2.getMessage());
            e2.printStackTrace();
        }
    }

    private DownloadBean parseDownloadBean(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        try {
            DownloadBean downloadBean = new DownloadBean();
            downloadBean.setId(jSONObject.optInt(SqTrackCommonKey.id, 0));
            downloadBean.setName(jSONObject.optString("name", ""));
            downloadBean.setTgid(jSONObject.optInt("tgid", 0));
            downloadBean.setPackage_name(jSONObject.optString("package_name", ""));
            downloadBean.setBackground_img_url(jSONObject.optString("background_img_url", ""));
            downloadBean.setDownload_button_url(jSONObject.optString("download_button_url", ""));
            downloadBean.setClose_button_url(jSONObject.optString("close_button_url", ""));
            downloadBean.setDownload_apk_url(jSONObject.optString("download_apk_url", ""));
            downloadBean.setDownload_apk_md5(jSONObject.optString("download_apk_md5", ""));
            downloadBean.setDownload_apk_size(jSONObject.optString("download_apk_size", ""));
            downloadBean.setClose_button_top_margin(jSONObject.optInt("close_button_top_margin", DownloadBean.defualt_margin));
            downloadBean.setClose_button_right_margin(jSONObject.optInt("close_button_right_margin", DownloadBean.defualt_margin));
            downloadBean.setDownload_button_bottom_margin(jSONObject.optInt("download_button_bottom_margin", DownloadBean.defualt_margin));
            LogUtil.d("parseDownloadBean: 解析对象详情 - id=" + downloadBean.getId() + ", name='" + downloadBean.getName() + "', tgid=" + downloadBean.getTgid() + ", package_name='" + downloadBean.getPackage_name() + "', background_img_url='" + downloadBean.getBackground_img_url() + "', download_button_url='" + downloadBean.getDownload_button_url() + "', close_button_url='" + downloadBean.getClose_button_url() + "', download_apk_url='" + downloadBean.getDownload_apk_url() + "', download_apk_md5='" + downloadBean.getDownload_apk_md5() + "', download_apk_size='" + downloadBean.getDownload_apk_size() + "'");
            return downloadBean;
        } catch (Exception e) {
            LogUtil.e("parseDownloadBean: 解析单个DownloadBean失败: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void preloadImages(Context context, List<DownloadBean> list) {
        if (list == null || list.isEmpty()) {
            LogUtil.w("preloadImages: DownloadBean列表为空");
            return;
        }
        ImageCacheManager imageCacheManager = ImageCacheManager.getInstance(context);
        ArrayList arrayList = new ArrayList();
        for (DownloadBean downloadBean : list) {
            if (downloadBean != null) {
                if (!TextUtils.isEmpty(downloadBean.getBackground_img_url())) {
                    arrayList.add(downloadBean.getBackground_img_url());
                }
                if (!TextUtils.isEmpty(downloadBean.getDownload_button_url())) {
                    arrayList.add(downloadBean.getDownload_button_url());
                }
                if (!TextUtils.isEmpty(downloadBean.getClose_button_url())) {
                    arrayList.add(downloadBean.getClose_button_url());
                }
            }
        }
        if (arrayList.isEmpty()) {
            LogUtil.w("preloadImages: 没有有效的图片URL");
            return;
        }
        LogUtil.i("preloadImages: 开始预下载图片，总数: " + arrayList.size());
        imageCacheManager.downloadImages(arrayList, new ImageCacheManager.ImageDownloadCallback() { // from class: com.sqwan.afinal.download.SQDownloadManager.4
            @Override // com.sqwan.msdk.utils.ImageCacheManager.ImageDownloadCallback
            public void onProgress(int i, int i2) {
                LogUtil.d("图片下载进度: " + i + "/" + i2);
            }

            @Override // com.sqwan.msdk.utils.ImageCacheManager.ImageDownloadCallback
            public void onAllCompleted() {
                LogUtil.i("所有图片预下载完成");
            }
        });
    }
}
