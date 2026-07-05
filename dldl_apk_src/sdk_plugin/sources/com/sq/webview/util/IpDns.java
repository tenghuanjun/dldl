package com.sq.webview.util;

import com.sq.tools.network.httpdns.util.HttpDnsUtil;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Dns;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class IpDns implements Dns {
    private static final IpDnsPool POOL = new IpDnsPool();
    final String domain;
    final String ip;

    public static Dns get(HttpUrl url, HttpUrl ipUrl) throws MalformedURLException {
        return get(url.host(), ipUrl.host());
    }

    public static Dns get(String domain, String ip) throws MalformedURLException {
        return POOL.get(domain, ip);
    }

    private static class IpDnsPool {
        private final Map<String, Dns> cache;

        private IpDnsPool() {
            this.cache = new ConcurrentHashMap();
        }

        Dns get(String domain, String ip) throws MalformedURLException {
            Dns ipDns;
            IpDns.check(domain, ip);
            String str = domain + "/" + ip;
            if (this.cache.containsKey(str)) {
                return this.cache.get(str);
            }
            synchronized (IpDnsPool.class) {
                ipDns = this.cache.get(str);
                if (ipDns == null) {
                    ipDns = new IpDns(domain, ip);
                    this.cache.put(str, ipDns);
                }
            }
            return ipDns;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void check(String domain, String ip) throws MalformedURLException {
        if (HttpDnsUtil.isIpAddress(domain)) {
            throw new MalformedURLException(domain + "是ip, 应该是域名");
        }
        if (HttpDnsUtil.isIpAddress(ip)) {
            return;
        }
        throw new MalformedURLException(ip + "不是ip");
    }

    private IpDns(String domain, String ip) throws MalformedURLException {
        check(domain, ip);
        this.domain = domain;
        this.ip = ip;
    }

    @Override // okhttp3.Dns
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        if (hostname == null) {
            throw new UnknownHostException("hostname == null");
        }
        if (this.domain.equals(hostname)) {
            return Collections.singletonList(InetAddress.getByAddress(hostname, InetAddress.getByName(this.ip).getAddress()));
        }
        return Dns.SYSTEM.lookup(hostname);
    }
}
