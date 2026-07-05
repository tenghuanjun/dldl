package com.sq.eventbus.core.bean;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Subscription {
    private Object subscriber;
    private SubscriberMethod subscriberMethod;

    public Subscription(Object obj, SubscriberMethod subscriberMethod) {
        this.subscriber = obj;
        this.subscriberMethod = subscriberMethod;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Subscription) {
            return this.subscriberMethod.equals(((Subscription) obj).subscriberMethod);
        }
        return false;
    }

    public Object getSubscriber() {
        return this.subscriber;
    }

    public SubscriberMethod getSubscriberMethod() {
        return this.subscriberMethod;
    }
}
