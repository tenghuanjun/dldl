package com.sq.tools.network.httpdns.util;

import android.os.Looper;
import com.sdk.sq.net.HttpClient;
import com.sdk.sq.net.RequestBuilder;
import com.sq.tools.network.httpdns.SqHttpDns;
import com.sq.tools.network.httpdns.network.IGetJsonRequest;
import com.sqnetwork.voly.DefaultRetryPolicy;
import com.sqnetwork.voly.NoConnectionError;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.TimeoutError;
import com.sqnetwork.voly.VolleyError;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class VolleyRequest implements IGetJsonRequest {
    private final HttpClient mHttpClient;

    public VolleyRequest(HttpClient httpClient) {
        this.mHttpClient = httpClient;
    }

    @Override // com.sq.tools.network.httpdns.network.IGetJsonRequest
    public void request(String url, Map<String, String> headers, Map<String, String> params, long timeout, IGetJsonRequest.IGetJsonRequestCallback callback) {
        Request<JSONObject> requestBuild = new RequestBuilder().get(url, params).headers(headers).setHttpDnsEnable(false).retryPolicy(new DefaultRetryPolicy((int) timeout, 0, 1.0f)).callback(new AnonymousClass1(callback)).build();
        requestBuild.setShouldCache(false);
        this.mHttpClient.enqueue(requestBuild);
    }

    /* JADX INFO: renamed from: com.sq.tools.network.httpdns.util.VolleyRequest$1, reason: invalid class name */
    class AnonymousClass1 implements RequestBuilder.RequestCallback {
        final /* synthetic */ IGetJsonRequest.IGetJsonRequestCallback val$callback;

        AnonymousClass1(final IGetJsonRequest.IGetJsonRequestCallback val$callback) {
            this.val$callback = val$callback;
        }

        @Override // com.sdk.sq.net.RequestBuilder.RequestCallback
        /* JADX INFO: renamed from: onSuccess, reason: merged with bridge method [inline-methods] */
        public void lambda$onSuccess$0$VolleyRequest$1(final int httpStatus, final Map<String, String> headers, final JSONObject response) {
            if (Looper.getMainLooper() == Looper.myLooper()) {
                SqHttpDns.getInstance().executeAsync(new Runnable() { // from class: com.sq.tools.network.httpdns.util.-$$Lambda$VolleyRequest$1$AuP-PTNBj7T6xuwcBoh8Zw_oOOk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onSuccess$0$VolleyRequest$1(httpStatus, headers, response);
                    }
                });
            } else {
                this.val$callback.onSuccess(httpStatus, response.toString());
            }
        }

        @Override // com.sdk.sq.net.RequestBuilder.RequestCallback
        /* JADX INFO: renamed from: onError, reason: merged with bridge method [inline-methods] */
        public void lambda$onError$1$VolleyRequest$1(final int volleyCode, final Map<String, String> headers, final VolleyError volleyError) {
            int i;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                SqHttpDns.getInstance().executeAsync(new Runnable() { // from class: com.sq.tools.network.httpdns.util.-$$Lambda$VolleyRequest$1$gXqxP2YVVuBMT35O52vwvWSEhAQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onError$1$VolleyRequest$1(volleyCode, headers, volleyError);
                    }
                });
                return;
            }
            String string = volleyError.toString();
            if (volleyError.networkResponse != null) {
                i = volleyError.networkResponse.statusCode;
                byte[] bArr = volleyError.networkResponse.data;
                if (bArr != null && bArr.length > 2 && bArr[0] == 123 && bArr[bArr.length - 1] == 125) {
                    string = new String(bArr);
                }
            } else {
                i = ((volleyError instanceof TimeoutError) || (volleyError instanceof NoConnectionError)) ? 400 : -1;
            }
            this.val$callback.onFailure(i, string);
        }
    }
}
