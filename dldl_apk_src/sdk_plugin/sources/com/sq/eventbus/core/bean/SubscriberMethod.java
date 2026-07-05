package com.sq.eventbus.core.bean;

import com.sq.eventbus.annotation.mode.ThreadMode;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SubscriberMethod {
    private Class<?> eventType;
    private Method method;
    private String methodName;
    private int priority;
    private boolean sticky;
    private ThreadMode threadMode;

    public SubscriberMethod(Class cls, String str, ThreadMode threadMode, Class<?> cls2, boolean z, int i) {
        this.methodName = str;
        this.threadMode = threadMode;
        this.eventType = cls2;
        this.sticky = z;
        this.priority = i;
        try {
            this.method = cls.getDeclaredMethod(str, cls2);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public String getMethodName() {
        return this.methodName;
    }

    public Method getMethod() {
        return this.method;
    }

    public ThreadMode getThreadMode() {
        return this.threadMode;
    }

    public Class<?> getEventType() {
        return this.eventType;
    }

    public boolean isSticky() {
        return this.sticky;
    }

    public int getPriority() {
        return this.priority;
    }
}
