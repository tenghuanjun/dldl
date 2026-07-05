package com.duowan.auk.asignal.notify;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MapPropertyUpdate<K, V> {
    public K key;
    public V newValue;
    public V oldValue;

    public MapPropertyUpdate(K k, V v, V v2) {
        this.key = k;
        this.oldValue = v;
        this.newValue = v2;
    }
}
