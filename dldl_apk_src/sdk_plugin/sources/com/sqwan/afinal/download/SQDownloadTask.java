package com.sqwan.afinal.download;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.sqwan.common.util.LogUtil;
import com.sy37sdk.account.alifast.FastLoginConstants;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SQDownloadTask {
    private static final long PROGRESS_QUERY_INTERVAL = 1000;
    private static final String TAG = "SQDownloadTask";
    private DownloadCompleteReceiver mCompleteReceiver;
    private Context mContext;
    private DownloadManager mDownloadManager;
    private ProgressQueryRunnable mProgressRunnable;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Map<Long, TaskInfo> mTaskMap = new HashMap();
    private CopyOnWriteArrayList<DownloadListener> mListeners = new CopyOnWriteArrayList<>();
    private boolean mIsDestroyed = false;

    public interface DownloadListener {
        void onDownloadCompleted(long j, String str);

        void onDownloadFailed(long j, int i, String str);

        void onProgressChanged(long j, int i, long j2, long j3);

        void onStateChanged(long j, DownloadState downloadState);
    }

    public enum DownloadState {
        PENDING,
        RUNNING,
        PAUSED,
        SUCCESSFUL,
        FAILED,
        CANCELLED
    }

    public static class TaskInfo {
        public long downloadId;
        public String errorMessage;
        public String fileName;
        public String filePath;
        public String url;
        public DownloadState state = DownloadState.PENDING;
        public int progress = 0;
        public long downloadedBytes = 0;
        public long totalBytes = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getErrorMessage(int i) {
        switch (i) {
            case 1001:
                return "文件错误";
            case 1002:
                return "未处理的HTTP状态码";
            case 1003:
            default:
                return FastLoginConstants.MESSAGE.FAILURE_VERIFY_FAIL_UNKNOWN;
            case 1004:
                return "HTTP数据错误";
            case 1005:
                return "重定向过多";
            case 1006:
                return "存储空间不足";
            case 1007:
                return "找不到外部存储设备";
            case 1008:
                return "无法恢复下载";
            case 1009:
                return "文件已存在";
        }
    }

    public SQDownloadTask(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mDownloadManager = (DownloadManager) applicationContext.getSystemService("download");
        init();
    }

    private void init() {
        this.mCompleteReceiver = new DownloadCompleteReceiver();
        this.mContext.registerReceiver(this.mCompleteReceiver, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
        ProgressQueryRunnable progressQueryRunnable = new ProgressQueryRunnable();
        this.mProgressRunnable = progressQueryRunnable;
        this.mHandler.post(progressQueryRunnable);
        LogUtil.d(TAG, "SQDownloadTask initialized");
    }

    public long startDownload(String str, String str2, String str3, String str4, String str5) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.e(TAG, "startDownload: url或fileName为空");
            return -1L;
        }
        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(str));
            request.setTitle(str3);
            request.setDescription(str4);
            request.setNotificationVisibility(1);
            String str6 = str5 + str2;
            request.setDestinationUri(Uri.fromFile(new File(str6)));
            request.setAllowedNetworkTypes(3);
            request.setAllowedOverRoaming(false);
            request.setAllowedOverMetered(true);
            long jEnqueue = this.mDownloadManager.enqueue(request);
            TaskInfo taskInfo = new TaskInfo();
            taskInfo.downloadId = jEnqueue;
            taskInfo.url = str;
            taskInfo.fileName = str2;
            taskInfo.filePath = str6;
            taskInfo.state = DownloadState.PENDING;
            this.mTaskMap.put(Long.valueOf(jEnqueue), taskInfo);
            LogUtil.d(TAG, "startDownload: 开始下载，downloadId=" + jEnqueue + ", url=" + str + ", filePath=" + str6);
            return jEnqueue;
        } catch (Exception e) {
            LogUtil.e(TAG, "startDownload: 下载启动失败 " + e.getMessage());
            return -1L;
        }
    }

    public boolean cancelDownload(long j) {
        TaskInfo taskInfo = this.mTaskMap.get(Long.valueOf(j));
        if (taskInfo == null) {
            LogUtil.w(TAG, "cancelDownload: 未找到下载任务 " + j);
            return false;
        }
        if (this.mDownloadManager.remove(j) > 0) {
            taskInfo.state = DownloadState.CANCELLED;
            notifyStateChanged(j, DownloadState.CANCELLED);
            this.mTaskMap.remove(Long.valueOf(j));
            LogUtil.d(TAG, "cancelDownload: 取消下载成功 " + j);
            return true;
        }
        LogUtil.e(TAG, "cancelDownload: 取消下载失败 " + j);
        return false;
    }

    public TaskInfo getTaskInfo(long j) {
        return this.mTaskMap.get(Long.valueOf(j));
    }

    public Map<Long, TaskInfo> getAllTasks() {
        return new HashMap(this.mTaskMap);
    }

    public void addListener(DownloadListener downloadListener) {
        if (downloadListener == null || this.mListeners.contains(downloadListener)) {
            return;
        }
        this.mListeners.add(downloadListener);
    }

    public void removeListener(DownloadListener downloadListener) {
        if (downloadListener != null) {
            this.mListeners.remove(downloadListener);
        }
    }

    public void clearListeners() {
        this.mListeners.clear();
    }

    public void destroy() {
        this.mIsDestroyed = true;
        ProgressQueryRunnable progressQueryRunnable = this.mProgressRunnable;
        if (progressQueryRunnable != null) {
            this.mHandler.removeCallbacks(progressQueryRunnable);
        }
        DownloadCompleteReceiver downloadCompleteReceiver = this.mCompleteReceiver;
        if (downloadCompleteReceiver != null) {
            try {
                this.mContext.unregisterReceiver(downloadCompleteReceiver);
            } catch (IllegalArgumentException unused) {
            }
        }
        this.mTaskMap.clear();
        this.mListeners.clear();
        LogUtil.d(TAG, "SQDownloadTask destroyed");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryDownloadProgress() throws Throwable {
        if (this.mIsDestroyed || this.mTaskMap.isEmpty()) {
            return;
        }
        Iterator<Long> it = this.mTaskMap.keySet().iterator();
        while (it.hasNext()) {
            queryProgressForTask(it.next().longValue());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v23 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v27 */
    /* JADX WARN: Type inference failed for: r4v28 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v8, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v9 */
    private void queryProgressForTask(long j) throws Throwable {
        TaskInfo taskInfo = this.mTaskMap.get(Long.valueOf(j));
        if (taskInfo == null || taskInfo.state == DownloadState.SUCCESSFUL || taskInfo.state == DownloadState.FAILED || taskInfo.state == DownloadState.CANCELLED) {
            return;
        }
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(j);
        ?? r4 = 0;
        r4 = 0;
        r4 = 0;
        r4 = 0;
        try {
            try {
                Cursor cursorQuery = this.mDownloadManager.query(query);
                if (cursorQuery != null) {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            int columnIndex = cursorQuery.getColumnIndex("status");
                            int columnIndex2 = cursorQuery.getColumnIndex("bytes_so_far");
                            int columnIndex3 = cursorQuery.getColumnIndex("total_size");
                            int columnIndex4 = cursorQuery.getColumnIndex("reason");
                            int i = cursorQuery.getInt(columnIndex);
                            long j2 = cursorQuery.getLong(columnIndex2);
                            long j3 = cursorQuery.getLong(columnIndex3);
                            int i2 = cursorQuery.getInt(columnIndex4);
                            taskInfo.downloadedBytes = j2;
                            taskInfo.totalBytes = j3;
                            DownloadState downloadStateConvertStatus = convertStatus(i);
                            if (downloadStateConvertStatus != taskInfo.state) {
                                taskInfo.state = downloadStateConvertStatus;
                                notifyStateChanged(j, downloadStateConvertStatus);
                                if (downloadStateConvertStatus == DownloadState.FAILED) {
                                    String errorMessage = getErrorMessage(i2);
                                    taskInfo.errorMessage = errorMessage;
                                    notifyDownloadFailed(j, i2, errorMessage);
                                }
                            }
                            int i3 = j3 > 0 ? (int) ((100 * j2) / j3) : 0;
                            r4 = i3;
                            if (i3 != taskInfo.progress) {
                                taskInfo.progress = i3 == true ? 1 : 0;
                                notifyProgressChanged(j, i3 == true ? 1 : 0, j2, j3);
                                r4 = i3;
                            }
                        }
                    } catch (Exception e) {
                        e = e;
                        r4 = cursorQuery;
                        LogUtil.e(TAG, "queryProgressForTask: 查询进度异常 " + e.getMessage());
                        if (r4 != 0) {
                            r4.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        r4 = cursorQuery;
                        if (r4 != 0) {
                            r4.close();
                        }
                        throw th;
                    }
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private DownloadState convertStatus(int i) {
        if (i == 1) {
            return DownloadState.PENDING;
        }
        if (i == 2) {
            return DownloadState.RUNNING;
        }
        if (i == 4) {
            return DownloadState.PAUSED;
        }
        if (i == 8) {
            return DownloadState.SUCCESSFUL;
        }
        if (i == 16) {
            return DownloadState.FAILED;
        }
        return DownloadState.PENDING;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyStateChanged(final long j, final DownloadState downloadState) {
        for (final DownloadListener downloadListener : this.mListeners) {
            this.mHandler.post(new Runnable() { // from class: com.sqwan.afinal.download.-$$Lambda$SQDownloadTask$ZKh1ovEjFIys3PPcHaWkX33Tpxs
                @Override // java.lang.Runnable
                public final void run() {
                    downloadListener.onStateChanged(j, downloadState);
                }
            });
        }
    }

    private void notifyProgressChanged(final long j, final int i, final long j2, final long j3) {
        for (final DownloadListener downloadListener : this.mListeners) {
            this.mHandler.post(new Runnable() { // from class: com.sqwan.afinal.download.-$$Lambda$SQDownloadTask$gpucv2fjP_FR2GieNpUCEQKxqK0
                @Override // java.lang.Runnable
                public final void run() {
                    downloadListener.onProgressChanged(j, i, j2, j3);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDownloadCompleted(final long j, final String str) {
        for (final DownloadListener downloadListener : this.mListeners) {
            this.mHandler.post(new Runnable() { // from class: com.sqwan.afinal.download.-$$Lambda$SQDownloadTask$mDYDfIFTzFoZIcACZQyEjua82rU
                @Override // java.lang.Runnable
                public final void run() {
                    downloadListener.onDownloadCompleted(j, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDownloadFailed(final long j, final int i, final String str) {
        for (final DownloadListener downloadListener : this.mListeners) {
            this.mHandler.post(new Runnable() { // from class: com.sqwan.afinal.download.-$$Lambda$SQDownloadTask$Ol_YbxHDpun6Fg2Ie85gTkcIVD0
                @Override // java.lang.Runnable
                public final void run() {
                    downloadListener.onDownloadFailed(j, i, str);
                }
            });
        }
    }

    private class DownloadCompleteReceiver extends BroadcastReceiver {
        private DownloadCompleteReceiver() {
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x00c3 A[Catch: all -> 0x0175, Exception -> 0x0178, TRY_LEAVE, TryCatch #5 {Exception -> 0x0178, all -> 0x0175, blocks: (B:10:0x0059, B:12:0x005f, B:14:0x007f, B:16:0x008f, B:17:0x0097, B:20:0x00a9, B:21:0x00c3, B:33:0x0165, B:38:0x0171, B:39:0x0174, B:22:0x00cf, B:25:0x00dd, B:27:0x00e3, B:29:0x00ee, B:30:0x010e, B:31:0x0139), top: B:58:0x0059, inners: #4 }] */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onReceive(android.content.Context r9, android.content.Intent r10) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 419
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sqwan.afinal.download.SQDownloadTask.DownloadCompleteReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    private class ProgressQueryRunnable implements Runnable {
        private ProgressQueryRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            if (SQDownloadTask.this.mIsDestroyed) {
                return;
            }
            SQDownloadTask.this.queryDownloadProgress();
            SQDownloadTask.this.mHandler.postDelayed(this, 1000L);
        }
    }
}
