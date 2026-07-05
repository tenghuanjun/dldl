package com.huya.mtp.utils;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ArrayUtils {
    public static <T> List<T> subList(List<T> list, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        int iMin = Math.min(i2 + i, list.size());
        while (i < iMin) {
            arrayList.add(list.get(i));
            i++;
        }
        return arrayList;
    }

    public static byte[] union(byte[]... bArr) {
        int length = 0;
        for (byte[] bArr2 : bArr) {
            length += bArr2.length;
        }
        byte[] bArr3 = new byte[length];
        union(bArr3, bArr);
        return bArr3;
    }

    public static boolean union(byte[] bArr, byte[]... bArr2) {
        int length = bArr.length;
        int length2 = 0;
        for (byte[] bArr3 : bArr2) {
            if (bArr3.length + length2 > length) {
                return false;
            }
            System.arraycopy(bArr3, 0, bArr, length2, bArr3.length);
            length2 += bArr3.length;
        }
        return true;
    }

    public static <T> boolean union(T[] tArr, T[]... tArr2) {
        int length = tArr.length;
        int length2 = 0;
        for (T[] tArr3 : tArr2) {
            int length3 = tArr3.length + length2;
            if (length3 > length) {
                return false;
            }
            System.arraycopy(tArr3, 0, tArr, length2, length3);
            length2 += tArr3.length;
        }
        return true;
    }

    public static <T> boolean add(T[] tArr, T[] tArr2, int i, T t) {
        if (tArr2.length - tArr.length != 1) {
            return false;
        }
        System.arraycopy(tArr, 0, tArr2, 0, i);
        tArr2[i] = t;
        System.arraycopy(tArr, i, tArr2, i + 1, tArr.length - i);
        return true;
    }

    public static <T> boolean remove(T[] tArr, T[] tArr2, int i) {
        if (tArr.length - tArr2.length != 1) {
            return false;
        }
        System.arraycopy(tArr, 0, tArr2, 0, i);
        System.arraycopy(tArr, i + 1, tArr2, i, (tArr.length - i) - 1);
        return true;
    }
}
