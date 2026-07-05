package com.sq.sdk.tool.download;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.sq.sdk.tool.util.SqLogUtil;
import com.sy37sdk.order.third.union.UnionPayWay;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DownloadTask extends AsyncTask<String, Long, Long> {
    public static final int CODE_APK_NOT_EXIST = 2;
    public static final int CODE_DID_NOT_FINISH = 4;
    public static final int CODE_EXCEPTION = 3;
    public static final int CODE_RANGE_NOT_SATIS = 416;
    public static final int CODE_URL_NULL = 1;
    private static final String TAG = "DownloadTask";
    private String downloadFileName;
    private String downloadPath;
    private DownloadListener mListener;
    private String mUrl;
    private long total;
    private int DEFAULT_RETRY_TIME = 2;
    private int retryTime = 2;
    private Handler mainHandler = new Handler(Looper.getMainLooper());

    public DownloadTask(String str, String str2, String str3, DownloadListener downloadListener) {
        this.mUrl = str;
        this.downloadFileName = str2;
        this.downloadPath = str3;
        this.mListener = downloadListener;
    }

    public void setRetryTime(int i) {
        this.retryTime = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Long doInBackground(String... strArr) throws Throwable {
        if (this.mUrl == null) {
            postFailureResult(null, 1, "url 为 null");
            return null;
        }
        int i = 0;
        while (i < this.retryTime) {
            StringBuilder sb = new StringBuilder();
            sb.append("第");
            i++;
            sb.append(i);
            sb.append("次尝试下载");
            SqLogUtil.i(sb.toString());
            Long lDownLoadFile = downLoadFile();
            if (lDownLoadFile != null) {
                return lDownLoadFile;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00ed A[Catch: Exception -> 0x00f1, TRY_ENTER, TryCatch #11 {Exception -> 0x00f1, blocks: (B:43:0x00c6, B:44:0x00c9, B:46:0x00ce, B:65:0x00ed, B:69:0x00f5, B:71:0x00fa), top: B:96:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00f5 A[Catch: Exception -> 0x00f1, TryCatch #11 {Exception -> 0x00f1, blocks: (B:43:0x00c6, B:44:0x00c9, B:46:0x00ce, B:65:0x00ed, B:69:0x00f5, B:71:0x00fa), top: B:96:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00fa A[Catch: Exception -> 0x00f1, TRY_LEAVE, TryCatch #11 {Exception -> 0x00f1, blocks: (B:43:0x00c6, B:44:0x00c9, B:46:0x00ce, B:65:0x00ed, B:69:0x00f5, B:71:0x00fa), top: B:96:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x010e A[Catch: Exception -> 0x010a, TryCatch #4 {Exception -> 0x010a, blocks: (B:78:0x0106, B:82:0x010e, B:84:0x0113), top: B:89:0x0106 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0113 A[Catch: Exception -> 0x010a, TRY_LEAVE, TryCatch #4 {Exception -> 0x010a, blocks: (B:78:0x0106, B:82:0x010e, B:84:0x0113), top: B:89:0x0106 }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0106 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v14, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r6v7 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.Long downLoadFile() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sq.sdk.tool.download.DownloadTask.downLoadFile():java.lang.Long");
    }

    private boolean handleResponseCode(int i) {
        SqLogUtil.i(TAG, "download response code is " + i);
        if (i == 404) {
            postFailureResult(null, UnionPayWay.ERROR_INVALID_RESULT, "返回404");
            return false;
        }
        if (i != 416) {
            return true;
        }
        postFailureResult(null, 416, "服务器不能满足客户在请求中指定的Range");
        return false;
    }

    private void postFailureResult(final Throwable th, final int i, final String str) {
        this.mainHandler.post(new Runnable() { // from class: com.sq.sdk.tool.download.DownloadTask.1
            @Override // java.lang.Runnable
            public void run() {
                if (DownloadTask.this.mListener != null) {
                    DownloadTask.this.mListener.onFailure(th, i, str);
                }
            }
        });
    }

    @Override // android.os.AsyncTask
    protected void onCancelled() {
        super.onCancelled();
        SqLogUtil.i(TAG, "task is canceled");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onProgressUpdate(Long... lArr) {
        super.onProgressUpdate((Object[]) lArr);
        this.mListener.onUpdate(this.total, lArr[0].longValue());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Long l) {
        super.onPostExecute(l);
        File file = new File(this.downloadPath, this.downloadFileName);
        if (!file.exists()) {
            SqLogUtil.e("file download fail : " + file.getAbsolutePath());
            this.mListener.onFailure(null, 2, "下载结束， apk文件没找到");
            return;
        }
        if (file.length() == this.total) {
            SqLogUtil.i(TAG, "task is success");
            this.mListener.onSuccess(file);
        } else if (file.length() < this.total) {
            this.mListener.onFailure(null, 4, "下载未完成");
        }
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        SqLogUtil.i(TAG, "task is start");
    }
}
