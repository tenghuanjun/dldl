package com.duowan.taf.jce.dynamic;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class BorrowPools {
    private static final String TAG = ObjectPool.class.getSimpleName();

    public interface IPool<T> {
        T borrow();

        boolean revert(T t);
    }

    public static class ObjectPool<T> implements IPool<T> {
        private final Object[] mPool;
        private int mPoolSize;

        public ObjectPool(int i) {
            if (i <= 0) {
                throw new RuntimeException("The max pool size must be > 0");
            }
            this.mPool = new Object[i];
        }

        @Override // com.duowan.taf.jce.dynamic.BorrowPools.IPool
        public T borrow() {
            int i = this.mPoolSize;
            if (i <= 0) {
                return null;
            }
            int i2 = i - 1;
            Object[] objArr = this.mPool;
            T t = (T) objArr[i2];
            objArr[i2] = null;
            this.mPoolSize = i - 1;
            return t;
        }

        @Override // com.duowan.taf.jce.dynamic.BorrowPools.IPool
        public boolean revert(T t) {
            if (isInPool(t)) {
                throw new RuntimeException("Already in the pool!");
            }
            int i = this.mPoolSize;
            Object[] objArr = this.mPool;
            if (i >= objArr.length) {
                return false;
            }
            objArr[i] = t;
            this.mPoolSize = i + 1;
            return true;
        }

        private boolean isInPool(T t) {
            for (int i = 0; i < this.mPoolSize; i++) {
                if (this.mPool[i] == t) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class SynchronizedObjectPool<T> extends ObjectPool<T> {
        private Object mLock;

        public SynchronizedObjectPool(int i) {
            super(i);
            this.mLock = new Object();
        }

        @Override // com.duowan.taf.jce.dynamic.BorrowPools.ObjectPool, com.duowan.taf.jce.dynamic.BorrowPools.IPool
        public T borrow() {
            T t;
            synchronized (this.mLock) {
                t = (T) super.borrow();
            }
            return t;
        }

        @Override // com.duowan.taf.jce.dynamic.BorrowPools.ObjectPool, com.duowan.taf.jce.dynamic.BorrowPools.IPool
        public boolean revert(T t) {
            boolean zRevert;
            synchronized (this.mLock) {
                zRevert = super.revert(t);
            }
            return zRevert;
        }
    }
}
