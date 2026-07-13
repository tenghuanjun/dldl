package com.bytedance.framwork.core.sdklog;

/* JADX INFO: loaded from: classes2.dex */
class LogItem {
    public long id;
    public int retryCount;
    public long retryTime;
    public long timestamp;
    public String type;
    public byte[] value;

    public LogItem() {
    }

    public LogItem(String str, byte[] bArr) {
        this.type = str;
        this.value = bArr;
    }
}
