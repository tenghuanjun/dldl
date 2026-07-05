package com.bytedance.sdk.openadsdk.api.plugin;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class b {
    private static final StringBuffer a = new StringBuffer();

    public static void a(String str, String str2) {
        StringBuffer stringBuffer = a;
        stringBuffer.append(System.currentTimeMillis());
        stringBuffer.append(';');
        stringBuffer.append(str);
        stringBuffer.append(';');
        stringBuffer.append(str2);
        stringBuffer.append('\n');
    }

    public static String a() {
        return a.toString();
    }
}
