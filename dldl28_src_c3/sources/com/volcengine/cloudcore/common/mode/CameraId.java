package com.volcengine.cloudcore.common.mode;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public enum CameraId {
    FRONT(0),
    BACK(1);

    public final int value;

    CameraId(int i) {
        this.value = i;
    }

    public static CameraId fromId(int i) {
        return i == 0 ? FRONT : BACK;
    }

    @Override // java.lang.Enum
    public String toString() {
        return "CameraId{value=" + this.value + '}';
    }
}
