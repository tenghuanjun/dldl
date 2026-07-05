package com.huya.mtp.hyns;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class NSSettings {
    public static final NSSettings DEFAULT_SETTINGS = builder().build();
    private final String mCacheDir;
    private final long mCacheExpireTimeMillis;
    private final String mCacheKey;
    private final long mCacheRefreshTimeMillis;
    private final int mCacheType;
    private final int mChannel;
    private final boolean mEnableCache;
    private final String mEncryptionKey;
    private final boolean mIsEncryption;
    private final boolean mMergeRequest;
    private final int mPriority;
    private final int mRetryCount;
    private final boolean mReturnOnMainThread;
    private final int mTimeOut;
    private final int mTimeOutIncrement;

    public interface CacheType {
        public static final int CACHE_FIRST = 3;
        public static final int CACHE_ONLY = 1;
        public static final int CACHE_THEN_NET = 2;
        public static final int NET_FIRST = 0;
        public static final int NET_ONLY = 4;
    }

    public interface Method {
        public static final int DELETE = 3;
        public static final int DEPRECATED_GET_OR_POST = -1;
        public static final int GET = 0;
        public static final int HEAD = 4;
        public static final int OPTIONS = 5;
        public static final int PATCH = 7;
        public static final int POST = 1;
        public static final int PUT = 2;
        public static final int TRACE = 6;
    }

    public interface NSChannel {
        public static final int Both = 3;
        public static final int Long = 2;
        public static final int Pushchannel = 5;
        public static final int Quic = 4;
        public static final int Short = 1;
        public static final int TcpWs = 5;
    }

    public interface Priority {
        public static final int HIGH = 1;
        public static final int IMMEDIATE = 0;
        public static final int LOW = 5;
        public static final int NORMAL = 3;
    }

    public static Builder builder() {
        return new Builder();
    }

    private NSSettings(int i, int i2, int i3, int i4, boolean z, int i5, String str, String str2, boolean z2, boolean z3, long j, long j2, boolean z4, String str3, int i6) {
        this.mTimeOut = i;
        this.mTimeOutIncrement = i2;
        this.mRetryCount = i3;
        this.mPriority = i4;
        this.mEnableCache = z;
        this.mCacheType = i5;
        this.mCacheDir = str;
        this.mCacheKey = str2;
        this.mCacheExpireTimeMillis = j;
        this.mCacheRefreshTimeMillis = j2;
        this.mIsEncryption = z4;
        this.mEncryptionKey = str3;
        this.mChannel = i6;
        this.mMergeRequest = z2;
        this.mReturnOnMainThread = z3;
    }

    public boolean isMergeRequest() {
        return this.mMergeRequest;
    }

    public boolean isReturnOnMainThread() {
        return this.mReturnOnMainThread;
    }

    public int getTimeOut() {
        return this.mTimeOut;
    }

    public int getRetryCount() {
        return this.mRetryCount;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public int getTimeOutIncrement() {
        return this.mTimeOutIncrement;
    }

    public boolean isEnableCache() {
        return this.mEnableCache;
    }

    public boolean isEncryption() {
        return this.mIsEncryption;
    }

    public int getCacheType() {
        return this.mCacheType;
    }

    public int getChannel() {
        return this.mChannel;
    }

    public String getCacheDir() {
        return this.mCacheDir;
    }

    public String getCacheKey() {
        return this.mCacheKey;
    }

    public long getCacheExpireTimeMillis() {
        return this.mCacheExpireTimeMillis;
    }

    public long getCacheRefreshTimeMillis() {
        return this.mCacheRefreshTimeMillis;
    }

    public String getEncryptionKey() {
        return this.mEncryptionKey;
    }

    public static final class Builder {
        public static final int DEFAULT_CACHE_TYPE = 4;
        public static final int DEFAULT_EXPIRE_TIME = 86400000;
        public static final int DEFAULT_PRIORITY = 3;
        public static final int DEFAULT_REFRESH_TIME = 60000;
        public static final int DEFAULT_RETRY_COUNT = 1;
        public static final int DEFAULT_TIMEOUT = 10000;
        public static final int DEFAULT_TIMEOUT_INCREMENT = 0;
        private String mCacheDir;
        private String mCacheKey;
        private boolean mEnableCache;
        private String mEncryptionKey;
        private boolean mIsEncryption;
        private int mTimeOut = 10000;
        private int mTimeOutIncrement = 0;
        private int mRetryCount = 1;
        private int mPriority = 3;
        private int mChannel = 3;
        private int mCacheType = 4;
        private boolean mMergeRequest = false;
        private boolean mReturnOnMainThread = false;
        private long mCacheExpireTimeMillis = 86400000;
        private long mCacheRefreshTimeMillis = 60000;

        public Builder timeOut(int i) {
            this.mTimeOut = i;
            return this;
        }

        public Builder timeOutIncrement(int i) {
            this.mTimeOutIncrement = i;
            return this;
        }

        public Builder retryCount(int i) {
            this.mRetryCount = i;
            return this;
        }

        public Builder priority(int i) {
            this.mPriority = i;
            return this;
        }

        public Builder enableCache(boolean z) {
            this.mEnableCache = z;
            return this;
        }

        public Builder isEncryption(boolean z) {
            this.mIsEncryption = z;
            return this;
        }

        public Builder setEncryptionKey(String str) {
            this.mEncryptionKey = str;
            return this;
        }

        public Builder cacheType(int i) {
            this.mCacheType = i;
            return this;
        }

        public Builder cacheDir(String str) {
            this.mCacheDir = str;
            return this;
        }

        public Builder cacheKey(String str) {
            this.mCacheKey = str;
            return this;
        }

        public Builder setMergeRequest(boolean z) {
            this.mMergeRequest = z;
            return this;
        }

        public Builder setReturnOnMainThread(boolean z) {
            this.mReturnOnMainThread = z;
            return this;
        }

        public Builder channel(int i) {
            this.mChannel = i;
            return this;
        }

        public Builder cacheExpireTimeMillis(long j) {
            this.mCacheExpireTimeMillis = j;
            return this;
        }

        public Builder cacheRefreshTimeMillis(long j) {
            this.mCacheRefreshTimeMillis = j;
            return this;
        }

        public NSSettings build() {
            return new NSSettings(this.mTimeOut, this.mTimeOutIncrement, this.mRetryCount, this.mPriority, this.mEnableCache, this.mCacheType, this.mCacheDir, this.mCacheKey, this.mMergeRequest, this.mReturnOnMainThread, this.mCacheExpireTimeMillis, this.mCacheRefreshTimeMillis, this.mIsEncryption, this.mEncryptionKey, this.mChannel);
        }
    }
}
