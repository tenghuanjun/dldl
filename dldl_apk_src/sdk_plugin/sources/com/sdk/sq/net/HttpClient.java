package com.sdk.sq.net;

import android.content.Context;
import com.sdk.sq.net.RequestBuilder;
import com.sq.tools.network.httpdns.SqHttpDns;
import com.sq.tools.report.event.IEventReporter;
import com.sq.tools.report.exception.IExceptionReporter;
import com.sqnetwork.voly.IpController;
import com.sqnetwork.voly.Request;
import com.sqnetwork.voly.RequestInterceptor;
import com.sqnetwork.voly.RequestQueue;
import com.sqnetwork.voly.RetryPolicyFactory;
import com.sqnetwork.voly.VolleyLog;
import com.sqnetwork.voly.toolbox.BaseHttpStack;
import com.sqnetwork.voly.toolbox.OkHttp3Stack;
import com.sqnetwork.voly.toolbox.SSLIgnore;
import com.sqnetwork.voly.toolbox.Voly;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpClient {
    private static final int DEFAULT_NETWORK_THREAD_POOL_SIZE = 4;
    private ExecutorDeliveryWithReporter mDelivery;
    private EventReporter mEventReporter;
    private boolean mFallbackHttpsToHttp;
    private List<RequestBuilder.HeadersTransformer> mHeadersTransformers;
    private boolean mHttpDnsEnable;
    private IpController mIpController;
    private List<RequestBuilder.ParamsTransformer> mParamsTransformers;
    private ReqIdGenerator mReqIdGenerator;
    private List<RequestInterceptor> mRequestInterceptors;
    private final RequestQueue mRequestQueue;
    private RetryPolicyFactory mRetryPolicyFactory;
    private Map<String, Boolean> mUserConsent;

    public interface ReqIdGenerator {
        String generateId();
    }

    private HttpClient(Context context, OkHttpClient.Builder builder, boolean ignoreSSL) {
        this.mHttpDnsEnable = true;
        builder = builder == null ? new OkHttpClient.Builder() : builder;
        OkHttpClient okHttpClientBuild = (ignoreSSL ? SSLIgnore.ignoreSSL(builder) : builder).eventListener(new OkHttpEventListener(context)).build();
        this.mDelivery = new ExecutorDeliveryWithReporter();
        RequestQueue requestQueueNewRequestQueue = Voly.newRequestQueue(context, new OkHttp3Stack(okHttpClientBuild), 4, this.mDelivery);
        this.mRequestQueue = requestQueueNewRequestQueue;
        monitorRequestFinish(requestQueueNewRequestQueue);
    }

    private HttpClient(Context context, BaseHttpStack stack) {
        this.mHttpDnsEnable = true;
        ExecutorDeliveryWithReporter executorDeliveryWithReporter = new ExecutorDeliveryWithReporter();
        this.mDelivery = executorDeliveryWithReporter;
        RequestQueue requestQueueNewRequestQueue = Voly.newRequestQueue(context, stack, 4, executorDeliveryWithReporter);
        this.mRequestQueue = requestQueueNewRequestQueue;
        monitorRequestFinish(requestQueueNewRequestQueue);
    }

    public HttpClient(RequestQueue queue) {
        this.mHttpDnsEnable = true;
        this.mRequestQueue = queue;
        monitorRequestFinish(queue);
    }

    public RequestBuilder newBuilder() {
        String strGenerateId;
        RequestBuilder requestBuilderHttpClient = new RequestBuilder().httpClient(this);
        ReqIdGenerator reqIdGenerator = this.mReqIdGenerator;
        if (reqIdGenerator != null && (strGenerateId = reqIdGenerator.generateId()) != null && !strGenerateId.isEmpty()) {
            requestBuilderHttpClient.id(strGenerateId);
        }
        requestBuilderHttpClient.setFallbackToHttp(this.mFallbackHttpsToHttp).setHttpDnsEnable(this.mHttpDnsEnable).retryPolicyFactory(this.mRetryPolicyFactory);
        List<RequestBuilder.ParamsTransformer> list = this.mParamsTransformers;
        if (list != null) {
            Iterator<RequestBuilder.ParamsTransformer> it = list.iterator();
            while (it.hasNext()) {
                requestBuilderHttpClient.addParamsTransformer(it.next());
            }
        }
        List<RequestBuilder.HeadersTransformer> list2 = this.mHeadersTransformers;
        if (list2 != null) {
            Iterator<RequestBuilder.HeadersTransformer> it2 = list2.iterator();
            while (it2.hasNext()) {
                requestBuilderHttpClient.addHeadersTransformer(it2.next());
            }
        }
        List<RequestInterceptor> list3 = this.mRequestInterceptors;
        if (list3 != null) {
            Iterator<RequestInterceptor> it3 = list3.iterator();
            while (it3.hasNext()) {
                requestBuilderHttpClient.addRequestInterceptor(it3.next());
            }
        }
        return requestBuilderHttpClient;
    }

    public <T> Request<T> enqueue(Request<T> request) {
        request.setUserConsent(this.mUserConsent);
        request.setIpController(this.mIpController);
        return this.mRequestQueue.add(request);
    }

    private void monitorRequestFinish(RequestQueue queue) {
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener() { // from class: com.sdk.sq.net.-$$Lambda$HttpClient$xHBCHzDx-YjzWEFwo_F7TRWjcW8
            @Override // com.sqnetwork.voly.RequestQueue.RequestFinishedListener
            public final void onRequestFinished(Request request) {
                this.f$0.lambda$monitorRequestFinish$0$HttpClient(request);
            }
        });
    }

    public /* synthetic */ void lambda$monitorRequestFinish$0$HttpClient(Request request) {
        EventReporter eventReporter;
        boolean zReportNetStat = false;
        try {
            if (SqHttpDns.getInstance().isGlobalEnable() && SqHttpDns.getInstance().hasInitialized()) {
                zReportNetStat = SqHttpDns.getInstance().reportNetStat();
            }
        } catch (Throwable unused) {
        }
        if (zReportNetStat && (eventReporter = this.mEventReporter) != null) {
            eventReporter.report(request);
        }
    }

    public void setExceptionReporter(IExceptionReporter reporter) {
        ExecutorDeliveryWithReporter executorDeliveryWithReporter = this.mDelivery;
        if (executorDeliveryWithReporter != null) {
            executorDeliveryWithReporter.setExceptionReporter(new ExceptionReporter(reporter));
        }
    }

    public void setUserConsent(Map<String, Boolean> consent) {
        VolleyLog.d("设置用户同意情况: " + consent, new Object[0]);
        this.mUserConsent = consent;
    }

    public void setIpController(IpController controller) {
        VolleyLog.d("设置IpController", new Object[0]);
        this.mIpController = controller;
    }

    public static class Builder {
        private IEventReporter mEventReporter;
        private IExceptionReporter mExceptionReporter;
        private boolean mFallbackHttpsToHttp;
        private List<RequestBuilder.HeadersTransformer> mHeadersTransformers;
        private boolean mHttpDnsEnable = true;
        private boolean mIgnoreSSL;
        private OkHttpClient.Builder mOkHttpBuilder;
        private List<RequestBuilder.ParamsTransformer> mParamsTransformers;
        private ReqIdGenerator mReqIdGenerator;
        private List<RequestInterceptor> mRequestInterceptors;
        private RetryPolicyFactory mRetryPolicyFactory;
        private BaseHttpStack mStack;

        public Builder setHttpStack(BaseHttpStack stack) {
            this.mStack = stack;
            return this;
        }

        public Builder setIgnoreSSL(boolean ignoreSSL) {
            this.mIgnoreSSL = ignoreSSL;
            return this;
        }

        public Builder setOkHttpBuilder(OkHttpClient.Builder builder) {
            this.mOkHttpBuilder = builder;
            return this;
        }

        public Builder setRetryPolicyFactory(RetryPolicyFactory factory) {
            this.mRetryPolicyFactory = factory;
            return this;
        }

        public Builder setEventReporter(IEventReporter reporter) {
            this.mEventReporter = reporter;
            return this;
        }

        public Builder setExceptionReporter(IExceptionReporter reporter) {
            this.mExceptionReporter = reporter;
            return this;
        }

        public Builder setFallbackToHttp(boolean enable) {
            this.mFallbackHttpsToHttp = enable;
            return this;
        }

        public Builder setHttpDnsEnable(boolean enable) {
            this.mHttpDnsEnable = enable;
            return this;
        }

        public Builder addParamsTransformer(RequestBuilder.ParamsTransformer transformer) {
            if (transformer == null) {
                return this;
            }
            if (this.mParamsTransformers == null) {
                this.mParamsTransformers = new ArrayList();
            }
            this.mParamsTransformers.add(transformer);
            return this;
        }

        public Builder addHeadersTransformer(RequestBuilder.HeadersTransformer transformer) {
            if (transformer == null) {
                return this;
            }
            if (this.mHeadersTransformers == null) {
                this.mHeadersTransformers = new ArrayList();
            }
            this.mHeadersTransformers.add(transformer);
            return this;
        }

        public Builder addRequestInterceptor(RequestInterceptor interceptor) {
            if (interceptor == null) {
                return this;
            }
            if (this.mRequestInterceptors == null) {
                this.mRequestInterceptors = new ArrayList();
            }
            this.mRequestInterceptors.add(interceptor);
            return this;
        }

        public Builder setReqIdGenerator(ReqIdGenerator reqIdGenerator) {
            this.mReqIdGenerator = reqIdGenerator;
            return this;
        }

        public HttpClient build(Context context) {
            HttpClient httpClient;
            BaseHttpStack baseHttpStack = this.mStack;
            if (baseHttpStack != null) {
                httpClient = new HttpClient(context, baseHttpStack);
            } else {
                httpClient = new HttpClient(context, this.mOkHttpBuilder, this.mIgnoreSSL);
            }
            httpClient.mRetryPolicyFactory = this.mRetryPolicyFactory;
            httpClient.mEventReporter = new EventReporter(this.mEventReporter);
            httpClient.setExceptionReporter(this.mExceptionReporter);
            httpClient.mFallbackHttpsToHttp = this.mFallbackHttpsToHttp;
            httpClient.mHttpDnsEnable = this.mHttpDnsEnable;
            httpClient.mParamsTransformers = this.mParamsTransformers;
            httpClient.mHeadersTransformers = this.mHeadersTransformers;
            httpClient.mRequestInterceptors = this.mRequestInterceptors;
            httpClient.mReqIdGenerator = this.mReqIdGenerator;
            return httpClient;
        }
    }
}
