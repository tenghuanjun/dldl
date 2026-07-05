package com.huya.hysignal.wrapper.business;

import com.huya.mtp.hyns.api.NSNetUtilApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface NetUtilBiz {
    boolean addLinkStatusListener(String str, NSNetUtilApi.LinkStatusListener linkStatusListener);

    int getLinkStatus();

    int getLinkStatus(int i);

    int getLinkStatus(String str);

    NSNetUtilApi.HySignalIPStack getLocalIPStack();

    boolean isLongLinkConnected(int i);

    boolean isLongLinkConnected(String str);

    boolean removeLinkStatusListener(String str, NSNetUtilApi.LinkStatusListener linkStatusListener);
}
