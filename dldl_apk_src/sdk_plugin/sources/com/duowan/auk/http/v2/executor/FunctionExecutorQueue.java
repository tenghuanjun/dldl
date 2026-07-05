package com.duowan.auk.http.v2.executor;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;
import com.duowan.auk.http.v2.HttpRequestDelegate;
import com.duowan.auk.http.v2.HttpResponseDelegate;
import com.duowan.auk.http.v2.exception.HttpV2Error;
import com.duowan.auk.util.L;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class FunctionExecutorQueue<Rsp> implements HttpResponseDelegate<Rsp> {
    private static final int PRIORITY_HIGH = 1;
    private static final int PRIORITY_LOW = -1;
    private static final int PRIORITY_NORMAL = 0;
    private static final String TAG = "FunctionExecutorQueue";
    private FunctionExecutor mCurrentExecutor;
    private int mCurrentRetryTime;
    private List<FunctionExecutor> mExecutors;
    private CustTimeOutRequestDelegate mHttpRequestDelegate;
    private HttpResponseDelegate<Rsp> mHttpResponseDelegate;
    private int mMaxRetryTimes;

    public FunctionExecutorQueue(List<FunctionExecutor> list, HttpRequestDelegate httpRequestDelegate, HttpResponseDelegate<Rsp> httpResponseDelegate) {
        ArrayList arrayList = new ArrayList(list);
        this.mExecutors = arrayList;
        Collections.sort(arrayList);
        CustTimeOutRequestDelegate custTimeOutRequestDelegate = new CustTimeOutRequestDelegate(httpRequestDelegate);
        this.mHttpRequestDelegate = custTimeOutRequestDelegate;
        this.mHttpResponseDelegate = httpResponseDelegate;
        this.mMaxRetryTimes = custTimeOutRequestDelegate.getRealMaxRetryTimes();
    }

    @Override // com.duowan.auk.http.v2.HttpResponseDelegate
    public Rsp parseResponse(NetworkResponse networkResponse) throws VolleyError {
        return this.mHttpResponseDelegate.parseResponse(networkResponse);
    }

    @Override // com.duowan.auk.http.v2.HttpResponseDelegate
    public void deliverError(VolleyError volleyError) {
        if (!(volleyError instanceof HttpV2Error)) {
            FunctionExecutor functionExecutor = this.mCurrentExecutor;
            if (functionExecutor != null) {
                functionExecutor.setPriority(-1);
            }
            int i = this.mMaxRetryTimes - this.mCurrentRetryTime;
            L.info(TAG, "Error occurred for executor: %s and cacheKey: %s, retry times remain %d", this.mCurrentExecutor, this.mHttpRequestDelegate.getCacheKey(), Integer.valueOf(i));
            if (i <= 0) {
                this.mHttpResponseDelegate.deliverError(volleyError);
                return;
            }
            L.error(TAG, (Throwable) volleyError);
            this.mCurrentRetryTime++;
            execute();
            return;
        }
        this.mHttpResponseDelegate.deliverError(volleyError);
    }

    @Override // com.duowan.auk.http.v2.HttpResponseDelegate
    public void deliverResponse(Rsp rsp) {
        this.mHttpResponseDelegate.deliverResponse(rsp);
        FunctionExecutor functionExecutor = this.mCurrentExecutor;
        if (functionExecutor != null) {
            functionExecutor.setPriority(1);
        }
    }

    public void execute() {
        FunctionExecutor nextExecutor = getNextExecutor();
        this.mCurrentExecutor = nextExecutor;
        if (nextExecutor != null) {
            L.info(TAG, "[execute] currentExecutor = %s, cacheKey = %s", nextExecutor, this.mHttpRequestDelegate.getCacheKey());
            this.mCurrentExecutor.execute(this.mHttpRequestDelegate, this);
        }
    }

    private FunctionExecutor getNextExecutor() {
        return this.mExecutors.get(this.mCurrentRetryTime % this.mExecutors.size());
    }
}
