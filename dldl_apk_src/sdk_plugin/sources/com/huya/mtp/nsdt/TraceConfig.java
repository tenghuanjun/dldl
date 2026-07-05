package com.huya.mtp.nsdt;

import android.util.Log;
import java.util.ArrayList;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: TraceConfig.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001a\n\u0002\u0010\u0011\n\u0002\b\u0005\u0018\u0000 $2\u00020\u0001:\u0002#$BG\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\u0011\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030 ¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\u0003H\u0016R\u001a\u0010\t\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u000b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u000e\"\u0004\b\u0018\u0010\u0010R\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u000e\"\u0004\b\u001a\u0010\u0010R\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u000e\"\u0004\b\u001c\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u000e\"\u0004\b\u001e\u0010\u0010¨\u0006%"}, d2 = {"Lcom/huya/mtp/nsdt/TraceConfig;", "", "host", "", "nqueries", "", "timeout", "protocol", "port", "addrType", "squeries", "maxTTL", "(Ljava/lang/String;IIIIIII)V", "getAddrType", "()I", "setAddrType", "(I)V", "getHost", "()Ljava/lang/String;", "setHost", "(Ljava/lang/String;)V", "getMaxTTL", "setMaxTTL", "getNqueries", "setNqueries", "getPort", "setPort", "getSqueries", "setSqueries", "getTimeout", "setTimeout", "toCmds", "", "()[Ljava/lang/String;", "toString", "Builder", "Companion", "nsdt-adr_release"}, k = 1, mv = {1, 1, 16})
public final class TraceConfig {
    public static final String DEFAULT_HOST = "www.baidu.com";
    public static final int DEFAULT_IPVX = 0;
    public static final int DEFAULT_MAXTTL = 30;
    public static final int DEFAULT_NQUERIES = 3;
    public static final int DEFAULT_PROTOCOL = 1;
    public static final int DEFAULT_SQUERIES = 16;
    public static final int DEFAULT_TIMEOUT = 5;
    public static final int INV_PORT = -1;
    public static final int IPV4 = 1;
    public static final int IPV6 = 2;
    private int addrType;
    private String host;
    private int maxTTL;
    private int nqueries;
    private int port;
    private int squeries;
    private int timeout;

    private TraceConfig(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.host = str;
        this.nqueries = i;
        this.timeout = i2;
        this.port = i4;
        this.addrType = i5;
        this.squeries = i6;
        this.maxTTL = i7;
    }

