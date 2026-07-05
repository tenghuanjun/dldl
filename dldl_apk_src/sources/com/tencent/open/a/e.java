package com.tencent.open.a;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class e {
    public static int a(String str) {
        int iA;
        if (com.tencent.open.utils.e.a() == null || (iA = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), str).a("Common_BusinessReportFrequency")) == 0) {
            return 100;
        }
        return iA;
    }

    public static int a() {
        int iA = com.tencent.open.utils.f.a(com.tencent.open.utils.e.a(), null).a("Common_HttpRetryCount");
        if (iA == 0) {
            return 2;
        }
        return iA;
    }
}
