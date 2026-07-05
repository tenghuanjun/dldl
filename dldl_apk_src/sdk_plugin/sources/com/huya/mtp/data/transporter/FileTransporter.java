package com.huya.mtp.data.transporter;

import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.param.FileParams;
import com.huya.mtp.data.transporter.param.FileResult;
import com.huya.mtp.data.transporter.param.Params;
import com.huya.mtp.http.Cache;
import com.huya.mtp.http.HttpFunctionEntry;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FileTransporter extends Transporter<FileParams, FileResult> {
    @Override // com.huya.mtp.data.transporter.Transporter
    public boolean cancel(FileParams fileParams) {
        return false;
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public /* bridge */ /* synthetic */ void read(Params params, TransportRequestListener transportRequestListener) {
        read((FileParams) params, (TransportRequestListener<FileResult>) transportRequestListener);
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public FileResult read(FileParams fileParams) {
        return new FileResult(HttpFunctionEntry.getCache(fileParams.getCacheDir(), fileParams.getCacheKey()));
    }

    public void read(final FileParams fileParams, final TransportRequestListener<FileResult> transportRequestListener) {
        HttpFunctionEntry.sCacheHandlerExecutor.execute(new Runnable() { // from class: com.huya.mtp.data.transporter.FileTransporter.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    transportRequestListener.onResponse(FileTransporter.this.read(fileParams), FileTransporter.this);
                } catch (DataException e) {
                    transportRequestListener.onError(e, FileTransporter.this);
                }
            }
        });
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public void write(FileParams fileParams, FileResult fileResult) {
        HttpFunctionEntry.setCache(fileParams.getCacheDir(), fileParams.getCacheKey(), parseEntry(fileParams, fileResult));
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public void write(FileParams fileParams, FileResult fileResult, UpdateListener updateListener) {
        HttpFunctionEntry.setDiscCacheAsync(fileParams.getCacheDir(), fileParams.getCacheKey(), parseEntry(fileParams, fileResult));
        updateListener.onUpdateSucceed();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Cache.Entry parseEntry(FileParams fileParams, FileResult fileResult) {
        Cache.Entry entry = (Cache.Entry) fileResult.mRsp;
        if (entry == null) {
            return null;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = entry.softTtl;
        long j2 = LongCompanionObject.MAX_VALUE;
        if (j == 0) {
            long cacheRefreshTimeMillis = fileParams.getCacheRefreshTimeMillis() + jCurrentTimeMillis;
            if (cacheRefreshTimeMillis < 0) {
                cacheRefreshTimeMillis = Long.MAX_VALUE;
            }
            entry.softTtl = cacheRefreshTimeMillis;
        }
        if (entry.ttl == 0) {
            long cacheExpireTimeMillis = fileParams.getCacheExpireTimeMillis() + jCurrentTimeMillis;
            if (cacheExpireTimeMillis >= 0) {
                j2 = cacheExpireTimeMillis;
            }
            entry.ttl = j2;
        }
        return entry;
    }
}
