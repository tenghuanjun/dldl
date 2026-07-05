package com.duowan.ark.preference;

import com.duowan.ark.ArkUtils;
import com.duowan.ark.asignal.notify.PropertySet;
import com.duowan.ark.util.Config;
import com.duowan.ark.util.ThreadUtils;
import de.greenrobot.event.EventBus;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class Preference<T> {
    private T mDefaultValue;
    protected String mMark;
    private PropertySet<T> mPropertySet;
    private T mValue;

    protected abstract T getConfigValue(Config config, String str, T t);

    protected abstract void updateConfig(Config config, String str, T t);

    public Preference(T t) {
        this(t, "");
    }

    public Preference(T t, String str) {
        this(t, str, new PropertySet(t, t));
    }

    public Preference(T t, String str, PropertySet<T> propertySet) {
        this.mMark = str;
        this.mDefaultValue = t;
        syncSet(t);
        this.mPropertySet = propertySet;
        init(t, str);
    }

    public boolean isDefault() {
        return isEquals(this.mValue, this.mDefaultValue);
    }

    public void reset() {
        set(this.mDefaultValue);
    }

    public synchronized T get() {
        return this.mValue;
    }

    public void notifyValueSet(T t, T t2) {
        this.mPropertySet.oldValue = t;
        this.mPropertySet.newValue = t2;
        sendNotify(this.mPropertySet);
    }

    protected <N> void sendNotify(final N n) {
        ThreadUtils.runAsyncOnAvailableThread(new Runnable() { // from class: com.duowan.ark.preference.Preference.1
            @Override // java.lang.Runnable
            public void run() {
                EventBus.getDefault().post(n);
            }
        });
    }

    protected boolean isEquals(T t, T t2) {
        return (t == null && t2 == null) || (t != null && t.equals(t2));
    }

    private synchronized void syncSet(T t) {
        this.mValue = t;
    }

    public boolean set(T t) {
        if (!doSet(t)) {
            return false;
        }
        updateConfig(getConfig(), this.mMark, t);
        return true;
    }

    private boolean doSet(T t) {
        if (isEquals(this.mValue, t)) {
            return false;
        }
        T t2 = this.mValue;
        syncSet(t);
        notifyValueSet(t2, t);
        return true;
    }

    protected void init(T t, String str) {
        doSet(getConfigValue(getConfig(), str, t));
    }

    private Config getConfig() {
        return ArkUtils.getConfig();
    }
}
