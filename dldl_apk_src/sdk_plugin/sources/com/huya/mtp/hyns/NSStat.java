package com.huya.mtp.hyns;

import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.Transporter;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface NSStat {
    void onError(NSFunction<?> nSFunction, DataException dataException, Transporter<?, ?> transporter);

    void onExecute(NSFunction<?> nSFunction);

    void onProduceEvent(NSFunction<?> nSFunction, int i);

    <T> void onResponse(NSFunction<T> nSFunction, NSResponse<T> nSResponse, Transporter<?, ?> transporter);
}
