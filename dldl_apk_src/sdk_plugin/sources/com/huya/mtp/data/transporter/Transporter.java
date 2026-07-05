package com.huya.mtp.data.transporter;

import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.param.Params;
import com.huya.mtp.data.transporter.param.Result;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class Transporter<Req extends Params, Rsp extends Result> implements Comparable<Transporter> {
    private volatile int mPriority;

    public abstract boolean cancel(Req req);

    public abstract Rsp read(Req req) throws DataException;

    public abstract void read(Req req, TransportRequestListener<Rsp> transportRequestListener);

    public abstract void write(Req req, Rsp rsp) throws DataException;

    public abstract void write(Req req, Rsp rsp, UpdateListener updateListener);

    public int getPriority() {
        return this.mPriority;
    }

    public void setPriority(int i) {
        this.mPriority = i;
    }

    @Override // java.lang.Comparable
    public synchronized int compareTo(Transporter transporter) {
        if (transporter == null) {
            return 0;
        }
        return transporter.getPriority() - getPriority();
    }
}
