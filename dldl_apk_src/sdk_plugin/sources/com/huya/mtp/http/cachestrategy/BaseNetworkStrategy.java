package com.huya.mtp.http.cachestrategy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.huya.mtp.api.MTPApi;
import com.huya.mtp.data.DataEntity;
import com.huya.mtp.data.DataListener;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.exception.NoAvailableNetworkException;
import com.huya.mtp.data.strategy.Strategy;
import com.huya.mtp.data.transporter.FileTransporter;
import com.huya.mtp.data.transporter.MemoryTransporter;
import com.huya.mtp.data.transporter.TransportRequestListener;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.data.transporter.UpdateListener;
import com.huya.mtp.data.transporter.http.HttpTransporter;
import com.huya.mtp.data.transporter.param.FileParams;
import com.huya.mtp.data.transporter.param.FileResult;
import com.huya.mtp.data.transporter.param.HttpResult;
import com.huya.mtp.data.transporter.param.MemoryParams;
import com.huya.mtp.data.transporter.param.MemoryResult;
import com.huya.mtp.data.transporter.param.NetworkParams;
import com.huya.mtp.data.transporter.param.NetworkResult;
import com.huya.mtp.http.Cache;
import com.huya.mtp.http.HandlerExecutor;
import com.huya.mtp.http.HttpFunctionEntry;
import com.huya.mtp.http.NetworkResponse;
import com.huya.mtp.http.RspCache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class BaseNetworkStrategy<Rsp> extends Strategy<NetworkParams<Rsp>, NetworkResult, Rsp> {
    private static final HandlerExecutor sReadCacheExecutor = new HandlerExecutor("http_function_read_cache");
    private MemoryTransporter mMemoryTransporter = new MemoryTransporter();
    private FileTransporter mFileTransporter = new FileTransporter();
    private HttpTransporter mHttpTransporter = null;

    @Override // com.huya.mtp.data.strategy.Strategy
    public Rsp read(DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity) {
        return null;
    }

    @Override // com.huya.mtp.data.strategy.Strategy
    public final void write(DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity, Rsp rsp) {
    }

    @Override // com.huya.mtp.data.strategy.Strategy
    public final void write(DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity, Rsp rsp, UpdateListener updateListener) {
    }

    public void readFromNetSilently(DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity) {
        readFromNet(dataEntity, null);
    }

    public void readFromNet(DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity, final DataListener<Rsp> dataListener) {
        if (!isNetworkAvailable(MTPApi.CONTEXT.getApplication())) {
            HttpFunctionEntry.sDispatchHandlerExecutor.execute(new Runnable() { // from class: com.huya.mtp.http.cachestrategy.BaseNetworkStrategy.1
                @Override // java.lang.Runnable
                public void run() {
                    DataListener dataListener2 = dataListener;
                    if (dataListener2 != null) {
                        dataListener2.onError(new NoAvailableNetworkException(), BaseNetworkStrategy.this.mHttpTransporter);
                    }
                }
            });
        } else {
            doReadFromNet(dataEntity, dataListener, (NetworkParams) dataEntity.getRequestParams());
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            if (context == null) {
                throw new DataException("MTPApi.CONTEXT.getApplication() is Null, you must setup it.");
            }
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            return activeNetworkInfo.isConnected() || (activeNetworkInfo.isAvailable() && activeNetworkInfo.isConnectedOrConnecting());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void doReadFromNet(final DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity, final DataListener<Rsp> dataListener, final NetworkParams<Rsp> networkParams) {
        this.mHttpTransporter.read(networkParams, new TransportRequestListener<HttpResult>() { // from class: com.huya.mtp.http.cachestrategy.BaseNetworkStrategy.2
            @Override // com.huya.mtp.data.transporter.TransportRequestListener
            public /* bridge */ /* synthetic */ void onResponse(HttpResult httpResult, Transporter transporter) throws DataException {
                onResponse2(httpResult, (Transporter<?, ?>) transporter);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX INFO: renamed from: onResponse, reason: avoid collision after fix types in other method */
            public void onResponse2(HttpResult httpResult, Transporter<?, ?> transporter) throws DataException {
                DataListener dataListener2 = dataListener;
                if (dataListener2 != null) {
                    dataListener2.onProducerEvent(105);
                }
                NetworkResponse networkResponse = (NetworkResponse) httpResult.mRsp;
                Object objDecodeResponse = dataEntity.decodeResponse(new NetworkResult(networkResponse));
                DataListener dataListener3 = dataListener;
                if (dataListener3 != null) {
                    dataListener3.onProducerEvent(109);
                }
                dataEntity.validateResponse(objDecodeResponse);
                DataListener dataListener4 = dataListener;
                if (dataListener4 != 0) {
                    dataListener4.onResponse(objDecodeResponse, transporter);
                } else {
                    MTPApi.LOGGER.debug("bug", "listener is null!");
                }
                if (networkParams.shouldUseCustomCache()) {
                    DataListener dataListener5 = dataListener;
                    if (dataListener5 != null) {
                        dataListener5.onProducerEvent(110);
                    }
                    BaseNetworkStrategy.this.writeToCache(networkResponse, objDecodeResponse, networkParams);
                    DataListener dataListener6 = dataListener;
                    if (dataListener6 != null) {
                        dataListener6.onProducerEvent(111);
                    }
                }
                DataListener dataListener7 = dataListener;
                if (dataListener7 != null) {
                    dataListener7.onProducerEvent(112);
                }
            }

            @Override // com.huya.mtp.data.transporter.TransportRequestListener
            public void onError(DataException dataException, Transporter<?, ?> transporter) {
                DataListener dataListener2 = dataListener;
                if (dataListener2 != null) {
                    dataListener2.onError(dataException, transporter);
                }
            }

            @Override // com.huya.mtp.data.transporter.TransportRequestListener
            public void onCancelled() {
                DataListener dataListener2 = dataListener;
                if (dataListener2 != null) {
                    dataListener2.onRequestCancelled();
                }
            }

            @Override // com.huya.mtp.data.transporter.TransportRequestListener
            public void onProducerEvent(int i) {
                DataListener dataListener2 = dataListener;
                if (dataListener2 != null) {
                    dataListener2.onProducerEvent(i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeToCache(NetworkResponse networkResponse, Rsp rsp, NetworkParams<Rsp> networkParams) {
        Cache.Entry entry = new Cache.Entry();
        entry.data = networkResponse.data;
        long jCurrentTimeMillis = System.currentTimeMillis();
        entry.softTtl = networkParams.getCacheRefreshTimeMillis() + jCurrentTimeMillis;
        entry.ttl = networkParams.getCacheExpireTimeMillis() + jCurrentTimeMillis;
        entry.responseHeaders = networkResponse.headers;
        this.mMemoryTransporter.write((MemoryParams) networkParams, new MemoryResult(new RspCache(rsp, entry.ttl, entry.softTtl)));
        this.mFileTransporter.write((FileParams) networkParams, new FileResult(entry));
    }

    public RspCache<Rsp> readFromCacheSync(DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity) {
        RspCache<Rsp> rspCacheEmptyCache;
        NetworkParams networkParams = (NetworkParams) dataEntity.getRequestParams();
        try {
            rspCacheEmptyCache = (RspCache) this.mMemoryTransporter.read((MemoryParams) networkParams).mRsp;
        } catch (ClassCastException unused) {
            rspCacheEmptyCache = RspCache.emptyCache();
            this.mMemoryTransporter.write((MemoryParams) networkParams, new MemoryResult(rspCacheEmptyCache));
        }
        if (rspCacheEmptyCache != null && !rspCacheEmptyCache.isEmpty()) {
            return rspCacheEmptyCache;
        }
        Cache.Entry entry = (Cache.Entry) this.mFileTransporter.read((FileParams) networkParams).mRsp;
        if (entry == null) {
            return RspCache.emptyCache();
        }
        try {
            Rsp rspDecodeResponse = dataEntity.decodeResponse(new NetworkResult(new NetworkResponse(entry.data)));
            this.mMemoryTransporter.write((MemoryParams) networkParams, new MemoryResult(new RspCache(rspDecodeResponse, entry.ttl, entry.softTtl)));
            dataEntity.validateResponse(rspDecodeResponse);
            return new RspCache<>(rspDecodeResponse, entry.ttl, entry.softTtl);
        } catch (DataException unused2) {
            return RspCache.emptyCache();
        }
    }

    public void readFromCache(final DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity, final DataListener<RspCache<Rsp>> dataListener) {
        RspCache<Rsp> rspCacheEmptyCache;
        final NetworkParams networkParams = (NetworkParams) dataEntity.getRequestParams();
        try {
            rspCacheEmptyCache = (RspCache) this.mMemoryTransporter.read((MemoryParams) networkParams).mRsp;
        } catch (ClassCastException unused) {
            rspCacheEmptyCache = RspCache.emptyCache();
        }
        if (rspCacheEmptyCache != null && !rspCacheEmptyCache.isEmpty()) {
            dataListener.onResponse(rspCacheEmptyCache, this.mMemoryTransporter);
        } else {
            this.mFileTransporter.read((FileParams) networkParams, new TransportRequestListener<FileResult>() { // from class: com.huya.mtp.http.cachestrategy.BaseNetworkStrategy.3
                @Override // com.huya.mtp.data.transporter.TransportRequestListener
                public /* bridge */ /* synthetic */ void onResponse(FileResult fileResult, Transporter transporter) throws DataException {
                    onResponse2(fileResult, (Transporter<?, ?>) transporter);
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX INFO: renamed from: onResponse, reason: avoid collision after fix types in other method */
                public void onResponse2(FileResult fileResult, Transporter<?, ?> transporter) throws DataException {
                    RspCache rspCache;
                    Cache.Entry entry = (Cache.Entry) fileResult.mRsp;
                    if (entry == null) {
                        rspCache = RspCache.emptyCache();
                    } else {
                        Object objDecodeResponse = dataEntity.decodeResponse(new NetworkResult(new NetworkResponse(entry.data)));
                        BaseNetworkStrategy.this.mMemoryTransporter.write((MemoryParams) networkParams, new MemoryResult(new RspCache(objDecodeResponse, entry.ttl, entry.softTtl)));
                        dataEntity.validateResponse(objDecodeResponse);
                        rspCache = new RspCache(objDecodeResponse, entry.ttl, entry.softTtl);
                    }
                    dataListener.onResponse(rspCache, transporter);
                }

                @Override // com.huya.mtp.data.transporter.TransportRequestListener
                public void onError(DataException dataException, Transporter<?, ?> transporter) {
                    BaseNetworkStrategy.this.mFileTransporter.write((FileParams) networkParams, new FileResult(null));
                    dataListener.onError(dataException, BaseNetworkStrategy.this.mFileTransporter);
                }

                @Override // com.huya.mtp.data.transporter.TransportRequestListener
                public void onCancelled() {
                    dataListener.onRequestCancelled();
                }

                @Override // com.huya.mtp.data.transporter.TransportRequestListener
                public void onProducerEvent(int i) {
                    dataListener.onProducerEvent(i);
                }
            });
        }
    }

    public BaseNetworkStrategy<Rsp> memory(MemoryTransporter memoryTransporter) {
        this.mMemoryTransporter = memoryTransporter;
        return this;
    }

    public BaseNetworkStrategy<Rsp> file(FileTransporter fileTransporter) {
        this.mFileTransporter = fileTransporter;
        return this;
    }

    public BaseNetworkStrategy<Rsp> http(HttpTransporter httpTransporter) {
        this.mHttpTransporter = httpTransporter;
        return this;
    }

    @Override // com.huya.mtp.data.strategy.Strategy
    public void cancel(DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity) {
        HttpTransporter httpTransporter = this.mHttpTransporter;
        if (httpTransporter == null) {
            return;
        }
        httpTransporter.cancel(dataEntity.getRequestParams());
    }
}
