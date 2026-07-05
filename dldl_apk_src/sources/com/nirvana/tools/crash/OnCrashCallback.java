package com.nirvana.tools.crash;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public interface OnCrashCallback {
    void onCrashOccurred(String str, String str2, String str3, String str4, boolean z, String str5);

    void onCrashUploadFailed(String str, String str2, String str3);
}
