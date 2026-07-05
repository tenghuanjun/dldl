package com.huya.mtp.nsdt;

import com.huya.mtp.hyns.report.NSPushReporter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: PingConfig.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0001\u0013BA\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\b\u0010\u0012\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u000e\u0010\t\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/huya/mtp/nsdt/PingConfig;", "", "host", "", NSPushReporter.NS_PUSH_COUNT, "", "interval", "timeout", "ttl", "ipvx", "size", "(Ljava/lang/String;IIIIII)V", "getCount", "()I", "getHost", "()Ljava/lang/String;", "getInterval", "getTimeout", "toString", "Builder", "nsdt-adr_release"}, k = 1, mv = {1, 1, 16})
public final class PingConfig {
    private final int count;
    private final String host;
    private final int interval;
    private final int ipvx;
    private final int size;
    private final int timeout;
    private final int ttl;

    private PingConfig(String str, int i, int i2, int i3, int i4, int i5, int i6) {
        this.host = str;
        this.count = i;
        this.interval = i2;
        this.timeout = i3;
        this.ttl = i4;
        this.ipvx = i5;
        this.size = i6;
    }

    public /* synthetic */ PingConfig(String str, int i, int i2, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, i3, i4, i5, i6);
    }

    public final int getCount() {
        return this.count;
    }

    public final String getHost() {
        return this.host;
    }

    public final int getInterval() {
        return this.interval;
    }

    public final int getTimeout() {
        return this.timeout;
    }

    public String toString() {
        return "PingConfig{host='" + this.host + "', count=" + this.count + ", interval=" + this.interval + ", timeout=" + this.timeout + ", ttl=" + this.ttl + ", ipvx=" + this.ipvx + ", size=" + this.size + AbstractJsonLexerKt.END_OBJ;
    }

    /* JADX INFO: compiled from: PingConfig.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010\u000f\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0004J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/huya/mtp/nsdt/PingConfig$Builder;", "", "()V", NSPushReporter.NS_PUSH_COUNT, "", "host", "", "interval", "ipvx", "size", "timeout", "ttl", "build", "Lcom/huya/mtp/nsdt/PingConfig;", "setCount", "setHost", "setInterval", "setIpvx", "setSize", "setTimeout", "setTtl", "nsdt-adr_release"}, k = 1, mv = {1, 1, 16})
    public static final class Builder {
        private int count;
        private String host;
        private int interval;
        private int ipvx;
        private int size;
        private int timeout;
        private int ttl;

        public final Builder setHost(String host) {
            this.host = host;
            return this;
        }

        public final Builder setCount(int count) {
            this.count = count;
            return this;
        }

        public final Builder setInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public final Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public final Builder setTtl(int ttl) {
            this.ttl = ttl;
            return this;
        }

        public final Builder setIpvx(int ipvx) {
            this.ipvx = ipvx;
            return this;
        }

        public final Builder setSize(int size) {
            this.size = size;
            return this;
        }

        public final PingConfig build() {
            String str = this.host;
            int i = this.count;
            int i2 = this.interval;
            int i3 = this.timeout;
            return new PingConfig(str, i, i2, i3, this.ttl, i3, this.size, null);
        }
    }
}
