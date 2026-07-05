package com.huya.component.login.module;

import com.duowan.networkmars.hysignal.HySignalProxy;
import com.duowan.networkmars.hysignal.HySignalSDK;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HySignalHelper {
    private static final String TAG = "HySignalHelper";

    static void onLoginSuccess(long j) {
        HySignalProxy.getInstance().setLoginInfo(j);
        HySignalSDK.getInstance().setUid(j);
    }

    static void onLogout() {
        HySignalProxy.getInstance().clearLoginInfo();
    }
}
