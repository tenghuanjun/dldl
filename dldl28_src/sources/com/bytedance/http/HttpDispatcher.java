package com.bytedance.http;

import com.bytedance.applog.aggregation.MetricsSQLiteCacheKt;
import com.bytedance.dns.DnsResolver;
import com.bytedance.http.b.c;
import com.bytedance.http.b.g;
import com.mobile.auth.gatewayauth.Constant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: classes2.dex */
public class HttpDispatcher {
    private static final String QUICK_HTTP_SDK_VERSION = "1.4.0";
    private final Builder mBuilder;

    public static final class Builder {
        private ExecutorService mExecutorService;
        private DnsResolver mResolver;
        private Scheduler mScheduler;
        private SSLSocketFactory mSslSocketFactory = null;
        private HostnameVerifier mHostnameVerifier = null;
        private int mConnectTimeout = 10000;
        private int mReadTimeout = 10000;
        private int mCacheExpiredTime = 900000;
        private int mRetryMode = 0;
        private int mRetryInterval = 1000;
        private int mRetryCount = 3;
        private int mDnsSelectStrategy = 0;
        private int mPayloadQueueSize = 10;
        private boolean mIgnoreCertificateVerify = false;
        private boolean mLogger = false;
        private final List mInterceptors = new ArrayList();
        private final Map mBackupIpList = new ConcurrentHashMap();

        public final Builder addBackupIp(String str, String str2) {
            Set set;
            if (this.mBackupIpList.containsKey(str)) {
                set = (Set) this.mBackupIpList.get(str);
            } else {
                HashSet hashSet = new HashSet();
                this.mBackupIpList.put(str, hashSet);
                set = hashSet;
            }
            set.add(str2);
            return this;
        }

        public final Builder addInterceptor(Interceptor interceptor) {
            this.mInterceptors.add(interceptor);
            return this;
        }

        public final HttpDispatcher build() {
            this.mScheduler = new Scheduler(this.mExecutorService);
            this.mResolver = new DnsResolver(this.mExecutorService, this.mCacheExpiredTime, this.mDnsSelectStrategy, this.mLogger);
            for (String str : this.mBackupIpList.keySet()) {
                this.mResolver.addHttpCloud(str, new ArrayList((Collection) this.mBackupIpList.get(str)));
            }
            c.a(this.mLogger);
            c.a("== QUICK-HTTP SDK CONFIGURATION ==");
            c.a(String.format(Locale.getDefault(), "connect timeout: %d ms", Integer.valueOf(this.mConnectTimeout)));
            c.a(String.format(Locale.getDefault(), "read timeout: %d ms", Integer.valueOf(this.mReadTimeout)));
            c.a(String.format(Locale.getDefault(), "cache expired time: %d ms", Integer.valueOf(this.mCacheExpiredTime)));
            c.a(String.format(Locale.getDefault(), "retry interval: %d ms", Integer.valueOf(this.mRetryInterval)));
            c.a(String.format(Locale.getDefault(), "retry mode: %d", Integer.valueOf(this.mRetryMode)));
            c.a(String.format(Locale.getDefault(), "retry count: %d", Integer.valueOf(this.mRetryCount)));
            c.a(String.format(Locale.getDefault(), "dns select strategy: %d", Integer.valueOf(this.mDnsSelectStrategy)));
            c.a(String.format(Locale.getDefault(), "logger switch: %b", Boolean.valueOf(this.mLogger)));
            for (String str2 : this.mBackupIpList.keySet()) {
                c.a(String.format(Locale.getDefault(), str2 + ": " + Arrays.toString(((Set) this.mBackupIpList.get(str2)).toArray()), new Object[0]));
            }
            c.a("== END ==");
            return new HttpDispatcher(this);
        }

        public final Builder cacheExpiredTime(long j, TimeUnit timeUnit) {
            this.mCacheExpiredTime = g.a("expired time", j, timeUnit);
            return this;
        }

        public final Builder connectTimeout(long j, TimeUnit timeUnit) {
            this.mConnectTimeout = g.a(Constant.API_PARAMS_KEY_TIMEOUT, j, timeUnit);
            return this;
        }

        public final Builder dnsSelectStrategy(int i) {
            this.mDnsSelectStrategy = i;
            return this;
        }

        public final Builder executor(ExecutorService executorService) {
            this.mExecutorService = executorService;
            return this;
        }

        public final Builder hostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.mHostnameVerifier = hostnameVerifier;
            return this;
        }

        public final Builder ignoreCertificateVerify(boolean z) {
            this.mIgnoreCertificateVerify = z;
            return this;
        }

        public final Builder logger(boolean z) {
            this.mLogger = z;
            return this;
        }

        public final Builder payloadQueueSize(int i) {
            this.mPayloadQueueSize = Math.max(i, 1);
            return this;
        }

        public final Builder readTimeout(long j, TimeUnit timeUnit) {
            this.mReadTimeout = g.a(Constant.API_PARAMS_KEY_TIMEOUT, j, timeUnit);
            return this;
        }

        public final Builder retryCount(int i) {
            this.mRetryCount = i;
            return this;
        }

        public final Builder retryInterval(long j, TimeUnit timeUnit) {
            this.mRetryInterval = g.a(MetricsSQLiteCacheKt.METRICS_INTERVAL, j, timeUnit);
            return this;
        }

        public final Builder retryMode(int i) {
            this.mRetryMode = i;
            return this;
        }

        public final Builder socketFactory(SSLSocketFactory sSLSocketFactory) {
            this.mSslSocketFactory = sSLSocketFactory;
            return this;
        }
    }

    public HttpDispatcher(Builder builder) {
        this.mBuilder = builder;
    }

    public int cacheExpiredTime() {
        return this.mBuilder.mCacheExpiredTime;
    }

    public int connectTimeout() {
        return this.mBuilder.mConnectTimeout;
    }

    public Scheduler dispatcher() {
        return this.mBuilder.mScheduler;
    }

    public int dnsSelectStrategy() {
        return this.mBuilder.mDnsSelectStrategy;
    }

    public String getSDKVersion() {
        return QUICK_HTTP_SDK_VERSION;
    }

    public HostnameVerifier hostnameVerifier() {
        return this.mBuilder.mHostnameVerifier;
    }

    public boolean ignoreCertificateVerify() {
        return this.mBuilder.mIgnoreCertificateVerify;
    }

    public List interceptors() {
        return this.mBuilder.mInterceptors;
    }

    public boolean logger() {
        return this.mBuilder.mLogger;
    }

    public Call newCall(HttpRequest httpRequest) {
        return RealCall.newRealCall(this, httpRequest);
    }

    public WebSocket newWebSocket(HttpRequest httpRequest) {
        return RealWebSocket.newWebSocket(this, httpRequest);
    }

    public int payloadQueueSize() {
        return this.mBuilder.mPayloadQueueSize;
    }

    public int readTimeout() {
        return this.mBuilder.mReadTimeout;
    }

    public DnsResolver resolver() {
        return this.mBuilder.mResolver;
    }

    public int retryCount() {
        return this.mBuilder.mRetryCount;
    }

    public int retryInterval() {
        return this.mBuilder.mRetryInterval;
    }

    public int retryMode() {
        return this.mBuilder.mRetryMode;
    }

    public SSLSocketFactory socketFactory() {
        return this.mBuilder.mSslSocketFactory;
    }
}
