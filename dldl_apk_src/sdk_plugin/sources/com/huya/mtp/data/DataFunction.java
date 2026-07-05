package com.huya.mtp.data;

import com.huya.mtp.data.strategy.Strategy;
import com.huya.mtp.data.transporter.UpdateListener;
import com.huya.mtp.data.transporter.param.Params;
import com.huya.mtp.data.transporter.param.Result;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class DataFunction<P extends Params, R extends Result<?>, Rsp> extends DataEntity<P, R, Rsp> implements DataListener<Rsp>, UpdateListener {
    protected abstract Strategy<P, R, Rsp> getDefaultStrategy();

    public Rsp read() {
        return read(getDefaultStrategy());
    }

    public Rsp read(Strategy<P, R, Rsp> strategy) {
        if (strategy == null) {
            return null;
        }
        return strategy.read(this);
    }

    public void readAsync() {
        readAsync(getDefaultStrategy());
    }

    public void readAsync(Strategy<P, R, Rsp> strategy) {
        if (strategy == null) {
            return;
        }
        strategy.read(this, this);
    }

    public void write(Rsp rsp) {
        write(getDefaultStrategy(), rsp);
    }

    public void write(Strategy<P, R, Rsp> strategy, Rsp rsp) {
        if (strategy == null) {
            return;
        }
        strategy.write(this, rsp);
    }

    public void writeAsync(Rsp rsp) {
        write(getDefaultStrategy(), rsp);
    }

    public void writeAsync(Strategy<P, R, Rsp> strategy, Rsp rsp) {
        if (strategy == null) {
            return;
        }
        strategy.write(this, rsp, this);
    }
}
