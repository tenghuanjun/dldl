package com.duowan.auk.asignal;

import com.duowan.auk.asignal.notify.PropertySet;
import com.duowan.auk.util.L;
import com.huya.live.common.api.BaseApi;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Property<T> {
    private static final Map<String, Property> msListeningProperties = new HashMap();
    private T mDefaultValue;
    protected String mMark;
    private T mValue;

    public Property(T t) {
        this(t, "");
    }

    public Property(T t, String str) {
        this.mMark = str;
        this.mDefaultValue = t;
        if (!str.equals("")) {
            msListeningProperties.put(str, this);
            L.debug(this, "can listen property : %s", str);
        }
        syncSet(this.mDefaultValue);
    }

    public boolean isDefault() {
        return isEquals(this.mValue, this.mDefaultValue);
    }

    public static Map<String, Property> getMsListeningProperties() {
        return msListeningProperties;
    }

    public T getDefaultValue() {
        return this.mDefaultValue;
    }

    public void reset() {
        set(this.mDefaultValue);
    }

    public boolean set(T t) {
        if (isEquals(this.mValue, t)) {
            return false;
        }
        T t2 = this.mValue;
        syncSet(t);
        notifyValueSet(t2, t);
        return true;
    }

    public boolean setAndNotify(T t) {
        if (isEquals(this.mValue, t)) {
            notifyValueSet(this.mValue, t);
            return false;
        }
        T t2 = this.mValue;
        syncSet(t);
        notifyValueSet(t2, t);
        return true;
    }

    public synchronized T get() {
        return this.mValue;
    }

    public void notifyValueSet(T t, T t2) {
        sendNotify(new PropertySet(t, t2));
    }

    protected <N> void sendNotify(N n) {
        if (this.mMark.equals("")) {
            return;
        }
        if (BaseApi.getSignalCenterApi() == null) {
            L.error("bug bug bug, set SignalCenterApi");
        }
        BaseApi.getSignalCenterApi().send(n, 0, this.mMark);
    }

    protected boolean isEquals(T t, T t2) {
        return (t == null && t2 == null) || (t != null && t.equals(t2));
    }

    private synchronized void syncSet(T t) {
        this.mValue = t;
    }
}
