package com.huya.hysignal.wrapper.listener;

import com.huya.hysignal.wrapper.RegistResultInfo;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface UnRegisterPushMsgListener {
    void onUnRegisterFailed(RegistResultInfo registResultInfo);

    void onUnRegisterSucceed(RegistResultInfo registResultInfo);
}
