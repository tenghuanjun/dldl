package com.bytedance.pangle.util;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d {
    public static <T> boolean a(T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    public static <T> boolean a(T[] tArr, T[] tArr2) {
        if (tArr2 == null) {
            return true;
        }
        for (T t : tArr2) {
            if (!a(tArr, t)) {
                return false;
            }
        }
        return true;
    }

    private static <T> boolean a(T[] tArr, T t) {
        int i;
        if (tArr != null) {
            i = 0;
            while (i < tArr.length) {
                if (tArr[i] == t || (tArr[i] != null && tArr[i].equals(t))) {
                    break;
                }
                i++;
            }
            i = -1;
        } else {
            i = -1;
        }
        return i != -1;
    }
}
