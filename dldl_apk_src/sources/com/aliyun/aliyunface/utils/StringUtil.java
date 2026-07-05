package com.aliyun.aliyunface.utils;

import com.igexin.push.core.b;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class StringUtil {
    public static final int INT_20 = 20;

    public static boolean isNullorEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static int getInt(String str, int i) {
        if (isNullorEmpty(str)) {
            return 0;
        }
        String str2 = "";
        for (int i2 = 0; i2 < str.length() && str2.length() < i; i2++) {
            char cCharAt = str.charAt(i2);
            if (Character.isDigit(cCharAt)) {
                str2 = str2 + cCharAt;
            }
        }
        if (isNullorEmpty(str2)) {
            return 0;
        }
        return Integer.parseInt(str2);
    }

    public static String data2String(byte[] bArr) {
        if (bArr != null && bArr.length > 20) {
            StringBuilder sb = new StringBuilder(120);
            sb.append('[');
            sb.append((int) bArr[0]);
            for (int i = 1; i < 20; i++) {
                sb.append(", ");
                sb.append((int) bArr[i]);
            }
            sb.append(']');
            return sb.toString();
        }
        return Arrays.toString(bArr);
    }

    public static String data2String(short[] sArr) {
        if (sArr != null && sArr.length > 20) {
            StringBuilder sb = new StringBuilder(120);
            sb.append('[');
            sb.append((int) sArr[0]);
            for (int i = 1; i < 20; i++) {
                sb.append(", ");
                sb.append((int) sArr[i]);
            }
            sb.append(']');
            return sb.toString();
        }
        return Arrays.toString(sArr);
    }

    public static String data2String(int[] iArr) {
        if (iArr != null && iArr.length > 20) {
            StringBuilder sb = new StringBuilder(120);
            sb.append('[');
            sb.append(iArr[0]);
            for (int i = 1; i < 20; i++) {
                sb.append(", ");
                sb.append(iArr[i]);
            }
            sb.append(']');
            return sb.toString();
        }
        return Arrays.toString(iArr);
    }

    public static <K, V> String map2String(Map<K, V> map) {
        return (map == null || map.isEmpty()) ? "" : collection2String(map.entrySet());
    }

    public static <T> String collection2String(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("[size=" + collection.size() + ": ");
        for (T t : collection) {
            if (t != null) {
                sb.append(t.toString());
                sb.append(b.aj);
            }
        }
        int iLastIndexOf = sb.lastIndexOf(b.aj);
        if (-1 != iLastIndexOf) {
            sb.deleteCharAt(iLastIndexOf);
        }
        sb.append("]");
        return sb.toString();
    }

    public static <T> String array2String(T[] tArr) {
        if (tArr == null || tArr.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder("[size=" + tArr.length + ": ");
        for (T t : tArr) {
            if (t != null) {
                sb.append(t.toString());
                sb.append(b.aj);
            }
        }
        int iLastIndexOf = sb.lastIndexOf(b.aj);
        if (-1 != iLastIndexOf) {
            sb.deleteCharAt(iLastIndexOf);
        }
        sb.append("]");
        return sb.toString();
    }

    public static <T> String join(T[] tArr, String str) {
        if (tArr == null || tArr.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T t : tArr) {
            if (t != null) {
                sb.append(t.toString());
                sb.append(str);
            }
        }
        int iLastIndexOf = sb.lastIndexOf(str);
        if (-1 != iLastIndexOf) {
            sb.delete(iLastIndexOf, sb.length());
        }
        return sb.toString();
    }

    public static <T> String join(Collection<T> collection, String str) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T t : collection) {
            if (t != null) {
                sb.append(t.toString());
                sb.append(str);
            }
        }
        int iLastIndexOf = sb.lastIndexOf(str);
        if (-1 != iLastIndexOf) {
            sb.delete(iLastIndexOf, sb.length());
        }
        return sb.toString();
    }
}
