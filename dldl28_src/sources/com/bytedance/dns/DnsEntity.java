package com.bytedance.dns;

import java.net.InetAddress;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public class DnsEntity {
    private final DnsCache mDnsCache;
    private final ExecutorService mExecutor;
    private final DnsHttpCloud mHttpCloud;

    public DnsEntity(String str, ExecutorService executorService, int i, int i2) {
        this.mExecutor = executorService;
        this.mDnsCache = new DnsCache(str, i, i2);
        this.mHttpCloud = new DnsHttpCloud(i2);
    }

    public void addHttpCloud(List list) {
        HashSet hashSet = new HashSet();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!hashSet.add(str)) {
                a.b("Duplicate " + str + " from HttpCloud");
            }
        }
        this.mHttpCloud.addHttpCloud(this.mExecutor, hashSet);
    }

    public DnsCache dnsCache() {
        return this.mDnsCache;
    }

    public InetAddress getIpAddress() {
        List ipList = this.mDnsCache.getIpList();
        return ipList.size() > 0 ? (InetAddress) ipList.get(0) : this.mHttpCloud.getIpAddress();
    }

    public void refresh() {
        this.mDnsCache.refresh(this.mExecutor);
    }
}
