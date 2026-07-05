package com.sqwan.afinal.download;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import com.sqwan.common.util.LogUtil;
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
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private long total;

    public DownloadTask(String str, String str2, String str3, DownloadListener downloadListener) {
        this.mUrl = str;
        this.downloadFileName = str2;
        this.downloadPath = str3;
        this.mListener = downloadListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01a1 A[Catch: Exception -> 0x01a5, TRY_ENTER, TryCatch #14 {Exception -> 0x01a5, blocks: (B:80:0x0163, B:82:0x0168, B:84:0x016d, B:86:0x0172, B:109:0x01a1, B:113:0x01a9, B:115:0x01ae, B:117:0x01b3), top: B:144:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01a9 A[Catch: Exception -> 0x01a5, TryCatch #14 {Exception -> 0x01a5, blocks: (B:80:0x0163, B:82:0x0168, B:84:0x016d, B:86:0x0172, B:109:0x01a1, B:113:0x01a9, B:115:0x01ae, B:117:0x01b3), top: B:144:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:115:0x01ae A[Catch: Exception -> 0x01a5, TryCatch #14 {Exception -> 0x01a5, blocks: (B:80:0x0163, B:82:0x0168, B:84:0x016d, B:86:0x0172, B:109:0x01a1, B:113:0x01a9, B:115:0x01ae, B:117:0x01b3), top: B:144:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x01b3 A[Catch: Exception -> 0x01a5, TRY_LEAVE, TryCatch #14 {Exception -> 0x01a5, blocks: (B:80:0x0163, B:82:0x0168, B:84:0x016d, B:86:0x0172, B:109:0x01a1, B:113:0x01a9, B:115:0x01ae, B:117:0x01b3), top: B:144:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01c8 A[Catch: Exception -> 0x01c4, TryCatch #16 {Exception -> 0x01c4, blocks: (B:124:0x01c0, B:128:0x01c8, B:130:0x01cd, B:132:0x01d2), top: B:145:0x01c0 }] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01cd A[Catch: Exception -> 0x01c4, TryCatch #16 {Exception -> 0x01c4, blocks: (B:124:0x01c0, B:128:0x01c8, B:130:0x01cd, B:132:0x01d2), top: B:145:0x01c0 }] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01d2 A[Catch: Exception -> 0x01c4, TRY_LEAVE, TryCatch #16 {Exception -> 0x01c4, blocks: (B:124:0x01c0, B:128:0x01c8, B:130:0x01cd, B:132:0x01d2), top: B:145:0x01c0 }] */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0163 A[Catch: Exception -> 0x01a5, TRY_ENTER, TryCatch #14 {Exception -> 0x01a5, blocks: (B:80:0x0163, B:82:0x0168, B:84:0x016d, B:86:0x0172, B:109:0x01a1, B:113:0x01a9, B:115:0x01ae, B:117:0x01b3), top: B:144:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0168 A[Catch: Exception -> 0x01a5, TryCatch #14 {Exception -> 0x01a5, blocks: (B:80:0x0163, B:82:0x0168, B:84:0x016d, B:86:0x0172, B:109:0x01a1, B:113:0x01a9, B:115:0x01ae, B:117:0x01b3), top: B:144:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x016d A[Catch: Exception -> 0x01a5, TryCatch #14 {Exception -> 0x01a5, blocks: (B:80:0x0163, B:82:0x0168, B:84:0x016d, B:86:0x0172, B:109:0x01a1, B:113:0x01a9, B:115:0x01ae, B:117:0x01b3), top: B:144:0x0004 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0172 A[Catch: Exception -> 0x01a5, TRY_LEAVE, TryCatch #14 {Exception -> 0x01a5, blocks: (B:80:0x0163, B:82:0x0168, B:84:0x016d, B:86:0x0172, B:109:0x01a1, B:113:0x01a9, B:115:0x01ae, B:117:0x01b3), top: B:144:0x0004 }] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v2, types: [java.io.RandomAccessFile] */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v29 */
    /* JADX WARN: Type inference failed for: r9v3, types: [java.io.RandomAccessFile] */
    /* JADX WARN: Type inference failed for: r9v9 */
    @Override // android.os.AsyncTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Long doInBackground(java.lang.String... r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sqwan.afinal.download.DownloadTask.doInBackground(java.lang.String[]):java.lang.Long");
    }

    private boolean handleResponseCode(int i) {
        LogUtil.i(TAG, "download response code is " + i);
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
        this.mainHandler.post(new Runnable() { // from class: com.sqwan.afinal.download.DownloadTask.1
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
        LogUtil.i(TAG, "task is canceled");
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
        LogUtil.i(TAG, "task is success");
        File file = new File(this.downloadPath, this.downloadFileName);
        if (!file.exists()) {
            this.mListener.onFailure(null, 2, "下载结束， apk文件没找到");
        } else if (file.length() == this.total) {
            this.mListener.onSuccess(file);
        } else if (file.length() < this.total) {
            this.mListener.onFailure(null, 4, "下载未完成");
        }
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
        super.onPreExecute();
        LogUtil.i(TAG, "task is start");
    }
}
