package com.huya.mtp.data;

import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.Transporter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface DataListener<Rsp> {
    void onError(DataException dataException, Transporter<?, ?> transporter);

    void onProducerEvent(int i);

    void onRequestCancelled();

    void onResponse(Rsp rsp, Transporter<?, ?> transporter);
}
