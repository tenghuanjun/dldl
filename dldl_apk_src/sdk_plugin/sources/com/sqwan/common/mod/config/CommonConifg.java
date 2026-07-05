package com.sqwan.common.mod.config;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class CommonConifg {
    private long timestamp;
    private long timestampLocal;
    private String userId = "";

    public void setTimestamp(long j) {
        this.timestamp = j;
        this.timestampLocal = System.currentTimeMillis() / 1000;
    }

    public long getCurrentTime() {
        long jCurrentTimeMillis = System.currentTimeMillis() / 1000;
        long j = this.timestamp;
        if (j == 0) {
            return jCurrentTimeMillis;
        }
        long j2 = this.timestampLocal;
        return j2 != 0 ? jCurrentTimeMillis + (j - j2) : jCurrentTimeMillis;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }
}
