package com.volcengine.cloudcore.common.mode;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public enum StreamType {
    AUDIO(1),
    VIDEO(2),
    BOTH(3);

    public final int value;

    StreamType(int i) {
        this.value = i;
    }

    public static StreamType valueOf(int i) {
        if (i == 1) {
            return AUDIO;
        }
        if (i == 2) {
            return VIDEO;
        }
        if (i != 3) {
            return null;
        }
        return BOTH;
    }
}
