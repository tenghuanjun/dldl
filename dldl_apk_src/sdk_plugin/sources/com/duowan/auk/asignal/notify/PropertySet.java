package com.duowan.auk.asignal.notify;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class PropertySet<T> {
    public T newValue;
    public T oldValue;

    public PropertySet(T t, T t2) {
        this.oldValue = t;
        this.newValue = t2;
    }
}
