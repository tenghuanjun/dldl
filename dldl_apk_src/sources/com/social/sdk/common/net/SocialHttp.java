package com.social.sdk.common.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.social.sdk.common.net.listener.OnRequestCallBack;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class SocialHttp {
    Handler handler;

    public static void get(String str, Map<String, String> map, OnRequestCallBack onRequestCallBack) {
        asyncHttp(str, "GET", map, null, onRequestCallBack);
    }

    public static void post(String str, Map<String, String> map, OnRequestCallBack onRequestCallBack) {
        asyncHttp(str, "POST", map, null, onRequestCallBack);
    }

    public static void get(String str, Map<String, String> map, Map<String, String> map2, OnRequestCallBack onRequestCallBack) {
        asyncHttp(str, "GET", map, map2, onRequestCallBack);
    }

    public static void post(String str, Map<String, String> map, Map<String, String> map2, OnRequestCallBack onRequestCallBack) {
        asyncHttp(str, "GET", map, map2, onRequestCallBack);
    }

    public static void asyncHttp(final String str, final String str2, final Map<String, String> map, final Map<String, String> map2, OnRequestCallBack onRequestCallBack) {
        SocialHttp socialHttp = new SocialHttp();
        final Handler handler = socialHttp.getHandler(onRequestCallBack);
        new Thread(new Runnable() { // from class: com.social.sdk.common.net.SocialHttp.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                SocialHttp.this.asyncHttpRequest(str, str2, map, map2, handler);
            }
        }).start();
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x016f A[Catch: Exception -> 0x016b, TRY_LEAVE, TryCatch #3 {Exception -> 0x016b, blocks: (B:57:0x0167, B:61:0x016f), top: B:67:0x0167 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0167 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void asyncHttpRequest(java.lang.String r9, java.lang.String r10, java.util.Map<java.lang.String, java.lang.String> r11, java.util.Map<java.lang.String, java.lang.String> r12, android.os.Handler r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 375
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.social.sdk.common.net.SocialHttp.asyncHttpRequest(java.lang.String, java.lang.String, java.util.Map, java.util.Map, android.os.Handler):void");
    }

    private Handler getHandler(final OnRequestCallBack onRequestCallBack) {
        if (this.handler == null) {
            this.handler = new Handler(Looper.getMainLooper()) { // from class: com.social.sdk.common.net.SocialHttp.2
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    String str = message.obj + "";
                    if (message.what == 0) {
                        onRequestCallBack.onSuccess(str);
                    } else {
                        onRequestCallBack.onError(1, str);
                    }
                }
            };
        }
        return this.handler;
    }
}
