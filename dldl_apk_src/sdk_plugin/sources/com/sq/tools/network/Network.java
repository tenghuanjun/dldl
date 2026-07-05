package com.sq.tools.network;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface Network {
    public static final String MOBILE_2G = "2G";
    public static final String MOBILE_3G = "3G";
    public static final String MOBILE_4G = "4G";
    public static final String MOBILE_5G = "5G";
    public static final String UNKNOWN = "unknown";
    public static final String WIFI = "wifi";

    @Target({ElementType.PARAMETER, ElementType.FIELD, ElementType.METHOD})
    @Retention(RetentionPolicy.SOURCE)
    public @interface NetworkType {
    }
}
