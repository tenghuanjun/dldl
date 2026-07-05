package com.huya.mtp.data.strategy;

import com.huya.mtp.data.DataEntity;
import com.huya.mtp.data.DataListener;
import com.huya.mtp.data.transporter.UpdateListener;
import com.huya.mtp.data.transporter.param.Params;
import com.huya.mtp.data.transporter.param.Result;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class Strategy<Param extends Params, RESULT extends Result<?>, Rsp> {
    public abstract void cancel(DataEntity<? extends Param, RESULT, Rsp> dataEntity);

    public abstract Rsp read(DataEntity<? extends Param, RESULT, Rsp> dataEntity);

    public abstract void read(DataEntity<? extends Param, RESULT, Rsp> dataEntity, DataListener<Rsp> dataListener);

    public abstract void write(DataEntity<? extends Param, RESULT, Rsp> dataEntity, Rsp rsp);

    public abstract void write(DataEntity<? extends Param, RESULT, Rsp> dataEntity, Rsp rsp, UpdateListener updateListener);
}
