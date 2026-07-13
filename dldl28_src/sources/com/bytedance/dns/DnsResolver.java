package com.bytedance.dns;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
public class DnsResolver {
    private static final String DNS_RESOLVER_SDK_VERSION = "1.3.0";
    private static final String EMPTY = "";
    private static final int KEEP_ALIVE_TIME = 10;
    private final int mDnsCacheExpiredTimeMillis;
    private final Map mDnsEntities = new ConcurrentHashMap();
    private final int mDnsSelectStrategy;
    private final ExecutorService mExecutor;
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() << 2;
    private static final int MAXIMUM_POOL_SIZE = Runtime.getRuntime().availableProcessors() << 3;
    private static final Object LOCK = new Object();

    public DnsResolver(ExecutorService executorService, int i, int i2, boolean z) {
        a.a(z);
        this.mExecutor = executorService == null ? new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 10L, TimeUnit.MILLISECONDS, new SynchronousQueue(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()) : executorService;
        this.mDnsCacheExpiredTimeMillis = i;
        this.mDnsSelectStrategy = i2;
    }

    public void addHttpCloud(String str, List list) {
        DnsEntity dnsEntity;
        synchronized (LOCK) {
            if (this.mDnsEntities.containsKey(str)) {
                dnsEntity = (DnsEntity) this.mDnsEntities.get(str);
            } else {
                dnsEntity = new DnsEntity(str, this.mExecutor, this.mDnsCacheExpiredTimeMillis, this.mDnsSelectStrategy);
                this.mDnsEntities.put(str, dnsEntity);
            }
            dnsEntity.addHttpCloud(list);
        }
    }

    public String getIpAddress(String str) {
        InetAddress ipAddress;
        synchronized (LOCK) {
            return (!this.mDnsEntities.containsKey(str) || (ipAddress = ((DnsEntity) this.mDnsEntities.get(str)).getIpAddress()) == null) ? "" : ipAddress.getHostAddress();
        }
    }

    public String getSDKVersion() {
        return DNS_RESOLVER_SDK_VERSION;
    }

    public boolean queryDnsCache(String str, String str2) {
        synchronized (LOCK) {
            if (!this.mDnsEntities.containsKey(str)) {
                return false;
            }
            return ((DnsEntity) this.mDnsEntities.get(str)).dnsCache().contains(str2);
        }
    }

    /* JADX INFO: renamed from: refresh, reason: merged with bridge method [inline-methods] */
    public void m6356lambda$refreshAsync$0$combytedancednsDnsResolver(String str) {
        DnsEntity dnsEntity;
        synchronized (LOCK) {
            if (this.mDnsEntities.containsKey(str)) {
                dnsEntity = (DnsEntity) this.mDnsEntities.get(str);
            } else {
                dnsEntity = new DnsEntity(str, this.mExecutor, this.mDnsCacheExpiredTimeMillis, this.mDnsSelectStrategy);
                this.mDnsEntities.put(str, dnsEntity);
            }
            dnsEntity.refresh();
        }
    }

    public void refreshAsync(final String str) {
        this.mExecutor.execute(new Runnable() { // from class: com.bytedance.dns.DnsResolver$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m6356lambda$refreshAsync$0$combytedancednsDnsResolver(str);
            }
        });
    }
}
