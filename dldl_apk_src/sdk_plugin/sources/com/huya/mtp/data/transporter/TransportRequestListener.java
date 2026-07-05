package com.huya.mtp.data.transporter;

import com.huya.mtp.data.exception.DataException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface TransportRequestListener<Rsp> {
    void onCancelled();

    void onError(DataException dataException, Transporter<?, ?> transporter);

    void onProducerEvent(int i);

    void onResponse(Rsp rsp, Transporter<?, ?> transporter) throws DataException;
}
