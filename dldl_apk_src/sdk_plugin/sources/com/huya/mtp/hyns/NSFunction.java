package com.huya.mtp.hyns;

import android.text.TextUtils;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.data.transporter.param.NetworkResult;
import com.huya.mtp.encrypt.HyEncrypt;
import com.huya.mtp.http.CacheType;
import com.huya.mtp.http.HttpFunction;
import com.huya.mtp.http.NetworkResponse;
import com.huya.mtp.http.Request;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSFunction<T> extends HttpFunction<NSResponse<T>> {
    public static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String GZIP = "gzip";
    public static final String TAG = "NetService-NSFunction";
    public static final String TEA_ENCRYPT_HEADER_KEY = "Content-Encrypt";
    public static final String TEA_ENCRYPT_HEADER_VALUE = "yyencrypt";
    public static final String TEA_ENCRY_KEY = "ABCDEFGHIJKLMNOP";
    private NSCallback<T> mCallback;
    private NSMethod mMethod;
    private String mReportId;
    private NSRequest mRequest;
    protected NSSettings mSettings = NSSettings.DEFAULT_SETTINGS;
    private NSStat mStat;

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.NetworkParams
    public Class<? extends NSResponse<T>> getResponseType() {
        return null;
    }

    @Override // com.huya.mtp.http.HttpFunction
    public NSTransporter initDefaultTransporter() {
        return null;
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.NetworkParams
    public boolean testDataEnabled() {
        return false;
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.DataListener
    public /* bridge */ /* synthetic */ void onResponse(Object obj, Transporter transporter) {
        onResponse((NSResponse) obj, (Transporter<?, ?>) transporter);
    }

    public NSFunction(NSMethod nSMethod, NSTransporter nSTransporter) {
        this.mRequest = nSMethod.readRequest();
        this.mMethod = nSMethod;
        setFunctionExecutor(nSTransporter);
        this.mStat = nSTransporter.initStat();
    }

    public void setCallback(NSCallback<T> nSCallback) {
        this.mCallback = nSCallback;
    }

    public void setSettings(NSSettings nSSettings) {
        this.mSettings = nSSettings;
    }

    public NSMethod getNSMethod() {
        return this.mMethod;
    }

    public NSRequest getRequest() {
        return this.mRequest;
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.HttpParams
    public int getTimeout() {
        return this.mSettings.getTimeOut();
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.HttpParams
    public Request.Priority getPriority() {
        int priority = this.mSettings.getPriority();
        if (priority == 0) {
            return Request.Priority.IMMEDIATE;
        }
        if (priority == 1) {
            return Request.Priority.HIGH;
        }
        if (priority == 3) {
            return Request.Priority.NORMAL;
        }
        if (priority == 5) {
            return Request.Priority.LOW;
        }
        return super.getPriority();
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.FileParams
    public long getCacheExpireTimeMillis() {
        return this.mSettings.getCacheExpireTimeMillis();
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.FileParams
    public long getCacheRefreshTimeMillis() {
        return this.mSettings.getCacheRefreshTimeMillis();
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.NetworkParams
    public boolean shouldUseCustomCache() {
        return this.mSettings.isEnableCache();
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.FileParams
    public String getCacheDir() {
        return this.mSettings.getCacheDir();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.huya.mtp.http.HttpFunction
    public NSResponse<T> onReadResponse(NetworkResult networkResult) throws DataException {
        return (NSResponse<T>) this.mMethod.readResponse(new NSResult((NetworkResponse) networkResult.mRsp), this);
    }

    public void onResponse(NSResponse<T> nSResponse, Transporter<?, ?> transporter) {
        NSStat nSStat = this.mStat;
        if (nSStat != null) {
            nSStat.onResponse(this, nSResponse, transporter);
        }
        super.onResponse(nSResponse, transporter);
    }

    @Override // com.huya.mtp.http.ResponseListener
    public void onResponse(NSResponse<T> nSResponse, boolean z) {
        if (this.mCallback != null) {
            nSResponse.setFromCache(z);
            this.mCallback.onResponse(nSResponse);
        }
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.http.ResponseListener
    public void onCancelled() {
        NSCallback<T> nSCallback = this.mCallback;
        if (nSCallback != null) {
            nSCallback.onCancelled();
        }
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.DataListener, com.huya.mtp.http.ResponseListener
    public void onProducerEvent(int i) {
        NSStat nSStat = this.mStat;
        if (nSStat != null) {
            nSStat.onProduceEvent(this, i);
        }
        super.onProducerEvent(i);
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.DataListener
    public void onError(DataException dataException, Transporter<?, ?> transporter) {
        NSStat nSStat = this.mStat;
        if (nSStat != null) {
            nSStat.onError(this, dataException, transporter);
        }
        super.onError(dataException, transporter);
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.http.ResponseListener
    public void onError(DataException dataException, boolean z) {
        NSCallback<T> nSCallback = this.mCallback;
        if (nSCallback != null) {
            if (dataException instanceof NSException) {
                NSException nSException = (NSException) dataException;
                nSException.setFromCache(z);
                this.mCallback.onError(nSException);
                return;
            }
            nSCallback.onError(new NSException(dataException, z));
        }
    }

    @Override // com.huya.mtp.http.HttpFunction
    protected byte[] encodeBody() {
        onProducerEvent(113);
        byte[] body = this.mRequest.getBody();
        if (this.mSettings.isEncryption()) {
            body = encryptBody(body);
        }
        onProducerEvent(114);
        return body;
    }

    protected byte[] encryptBody(byte[] bArr) {
        return HyEncrypt.encrypt(null, encryKey(), bArr);
    }

    protected String encryKey() {
        if (TextUtils.isEmpty(this.mSettings.getEncryptionKey())) {
            return "ABCDEFGHIJKLMNOP";
        }
        if (this.mSettings.getEncryptionKey().length() != 16 && NSConstants.isApkInDebug()) {
            throw new RuntimeException("TEA加密算法限制加密key为16位。");
        }
        return this.mSettings.getEncryptionKey();
    }

    @Override // com.huya.mtp.http.HttpFunction
    public boolean shouldDeliverInBackground() {
        return !this.mSettings.isReturnOnMainThread();
    }

    @Override // com.huya.mtp.http.HttpFunction
    protected boolean mergeRequest() {
        return this.mSettings.isMergeRequest();
    }

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public String getUrl() {
        String url = this.mRequest.getUrl();
        if (TextUtils.isEmpty(url)) {
            MTPApi.LOGGER.warn("url is empty when getUrl, cacheKey = %s", getCacheKey());
        }
        return url;
    }

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public String getBodyContentType() {
        return this.mRequest.getBodyContentType();
    }

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public int getMethod() {
        return this.mRequest.getMethod();
    }

    @Override // com.huya.mtp.data.transporter.param.FileParams, com.huya.mtp.data.transporter.param.MemoryParams, com.huya.mtp.data.transporter.param.HttpParams
    public String getCacheKey() {
        Object identifier = this.mMethod.getIdentifier();
        String strValueOf = identifier == null ? "" : String.valueOf(identifier);
        String cacheKey = this.mSettings.getCacheKey();
        if (TextUtils.isEmpty(cacheKey)) {
            return String.valueOf(strValueOf);
        }
        return String.format("%s_%s", strValueOf, cacheKey);
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.HttpParams
    public int getMaxRetryTimes() {
        NSSettings nSSettings = this.mSettings;
        if (nSSettings != null) {
            return nSSettings.getRetryCount();
        }
        return super.getMaxRetryTimes();
    }

    @Override // com.huya.mtp.data.transporter.param.HttpParams
    public String getCgi() {
        return this.mRequest.getCgi();
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.HttpParams
    public Map<String, String> getHeaders() {
        HashMap map = new HashMap();
        if (super.getHeaders() != null && !super.getHeaders().isEmpty()) {
            map.putAll(super.getHeaders());
        }
        if (this.mRequest.getHeaders() != null && !this.mRequest.getHeaders().isEmpty()) {
            map.putAll(this.mRequest.getHeaders());
        }
        if (this.mSettings.isEncryption()) {
            map.put(TEA_ENCRYPT_HEADER_KEY, TEA_ENCRYPT_HEADER_VALUE);
        }
        return map;
    }

    @Override // com.huya.mtp.http.HttpFunction
    public void execute() {
        NSStat nSStat = this.mStat;
        if (nSStat != null) {
            nSStat.onExecute(this);
        }
        execute(CacheType.values()[this.mSettings.getCacheType()]);
    }

    @Override // com.huya.mtp.http.HttpFunction, com.huya.mtp.data.transporter.param.HttpParams
    public String getReportId() {
        if (this.mReportId == null) {
            this.mReportId = generateTraceId();
            MTPApi.LOGGER.info(TAG, "cgi:%s, traceId:%s", getCgi(), this.mReportId);
        }
        return this.mReportId;
    }

    private String generateTraceId() {
        String strReplace = UUID.randomUUID().toString().replace("-", "");
        if (strReplace.length() > 16) {
            strReplace = strReplace.substring(0, 16);
        }
        String strReplace2 = UUID.randomUUID().toString().replace("-", "");
        if (strReplace2.length() > 16) {
            strReplace2 = strReplace2.substring(0, 16);
        }
        return strReplace + "-" + strReplace2;
    }

    @Override // com.huya.mtp.http.HttpFunction
    public int getChannel() {
        return this.mSettings.getChannel();
    }

    @Override // com.huya.mtp.http.HttpFunction
    public boolean isEncrypted() {
        return this.mSettings.isEncryption();
    }
}
