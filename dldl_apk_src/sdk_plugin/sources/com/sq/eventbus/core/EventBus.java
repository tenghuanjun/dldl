package com.sq.eventbus.core;

import android.os.Handler;
import android.os.Looper;
import com.sq.eventbus.annotation.mode.ThreadMode;
import com.sq.eventbus.core.bean.SubscriberInfo;
import com.sq.eventbus.core.bean.SubscriberInfoIndex;
import com.sq.eventbus.core.bean.SubscriberMethod;
import com.sq.eventbus.core.bean.Subscription;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class EventBus {
    private static final Map<Class<?>, List<SubscriberMethod>> METHOD_CACHE = new ConcurrentHashMap();
    private static volatile EventBus defaultInstance;
    private Map<Class<?>, List<Class<?>>> typesBySubscriber = new HashMap();
    private Set<SubscriberInfoIndex> subscriberInfoIndices = new HashSet();
    private Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType = new HashMap();
    private final Map<Class<?>, Object> stickyEvents = new HashMap();
    private Handler handler = new Handler(Looper.getMainLooper());
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private EventBus() {
    }

    public static EventBus getDefault() {
        if (defaultInstance == null) {
            synchronized (EventBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new EventBus();
                }
            }
        }
        return defaultInstance;
    }

    public void register(Object obj) {
        List<SubscriberMethod> listFindSubscriberMethodByClass = findSubscriberMethodByClass(obj.getClass());
        if (listFindSubscriberMethodByClass != null) {
            Iterator<SubscriberMethod> it = listFindSubscriberMethodByClass.iterator();
            while (it.hasNext()) {
                subscribe(obj, it.next());
            }
        }
    }

    private void subscribe(Object obj, SubscriberMethod subscriberMethod) {
        Subscription subscription = new Subscription(obj, subscriberMethod);
        Class<?> eventType = subscriberMethod.getEventType();
        List<Class<?>> arrayList = this.typesBySubscriber.get(obj.getClass());
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.typesBySubscriber.put(obj.getClass(), arrayList);
        }
        arrayList.add(eventType);
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList = this.subscriptionsByEventType.get(eventType);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            this.subscriptionsByEventType.put(eventType, copyOnWriteArrayList);
        } else if (copyOnWriteArrayList.contains(subscription)) {
            sticky(subscriberMethod, eventType, subscription);
            return;
        }
        int size = copyOnWriteArrayList.size();
        for (int i = 0; i <= size; i++) {
            if (i == size || subscriberMethod.getPriority() > copyOnWriteArrayList.get(i).getSubscriberMethod().getPriority()) {
                copyOnWriteArrayList.add(i, subscription);
                break;
            }
        }
        sticky(subscriberMethod, eventType, subscription);
    }

    private List<SubscriberMethod> findSubscriberMethodByClass(Class<?> cls) {
        if (METHOD_CACHE.get(cls) != null) {
            return METHOD_CACHE.get(cls);
        }
        Iterator<SubscriberInfoIndex> it = this.subscriberInfoIndices.iterator();
        while (it.hasNext()) {
            SubscriberInfo subscriberInfo = it.next().getSubscriberInfo(cls);
            if (subscriberInfo != null) {
                List<SubscriberMethod> listAsList = Arrays.asList(subscriberInfo.getSubscriberMethods());
                METHOD_CACHE.put(subscriberInfo.getSubscriberClass(), listAsList);
                return listAsList;
            }
        }
        return null;
    }

    public void unregister(Object obj) {
        List<Class<?>> list = this.typesBySubscriber.get(obj.getClass());
        if (list != null) {
            list.clear();
            this.typesBySubscriber.remove(obj.getClass());
        }
    }

    public boolean isRegister(Object obj) {
        return this.typesBySubscriber.get(obj.getClass()) != null;
    }

    public void addIndex(SubscriberInfoIndex subscriberInfoIndex) {
        this.subscriberInfoIndices.add(subscriberInfoIndex);
    }

    private void postToSubscription(final Subscription subscription, final Object obj) {
        if (isRegister(subscription.getSubscriber())) {
            int i = AnonymousClass3.$SwitchMap$com$sq$eventbus$annotation$mode$ThreadMode[subscription.getSubscriberMethod().getThreadMode().ordinal()];
            if (i == 1) {
                invokeSubscriber(subscription, obj);
                return;
            }
            if (i == 2) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    invokeSubscriber(subscription, obj);
                    return;
                } else {
                    this.handler.post(new Runnable() { // from class: com.sq.eventbus.core.EventBus.1
                        @Override // java.lang.Runnable
                        public void run() {
                            EventBus.this.invokeSubscriber(subscription, obj);
                        }
                    });
                    return;
                }
            }
            if (i == 3) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    this.executorService.execute(new Runnable() { // from class: com.sq.eventbus.core.EventBus.2
                        @Override // java.lang.Runnable
                        public void run() {
                            EventBus.this.invokeSubscriber(subscription, obj);
                        }
                    });
                    return;
                } else {
                    invokeSubscriber(subscription, obj);
                    return;
                }
            }
            throw new IllegalStateException("未知线程模式！" + subscription.getSubscriberMethod().getThreadMode());
        }
    }

    /* JADX INFO: renamed from: com.sq.eventbus.core.EventBus$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$sq$eventbus$annotation$mode$ThreadMode;

        static {
            int[] iArr = new int[ThreadMode.values().length];
            $SwitchMap$com$sq$eventbus$annotation$mode$ThreadMode = iArr;
            try {
                iArr[ThreadMode.POSTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sq$eventbus$annotation$mode$ThreadMode[ThreadMode.MAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sq$eventbus$annotation$mode$ThreadMode[ThreadMode.ASYNC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invokeSubscriber(Subscription subscription, Object obj) {
        try {
            subscription.getSubscriberMethod().getMethod().invoke(subscription.getSubscriber(), obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void post(Object obj) {
        postSingleEventForEventType(obj, obj.getClass());
    }

    private void postSingleEventForEventType(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<Subscription> copyOnWriteArrayList;
        synchronized (this) {
            copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        }
        if (copyOnWriteArrayList == null || copyOnWriteArrayList.isEmpty()) {
            return;
        }
        Iterator<Subscription> it = copyOnWriteArrayList.iterator();
        while (it.hasNext()) {
            postToSubscription(it.next(), obj);
        }
    }

    private void sticky(SubscriberMethod subscriberMethod, Class<?> cls, Subscription subscription) {
        Object obj;
        if (!subscriberMethod.isSticky() || (obj = this.stickyEvents.get(cls)) == null) {
            return;
        }
        postToSubscription(subscription, obj);
    }

    public void postSticky(Object obj) {
        this.stickyEvents.put(obj.getClass(), obj);
        post(obj);
    }

    public <T> T getStickyEvent(Class<T> cls) {
        return cls.cast(this.stickyEvents.get(cls));
    }

    public <T> T removeStickyEvent(Class<T> cls) {
        return cls.cast(this.stickyEvents.remove(cls));
    }
}
