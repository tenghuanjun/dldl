package com.sq.eventbus.core.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EventBeans implements SubscriberInfo {
    private final Class<?> subscriberClass;
    private final SubscriberMethod[] subscriberMethods;

    public EventBeans(Class<?> cls, SubscriberMethod[] subscriberMethodArr) {
        this.subscriberClass = cls;
        this.subscriberMethods = subscriberMethodArr;
    }

    @Override // com.sq.eventbus.core.bean.SubscriberInfo
    public Class<?> getSubscriberClass() {
        return this.subscriberClass;
    }

    @Override // com.sq.eventbus.core.bean.SubscriberInfo
    public SubscriberMethod[] getSubscriberMethods() {
        return this.subscriberMethods;
    }
}
