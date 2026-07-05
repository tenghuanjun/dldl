package com.bytedance.pangle.util;

import android.text.TextUtils;
import java.io.File;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class c {
    public static String[] a(File file) throws Throwable {
        String[] strArrA = com.bytedance.pangle.util.a.b.a(file);
        return TextUtils.isEmpty(strArrA[0]) ? com.bytedance.pangle.util.a.a.a(file) : strArrA;
    }
}
