package com.huya.mtp.hyns.volley;

import android.os.Handler;
import android.os.Looper;
import com.android.volley.ExecutorDelivery;
import com.android.volley.RequestQueue;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.TransportRequestListener;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.data.transporter.param.HttpParams;
import com.huya.mtp.data.transporter.param.HttpResult;
import com.huya.mtp.data.transporter.param.Params;
import com.huya.mtp.http.HttpFunctionEntry;
import com.huya.mtp.hyns.NSFunction;
import com.huya.mtp.hyns.NSResponse;
import com.huya.mtp.hyns.NSStat;
import com.huya.mtp.hyns.NSTransporter;
import com.squareup.okhttp.OkHttpClient;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MtpVolleyTransporter extends NSTransporter {
    private static RequestQueue msQueue;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override // com.huya.mtp.data.transporter.Transporter
    public /* bridge */ /* synthetic */ void read(Params params, TransportRequestListener transportRequestListener) {
        read((HttpParams) params, (TransportRequestListener<HttpResult>) transportRequestListener);
    }

    public MtpVolleyTransporter() {
        if (msQueue != null || MTPApi.CONTEXT.getApplication() == null) {
            return;
        }
        msQueue = Volley.newRequestQueue(MTPApi.CONTEXT.getApplication(), new HttpsSupportStack(), new ExecutorDelivery(HttpFunctionEntry.sDispatchHandlerExecutor));
    }

    private static OkHttpClient getSafeOkHttpClient(String str) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setHostnameVerifier(getSafeHostnameVerifier(str));
        return okHttpClient;
    }

    private static HostnameVerifier getSafeHostnameVerifier(final String str) {
        return new HostnameVerifier() { // from class: com.huya.mtp.hyns.volley.MtpVolleyTransporter.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str2, SSLSession sSLSession) {
                if (str.equals(str2)) {
                    return true;
                }
                return HttpsURLConnection.getDefaultHostnameVerifier().verify(str2, sSLSession);
            }
        };
    }

    public void read(final HttpParams httpParams, final TransportRequestListener<HttpResult> transportRequestListener) {
        Runnable runnable = new Runnable() { // from class: com.huya.mtp.hyns.volley.MtpVolleyTransporter.2
            @Override // java.lang.Runnable
            public void run() {
                if (MtpVolleyTransporter.msQueue == null) {
                    RequestQueue unused = MtpVolleyTransporter.msQueue = Volley.newRequestQueue(MTPApi.CONTEXT.getApplication(), new HttpsSupportStack(), new ExecutorDelivery(HttpFunctionEntry.sDispatchHandlerExecutor));
                }
                HttpVolleyRequest.getInstance(httpParams, true).setListener(transportRequestListener).setTransporter(MtpVolleyTransporter.this).execute(MtpVolleyTransporter.msQueue);
            }
        };
        if (MTPApi.CONTEXT.getApplication() == null) {
            this.mHandler.postDelayed(runnable, 2000L);
        } else {
            runnable.run();
        }
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public boolean cancel(HttpParams httpParams) {
        HttpVolleyRequest httpVolleyRequest = HttpVolleyRequest.getInstance(httpParams, false);
        if (httpVolleyRequest == null) {
            return false;
        }
        httpVolleyRequest.cancel();
        return true;
    }

    @Override // com.huya.mtp.hyns.NSTransporter
    protected NSStat initStat() {
        return new NSStat() { // from class: com.huya.mtp.hyns.volley.MtpVolleyTransporter.3
            @Override // com.huya.mtp.hyns.NSStat
            public void onError(NSFunction<?> nSFunction, DataException dataException, Transporter<?, ?> transporter) {
            }

            @Override // com.huya.mtp.hyns.NSStat
            public void onExecute(NSFunction<?> nSFunction) {
            }

            @Override // com.huya.mtp.hyns.NSStat
            public void onProduceEvent(NSFunction<?> nSFunction, int i) {
            }

            @Override // com.huya.mtp.hyns.NSStat
            public <T> void onResponse(NSFunction<T> nSFunction, NSResponse<T> nSResponse, Transporter<?, ?> transporter) {
            }
        };
    }
}
