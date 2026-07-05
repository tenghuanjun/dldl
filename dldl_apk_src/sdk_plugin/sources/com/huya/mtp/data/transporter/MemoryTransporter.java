package com.huya.mtp.data.transporter;

import android.util.LruCache;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.param.MemoryParams;
import com.huya.mtp.data.transporter.param.MemoryResult;
import com.huya.mtp.data.transporter.param.Params;
import com.huya.mtp.http.RspCache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MemoryTransporter extends Transporter<MemoryParams, MemoryResult> {
    private static LruCache<String, RspCache> sCache = new LruCache<>(20);

    @Override // com.huya.mtp.data.transporter.Transporter
    public boolean cancel(MemoryParams memoryParams) {
        return false;
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public /* bridge */ /* synthetic */ void read(Params params, TransportRequestListener transportRequestListener) {
        read((MemoryParams) params, (TransportRequestListener<MemoryResult>) transportRequestListener);
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public MemoryResult read(MemoryParams memoryParams) {
        return new MemoryResult(sCache.get(memoryParams.getCacheKey()));
    }

    public void read(MemoryParams memoryParams, TransportRequestListener<MemoryResult> transportRequestListener) {
        try {
            transportRequestListener.onResponse(read(memoryParams), this);
        } catch (DataException e) {
            transportRequestListener.onError(e, this);
        }
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.huya.mtp.data.transporter.Transporter
    public void write(MemoryParams memoryParams, MemoryResult memoryResult) {
        sCache.put(memoryParams.getCacheKey(), (RspCache) memoryResult.mRsp);
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public void write(MemoryParams memoryParams, MemoryResult memoryResult, UpdateListener updateListener) {
        write(memoryParams, memoryResult);
        updateListener.onUpdateSucceed();
    }
}
