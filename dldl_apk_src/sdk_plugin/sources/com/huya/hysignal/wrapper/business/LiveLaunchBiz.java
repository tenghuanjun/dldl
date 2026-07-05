package com.huya.hysignal.wrapper.business;

import com.huya.hysignal.listener.HySignalGuidListener;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface LiveLaunchBiz {
    boolean addGuidListener(HySignalGuidListener hySignalGuidListener);

    String getClientIp();

    String getGuid();

    boolean removeGuidListener(HySignalGuidListener hySignalGuidListener);
}
