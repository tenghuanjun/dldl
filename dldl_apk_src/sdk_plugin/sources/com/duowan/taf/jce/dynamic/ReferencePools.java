package com.duowan.taf.jce.dynamic;

import java.util.Vector;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ReferencePools {

    public interface ReuseablePoolFactory {
        Reuseable createReuseableObject();
    }

    public abstract class Reuseable {
        private int mRef = 0;
        private ReuseablePool mOwner = null;

        public Reuseable() {
        }

        public synchronized void addRef() {
            this.mRef++;
        }

        public synchronized void release() {
            int i = this.mRef - 1;
            this.mRef = i;
            if (i == 0) {
                this.mOwner.releaseObject(this);
            }
        }
    }

    public static class ReuseablePool {
        private int mMaxSize = 0;
        private Vector<Reuseable> mObjPool = new Vector<>();
        private ReuseablePoolFactory mReuseablePoolFactory;

        public ReuseablePool(ReuseablePoolFactory reuseablePoolFactory) {
            this.mReuseablePoolFactory = null;
            this.mReuseablePoolFactory = reuseablePoolFactory;
        }

        public synchronized void releaseObject(Reuseable reuseable) {
            if (reuseable != null) {
                if (reuseable.mOwner == this) {
                    this.mObjPool.add(reuseable);
                }
            }
        }

        public synchronized Reuseable obtainReuseable() {
            if (this.mObjPool.size() == 0) {
                return null;
            }
            Reuseable reuseable = this.mObjPool.get(this.mObjPool.size() - 1);
            reuseable.mRef = 1;
            this.mObjPool.remove(reuseable);
            return reuseable;
        }

        public void setMaxPoolSize(int i) {
            this.mMaxSize = i;
            for (int i2 = 0; i2 < i; i2++) {
                Reuseable reuseableCreateReuseableObject = this.mReuseablePoolFactory.createReuseableObject();
                reuseableCreateReuseableObject.mOwner = this;
                this.mObjPool.add(reuseableCreateReuseableObject);
            }
        }
    }
}
