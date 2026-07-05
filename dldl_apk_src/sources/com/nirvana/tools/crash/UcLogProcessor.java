package com.nirvana.tools.crash;

import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
class UcLogProcessor {
    UcLogProcessor() {
    }

    public static final String getJavaStackTracingFromLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int iIndexOf = str.indexOf("Back traces starts.");
        int iIndexOf2 = str.indexOf("Back traces ends.");
        return (iIndexOf < 0 || iIndexOf2 < 0) ? str : str.substring(iIndexOf, iIndexOf2);
    }

    public static final String getNativeStackTracingFromLog(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iIndexOf = str.indexOf("Process Name:");
        int iIndexOf2 = str.indexOf("--- --- --- ---", iIndexOf);
        return iIndexOf >= 0 ? iIndexOf2 >= 0 ? str.substring(iIndexOf, iIndexOf2) : str.substring(iIndexOf) : "";
    }
}
