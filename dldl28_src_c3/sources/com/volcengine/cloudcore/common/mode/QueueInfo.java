package com.volcengine.cloudcore.common.mode;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class QueueInfo {
    public final String configurationCode;
    public final int total;
    public final int userPosition;

    public QueueInfo(String str, int i, int i2) {
        this.configurationCode = str;
        this.userPosition = i;
        this.total = i2;
    }

    public String toString() {
        return "QueueInfo{configurationCode='" + this.configurationCode + "', userPosition=" + this.userPosition + ", total=" + this.total + '}';
    }
}
