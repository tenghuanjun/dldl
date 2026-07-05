package com.sq.tools.utils;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PingResult {
    private float avg;
    private String domain;
    private float max;
    private float mdev;
    private float min;
    private String pingDesc;

    public String toString() {
        return "PingResult{pingDesc='" + this.pingDesc + "', min=" + this.min + ", avg=" + this.avg + ", max=" + this.max + ", mdev=" + this.mdev + ", domain='" + this.domain + '\'' + AbstractJsonLexerKt.END_OBJ;
    }

    public String getDomain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public String getPingDesc() {
        return this.pingDesc;
    }

    public void setPingDesc(String str) {
        this.pingDesc = str;
    }

    public float getMin() {
        return this.min;
    }

    public void setMin(float f) {
        this.min = f;
    }

    public float getAvg() {
        return this.avg;
    }

    public void setAvg(float f) {
        this.avg = f;
    }

    public float getMax() {
        return this.max;
    }

    public void setMax(float f) {
        this.max = f;
    }

    public float getMdev() {
        return this.mdev;
    }

    public void setMdev(float f) {
        this.mdev = f;
    }
}
