package com.bytedance.http.a;

import com.bytedance.dns.DnsResolver;
import com.bytedance.http.HttpRequest;
import com.bytedance.http.HttpResponse;
import com.bytedance.http.Interceptor;

/* JADX INFO: loaded from: classes2.dex */
public final class b implements Interceptor {
    @Override // com.bytedance.http.Interceptor
    public final HttpResponse intercept(Interceptor.Chain chain) {
        DnsResolver dnsResolverB = ((com.bytedance.http.b.e) chain).b();
        HttpRequest httpRequestRequest = chain.request();
        String strMasterHost = httpRequestRequest.masterHost();
        String strHost = httpRequestRequest.url().host();
        if (!com.bytedance.http.b.g.a(strHost)) {
            String ipAddress = dnsResolverB.getIpAddress(strMasterHost);
            if (dnsResolverB.queryDnsCache(strMasterHost, ipAddress)) {
                httpRequestRequest.extras().put("dns_cache_ip", ipAddress);
            }
        }
        HttpResponse httpResponseProceed = chain.proceed(httpRequestRequest);
        if (httpResponseProceed.code() > 0 && !com.bytedance.http.b.g.a(strHost)) {
            dnsResolverB.refreshAsync(strHost);
        }
        return httpResponseProceed;
    }

    @Override // com.bytedance.http.Interceptor
    public final String name() {
        return "cache_int";
    }
}
