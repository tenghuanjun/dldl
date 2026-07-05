package com.duowan.networkmars.http;

import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.duowan.auk.http.v2.HttpRequestDelegate;
import com.duowan.auk.http.v2.HttpResponseDelegate;
import com.duowan.auk.http.v2.executor.VolleyRequestExecutor;
import com.duowan.networkmars.hysignal.HySignalSDK;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HttpExecutor extends VolleyRequestExecutor {

    private class HttpResponse<Rsp> implements HttpResponseDelegate<Rsp> {
        private HttpResponseDelegate<Rsp> mHttpResponseDelegate;

        public HttpResponse(HttpResponseDelegate<Rsp> httpResponseDelegate) {
            this.mHttpResponseDelegate = httpResponseDelegate;
        }

        @Override // com.duowan.auk.http.v2.HttpResponseDelegate
        public Rsp parseResponse(NetworkResponse networkResponse) throws VolleyError {
            return this.mHttpResponseDelegate.parseResponse(networkResponse);
        }

        @Override // com.duowan.auk.http.v2.HttpResponseDelegate
        public void deliverError(VolleyError volleyError) {
            if ((volleyError instanceof TimeoutError) || (volleyError instanceof NoConnectionError)) {
                HySignalSDK.getInstance().removeUrl();
            }
            this.mHttpResponseDelegate.deliverError(volleyError);
        }

        @Override // com.duowan.auk.http.v2.HttpResponseDelegate
        public void deliverResponse(Rsp rsp) {
            this.mHttpResponseDelegate.deliverResponse(rsp);
        }
    }

    @Override // com.duowan.auk.http.v2.executor.VolleyRequestExecutor, com.duowan.auk.http.v2.executor.FunctionExecutor
    public <Rsp> void execute(HttpRequestDelegate httpRequestDelegate, HttpResponseDelegate<Rsp> httpResponseDelegate) {
        super.execute(httpRequestDelegate, new HttpResponse(httpResponseDelegate));
    }
}
