package com.duowan.live.one.module.uploadLog.function;

import android.os.Build;
import com.duowan.auk.http.HttpTask;
import com.duowan.live.one.util.AsyncHttpClient;
import com.huya.mtp.utils.Utils;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class UploadTask {
    public static AsyncHttpClient mAsyncHttpClient;
    private final int LOAD_TIME_MAX_MILLIS = 60000;
    private HttpTask mHttpTask;
    private String mLoadTimeReportKey;
    private String mUrl;

    protected abstract void onResponse(boolean z, AsyncHttpClient.RequestParams requestParams, String str);

    static {
        if (Build.VERSION.SDK_INT <= 16) {
            try {
                Class.forName("android.os.AsyncTask");
            } catch (Throwable unused) {
                Utils.dwAssert(false);
            }
        }
        mAsyncHttpClient = new AsyncHttpClient();
    }

    public UploadTask(String str) {
        this.mUrl = str;
    }

    public void runPost(AsyncHttpClient.RequestParams requestParams, boolean z) {
        HttpTask httpTask = this.mHttpTask;
        if (httpTask == null) {
            realRunPost(requestParams);
        } else if (z) {
            httpTask.cancel();
            realRunPost(requestParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getErrorMsg(int i, Throwable th) {
        String message = th != null ? th.getMessage() : "";
        return (message != null ? message : "") + " statusCode: " + i;
    }

    protected void realRunPost(final AsyncHttpClient.RequestParams requestParams) {
        this.mHttpTask = mAsyncHttpClient.post(this.mUrl, requestParams, new AsyncHttpClient.AsyncHttpResponseHandler() { // from class: com.duowan.live.one.module.uploadLog.function.UploadTask.1
            @Override // com.duowan.live.one.util.AsyncHttpClient.AsyncHttpResponseHandler
            public void onSuccess(int i, Map<String, List<String>> map, byte[] bArr) {
                UploadTask.this.onResponse(true, requestParams, bArr == null ? "" : new String(bArr));
                UploadTask.this.mHttpTask = null;
            }

            @Override // com.duowan.live.one.util.AsyncHttpClient.AsyncHttpResponseHandler
            public void onFailure(int i, Map<String, List<String>> map, byte[] bArr, Throwable th) {
                UploadTask uploadTask = UploadTask.this;
                uploadTask.onResponse(false, requestParams, uploadTask.getErrorMsg(i, th));
                UploadTask.this.mHttpTask = null;
            }
        });
    }
}
