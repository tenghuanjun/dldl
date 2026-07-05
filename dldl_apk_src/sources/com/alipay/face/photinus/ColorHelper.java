package com.alipay.face.photinus;

import android.graphics.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
final class ColorHelper {
    ColorHelper() {
    }

    static List<Integer> appendGrayPaddingsToList(List<Integer> list, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Collections.nCopies(i, -7829368));
        arrayList.addAll(list);
        arrayList.addAll(Collections.nCopies(i, -7829368));
        return arrayList;
    }

    static List<Integer> prepareListForSmoothTransition(int[] iArr, int i) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < iArr.length; i2++) {
            int i3 = iArr[i2];
            arrayList.add(Integer.valueOf(i3));
            if (i2 < iArr.length - 1 && i3 == iArr[i2 + 1]) {
                for (int i4 = 0; i4 < i; i4++) {
                    arrayList.add(Integer.valueOf(i3));
                }
            }
        }
        return arrayList;
    }

    static List<Integer> smoothTransitionOfList(List<Integer> list, int i) {
        if (i <= 0) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < list.size(); i2++) {
            int iIntValue = list.get(i2).intValue();
            if (i2 == list.size() - 1) {
                arrayList.add(Integer.valueOf(iIntValue));
            } else {
                int iIntValue2 = list.get(i2 + 1).intValue();
                if (iIntValue == iIntValue2) {
                    arrayList.add(Integer.valueOf(iIntValue));
                } else {
                    int i3 = i + 1;
                    for (int i4 = 0; i4 < i3; i4++) {
                        arrayList.add(Integer.valueOf(lerp(iIntValue, iIntValue2, i4 / i3)));
                    }
                }
            }
        }
        return arrayList;
    }

    static int lerp(int i, int i2, float f) {
        if (i == i2) {
            return i;
        }
        float f2 = 1.0f - f;
        return Color.rgb((int) ((Color.red(i) * f2) + (Color.red(i2) * f)), (int) ((Color.green(i) * f2) + (Color.green(i2) * f)), (int) ((Color.blue(i) * f2) + (Color.blue(i2) * f)));
    }

    static int[] arrayFromList(List<Integer> list) {
        int size = list.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = list.get(i).intValue();
        }
        return iArr;
    }
}
