package com.sq.tools.network.httpdns;

import android.text.TextUtils;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqHttpDnsConfig {
    public final String appId;
    public final String appKey;
    public final String dnsServerHost;
    public final String[] dnsServerIps;
    public final boolean enable;
    public final boolean reportDetail;
    public final int timeout;

    public SqHttpDnsConfig(Builder builder) {
        this.enable = builder.enable;
        this.appId = builder.appId;
        this.appKey = builder.appKey;
        this.timeout = builder.timeOut;
        this.dnsServerIps = builder.dnsServerIps;
        this.dnsServerHost = builder.dnsServerHost;
        this.reportDetail = builder.reportDetail;
        if (TextUtils.isEmpty(this.appId)) {
            throw new IllegalArgumentException("HttpDns app id can not be null");
        }
        if (TextUtils.isEmpty(this.appKey)) {
            throw new IllegalArgumentException("HttpDns app key can not be null");
        }
        String[] strArr = this.dnsServerIps;
        if (strArr == null || strArr.length <= 0) {
            throw new IllegalArgumentException("HttpDns server ips can not be null");
        }
        if (TextUtils.isEmpty(this.dnsServerHost)) {
            throw new IllegalArgumentException("HttpDns server host can not be null");
        }
    }

    public static class Builder {
        private String appId;
        private String appKey;
        private String dnsServerHost;
        private String[] dnsServerIps;
        public boolean reportDetail;
        private boolean enable = true;
        private int timeOut = 2000;

        public Builder setSecretInfo(String appId, String appKey) {
            if (!TextUtils.isEmpty(appId) && !TextUtils.isEmpty(appKey)) {
                this.appId = appId;
                this.appKey = appKey;
            }
            return this;
        }

        public Builder setTimeOut(int timeout) {
            if (timeout > 0) {
                this.timeOut = timeout;
            }
            return this;
        }

        public Builder enable(boolean enable) {
            this.enable = enable;
            return this;
        }

        public Builder setDnsServerIps(String... ips) {
            this.dnsServerIps = ips;
            return this;
        }

        public Builder setDnsServerHost(String host) {
            this.dnsServerHost = host;
            return this;
        }

        public Builder reportDetail(boolean yes) {
            this.reportDetail = yes;
            return this;
        }

        public SqHttpDnsConfig build() {
            return new SqHttpDnsConfig(this);
        }
    }

    public String toString() {
        return "HttpDnsConfig{, enable=" + this.enable + AbstractJsonLexerKt.END_OBJ;
    }
}