    public /* synthetic */ TraceConfig(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, i3, i4, i5, i6, i7);
    }

    public final int getAddrType() {
        return this.addrType;
    }

    public final String getHost() {
        return this.host;
    }

    public final int getMaxTTL() {
        return this.maxTTL;
    }

    public final int getNqueries() {
        return this.nqueries;
    }

    public final int getPort() {
        return this.port;
    }

    public final int getSqueries() {
        return this.squeries;
    }

    public final int getTimeout() {
        return this.timeout;
    }

    public final void setAddrType(int i) {
        this.addrType = i;
    }

    public final void setHost(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.host = str;
    }

    public final void setMaxTTL(int i) {
        this.maxTTL = i;
    }

    public final void setNqueries(int i) {
        this.nqueries = i;
    }

    public final void setPort(int i) {
        this.port = i;
    }

    public final void setSqueries(int i) {
        this.squeries = i;
    }

    public final void setTimeout(int i) {
        this.timeout = i;
    }

    public final String[] toCmds() {
        ArrayList arrayList = new ArrayList();
        new StringBuffer();
        arrayList.add("traceroute");
        arrayList.add("-q " + this.nqueries);
        arrayList.add("-w " + this.timeout);
        if (this.port != -1) {
            arrayList.add("-p " + this.port);
        }
        int i = this.addrType;
        if (i == 1) {
            arrayList.add("-4");
        } else if (i == 2) {
            arrayList.add("-6");
        }
        arrayList.add("-N " + this.squeries);
        arrayList.add("-m " + this.maxTTL);
        arrayList.add(this.host);
        Log.e(TraceConfig.class.getName(), "toCmds: " + arrayList);
        Object[] array = arrayList.toArray(new String[0]);
        if (array != null) {
            return (String[]) array;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    public String toString() {
        return "TraceConfig{host='" + this.host + "', nqueries=" + this.nqueries + ", timeout=" + this.timeout + ", port=" + this.port + ", addrType=" + this.addrType + ", squeries=" + this.squeries + ", maxTTL=" + this.maxTTL + AbstractJsonLexerKt.END_OBJ;
    }

    /* JADX INFO: compiled from: TraceConfig.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010!\u001a\u00020\"J\u000e\u0010\u0007\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0004J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0004J\u0010\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0004H\u0007J\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0004J\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\bR\u001a\u0010\u001e\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0006\"\u0004\b \u0010\b¨\u0006#"}, d2 = {"Lcom/huya/mtp/nsdt/TraceConfig$Builder;", "", "()V", "addrType", "", "getAddrType", "()I", "setAddrType", "(I)V", "host", "", "getHost", "()Ljava/lang/String;", "setHost", "(Ljava/lang/String;)V", "maxTTL", "getMaxTTL", "setMaxTTL", "nqueries", "getNqueries", "setNqueries", "port", "getPort", "setPort", "protocol", "getProtocol", "setProtocol", "squeries", "getSqueries", "setSqueries", "timeout", "getTimeout", "setTimeout", "build", "Lcom/huya/mtp/nsdt/TraceConfig;", "nsdt-adr_release"}, k = 1, mv = {1, 1, 16})
    public static final class Builder {
        private int addrType;
        private String host = TraceConfig.DEFAULT_HOST;
        private int nqueries = 3;
        private int timeout = 5;
        private int protocol = 1;
        private int port = -1;
        private int squeries = 16;
        private int maxTTL = 30;

        public final String getHost() {
            return this.host;
        }

        /* JADX INFO: renamed from: setHost, reason: collision with other method in class */
        public final void m20setHost(String str) {
            Intrinsics.checkParameterIsNotNull(str, "<set-?>");
            this.host = str;
        }

        public final int getNqueries() {
            return this.nqueries;
        }

        /* JADX INFO: renamed from: setNqueries, reason: collision with other method in class */
        public final void m22setNqueries(int i) {
            this.nqueries = i;
        }

        public final int getTimeout() {
            return this.timeout;
        }

        /* JADX INFO: renamed from: setTimeout, reason: collision with other method in class */
        public final void m26setTimeout(int i) {
            this.timeout = i;
        }

        public final int getProtocol() {
            return this.protocol;
        }

        /* JADX INFO: renamed from: setProtocol, reason: collision with other method in class */
        public final void m24setProtocol(int i) {
            this.protocol = i;
        }

        public final int getPort() {
            return this.port;
        }

        /* JADX INFO: renamed from: setPort, reason: collision with other method in class */
        public final void m23setPort(int i) {
            this.port = i;
        }

        public final int getAddrType() {
            return this.addrType;
        }

        /* JADX INFO: renamed from: setAddrType, reason: collision with other method in class */
        public final void m19setAddrType(int i) {
            this.addrType = i;
        }

        public final int getSqueries() {
            return this.squeries;
        }

        /* JADX INFO: renamed from: setSqueries, reason: collision with other method in class */
        public final void m25setSqueries(int i) {
            this.squeries = i;
        }

        public final int getMaxTTL() {
            return this.maxTTL;
        }

        /* JADX INFO: renamed from: setMaxTTL, reason: collision with other method in class */
        public final void m21setMaxTTL(int i) {
            this.maxTTL = i;
        }

        public final Builder setHost(String host) {
            Intrinsics.checkParameterIsNotNull(host, "host");
            this.host = host;
            return this;
        }

        public final Builder setNqueries(int nqueries) {
            this.nqueries = nqueries;
            return this;
        }

        public final Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        @Deprecated(message = "")
        public final Builder setProtocol(int protocol) {
            this.protocol = protocol;
            return this;
        }

        public final Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public final Builder setAddrType(int addrType) {
            this.addrType = addrType;
            return this;
        }

        public final Builder setSqueries(int squeries) {
            this.squeries = squeries;
            return this;
        }

        public final Builder setMaxTTL(int maxTTL) {
            this.maxTTL = maxTTL;
            return this;
        }

        public final TraceConfig build() {
            return new TraceConfig(this.host, this.nqueries, this.timeout, this.protocol, this.port, this.addrType, this.squeries, this.maxTTL, null);
        }
    }
}
