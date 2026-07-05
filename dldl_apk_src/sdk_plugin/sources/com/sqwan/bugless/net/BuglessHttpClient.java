package com.sqwan.bugless.net;

import android.text.TextUtils;
import android.util.Log;
import com.sqwan.bugless.core.Bugless;
import com.sqwan.bugless.util.LogUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class BuglessHttpClient {
    private BuglessHttpClient() {
    }

    public static void post(String body, final IHttpCallback callback) {
        IHttpClient httpClient = Bugless.getInstance().getHttpClient();
        if (httpClient == null) {
            Log.w("Bugless", "Can not post as the Http Client is null!");
            return;
        }
        String reportUrl = Bugless.getInstance().getReportUrl();
        if (TextUtils.isEmpty(reportUrl)) {
            Log.w("Bugless", "Can not post as the url is empty!");
            return;
        }
        HashMap map = new HashMap();
        map.put("Content-Type", "application/json;charset:utf-8");
        httpClient.postString(reportUrl, body, map, new IHttpCallback() { // from class: com.sqwan.bugless.net.BuglessHttpClient.1
            @Override // com.sqwan.bugless.net.IHttpCallback
            public void onSuccess(String response) {
                LogUtil.i("post success response --> " + response);
                IHttpCallback iHttpCallback = callback;
                if (iHttpCallback != null) {
                    iHttpCallback.onSuccess(response);
                }
            }

            @Override // com.sqwan.bugless.net.IHttpCallback
            public void onFail(int code, String msg) {
                LogUtil.e("post fail code --> " + code + ", message --> " + msg);
                IHttpCallback iHttpCallback = callback;
                if (iHttpCallback != null) {
                    iHttpCallback.onFail(code, msg);
                }
            }
        });
    }
}
