package com.huya.hyhttpdns.dns;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpDnsHostsWithSource {
    private String[] hosts = null;
    private HttpDnsSource source = HttpDnsSource.None;

    public String[] getHosts() {
        return this.hosts;
    }

    public void setHosts(String[] strArr) {
        this.hosts = strArr;
    }

    public HttpDnsSource getSource() {
        return this.source;
    }

    public void setSource(HttpDnsSource httpDnsSource) {
        this.source = httpDnsSource;
    }
}
