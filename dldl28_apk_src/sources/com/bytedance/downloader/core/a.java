package com.bytedance.downloader.core;

import com.bytedance.dns.DnsResolver;
import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected static final AtomicInteger f351a = new AtomicInteger(0);
    protected final g b;
    protected final int c;
    protected final int d;
    protected final boolean e;
    protected final boolean f;
    protected final DnsResolver g;

    protected a(DnsResolver dnsResolver, DownloadConfig downloadConfig, g gVar) {
        this.g = dnsResolver;
        this.b = gVar;
        this.c = downloadConfig.getConnectTimeout();
        this.d = downloadConfig.getReadTimeout();
        downloadConfig.getCacheExpiredTime();
        this.e = downloadConfig.forceFlushWhenWriteFile();
        this.f = downloadConfig.ignoreCertificateVerify();
    }

    protected static String c(String str) {
        String host;
        if (q.a(str)) {
            return "";
        }
        if (!str.toLowerCase().startsWith("https://") && !str.toLowerCase().startsWith("http://")) {
            str = "http://" + str;
        }
        try {
            host = new URI(str).getHost();
        } catch (Exception e) {
            e.printStackTrace();
            host = "";
        }
        return (host.toLowerCase().endsWith(".html") || host.toLowerCase().endsWith(".htm")) ? "" : host;
    }

    protected final String a(g gVar) {
        String strE = gVar.e();
        return q.a(strE) ? c(gVar.getUrl()) : strE;
    }

    protected final void a(String str) {
        if (q.a(str)) {
            return;
        }
        this.g.m6356lambda$refreshAsync$0$combytedancednsDnsResolver(str);
    }

    protected final boolean a(String str, String str2) {
        return this.g.queryDnsCache(str, str2);
    }

    protected final String b(String str) {
        return this.g.getIpAddress(str);
    }
}
