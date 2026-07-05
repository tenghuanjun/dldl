package com.huya.mtp.nsdt;

import kotlin.Metadata;

/* JADX INFO: compiled from: PingStatus.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\b¨\u0006\u001b"}, d2 = {"Lcom/huya/mtp/nsdt/PingStatus;", "", "()V", "avgRtt", "", "getAvgRtt", "()D", "setAvgRtt", "(D)V", "ip", "", "getIp", "()Ljava/lang/String;", "setIp", "(Ljava/lang/String;)V", "lossate", "getLossate", "setLossate", "maxRtt", "getMaxRtt", "setMaxRtt", "minRtt", "getMinRtt", "setMinRtt", "ttl", "getTtl", "setTtl", "nsdt-adr_release"}, k = 1, mv = {1, 1, 16})
public final class PingStatus {
    private double avgRtt;
    private String ip;
    private double lossate;
    private double maxRtt;
    private double minRtt;
    private double ttl;

    public final double getLossate() {
        return this.lossate;
    }

    public final void setLossate(double d) {
        this.lossate = d;
    }

    public final double getMinRtt() {
        return this.minRtt;
    }

    public final void setMinRtt(double d) {
        this.minRtt = d;
    }

    public final double getAvgRtt() {
        return this.avgRtt;
    }

    public final void setAvgRtt(double d) {
        this.avgRtt = d;
    }

    public final double getMaxRtt() {
        return this.maxRtt;
    }

    public final void setMaxRtt(double d) {
        this.maxRtt = d;
    }

    public final double getTtl() {
        return this.ttl;
    }

    public final void setTtl(double d) {
        this.ttl = d;
    }

    public final String getIp() {
        return this.ip;
    }

    public final void setIp(String str) {
        this.ip = str;
    }
}
