package com.huya.mtp.http.cachestrategy;

import com.huya.mtp.data.DataEntity;
import com.huya.mtp.data.DataListener;
import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.data.transporter.param.NetworkParams;
import com.huya.mtp.data.transporter.param.NetworkResult;
import com.huya.mtp.http.RspCache;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NetFirst<Rsp> extends BaseNetworkStrategy<Rsp> {
    @Override // com.huya.mtp.data.strategy.Strategy
    public void read(final DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity, final DataListener<Rsp> dataListener) {
        readFromNet(dataEntity, new DataListener<Rsp>() { // from class: com.huya.mtp.http.cachestrategy.NetFirst.1
            @Override // com.huya.mtp.data.DataListener
            public void onResponse(Rsp rsp, Transporter<?, ?> transporter) {
                dataListener.onResponse(rsp, transporter);
            }

            @Override // com.huya.mtp.data.DataListener
            public void onError(final DataException dataException, final Transporter<?, ?> transporter) {
                NetFirst.this.readFromCache(dataEntity, new DataListener<RspCache<Rsp>>() { // from class: com.huya.mtp.http.cachestrategy.NetFirst.1.1
                    @Override // com.huya.mtp.data.DataListener
                    public /* bridge */ /* synthetic */ void onResponse(Object obj, Transporter transporter2) {
                        onResponse((RspCache) obj, (Transporter<?, ?>) transporter2);
                    }

                    public void onResponse(RspCache<Rsp> rspCache, Transporter<?, ?> transporter2) {
                        Rsp rsp = rspCache.isExpired() ? null : rspCache.data;
                        if (rsp != null) {
                            dataListener.onResponse(rsp, transporter2);
                            if (rspCache.refreshNeeded()) {
                                NetFirst.this.readFromNetSilently(dataEntity);
                                return;
                            }
                            return;
                        }
                        dataListener.onError(dataException, transporter);
                    }

                    @Override // com.huya.mtp.data.DataListener
                    public void onError(DataException dataException2, Transporter<?, ?> transporter2) {
                        dataListener.onError(dataException, transporter);
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
