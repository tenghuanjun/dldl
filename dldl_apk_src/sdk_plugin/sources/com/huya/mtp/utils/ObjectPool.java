package com.huya.mtp.utils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ObjectPool<T> {
    private ObjectCreator creator;
    private int freeIdx = -1;
    private Object[] pooledObjs;

    public interface ObjectCreator {
        Object create(Object[] objArr);
    }

    public ObjectPool(int i, ObjectCreator objectCreator) {
        this.pooledObjs = new Object[i];
        this.creator = objectCreator;
    }

    public synchronized T borrow(Object[] objArr) {
        if (this.freeIdx < 0) {
            return (T) this.creator.create(objArr);
        }
        Object[] objArr2 = this.pooledObjs;
        int i = this.freeIdx;
        this.freeIdx = i - 1;
        return (T) objArr2[i];
    }

    public synchronized void release(T t) {
        Utils.dwAssert(t != null);
        if (this.freeIdx >= this.pooledObjs.length - 1) {
            return;
        }
        Object[] objArr = this.pooledObjs;
        int i = this.freeIdx + 1;
        this.freeIdx = i;
        objArr[i] = t;
    }

    public synchronized void reset() {
        for (int i = 0; i <= this.freeIdx; i++) {
            this.pooledObjs[i] = null;
        }
        this.freeIdx = -1;
    }
}
