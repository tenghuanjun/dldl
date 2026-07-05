package com.huya.mtp.nsdt;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: compiled from: TcpConfig.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u00020\u0001:\u0001\u0010B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u0011"}, d2 = {"Lcom/huya/mtp/nsdt/TcpConfig;", "", "host", "", "port", "", "timeout", "body", "(Ljava/lang/String;IILjava/lang/String;)V", "getBody", "()Ljava/lang/String;", "getHost", "getPort", "()I", "getTimeout", "toString", "Builder", "nsdt-adr_release"}, k = 1, mv = {1, 1, 16})
public final class TcpConfig {
    private final String body;
    private final String host;
    private final int port;
    private final int timeout;

    public TcpConfig(String host, int i, int i2, String str) {
        Intrinsics.checkParameterIsNotNull(host, "host");
        this.host = host;
        this.port = i;
        this.timeout = i2;
        this.body = str;
    }

    public final String getBody() {
        return this.body;
    }

    public final String getHost() {
        return this.host;
    }

    public final int getPort() {
        return this.port;
    }

    public final int getTimeout() {
        return this.timeout;
    }

    public String toString() {
        return "TcpConfig{host=" + this.host + ", port=" + this.port + ", timeout=" + this.timeout + ", body='" + this.body + '\'' + AbstractJsonLexerKt.END_OBJ;
    }

    /* JADX INFO: compiled from: TcpConfig.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/huya/mtp/nsdt/TcpConfig$Builder;", "", "()V", "body", "", "host", "port", "", "timeout", "build", "Lcom/huya/mtp/nsdt/TcpConfig;", "setBody", "setHost", "setPort", "setTimeout", "nsdt-adr_release"}, k = 1, mv = {1, 1, 16})
    public static final class Builder {
        private String body;
        private String host;
        private int port;
        private int timeout;

        public final Builder setHost(String host) {
            Intrinsics.checkParameterIsNotNull(host, "host");
            this.host = host;
            return this;
        }

        public final Builder setPort(int port) {
            this.port = port;
            return this;
        }

        public final Builder setTimeout(int timeout) {
            this.timeout = timeout;
            return this;
        }

        public final Builder setBody(String body) {
            this.body = body;
            return this;
        }

        public final TcpConfig build() {
            String str = this.host;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("host");
            }
            return new TcpConfig(str, this.port, this.timeout, this.body);
        }
    }
}
