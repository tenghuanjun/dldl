package com.donkingliang.imageselector.utils;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class StringUtils {
    public static boolean isNotEmptyString(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean isEmptyString(String str) {
        return str == null || str.length() <= 0;
    }
}
