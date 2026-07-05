package com.duowan.ark.thread;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ParamBuild {
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private int mCoreCount = CPU_COUNT + 1;
    private int mMaxCount = Integer.MAX_VALUE;
    private String mType = null;
    private int mPriority = 10;
    private boolean mHasLooper = true;

    public ParamBuild setCoreCount(int i) {
        this.mCoreCount = i;
        return this;
    }

    public ParamBuild setMaxCount(int i) {
        this.mMaxCount = i;
        return this;
    }

    public ParamBuild setType(String str) {
        this.mType = str;
        return this;
    }

    public ParamBuild setPriority(int i) {
        this.mPriority = i;
        return this;
    }

    public ParamBuild setHasLooper(boolean z) {
        this.mHasLooper = z;
        return this;
    }

    public Params build() {
        return new Params(this.mCoreCount, this.mMaxCount, this.mType, this.mPriority, this.mHasLooper);
    }

    public static class Params {
        private int mCoreCount;
        private boolean mHasLooper;
        private int mMaxCount;
        private int mPriority;
        private String mType;

        public Params(int i, int i2, String str, int i3, boolean z) {
            this.mCoreCount = i;
            this.mType = str;
            this.mPriority = i3;
            this.mHasLooper = z;
            this.mMaxCount = i2;
        }

        public int getCount() {
            return this.mCoreCount;
        }

        public String getType() {
            return this.mType;
        }

        public int getPriority() {
            return this.mPriority;
        }

        public boolean hasLooper() {
            return this.mHasLooper;
        }

        public int getMaxCount() {
            return this.mMaxCount;
        }
    }
}
