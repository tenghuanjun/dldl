package com.sy37sdk.account.eventbus;

import com.sq.eventbus.annotation.mode.ThreadMode;
import com.sq.eventbus.core.bean.EventBeans;
import com.sq.eventbus.core.bean.SubscriberInfo;
import com.sq.eventbus.core.bean.SubscriberInfoIndex;
import com.sq.eventbus.core.bean.SubscriberMethod;
import com.sqwan.common.eventbus.OnActivityResultEvent;
import com.sqwan.common.eventbus.SActiveEvent;
import com.sy37sdk.account.AccountModImpl;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class EventBusIndex implements SubscriberInfoIndex {
    private static final Map<Class, SubscriberInfo> SUBSCRIBER_INDEX = new HashMap();

    static {
        putIndex(new EventBeans(AccountModImpl.class, new SubscriberMethod[]{new SubscriberMethod(AccountModImpl.class, "onSActiveEvent", ThreadMode.POSTING, SActiveEvent.class, false, 0), new SubscriberMethod(AccountModImpl.class, "onActivityResult", ThreadMode.POSTING, OnActivityResultEvent.class, false, 0)}));
    }

    @Override // com.sq.eventbus.core.bean.SubscriberInfoIndex
    public SubscriberInfo getSubscriberInfo(Class cls) {
        return SUBSCRIBER_INDEX.get(cls);
    }

    private static void putIndex(SubscriberInfo subscriberInfo) {
        SUBSCRIBER_INDEX.put(subscriberInfo.getSubscriberClass(), subscriberInfo);
    }
}
