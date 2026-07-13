package com.volcengine.f;

import android.text.TextUtils;
import com.bytedance.http.HttpHeaders;
import com.bytedance.http.HttpRequest;
import com.bytedance.http.HttpResponse;
import com.bytedance.http.Interceptor;
import com.tencent.connect.common.Constants;
import com.volcengine.androidcloud.common.log.AcLog;
import com.volcengine.common.contant.CommonConstants;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class c implements Interceptor {
    private final boolean a;

    public c(boolean z) {
        this.a = z;
    }

    private long a(HttpHeaders httpHeaders, String str, String str2) {
        String str3 = httpHeaders.get(str);
        String str4 = httpHeaders.get(str2);
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return -1L;
        }
        try {
            return Long.parseLong(str4) - Long.parseLong(str3);
        } catch (Exception e) {
            AcLog.e("HttpService", e.getMessage());
            return -1L;
        }
    }

    private void a(HttpResponse httpResponse) {
        long jA = a(httpResponse.headers(), "X-PaaS-start", "X-PaaS-end");
        if (jA > 0) {
            httpResponse.extras().put(CommonConstants.KEY_PAAS_DURATION, Long.toString(jA));
        }
        long jA2 = a(httpResponse.headers(), "X-Android-Sent-Millis", "X-Android-Received-Millis");
        if (jA2 > 0) {
            httpResponse.extras().put(CommonConstants.KEY_ANDROID_DURATION, Long.toString(jA2));
        }
    }

    public HttpResponse intercept(Interceptor.Chain chain) {
        HttpRequest.Builder builderNewBuilder = chain.request().newBuilder();
        builderNewBuilder.addHeader(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/json");
        builderNewBuilder.addHeader("Accept-Charset", Constants.ENC_UTF_8);
        builderNewBuilder.addHeader(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_ACCEPT_ENCODING, "gzip");
        builderNewBuilder.addHeader("X-Expires", Integer.toString(604800));
        if (this.a) {
            builderNewBuilder.addHeader(com.lzy.okgo.model.HttpHeaders.HEAD_KEY_CONNECTION, "close");
        }
        HttpResponse httpResponseProceed = chain.proceed(builderNewBuilder.build());
        a(httpResponseProceed);
        return httpResponseProceed;
    }

    public String name() {
        return "volc_int";
    }
}
