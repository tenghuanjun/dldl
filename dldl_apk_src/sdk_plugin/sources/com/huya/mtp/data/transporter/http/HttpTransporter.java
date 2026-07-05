package com.huya.mtp.data.transporter.http;

import com.huya.mtp.data.exception.DataException;
import com.huya.mtp.data.transporter.Transporter;
import com.huya.mtp.data.transporter.UpdateListener;
import com.huya.mtp.data.transporter.param.HttpParams;
import com.huya.mtp.data.transporter.param.HttpResult;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class HttpTransporter extends Transporter<HttpParams, HttpResult> {
    @Override // com.huya.mtp.data.transporter.Transporter
    public HttpResult read(HttpParams httpParams) throws DataException {
        throw new UnsupportedOperationException();
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public void write(HttpParams httpParams, HttpResult httpResult) {
        throw new UnsupportedOperationException();
    }

    @Override // com.huya.mtp.data.transporter.Transporter
    public void write(HttpParams httpParams, HttpResult httpResult, UpdateListener updateListener) {
        throw new UnsupportedOperationException();
    }
}
