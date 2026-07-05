package com.huya.hysignal.wrapper.business;

import com.huya.mtp.hyns.api.NSLongLinkApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface PushBiz {
    void addPushListener(NSLongLinkApi.PushListener pushListener);

    boolean addPushStatInfoListener(NSLongLinkApi.PushStatListener pushStatListener);

    int getLinkStatus();

    void removePushListener(NSLongLinkApi.PushListener pushListener);

    boolean removePushStatInfoListener(NSLongLinkApi.PushStatListener pushStatListener);
}
