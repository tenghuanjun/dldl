package com.sq.tool.network;

import com.sdk.sq.net.HttpClient;
import com.sdk.sq.net.SqRetryPolicyFactory;
import com.sdk.sq.net.gateway.GateWayEncryptInterceptor;
import com.sqwan.common.net.base.RequestUtil;
import com.sqwan.common.util.SQContextWrapper;
import java.util.HashSet;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqHttpClient {
    private static volatile SqHttpClient sInstance;
    private HttpClient mHttpClient;

    public static HttpClient getInstance() {
        if (sInstance == null) {
            synchronized (SqHttpClient.class) {
                if (sInstance == null) {
                    sInstance = new SqHttpClient();
                }
            }
        }
        return sInstance.getHttpClient();
    }

    public HttpClient getHttpClient() {
        HttpClient httpClient = this.mHttpClient;
        if (httpClient != null) {
            return httpClient;
        }
        HttpClient httpClientBuild = new HttpClient.Builder().setOkHttpBuilder(createBuilder()).setReqIdGenerator(new HttpClient.ReqIdGenerator() { // from class: com.sq.tool.network.-$$Lambda$3aWXdbfv9g_RqZ8B59IFS7DrD7Y
            @Override // com.sdk.sq.net.HttpClient.ReqIdGenerator
            public final String generateId() {
                return RequestUtil.generateRequestId();
            }
        }).setFallbackToHttp(true).setRetryPolicyFactory(new SqRetryPolicyFactory(5000, 15000)).setEventReporter(new EventReporter()).setExceptionReporter(new ExceptionReporter()).build(SQContextWrapper.getApplicationContext());
        this.mHttpClient = httpClientBuild;
        return httpClientBuild;
    }

    private static OkHttpClient.Builder createBuilder() {
        return new OkHttpClient.Builder().addInterceptor(new RequestIdOkInterceptor()).addInterceptor(new SignInterceptor()).addInterceptor(new GateWayEncryptInterceptor(new GateWayEncryptInterceptor.Provider() { // from class: com.sq.tool.network.SqHttpClient.1
            @Override // com.sdk.sq.net.gateway.GateWayEncryptInterceptor.Provider
            public String provideKey() {
                return GateWayManager.getKey();
            }

            @Override // com.sdk.sq.net.gateway.GateWayEncryptInterceptor.Provider
            public HashSet<String> provideWhiteList() {
                return GateWayManager.getWhiteList();
            }

            @Override // com.sdk.sq.net.gateway.GateWayEncryptInterceptor.Provider
            public String provideXRequestVersion() {
                return GateWayManager.getXVersion();
            }
        }, new ExceptionReporter()));
    }
}
