package com.huya.mtp.http.cachestrategy;

import com.huya.mtp.data.DataEntity;
import com.huya.mtp.data.DataListener;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.data.transporter.param.NetworkParams;
import com.huya.mtp.data.transporter.param.NetworkResult;
import com.huya.mtp.http.RspCache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CacheFirst<Rsp> extends BaseNetworkStrategy<Rsp> {
    @Override // com.huya.mtp.data.strategy.Strategy
    public void read(final DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity, final DataListener<Rsp> dataListener) {
        readFromCache(dataEntity, new DataListener<RspCache<Rsp>>() { // from class: com.huya.mtp.http.cachestrategy.CacheFirst.1
            @Override // com.huya.mtp.data.DataListener
            public /* bridge */ /* synthetic */ void onResponse(Object obj, Transporter transporter) {
                onResponse((RspCache) obj, (Transporter<?, ?>) transporter);
            }

            public void onResponse(RspCache<Rsp> rspCache, Transporter<?, ?> transporter) {
                Rsp rsp = rspCache.isExpired() ? null : rspCache.data;
                if (rsp != null) {
                    dataListener.onResponse(rsp, transporter);
                    if (rspCache.refreshNeeded()) {
                        CacheFirst.this.readFromNetSilently(dataEntity);
                        return;
                    }
                    return;
                }
                CacheFirst.this.readFromNet(dataEntity, dataListener);
            }

            @Override // com.huya.mtp.data.DataListener
            public void onError(DataException dataException, Transporter<?, ?> transporter) {
                CacheFirst.this.readFromNet(dataEntity, dataListener);
            }

            @Override // com.huya.mtp.data.DataListener
            public void onRequestCancelled() {
                dataListener.onRequestCancelled();
            }

            @Override // com.huya.mtp.data.DataListener, com.huya.mtp.http.ResponseListener
            public void onProducerEvent(int i) {
                dataListener.onProducerEvent(i);
            }
        });
    }
}
