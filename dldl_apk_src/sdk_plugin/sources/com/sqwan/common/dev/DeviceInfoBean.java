package com.sqwan.common.dev;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DeviceInfoBean {
    public static final int SOURCE_CACHE = 1;
    public static final int SOURCE_RANDOM = 3;
    public static final int SOURCE_SYS_API = 2;
    private int source;
    private String value;

    public DeviceInfoBean(int i, String str) {
        this.source = i;
        this.value = str;
    }

    public int getSource() {
        return this.source;
    }

    public String getValue() {
        return this.value;
    }
}
