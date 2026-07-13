package com.bytedance.dns;

import androidx.compose.animation.core.ComplexDouble$$ExternalSyntheticBackport0;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public class DnsCache implements Runnable {
    private static final int RACE_DNS_SELECT_STRATEGY = 0;
    private final int mDnsCacheExpiredTimeMillis;
    private final int mDnsSelectStrategy;
    private final String mHost;
    private int mRefreshCount = 0;
    private long mLastUpdateTimeMillis = 0;
    private String mLastLocalIp = "0.0.0.0";
    private final List mCacheIpList = new ArrayList();
    private final AtomicReference mRefreshingLock = new AtomicReference(0);

    public DnsCache(String str, int i, int i2) {
        this.mHost = str;
        this.mDnsCacheExpiredTimeMillis = i;
        this.mDnsSelectStrategy = i2;
    }

    public boolean contains(String str) {
        if (this.mCacheIpList.size() == 0) {
            return false;
        }
        for (int i = 0; i < this.mCacheIpList.size(); i++) {
            if (str != null && str.equals(((InetAddress) this.mCacheIpList.get(i)).getHostAddress())) {
                return true;
            }
        }
        return false;
    }

    public List getIpList() {
        return (!b.a().equals(this.mLastLocalIp) || System.currentTimeMillis() - this.mLastUpdateTimeMillis > ((long) this.mDnsCacheExpiredTimeMillis)) ? Collections.emptyList() : this.mCacheIpList;
    }

    public void refresh(ExecutorService executorService) {
        if (!ComplexDouble$$ExternalSyntheticBackport0.m(this.mRefreshingLock, 0, 1)) {
            a.a("You can't refresh while refreshing");
            return;
        }
        String strA = b.a();
        long jCurrentTimeMillis = System.currentTimeMillis() - this.mLastUpdateTimeMillis;
        a.a("internal : " + jCurrentTimeMillis);
        if (strA.equals(this.mLastLocalIp) && this.mLastUpdateTimeMillis != 0 && jCurrentTimeMillis <= this.mDnsCacheExpiredTimeMillis / 2) {
            this.mRefreshingLock.set(0);
            a.a("No need to refresh localDNS");
        } else {
            this.mLastLocalIp = strA;
            this.mLastUpdateTimeMillis = System.currentTimeMillis();
            a.a("Start to refresh localDNS");
            executorService.submit(this);
        }
    }

    public int refreshCount() {
        return this.mRefreshCount;
    }

    @Override // java.lang.Runnable
    public void run() {
        List list;
        try {
            try {
                this.mRefreshCount++;
                String str = this.mHost;
                if (str == null || str.length() <= 0) {
                    a.c("Host is unavailable");
                } else {
                    List linkedList = new LinkedList(Arrays.asList(InetAddress.getAllByName(this.mHost)));
                    if (linkedList.size() > 0) {
                        this.mCacheIpList.clear();
                        a.a("Resolve localDNS as follows:");
                        for (int size = linkedList.size() - 1; size >= 0; size--) {
                            InetAddress inetAddress = (InetAddress) linkedList.get(size);
                            a.a(inetAddress.toString());
                            if (((inetAddress instanceof Inet4Address) && b.a(inetAddress)) || (inetAddress instanceof Inet6Address)) {
                                linkedList.remove(size);
                            }
                        }
                        if (this.mDnsSelectStrategy != 0 || linkedList.size() <= 6) {
                            list = this.mCacheIpList;
                        } else {
                            list = this.mCacheIpList;
                            linkedList = linkedList.subList(0, 6);
                        }
                        list.addAll(linkedList);
                    } else {
                        a.a("Not found any target ip in localDNS");
                    }
                }
            } catch (Exception e) {
                a.c(e.getMessage());
            }
        } finally {
            a.a("Update local ip, last update time and state lock");
            this.mLastLocalIp = b.a();
            this.mLastUpdateTimeMillis = System.currentTimeMillis();
            this.mRefreshingLock.set(0);
        }
    }
}
