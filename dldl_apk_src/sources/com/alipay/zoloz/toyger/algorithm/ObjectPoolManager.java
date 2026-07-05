package com.alipay.zoloz.toyger.algorithm;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ObjectPoolManager {
    private static final ObjectPoolManager _defaultManager = new ObjectPoolManager();
    private HashMap<String, CacheObjectContainer> cacheObjectHash = new HashMap<>();

    public static ObjectPoolManager getDefault() {
        return _defaultManager;
    }

    public static void main(String[] strArr) {
        getDefault().release(getDefault().getObject(ObjectPoolManager.class));
        getDefault().release(getDefault().getObject(ObjectPoolManager.class));
    }

    public void destory() {
        Iterator<String> it = this.cacheObjectHash.keySet().iterator();
        while (it.hasNext()) {
            this.cacheObjectHash.get(it.next()).destory();
        }
    }

    public <T> void destory(T t) {
        String name = t.getClass().getName();
        if (this.cacheObjectHash.containsKey(name)) {
            this.cacheObjectHash.get(name).destory();
        }
    }

    public <T> T getObject(Class<T> cls) {
        T tNewInstance;
        synchronized (this) {
            try {
                try {
                    tNewInstance = cls.newInstance();
                } catch (Throwable th) {
                    throw th;
                }
            } catch (Exception e) {
                e = e;
                tNewInstance = null;
            }
            try {
                String name = tNewInstance.getClass().getName();
                if (this.cacheObjectHash.containsKey(name)) {
                    return (T) this.cacheObjectHash.get(name).getObject();
                }
                CacheObjectContainer cacheObjectContainer = new CacheObjectContainer(cls);
                this.cacheObjectHash.put(name, cacheObjectContainer);
                return (T) cacheObjectContainer.getObject();
            } catch (Exception e2) {
                e = e2;
                System.out.println(e.getMessage());
                return tNewInstance;
            }
        }
    }

    public <T> void release(T t) {
        String name = t.getClass().getName();
        if (this.cacheObjectHash.containsKey(name)) {
            this.cacheObjectHash.get(name).release(t);
        }
    }
}
