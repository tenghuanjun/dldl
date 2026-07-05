package com.huya.hysignal.listener;

import com.huya.hysignal.core.HySignalMessage;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Deprecated
public interface PushListener {
    public static final int STATUS_CONNECTED = 4;
    public static final int STATUS_CONNECTTING = 3;
    public static final int STATUS_GATEWAY_FAILED = 1;
    public static final int STATUS_NETWORK_UNAVAILABLE = 0;
    public static final int STATUS_NETWORK_UNKNOWN = -1;
    public static final int STATUS_SERVER_FAILED = 2;

    void onLinkStateChange(int i);

    void onPush(HySignalMessage hySignalMessage);
}
