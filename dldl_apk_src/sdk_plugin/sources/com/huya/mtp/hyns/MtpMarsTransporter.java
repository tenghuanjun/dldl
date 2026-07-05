package com.huya.mtp.hyns;

import android.os.Handler;
import android.os.Looper;
import com.huya.hal.Hal;
import com.huya.hal.HalConfig;
import com.huya.hysignal.core.Call;
import com.huya.hysignal.core.Callback;
import com.huya.hysignal.core.HySignalError;
import com.huya.hysignal.core.HySignalException;
import com.huya.hysignal.core.Request;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.exception.DataNetworkException;
import com.huya.mtp.data.transporter.TransportRequestListener;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.data.transporter.param.HttpParams;
import com.huya.mtp.data.transporter.param.HttpResult;
import com.huya.mtp.data.transporter.param.Params;
import com.huya.mtp.http.HttpFunction;
import com.huya.mtp.http.NetworkResponse;
import com.huya.mtp.hyns.api.Request;
import com.huya.mtp.hyns.hysignal.HalConfigWrapper;
import com.huya.mtp.hyns.hysignal.HyDns;
import com.huya.mtp.hyns.hysignal.HyDownloadImpl;
import com.huya.mtp.hyns.hysignal.HyLaunchBiz;
import com.huya.mtp.hyns.hysignal.HyLongLink;
import com.huya.mtp.hyns.hysignal.HyNetUtilImpl;
import com.huya.mtp.hyns.hysignal.HyProxySignalImpl;
import com.huya.mtp.hyns.hysignal.HyPushControlImpl;
import com.huya.mtp.hyns.hysignal.HyRegisterImpl;
import com.huya.mtp.hyns.hysignal.HyTimeSyncImpl;
import com.huya.mtp.hyns.hysignal.HyUserInfoImpl;
import com.huya.mtp.hyns.hysignal.HyVerifyImpl;
import com.huya.mtp.hyns.protocol.NSDnsProtocol;
import com.huya.mtp.hyns.protocol.NSDownloadProtocol;
import com.huya.mtp.hyns.protocol.NSLaunchProtocol;
import com.huya.mtp.hyns.protocol.NSLongLinkProtocol;
import com.huya.mtp.hyns.protocol.NSNetUtilProtocol;
import com.huya.mtp.hyns.protocol.NSProxySignalProtocol;
import com.huya.mtp.hyns.protocol.NSPushControlProtocol;
import com.huya.mtp.hyns.protocol.NSRegisterProtocol;
import com.huya.mtp.hyns.protocol.NSTimeSyncProtocol;
import com.huya.mtp.hyns.protocol.NSUserInfoProtocol;
import com.huya.mtp.hyns.protocol.NSVerifyProtocol;
import com.huya.mtp.hyns.stat.HySignalStat;
import com.huya.mtp.nsdt.NSDT;
import com.taptap.sdk.common.oaid.helper.OAIDHelper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class MtpMarsTransporter extends NSTransporter {
    public static final int PRINT_CONFIG_DELAY_MILLIS = 5000;
    public static final String TAG = "NetService-MtpMarsTransporter";
    private static final int WUP_REQ = 3;
    private static boolean isInit;
    private Map<HttpParams, Call> mCalls;
    private int mDefaultChannel;
    private int mDefaultTotalTimeout;
    private OnReadRequestListener mOnReadRequestListener;
    private OnReadRequestListenerV2 mOnReadRequestListenerV2;

    @Deprecated
    public interface OnReadRequestListener {
        @Deprecated
        void onRead(HttpParams httpParams, Request.Builder builder);
    }

    public interface OnReadRequestListenerV2 {
        void onRead(HttpParams httpParams, Request.Builder builder);
    }

    public interface ThreadPoolFactory {
        ThreadPoolExecutor getThreadPool();
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public /* bridge */ /* synthetic */ void read(Params params, TransportRequestListener transportRequestListener) {
        read((HttpParams) params, (TransportRequestListener<HttpResult>) transportRequestListener);
    }

    @Deprecated
    public void setOnReadRequestListener(OnReadRequestListener onReadRequestListener) {
        this.mOnReadRequestListener = onReadRequestListener;
    }

    public void setOnReadRequestListenerV2(OnReadRequestListenerV2 onReadRequestListenerV2) {
        this.mOnReadRequestListenerV2 = onReadRequestListenerV2;
    }

    public MtpMarsTransporter(HalConfigWrapper halConfigWrapper) {
        this(halConfigWrapper, true);
    }

    @Deprecated
    public MtpMarsTransporter(HalConfigWrapper halConfigWrapper, boolean z) {
        this.mCalls = new ConcurrentHashMap();
        this.mDefaultChannel = 3;
        this.mDefaultTotalTimeout = 0;
        if (isInit) {
            return;
        }
        isInit = true;
        HalConfig.Builder halConfig = halConfigWrapper.getHalConfig();
        halConfig.setEncryptKey("ABCDEFGHIJKLMNOP");
        initHal(halConfig.build());
        injectImpl();
    }

    private void initHal(final HalConfig halConfig) {
        Hal.init(halConfig);
        NSDT.init();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.huya.mtp.hyns.MtpMarsTransporter.1
            @Override // java.lang.Runnable
            public void run() {
                MTPApi.LOGGER.info(MtpMarsTransporter.TAG, halConfig.toString());
            }
        }, OAIDHelper.TIMEOUT);
    }

    public void injectImpl() {
        ((NSDnsProtocol) NS.getProtocolImpl(NSDnsProtocol.class)).setApi(new HyDns());
        ((NSLaunchProtocol) NS.getProtocolImpl(NSLaunchProtocol.class)).setApi(new HyLaunchBiz());
        ((NSRegisterProtocol) NS.getProtocolImpl(NSRegisterProtocol.class)).setApi(new HyRegisterImpl());
        ((NSVerifyProtocol) NS.getProtocolImpl(NSVerifyProtocol.class)).setApi(new HyVerifyImpl());
        ((NSPushControlProtocol) NS.getProtocolImpl(NSPushControlProtocol.class)).setApi(new HyPushControlImpl());
        ((NSLongLinkProtocol) NS.getProtocolImpl(NSLongLinkProtocol.class)).setApi(new HyLongLink());
        ((NSUserInfoProtocol) NS.getProtocolImpl(NSUserInfoProtocol.class)).setApi(new HyUserInfoImpl());
        ((NSDownloadProtocol) NS.getProtocolImpl(NSDownloadProtocol.class)).setApi(new HyDownloadImpl());
        ((NSTimeSyncProtocol) NS.getProtocolImpl(NSTimeSyncProtocol.class)).setApi(new HyTimeSyncImpl());
        ((NSProxySignalProtocol) NS.getProtocolImpl(NSProxySignalProtocol.class)).setApi(new HyProxySignalImpl());
        ((NSNetUtilProtocol) NS.getProtocolImpl(NSNetUtilProtocol.class)).setApi(new HyNetUtilImpl());
    }

    @Override // com.huya.mtp.data.transporter.http.HttpTransporter, com.huya.mtp.data.transporter.Transporter
    public HttpResult read(HttpParams httpParams) throws DataException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            MTPApi.DEBUGGER.crashIfDebug("Cannot call sync read method in main thread!", new Object[0]);
        }
        return new AsyncToSync<HttpParams, HttpResult, DataException>() { // from class: com.huya.mtp.hyns.MtpMarsTransporter.2
            @Override // com.huya.mtp.hyns.AsyncToSync
            public void executeAsync(HttpParams httpParams2) {
                MtpMarsTransporter.this.read(httpParams2, new TransportRequestListener<HttpResult>() { // from class: com.huya.mtp.hyns.MtpMarsTransporter.2.1
                    @Override // com.huya.mtp.data.transporter.TransportRequestListener
                    public void onProducerEvent(int i) {
                    }

                    @Override // com.huya.mtp.data.transporter.TransportRequestListener
                    public /* bridge */ /* synthetic */ void onResponse(HttpResult httpResult, Transporter transporter) throws DataException {
                        onResponse2(httpResult, (Transporter<?, ?>) transporter);
                    }

                    /* JADX INFO: renamed from: onResponse, reason: avoid collision after fix types in other method */
                    public void onResponse2(HttpResult httpResult, Transporter<?, ?> transporter) throws DataException {
                        onTaskSucceed(httpResult);
                    }

                    @Override // com.huya.mtp.data.transporter.TransportRequestListener
                    public void onError(DataException dataException, Transporter<?, ?> transporter) {
                        onTaskFailed(dataException);
                    }

                    @Override // com.huya.mtp.data.transporter.TransportRequestListener
                    public void onCancelled() {
                        onTaskCancelled();
                    }
                });
            }
        }.execute(httpParams);
    }

    @Override // com.huya.mtp.hyns.NSTransporter
    protected NSStat initStat() {
        return new HySignalStat();
    }

    public void read(final HttpParams httpParams, final TransportRequestListener<HttpResult> transportRequestListener) {
        boolean zIsEncrypted;
        int i = this.mDefaultChannel;
        if (httpParams instanceof HttpFunction) {
            HttpFunction httpFunction = (HttpFunction) httpParams;
            int channel = httpFunction.getChannel() != -1 ? httpFunction.getChannel() : this.mDefaultChannel;
            zIsEncrypted = httpFunction.isEncrypted();
            i = channel;
        } else {
            zIsEncrypted = false;
        }
        int maxRetryTimes = httpParams.getMaxRetryTimes();
        int iOrdinal = httpParams.getPriority().ordinal();
        int i2 = this.mDefaultTotalTimeout;
        Request.Builder builderNetworkStatusSensitive = new Request.Builder().cmdId(3).cgi(getCgi(httpParams)).retryCount(maxRetryTimes).body(httpParams.getBody()).channel(i).limitFlow(false).limitFrequency(false).totalTimeout(i2).priority(iOrdinal).encrypt(zIsEncrypted).networkStatusSensitive(false);
        if (NSInnerConfig.getInstance().isNSStatOpen()) {
            builderNetworkStatusSensitive.traceId(httpParams.getReportId());
        }
        OnReadRequestListenerV2 onReadRequestListenerV2 = this.mOnReadRequestListenerV2;
        if (onReadRequestListenerV2 != null) {
            onReadRequestListenerV2.onRead(httpParams, builderNetworkStatusSensitive);
        }
        if (this.mOnReadRequestListener != null) {
            Request.Builder builderNetworkStatusSensitive2 = new Request.Builder().cmdId(3).cgi(getCgi(httpParams)).retryCount(maxRetryTimes).body(httpParams.getBody()).channel(i).limitFlow(false).limitFrequency(false).totalTimeout(i2).priority(iOrdinal).networkStatusSensitive(false);
            if (NSInnerConfig.getInstance().isNSStatOpen()) {
                builderNetworkStatusSensitive2.traceId(httpParams.getReportId());
            }
            this.mOnReadRequestListener.onRead(httpParams, builderNetworkStatusSensitive2);
        }
        Call callNewCall = Hal.getBaseBiz().newCall(builderNetworkStatusSensitive.build());
        this.mCalls.put(httpParams, callNewCall);
        transportRequestListener.onProducerEvent(101);
        callNewCall.enqueue(new Callback() { // from class: com.huya.mtp.hyns.MtpMarsTransporter.3
            @Override // com.huya.hysignal.core.Callback
            public void onResponse(final byte[] bArr, final HySignalError hySignalError) {
                transportRequestListener.onProducerEvent(102);
                MtpMarsTransporter.this.mCalls.remove(httpParams);
                DispatcherThread.execute(new Runnable() { // from class: com.huya.mtp.hyns.MtpMarsTransporter.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        int errCode;
                        HySignalError hySignalError2 = hySignalError;
                        int errType = 0;
                        if (hySignalError2 != null) {
                            errType = hySignalError2.getErrType();
                            errCode = hySignalError.getErrCode();
                        } else {
                            errCode = 0;
                        }
                        transportRequestListener.onProducerEvent(103);
                        if (errType == 10) {
                            transportRequestListener.onCancelled();
                            return;
                        }
                        if (errType != 0) {
                            transportRequestListener.onError(new DataNetworkException(new HySignalException(errType, errCode)), MtpMarsTransporter.this);
                            return;
                        }
                        try {
                            transportRequestListener.onProducerEvent(104);
                            transportRequestListener.onResponse(new HttpResult(new NetworkResponse(bArr)), MtpMarsTransporter.this);
                        } catch (DataException e) {
                            transportRequestListener.onError(e, MtpMarsTransporter.this);
                        }
                    }
                });
            }
        });
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public boolean cancel(HttpParams httpParams) {
        Call callRemove = this.mCalls.remove(httpParams);
        if (callRemove == null) {
            return false;
        }
        callRemove.cancel();
        return false;
    }

    private String getCgi(HttpParams httpParams) {
        return httpParams.getCgi();
    }

    public static final class DispatcherThread {
        public static final String TAG = "MTPMars-DispatcherThread";
        private ThreadPoolExecutor mThreadPoolExecutor;
        private ThreadPoolFactory mThreadPoolFactory = new ThreadPoolFactory() { // from class: com.huya.mtp.hyns.MtpMarsTransporter.DispatcherThread.1
            private static final int CORE_POOL_SIZE = 8;
            private static final int KEEP_ALIVE_TIME = 60;
            private static final int MAXIMUM_POOL_SIZE = 8;
            private static final String THREAD_NAME_PREFIX = "HySignalDispatcherThread-";

            @Override // com.huya.mtp.hyns.MtpMarsTransporter.ThreadPoolFactory
            public ThreadPoolExecutor getThreadPool() {
                MTPApi.LOGGER.info(DispatcherThread.TAG, "MtpMarsTransporter DispatcherThread get Default.");
                return new ThreadPoolExecutor(8, 8, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.huya.mtp.hyns.MtpMarsTransporter.DispatcherThread.1.1
                    private final AtomicInteger mCount = new AtomicInteger(1);

                    @Override // java.util.concurrent.ThreadFactory
                    public Thread newThread(Runnable runnable) {
                        return new Thread(runnable, AnonymousClass1.THREAD_NAME_PREFIX + this.mCount.getAndIncrement());
                    }
                });
            }
        };

        public static DispatcherThread getIntance() {
            return Holder.INSTANCE;
        }

        private static class Holder {
            static final DispatcherThread INSTANCE = new DispatcherThread();

            private Holder() {
            }
        }

        public synchronized void setThreadPoolFactory(ThreadPoolFactory threadPoolFactory) {
            this.mThreadPoolFactory = threadPoolFactory;
        }

        public ThreadPoolExecutor getThreadPool() {
            if (this.mThreadPoolExecutor == null) {
                synchronized (DispatcherThread.class) {
                    if (this.mThreadPoolExecutor == null) {
                        this.mThreadPoolExecutor = this.mThreadPoolFactory.getThreadPool();
                    }
                }
            }
            return this.mThreadPoolExecutor;
        }

        public static void execute(Runnable runnable) {
            if (runnable != null) {
                getIntance().getThreadPool().execute(runnable);
            }
        }
    }
}
