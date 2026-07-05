package com.duowan.auk.asignal;

import com.duowan.auk.asignal.notify.MapPropertyUpdate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class MapProperty<K, V> extends Property<Map<K, V>> implements Map<K, V> {
    public MapProperty() {
        this(new HashMap());
    }

    public MapProperty(String str) {
        this(new HashMap(), str);
    }

    public MapProperty(Map<K, V> map) {
        super(map);
    }

    public MapProperty(Map<K, V> map, String str) {
        super(map, str);
    }

    @Override // java.util.Map
    public int size() {
        return get().size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return get().isEmpty();
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        return get().containsKey(obj);
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return get().containsValue(obj);
    }

    @Override // java.util.Map
    public V get(Object obj) {
        return get().get(obj);
    }

    @Override // java.util.Map
    public V put(K k, V v) {
        V vPut = get().put(k, v);
        itemValueUpdate(k, vPut, v);
        return vPut;
    }

    @Override // java.util.Map
    public V remove(Object obj) {
        V vRemove = get().remove(obj);
        if (vRemove != null) {
            itemValueUpdate(obj, vRemove, null);
        }
        return vRemove;
    }

    @Override // java.util.Map
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map
    public void clear() {
        Map<K, V> map = get();
        set(Collections.emptyMap());
        map.clear();
    }

    @Override // java.util.Map
    public Set<K> keySet() {
        return get().keySet();
    }

    @Override // java.util.Map
    public Collection<V> values() {
        return get().values();
    }

    @Override // java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        return get().entrySet();
    }

    private void itemValueUpdate(K k, V v, V v2) {
        sendNotify(new MapPropertyUpdate(k, v, v2));
    }
}
