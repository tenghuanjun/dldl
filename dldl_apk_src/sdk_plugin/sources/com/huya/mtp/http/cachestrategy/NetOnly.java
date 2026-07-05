package com.huya.mtp.http.cachestrategy;

import com.huya.mtp.data.DataEntity;
import com.huya.mtp.data.DataListener;
import com.huya.mtp.data.transporter.param.NetworkParams;
import com.huya.mtp.data.transporter.param.NetworkResult;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NetOnly<Rsp> extends BaseNetworkStrategy<Rsp> {
    @Override // com.huya.mtp.data.strategy.Strategy
    public void read(DataEntity<? extends NetworkParams<Rsp>, NetworkResult, Rsp> dataEntity, DataListener<Rsp> dataListener) {
        readFromNet(dataEntity, dataListener);
    }
}
