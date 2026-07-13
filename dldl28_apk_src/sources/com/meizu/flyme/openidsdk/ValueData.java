package com.meizu.flyme.openidsdk;

/* JADX INFO: loaded from: classes3.dex */
public class ValueData {
    public int code;
    public long expired = System.currentTimeMillis() + 86400000;
    public String value;

    public ValueData(String str, int i) {
        this.value = str;
        this.code = i;
    }

    public native String toString();
}
