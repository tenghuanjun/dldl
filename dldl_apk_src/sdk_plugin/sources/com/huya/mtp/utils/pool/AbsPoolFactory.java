package com.huya.mtp.utils.pool;

import com.huya.mtp.utils.pool.BorrowPools;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public abstract class AbsPoolFactory<T> {
    private static final int DEFAULT_POOL_SIZE = 10;
    protected static final String TAG = AbsPoolFactory.class.getSimpleName();
    private BorrowPools.IPool sPool;

    protected abstract T createObject();

    protected abstract void resetObject(T t);

    public AbsPoolFactory() {
        this.sPool = new BorrowPools.SynchronizedObjectPool(10);
    }

    public AbsPoolFactory(int i) {
        this.sPool = new BorrowPools.SynchronizedObjectPool(i);
    }

    public T obtain() {
        T t = (T) this.sPool.borrow();
        return t != null ? t : createObject();
    }

    public void recycle(T t) {
        resetObject(t);
        this.sPool.revert(t);
    }
}
