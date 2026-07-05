package com.alipay.zoloz.toyger.algorithm;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class CacheObjectContainer<T> {
    private List<CacheObject> cacheObjectList = new ArrayList();
    private Class<T> type;

    public CacheObjectContainer(Class<T> cls) {
        this.type = cls;
    }

    public void destory() {
        this.cacheObjectList.clear();
    }

    public List<CacheObject> getCacheObjectList() {
        return this.cacheObjectList;
    }

    public T getObject() {
        synchronized (this) {
            for (CacheObject cacheObject : this.cacheObjectList) {
                if (!cacheObject.isUsing()) {
                    return (T) cacheObject.getCacheObject();
                }
            }
            try {
                T tNewInstance = this.type.newInstance();
                CacheObject cacheObject2 = new CacheObject();
                cacheObject2.setUsing(true);
                cacheObject2.setCacheObject(tNewInstance);
                this.cacheObjectList.add(cacheObject2);
                return tNewInstance;
            } catch (Exception unused) {
                return null;
            }
        }
    }

    public void release(T t) {
        for (CacheObject cacheObject : this.cacheObjectList) {
            if (cacheObject.getCacheObject() == t) {
                cacheObject.setUsing(false);
                return;
            }
        }
    }

    public void setCacheObjectList(List<CacheObject> list) {
        this.cacheObjectList = list;
    }
}
