package com.volcengine.androidcloud.common.log;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public enum AcTargetType {
    InjectTarget(1),
    LocalStorageTarget(2),
    LoggingTarget(4);

    private final int flag;

    AcTargetType(int i) {
        this.flag = i;
    }

    public Integer getValue() {
        return Integer.valueOf(this.flag);
    }

    public boolean isActive(int i) {
        int i2 = this.flag;
        return (i & i2) == i2;
    }
}
