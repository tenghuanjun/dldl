package com.bytedance.pangle.log;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    private String a;
    private String b;
    private String c;
    private long d;
    private long e;

    public static a a(String str, String str2, String str3) {
        return new a(str, str2, str3);
    }

    private a(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        long jCurrentTimeMillis = System.currentTimeMillis();
        this.e = jCurrentTimeMillis;
        this.d = jCurrentTimeMillis;
        ZeusLogger.i(this.a, this.b + String.format(" watcher[%s]-start", str3));
    }

    public final long a(String str) {
        long jCurrentTimeMillis = System.currentTimeMillis() - this.e;
        long jCurrentTimeMillis2 = System.currentTimeMillis() - this.d;
        ZeusLogger.i(this.a, this.b + String.format(" watcher[%s]-%s cost=%s, total=%s", this.c, str, Long.valueOf(jCurrentTimeMillis), Long.valueOf(jCurrentTimeMillis2)));
        return jCurrentTimeMillis2;
    }

    public final long a() {
        return System.currentTimeMillis() - this.d;
    }
}
