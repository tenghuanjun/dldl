package com.huya.mtp.http;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.data.DataFunction;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.exception.NullResponseException;
import com.huya.mtp.data.exception.ParseException;
import com.huya.mtp.data.exception.ValidationException;
import com.huya.mtp.data.parser.Parser;
import com.huya.mtp.data.strategy.Strategy;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.data.transporter.http.HttpTransporter;
import com.huya.mtp.data.transporter.param.NetworkParams;
import com.huya.mtp.data.transporter.param.NetworkResult;
import com.huya.mtp.http.Request;
import com.huya.mtp.http.cachestrategy.BaseNetworkStrategy;
import com.huya.mtp.http.cachestrategy.CacheOnly;
import com.huya.mtp.http.cachestrategy.CacheStrategyFactory;
import com.huya.mtp.http.cachestrategy.NetOnly;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class HttpFunction<Rsp> extends DataFunction<NetworkParams<Rsp>, NetworkResult, Rsp> implements NetworkParams<Rsp>, ResponseListener<Rsp> {
    public static final int DEFAULT_BACKOFF_MULTIPLIER = 0;
    public static final int DEFAULT_RETRY_TIME = 1;
    public static final int DEFAULT_TIMEOUT_INCREMENT = 0;
    public static final int DEFAULT_TIME_OUT = 30000;
    public static final int ILLEGAL_CHANNEL_VALUE = -1;
    private static final int MSG_TIMEOUT = 100;
    private static final String TAG = "HttpFunction";
    private int mBodyLength;
    private BaseNetworkStrategy<Rsp> mCacheStrategy;
    private static final Handler sMainHandler = new Handler(Looper.getMainLooper());
    private static final HandlerExecutor sBackgroundExecutor = new HandlerExecutor("HttpDeliverThread");
    private static final Map<String, HttpFunction> sExecutingFunctions = new HashMap();
    private static SegmentLock sSegmentLock = new SegmentLock();
    public static boolean debuggable = false;
    private static final Handler sWatchDogHandler = new Handler(Looper.getMainLooper()) { // from class: com.huya.mtp.http.HttpFunction.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 100 && message.obj != null && (message.obj instanceof Class)) {
                MTPApi.DEBUGGER.crashIfDebug("HttpFunction deliver timeout " + ((Class) message.obj).getName() + " " + HttpFunction.getStackTrace(), new Object[0]);
            }
        }
    };
    private int mCurrentRetryTimes = 0;
    private CacheType mCacheType = CacheType.NetOnly;
    private HttpTransporter mFunctionExecutor = initDefaultTransporter();
    private List<ResponseListener<Rsp>> mResponseListenerList = new ArrayList();

    private interface MergedRequestMessageDelivery<Rsp> {
        void deliver(ResponseListener<Rsp> responseListener);
    }

    protected abstract byte[] encodeBody();

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public int getBackoffMultiplier() {
        return 0;
    }

    @Override // com.huya.mtp.data.transporter.param.FileParams
    public String getCacheDir() {
        return null;
    }

    public int getChannel() {
        return -1;
    }

    public int getMaxRetryTimes() {
        return 1;
    }

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public String getReportId() {
        return "";
    }

    @Override // com.huya.mtp.data.DataEntity
    public NetworkParams<Rsp> getRequestParams() {
        return this;
    }

    @Override // com.huya.mtp.data.transporter.param.NetworkParams
    public abstract Class<? extends Rsp> getResponseType();

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public int getTimeout() {
        return 30000;
    }

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public int getTimeoutIncrement() {
        return 0;
    }

    public abstract HttpTransporter initDefaultTransporter();

    public boolean isEncrypted() {
        return false;
    }

    protected boolean mergeRequest() {
        return false;
    }

    @Override // com.huya.mtp.http.ResponseListener
    public void onCancelled() {
    }

    @Override // com.huya.mtp.http.ResponseListener
    @Deprecated
    public void onError(DataException dataException) {
    }

    @Override // com.huya.mtp.data.DataListener, com.huya.mtp.http.ResponseListener
    public void onProducerEvent(int i) {
    }

    protected abstract Rsp onReadResponse(NetworkResult networkResult) throws DataException;

    @Override // com.huya.mtp.data.transporter.UpdateListener
    public final void onUpdateCancelled() {
    }

    @Override // com.huya.mtp.data.transporter.UpdateListener
    public final void onUpdateError(DataException dataException) {
    }

    @Override // com.huya.mtp.data.transporter.UpdateListener
    public final void onUpdateSucceed() {
    }

    @Override // com.huya.mtp.data.DataFunction
    public final Rsp read() {
        return null;
    }

    @Override // com.huya.mtp.data.DataFunction
    public final Rsp read(Strategy<NetworkParams<Rsp>, NetworkResult, Rsp> strategy) {
        return null;
    }

    @Deprecated
    public boolean shouldDeliverInBackground() {
        return false;
    }

    @Override // com.huya.mtp.data.transporter.param.NetworkParams
    public boolean shouldUseCustomCache() {
        return false;
    }

    @Override // com.huya.mtp.data.transporter.param.NetworkParams
    public boolean testDataEnabled() {
        return false;
    }

    public static void putExecuting(HttpFunction httpFunction) {
        sExecutingFunctions.put(String.format("%s_%s", httpFunction.getClass().getName(), httpFunction.getCacheKey()), httpFunction);
    }

    public static <Rsp> HttpFunction<Rsp> getExecuting(HttpFunction<Rsp> httpFunction) {
        return sExecutingFunctions.get(String.format("%s_%s", httpFunction.getClass().getName(), httpFunction.getCacheKey()));
    }

    public static void removeExecuting(HttpFunction httpFunction) {
        sExecutingFunctions.remove(String.format("%s_%s", httpFunction.getClass().getName(), httpFunction.getCacheKey()));
    }

    @Override // com.huya.mtp.data.DataEntity
    protected Parser<NetworkResult, Rsp> initResponseParser() {
        return new Parser<NetworkResult, Rsp>() { // from class: com.huya.mtp.http.HttpFunction.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.huya.mtp.data.parser.Parser
            public NetworkResult encode(Rsp rsp) throws ParseException {
                return null;
            }

            @Override // com.huya.mtp.data.parser.Parser
            public Rsp decode(NetworkResult networkResult) throws ParseException {
                return (Rsp) HttpFunction.this.parseResponse(networkResult);
            }
        };
    }

    public void setFunctionExecutor(HttpTransporter httpTransporter) {
        this.mFunctionExecutor = httpTransporter;
    }

    public HttpTransporter getFunctionExecutor() {
        return this.mFunctionExecutor;
    }

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public void updateCurrentRetryTimes(int i) {
        this.mCurrentRetryTimes = i;
    }

    public int getCurrentRetryTimes() {
        return this.mCurrentRetryTimes;
    }

    @Override // com.huya.mtp.data.transporter.param.FileParams
    public long getCacheExpireTimeMillis() {
        return shouldUseCustomCache() ? 86400000L : 0L;
    }

    @Override // com.huya.mtp.data.transporter.param.FileParams
    public long getCacheRefreshTimeMillis() {
        return shouldUseCustomCache() ? 60000L : 0L;
    }

    protected Handler getDeliverHandler() {
        if (!shouldDeliverInBackground()) {
            return sMainHandler;
        }
        Looper looperMyLooper = Looper.myLooper();
        return (looperMyLooper == null || looperMyLooper == Looper.getMainLooper()) ? sBackgroundExecutor.getHandler() : new Handler(looperMyLooper);
    }

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public Map<String, String> getHeaders() {
        return new HashMap();
    }

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public Map<String, String> getParams() {
        return new HashMap();
    }

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public Request.Priority getPriority() {
        return Request.Priority.NORMAL;
    }

    public RspCache<Rsp> getCache() {
        return new CacheOnly().readFromCacheSync(this);
    }

    public static String getStackTrace() {
        StringBuilder sb = new StringBuilder("");
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Thread thread : allStackTraces.keySet()) {
            StackTraceElement[] stackTraceElementArr = allStackTraces.get(thread);
            if (stackTraceElementArr != null && (thread.getName().contains("IOBound") || "HttpDeliverThread".equals(thread.getName()))) {
                sb.append("Thread:");
                sb.append(thread.getName());
                sb.append("\r\n");
                for (StackTraceElement stackTraceElement : stackTraceElementArr) {
                    MTPApi.LOGGER.info(TAG, "timeout stack trace: " + stackTraceElement.toString());
                    sb.append(stackTraceElement.toString());
                    sb.append("\r\n");
                }
            }
        }
        return sb.toString();
    }

    private void deliver(final Runnable runnable) {
        if (runnable == null) {
            return;
        }
        Handler deliverHandler = getDeliverHandler();
        if (deliverHandler.getLooper() == Looper.myLooper()) {
            runnable.run();
            return;
        }
        if (Looper.getMainLooper() == deliverHandler.getLooper()) {
            deliverHandler.post(runnable);
        } else if (!debuggable) {
            deliverHandler.post(runnable);
        } else {
            deliverHandler.post(new Runnable() { // from class: com.huya.mtp.http.HttpFunction.3
                @Override // java.lang.Runnable
                public void run() {
                    Message message = new Message();
                    message.what = 100;
                    message.obj = HttpFunction.this.getClass();
                    HttpFunction.sWatchDogHandler.sendMessageDelayed(message, 10000L);
                    runnable.run();
                    HttpFunction.sWatchDogHandler.removeMessages(100);
                }
            });
        }
    }

    @Override // com.huya.mtp.data.DataFunction
    protected Strategy<NetworkParams<Rsp>, NetworkResult, Rsp> getDefaultStrategy() {
        return new NetOnly();
    }

    public void execute() {
        execute(this.mCacheType);
    }

    public void execute(CacheType cacheType) {
        if (needPrintEntity()) {
            MTPApi.LOGGER.info(TAG, "execute, cacheKey = %s, cacheType = %s, function entity = %s", getCacheKey(), cacheType, this);
        } else {
            MTPApi.LOGGER.info(TAG, "execute, cacheKey = %s, cacheType = %s", getCacheKey(), cacheType);
        }
        this.mCacheType = cacheType;
        if (mergeRequest()) {
            String cacheKey = getCacheKey();
            sSegmentLock.lock(cacheKey);
            try {
                HttpFunction executing = getExecuting(this);
                if (executing != null) {
                    executing.mResponseListenerList.add(this);
                    return;
                }
                putExecuting(this);
            } finally {
                sSegmentLock.unlock(cacheKey);
            }
        }
        BaseNetworkStrategy<Rsp> baseNetworkStrategyCreateCacheStrategy = CacheStrategyFactory.createCacheStrategy(cacheType);
        this.mCacheStrategy = baseNetworkStrategyCreateCacheStrategy;
        readAsync(baseNetworkStrategyCreateCacheStrategy.http(getFunctionExecutor()));
    }

    public HttpFunction<Rsp> setCacheType(CacheType cacheType) {
        this.mCacheType = cacheType;
        return this;
    }

    public CacheType getCacheType() {
        return this.mCacheType;
    }

    @Override // com.huya.mtp.data.DataListener
    public void onResponse(Rsp rsp, Transporter<?, ?> transporter) {
        doDeliverResponse(rsp, !(transporter instanceof HttpTransporter));
    }

    public void onError(DataException dataException, Transporter<?, ?> transporter) {
        doDeliverError(dataException, !(transporter instanceof HttpTransporter));
    }

    @Override // com.huya.mtp.data.DataListener
    public void onRequestCancelled() {
        deliverCancelled();
    }

    @Override // com.huya.mtp.data.transporter.param.NetworkParams
    public String testDataFolderPath() {
        return Environment.getExternalStorageDirectory() + "/httpfunction/";
    }

    @Override // com.huya.mtp.data.transporter.param.NetworkParams
    public String testDataFileName() {
        return getResponseType() != null ? getResponseType().getName() : "";
    }

    public void cancel() {
        BaseNetworkStrategy<Rsp> baseNetworkStrategy = this.mCacheStrategy;
        if (baseNetworkStrategy != null) {
            baseNetworkStrategy.cancel(this);
        }
        if (needPrintEntity()) {
            MTPApi.LOGGER.info(TAG, "cancel, cacheKey = %s, function entity = %s", getCacheKey(), this);
        } else {
            MTPApi.LOGGER.info(TAG, "cancel, cacheKey = %s", getCacheKey());
        }
    }

    public Rsp parseResponse(NetworkResult networkResult) throws ParseException {
        try {
            return onReadResponse(networkResult);
        } catch (ParseException e) {
            throw e;
        } catch (Exception e2) {
            throw new ParseException(e2);
        }
    }

    @Override // com.huya.mtp.data.DataEntity
    public void validateResponse(Rsp rsp) throws ValidationException {
        if (rsp == null) {
            throw new NullResponseException();
        }
    }

    public void deliverCancelled() {
        deliver(new Runnable() { // from class: com.huya.mtp.http.HttpFunction.4
            @Override // java.lang.Runnable
            public void run() {
                HttpFunction.this.onCancelled();
                HttpFunction.this.deliverToMergedRequests(new MergedRequestMessageDelivery<Rsp>() { // from class: com.huya.mtp.http.HttpFunction.4.1
                    @Override // com.huya.mtp.http.HttpFunction.MergedRequestMessageDelivery
                    public void deliver(ResponseListener<Rsp> responseListener) {
                        if (responseListener != null) {
                            responseListener.onCancelled();
                        }
                    }
                });
            }
        });
    }

    protected void doDeliverError(final DataException dataException, final boolean z) {
        MTPApi.LOGGER.error(TAG, String.format("deliverError, cacheKey = %s, fromCache = %b", getCacheKey(), Boolean.valueOf(z)), dataException);
        deliver(new Runnable() { // from class: com.huya.mtp.http.HttpFunction.5
            @Override // java.lang.Runnable
            public void run() {
                HttpFunction.this.onError(dataException, z);
                HttpFunction.this.deliverToMergedRequests(new MergedRequestMessageDelivery<Rsp>() { // from class: com.huya.mtp.http.HttpFunction.5.1
                    @Override // com.huya.mtp.http.HttpFunction.MergedRequestMessageDelivery
                    public void deliver(ResponseListener<Rsp> responseListener) {
                        if (responseListener != null) {
                            responseListener.onError(dataException, z);
                        }
                    }
                });
            }
        });
    }

    protected void doDeliverResponse(final Rsp rsp, final boolean z) {
        if (needPrintEntity()) {
            MTPApi.LOGGER.info(TAG, "deliverResponse, cacheKey = %s, fromCache = %b， response = %s", getCacheKey(), Boolean.valueOf(z), rsp);
        } else {
            MTPApi.LOGGER.info(TAG, "deliverResponse, cacheKey = %s, fromCache = %b", getCacheKey(), Boolean.valueOf(z));
        }
        deliver(new Runnable() { // from class: com.huya.mtp.http.HttpFunction.6
            @Override // java.lang.Runnable
            public void run() {
                HttpFunction.this.onResponse(rsp, z);
                HttpFunction.this.deliverToMergedRequests(new MergedRequestMessageDelivery<Rsp>() { // from class: com.huya.mtp.http.HttpFunction.6.1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // com.huya.mtp.http.HttpFunction.MergedRequestMessageDelivery
                    public void deliver(ResponseListener<Rsp> responseListener) {
                        if (responseListener != 0) {
                            responseListener.onResponse(rsp, z);
                        }
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deliverToMergedRequests(MergedRequestMessageDelivery<Rsp> mergedRequestMessageDelivery) {
        if (mergeRequest()) {
            String cacheKey = getCacheKey();
            sSegmentLock.lock(cacheKey);
            try {
                removeExecuting(this);
                for (ResponseListener<Rsp> responseListener : this.mResponseListenerList) {
                    if (responseListener == null) {
                        MTPApi.LOGGER.info(TAG, "deliverToMergedRequests, listener is null");
                    }
                    mergedRequestMessageDelivery.deliver(responseListener);
                }
            } finally {
                sSegmentLock.unlock(cacheKey);
            }
        }
    }

    protected boolean needPrintEntity() {
        return MTPApi.LOGGER.isLogLevelEnabled(3);
    }

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public final byte[] getBody() {
        byte[] bArrEncodeBody = encodeBody();
        this.mBodyLength = bArrEncodeBody == null ? 0 : bArrEncodeBody.length;
        return bArrEncodeBody;
    }

    public int getBodyLength() {
        return this.mBodyLength;
    }

    @Override // com.huya.mtp.http.ResponseListener
    public void onError(DataException dataException, boolean z) {
        onError(dataException);
    }
}
