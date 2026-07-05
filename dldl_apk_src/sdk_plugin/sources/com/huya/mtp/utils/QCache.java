package com.huya.mtp.utils;

import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class QCache<K, V> {
    private static final Integer KDefaultCapacity = 50;
    private ArrayList<K> mKeyList = new ArrayList<>();
    private HashMap<K, V> mData = new HashMap<>();
    private Integer mCapacity = 0;

    public QCache() {
        init(KDefaultCapacity.intValue());
    }

    public QCache(int i) {
        init(i);
    }

    public void put(K k, V v) {
        if (!this.mData.containsKey(k)) {
            trim();
            ArrayList<K> arrayList = this.mKeyList;
            arrayList.add(arrayList.size(), k);
        }
        this.mData.put(k, v);
        bringToTop(k);
    }

    public V get(K k) {
        V v = this.mData.get(k);
        if (v != null) {
            bringToTop(k);
        }
        return v;
    }

    private void init(int i) {
        this.mCapacity = Integer.valueOf(i);
    }

    private void bringToTop(K k) {
        int iIndexOf = this.mKeyList.indexOf(k);
        if (iIndexOf == -1 || iIndexOf == 0) {
            return;
        }
        this.mKeyList.remove(iIndexOf);
        this.mKeyList.add(0, k);
    }

    private void trim() {
        if (this.mKeyList.size() + 1 > this.mCapacity.intValue()) {
            this.mKeyList.remove(r0.size() - 1);
        }
    }
}
