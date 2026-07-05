package com.getui.gtc.i.c;

import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.log.Logger;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public Logger a;

    /* JADX INFO: renamed from: com.getui.gtc.i.c.a$a, reason: collision with other inner class name */
    public static class C0047a {
        private static a a = new a(0);
    }

    private a() {
        this.a = new Logger(GtcProvider.context());
        this.a.setGlobalTag("gtc");
        this.a.setFileEnableProperty("gtc.fileLog");
        this.a.setLogcatEnable(false);
        this.a.setLogFileNameSuffix("gtc");
        this.a.setStackOffset(1);
    }

    /* synthetic */ a(byte b) {
        this();
    }

    public static void a(String str) {
        C0047a.a.a.d(str);
    }

    public static void a(Throwable th) {
        C0047a.a.a.w(th);
    }

    public static void b(String str) {
        C0047a.a.a.w(str);
    }

    public static void b(Throwable th) {
        C0047a.a.a.e(th);
    }

    public static void c(String str) {
        C0047a.a.a.e(str);
    }

    public static void c(Throwable th) {
        C0047a.a.a.filelog(2, null, null, th);
    }

    public static void d(String str) {
        C0047a.a.a.filelog(2, null, str, null);
    }
}
