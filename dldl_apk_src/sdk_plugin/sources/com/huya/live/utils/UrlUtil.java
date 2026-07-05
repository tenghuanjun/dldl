package com.huya.live.utils;

import android.text.TextUtils;
import com.sqwan.bugless.util.FileUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class UrlUtil {
    public static final int _MSGTERM_MOB_ADR = 8;

    public static String getRightUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] strArrSplit = str.split("\\.");
        if (strArrSplit.length < 2) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = strArrSplit.length - 2;
        sb.append(strArrSplit[length]);
        sb.append("_8");
        strArrSplit[length] = sb.toString();
        StringBuilder sb2 = new StringBuilder(strArrSplit[0]);
        for (int i = 1; i < strArrSplit.length; i++) {
            sb2.append(FileUtil.FILE_EXTENSION_SEPARATOR);
            sb2.append(strArrSplit[i]);
        }
        return sb2.toString();
    }
}
