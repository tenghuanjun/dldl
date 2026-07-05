package com.sqwan.bugless.core;

import android.app.IntentService;
import android.content.Intent;
import com.sqwan.bugless.net.BuglessHttpClient;
import com.sqwan.bugless.net.IHttpCallback;
import com.sqwan.bugless.util.LogUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LogUploadService extends IntentService {
    public static final String KEY_LOG_NAME = "logName";
    public static final String KEY_POST_BODY = "postBody";

    public LogUploadService() {
        super("LogUploadService");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        try {
            uploadLog(intent.getExtras().getString(KEY_POST_BODY), intent.getExtras().getString(KEY_LOG_NAME));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadLog(String body, final String logName) {
        BuglessHttpClient.post(body, new IHttpCallback() { // from class: com.sqwan.bugless.core.LogUploadService.1
            @Override // com.sqwan.bugless.net.IHttpCallback
            public void onFail(int code, String msg) {
            }

            @Override // com.sqwan.bugless.net.IHttpCallback
            public void onSuccess(String response) {
                String str = logName;
                if (str == null || "".equals(str)) {
                    return;
                }
                BugHandler.getInstance().deleteFile(logName);
            }
        });
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        LogUtil.w("LogUploadService --> onDestroy");
        super.onDestroy();
    }
}
