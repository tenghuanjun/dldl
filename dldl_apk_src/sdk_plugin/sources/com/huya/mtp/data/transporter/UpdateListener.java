package com.huya.mtp.data.transporter;

import com.huya.mtp.data.exception.DataException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface UpdateListener {
    void onUpdateCancelled();

    void onUpdateError(DataException dataException);

    void onUpdateSucceed();
}
