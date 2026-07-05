package com.sq.eventbus.core.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface SubscriberInfo {
    Class<?> getSubscriberClass();

    SubscriberMethod[] getSubscriberMethods();
}
