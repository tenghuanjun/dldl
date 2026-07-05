package com.duowan.networkmars.hysignal;

import android.text.TextUtils;
import com.android.volley.NetworkResponse;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.duowan.auk.http.v2.HttpRequestDelegate;
import com.duowan.auk.http.v2.HttpResponseDelegate;
import com.duowan.auk.http.v2.executor.FunctionExecutor;
import com.duowan.auk.util.L;
import com.huya.mtp.hyns.MtpMarsTransporter;
import com.huya.mtp.hyns.NS;
import com.huya.mtp.hyns.api.Call;
import com.huya.mtp.hyns.api.Callback;
import com.huya.mtp.hyns.api.NSLongLinkApi;
import com.huya.mtp.hyns.api.Request;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HySignalExecutor extends FunctionExecutor {
    public static final String DEFAULT_FUNCNAME_KEY = "sFuncName";
    public static final String DEFAULT_REQUEST_SHORT_KEY = "sShort";
    public static final String DEFAULT_RETRYCOUNT_KEY = "retryCount";
    public static final String DEFAULT_SERVANTNAME_KEY = "sServantName";
    private static final int WUP_REQ = 3;
    private Map<HttpRequestDelegate, Call> mCalls = new HashMap();

    @Override // com.duowan.auk.http.v2.executor.FunctionExecutor
    public <Rsp> void cancel(HttpRequestDelegate httpRequestDelegate, HttpResponseDelegate<Rsp> httpResponseDelegate) {
        Call call = this.mCalls.get(httpRequestDelegate);
        if (call == null) {
            return;
        }
        call.cancel();
    }

    @Override // com.duowan.auk.http.v2.executor.FunctionExecutor
    public <Rsp> void execute(final HttpRequestDelegate httpRequestDelegate, final HttpResponseDelegate<Rsp> httpResponseDelegate) {
        int iIntValue;
        String str = httpRequestDelegate.getParams().get(DEFAULT_SERVANTNAME_KEY);
        String str2 = httpRequestDelegate.getParams().get(DEFAULT_FUNCNAME_KEY);
        String str3 = httpRequestDelegate.getParams().get(DEFAULT_REQUEST_SHORT_KEY);
        String str4 = httpRequestDelegate.getParams().get(DEFAULT_RETRYCOUNT_KEY);
        if (TextUtils.isEmpty(str4)) {
            iIntValue = 1;
        } else {
            try {
                iIntValue = Integer.valueOf(str4).intValue();
            } catch (Throwable th) {
                L.error("HySignalExecutor", th);
                iIntValue = 1;
            }
        }
        String str5 = "/" + str + "/" + str2;
        HySignalDynamicParams hySignalDynamicParams = HySignalDynamicParams.getInstance();
        int channel = (TextUtils.isEmpty(str3) || !Boolean.parseBoolean(str3)) ? hySignalDynamicParams.getChannel(str5) : 1;
        int retryCount = hySignalDynamicParams.getRetryCount(str5);
        boolean limitFlow = hySignalDynamicParams.getLimitFlow(str5);
        boolean limitFrequency = hySignalDynamicParams.getLimitFrequency(str5);
        int totalTimeout = hySignalDynamicParams.getTotalTimeout(str5);
        boolean networkStatusSensitive = hySignalDynamicParams.getNetworkStatusSensitive(str5);
        Request.Builder builderChannel = new Request.Builder().cmdId(3).cgi(str5).body(httpRequestDelegate.getBody()).channel(channel);
        if (iIntValue == 1) {
            iIntValue = retryCount;
        }
        Call callNewCall = ((NSLongLinkApi) NS.get(NSLongLinkApi.class)).newCall(builderChannel.retryCount(iIntValue).limitFlow(limitFlow).limitFrequency(limitFrequency).totalTimeout(totalTimeout).networkStatusSensitive(networkStatusSensitive).build());
        this.mCalls.put(httpRequestDelegate, callNewCall);
        callNewCall.enqueue(new Callback() { // from class: com.duowan.networkmars.hysignal.HySignalExecutor.1
            @Override // com.huya.mtp.hyns.api.Callback
            public void onResponse(byte[] bArr, int i, int i2) {
                HySignalExecutor.this.dispatchResponse(bArr, i, i2, httpResponseDelegate);
                HySignalExecutor.this.mCalls.remove(httpRequestDelegate);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchResponse(final byte[] bArr, final int i, int i2, final HttpResponseDelegate httpResponseDelegate) {
        MtpMarsTransporter.DispatcherThread.execute(new Runnable() { // from class: com.duowan.networkmars.hysignal.HySignalExecutor.2
            @Override // java.lang.Runnable
            public void run() {
                int i3 = i;
                if (i3 == 10) {
                    return;
                }
                if (i3 != 0) {
                    httpResponseDelegate.deliverError(new TimeoutError());
                    return;
                }
                try {
                    httpResponseDelegate.deliverResponse(httpResponseDelegate.parseResponse(new NetworkResponse(bArr)));
                } catch (VolleyError e) {
                    httpResponseDelegate.deliverError(e);
                }
            }
        });
    }
}
