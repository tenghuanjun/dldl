package com.sq.tool.network;

import com.sqwan.common.data.cache.SpRequestInfo;
import com.sqwan.common.net.base.RequestUtil;
import com.sqwan.common.util.SQContextWrapper;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RequestIdOkInterceptor implements Interceptor {
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().addHeader("Request-Id", RequestUtil.generateRequestId()).addHeader("Request-LiveId", SpRequestInfo.getRequestLiveId(SQContextWrapper.getApplicationContext())).build());
    }
}
